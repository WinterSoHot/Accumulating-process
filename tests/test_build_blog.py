import json
import tempfile
import unittest
from pathlib import Path

from tools.build_blog import discover_records, extract_notebook, extract_record


class BlogBuilderTest(unittest.TestCase):
    def setUp(self):
        self.temp_dir = tempfile.TemporaryDirectory()
        self.root = Path(self.temp_dir.name)

    def tearDown(self):
        self.temp_dir.cleanup()

    def write(self, relative_path, content, *, binary=False):
        path = self.root / relative_path
        path.parent.mkdir(parents=True, exist_ok=True)
        if binary:
            path.write_bytes(content)
        else:
            path.write_text(content, encoding="utf-8")
        return path

    def test_discover_records_is_sorted_and_excludes_generated_files(self):
        self.write("b.md", "# B")
        self.write("a.html", "<title>A</title>")
        self.write("nested/n.ipynb", "{}")
        self.write("notes.txt", "ignored")
        self.write("index.html", "generated")
        self.write(".git/hidden.md", "ignored")
        self.write("docs/superpowers/plan.md", "ignored")

        paths = discover_records(self.root)

        self.assertEqual(
            [path.relative_to(self.root).as_posix() for path in paths],
            ["a.html", "b.md", "nested/n.ipynb"],
        )

    def test_extract_record_normalizes_markdown_and_html(self):
        markdown = self.write("notes/介绍.md", "# 介绍\n\n正文")
        html_file = self.write(
            "demos/example.html",
            "<!doctype html><title>Demo</title><script>alert('x')</script>",
        )

        markdown_record = extract_record(markdown, self.root)
        html_record = extract_record(html_file, self.root)

        self.assertEqual(markdown_record["id"], "notes-介绍-md")
        self.assertEqual(markdown_record["title"], "介绍")
        self.assertEqual(markdown_record["path"], "notes/介绍.md")
        self.assertEqual(markdown_record["kind"], "markdown")
        self.assertEqual(markdown_record["content"], "# 介绍\n\n正文")
        self.assertEqual(markdown_record["text"], "介绍 正文")
        self.assertEqual(markdown_record["error"], "")
        self.assertEqual(html_record["title"], "Demo")
        self.assertEqual(html_record["kind"], "html")
        self.assertIn("&lt;script&gt;alert('x')&lt;/script&gt;", html_record["content"])
        self.assertNotIn("<script>", html_record["content"])
        self.assertIn("Demo", html_record["text"])

    def test_extract_notebook_preserves_cell_and_output_order(self):
        notebook = self.write(
            "demo.ipynb",
            json.dumps(
                {
                    "cells": [
                        {"cell_type": "markdown", "source": ["# Heading\n", "intro"]},
                        {
                            "cell_type": "code",
                            "source": ["print('hello')"],
                            "outputs": [
                                {"output_type": "stream", "text": ["hello\n"]},
                                {
                                    "output_type": "execute_result",
                                    "data": {"text/plain": ["'result'"]},
                                },
                            ],
                        },
                    ]
                }
            ),
        )

        content, search_text = extract_notebook(notebook)

        self.assertLess(content.index("# Heading"), content.index("print('hello')"))
        self.assertLess(content.index("print('hello')"), content.index("hello"))
        self.assertLess(content.index("hello"), content.index("'result'"))
        self.assertEqual(search_text, "Heading intro print('hello') hello 'result'")

    def test_extract_record_returns_error_for_malformed_input(self):
        invalid_notebook = self.write("broken.ipynb", "{not json")
        invalid_markdown = self.write("broken.md", b"\xff\xfe", binary=True)

        notebook_record = extract_record(invalid_notebook, self.root)
        markdown_record = extract_record(invalid_markdown, self.root)

        self.assertEqual(notebook_record["kind"], "notebook")
        self.assertEqual(markdown_record["kind"], "markdown")
        self.assertTrue(notebook_record["error"])
        self.assertTrue(markdown_record["error"])
        self.assertEqual(notebook_record["content"], "")
        self.assertEqual(markdown_record["content"], "")


if __name__ == "__main__":
    unittest.main()
