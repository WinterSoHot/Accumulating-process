import html
import json
import re
from pathlib import Path


KINDS = {".md": "markdown", ".html": "html", ".ipynb": "notebook"}


def discover_records(root: Path) -> list[Path]:
    records = []
    for path in root.rglob("*"):
        if not path.is_file() or path.suffix.lower() not in KINDS:
            continue
        relative = path.relative_to(root)
        if ".git" in relative.parts or relative.parts[:2] == ("docs", "superpowers"):
            continue
        if relative == Path("index.html"):
            continue
        records.append(path)
    return sorted(records, key=lambda path: path.relative_to(root).as_posix())


def _source(value) -> str:
    return "".join(value) if isinstance(value, list) else str(value or "")


def _plain_text(value: str) -> str:
    value = re.sub(r"<[^>]+>", " ", value)
    value = re.sub(r"[#*_>`~\[\]!|]+", " ", html.unescape(value))
    return " ".join(value.split())


def _output_text(output: dict) -> str:
    if output.get("output_type") == "stream":
        return _source(output.get("text"))
    if output.get("output_type") == "error":
        traceback = output.get("traceback") or []
        return "\n".join(traceback) or f"{output.get('ename', '')}: {output.get('evalue', '')}".strip(": ")
    data = output.get("data") or {}
    return _source(data.get("text/plain"))


def extract_notebook(path: Path) -> tuple[str, str]:
    notebook = json.loads(path.read_text(encoding="utf-8"))
    sections = []
    search_parts = []
    for cell in notebook.get("cells", []):
        source = _source(cell.get("source"))
        if cell.get("cell_type") == "markdown":
            sections.append(source)
            search_parts.append(_plain_text(source))
            continue
        if cell.get("cell_type") != "code":
            continue
        sections.append(f"```python\n{source}\n```")
        search_parts.append(_plain_text(source))
        for output in cell.get("outputs", []):
            output_text = _output_text(output)
            if output_text:
                sections.append(f"```text\n{output_text}\n```")
                search_parts.append(_plain_text(output_text))
    return "\n\n".join(sections), " ".join(part for part in search_parts if part)


def _title_from_markdown(content: str, fallback: str) -> str:
    match = re.search(r"^#{1,6}\s+(.+?)\s*$", content, re.MULTILINE)
    return _plain_text(match.group(1)) if match else fallback


def extract_record(path: Path, root: Path) -> dict[str, object]:
    relative = path.relative_to(root).as_posix()
    kind = KINDS[path.suffix.lower()]
    record = {
        "id": re.sub(r"[^0-9A-Za-z\u0080-\uffff]+", "-", relative).strip("-").lower(),
        "title": path.stem,
        "path": relative,
        "kind": kind,
        "content": "",
        "text": "",
        "error": "",
    }
    try:
        if kind == "notebook":
            content, search_text = extract_notebook(path)
            record.update(
                title=_title_from_markdown(content, path.stem),
                content=content,
                text=search_text,
            )
        else:
            source = path.read_text(encoding="utf-8")
            if kind == "markdown":
                record.update(
                    title=_title_from_markdown(source, path.stem),
                    content=source,
                    text=_plain_text(source),
                )
            else:
                title_match = re.search(r"<title[^>]*>(.*?)</title>", source, re.IGNORECASE | re.DOTALL)
                record.update(
                    title=_plain_text(title_match.group(1)) if title_match else path.stem,
                    content=html.escape(source, quote=False),
                    text=_plain_text(source),
                )
    except (OSError, UnicodeError, json.JSONDecodeError, TypeError) as error:
        record["error"] = f"{type(error).__name__}: {error}"
    return record
