import json
import tempfile
import unittest
from pathlib import Path

from tools.build_blog import (
    build_site,
    discover_records,
    extract_notebook,
    extract_record,
    render_markdown,
    render_site,
)


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

    def test_render_markdown_supports_readable_safe_content(self):
        source_path = self.write("notes/介绍.md", "")
        self.write("notes/images/图 +#.png", b"", binary=True)
        markdown = """# 标题

正文 **加粗** *强调* [首页](../README.md)

![本地图](images/图 +#.png)
![远程图](https://example.com/image.png)

```html
</script><b>code</b>
```

- 项目一
- 项目二

> 引用

| 名称 | 值 |
| --- | --- |
| A | 1 |
"""

        rendered = render_markdown(markdown, source_path, self.root)

        self.assertIn("<h1>标题</h1>", rendered)
        self.assertIn("<strong>加粗</strong>", rendered)
        self.assertIn("<em>强调</em>", rendered)
        self.assertIn('href="README.md"', rendered)
        self.assertIn(
            'src="notes/images/%E5%9B%BE%20%2B%23.png"',
            rendered,
        )
        self.assertNotIn('<img src="https://', rendered)
        self.assertIn('href="https://example.com/image.png"', rendered)
        self.assertIn("&lt;/script&gt;&lt;b&gt;code&lt;/b&gt;", rendered)
        self.assertIn("<ul>", rendered)
        self.assertIn("<blockquote>引用</blockquote>", rendered)
        self.assertIn("<table>", rendered)

    def test_render_site_contains_navigation_search_theme_and_metadata(self):
        source_path = self.write("notes/intro.md", "# Intro")
        records = [extract_record(source_path, self.root)]

        page = render_site(records, self.root)

        self.assertIn('<meta name="viewport"', page)
        self.assertIn('id="sidebar"', page)
        self.assertIn('id="search"', page)
        self.assertIn('id="theme-toggle"', page)
        self.assertIn('class="article-link"', page)
        self.assertIn("notes/intro.md", page)
        self.assertIn("<h1>Intro</h1>", page)
        self.assertIn("@media (max-width: 760px)", page)
        self.assertNotIn("<script src=", page)
        self.assertNotIn("<link rel=\"stylesheet\"", page)

    def test_render_site_keeps_script_terminators_inert(self):
        records = [
            {
                "id": "danger",
                "title": "Danger",
                "path": "danger.md",
                "kind": "markdown",
                "content": "# Danger\n\n</script><script>alert(1)</script>",
                "text": "</script><script>alert(1)</script>",
                "error": "",
            }
        ]

        page = render_site(records, self.root)

        self.assertNotIn("</script><script>alert(1)</script>", page)
        self.assertIn("&lt;/script&gt;&lt;script&gt;alert(1)&lt;/script&gt;", page)

    def test_render_site_handles_html_errors_and_empty_state(self):
        html_record = {
            "id": "demo-html",
            "title": "Demo",
            "path": "demos/demo.html",
            "kind": "html",
            "content": "&lt;h1&gt;Demo&lt;/h1&gt;",
            "text": "Demo",
            "error": "",
        }
        error_record = {
            "id": "broken",
            "title": "Broken",
            "path": "broken.ipynb",
            "kind": "notebook",
            "content": "",
            "text": "",
            "error": "JSONDecodeError: broken",
        }

        page = render_site([html_record, error_record], self.root)
        empty_page = render_site([], self.root)

        self.assertIn('href="demos/demo.html"', page)
        self.assertIn("&lt;h1&gt;Demo&lt;/h1&gt;", page)
        self.assertIn("JSONDecodeError: broken", page)
        self.assertIn("暂无可浏览的记录", empty_page)

    def test_build_site_discovers_and_renders_records(self):
        self.write("README.md", "# Home")
        self.write("demo.html", "<title>Demo</title><h1>Demo</h1>")

        page = build_site(self.root)

        self.assertIn("共 2 篇记录", page)
        self.assertIn("Home", page)
        self.assertIn("Demo", page)


if __name__ == "__main__":
    unittest.main()
