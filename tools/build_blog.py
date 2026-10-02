import html
import json
import re
from pathlib import Path
from urllib.parse import quote, unquote, urlsplit


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


def _local_url(target: str, source_path: Path, root: Path) -> str:
    full_target = source_path.parent / unquote(target)
    if full_target.exists():
        path_part, separator, fragment = target, "", ""
    else:
        path_part, separator, fragment = target.partition("#")
    try:
        resolved = (source_path.parent / unquote(path_part)).resolve()
        relative = resolved.relative_to(root.resolve()).as_posix()
        url = quote(relative, safe="/%:@?=&")
    except (OSError, ValueError):
        url = quote(path_part, safe="/%:@?=&")
    return f"{url}{separator}{quote(fragment)}" if separator else url


def _url(target: str, source_path: Path, root: Path) -> tuple[str, bool]:
    scheme = urlsplit(target).scheme.lower()
    if scheme in {"http", "https", "mailto"}:
        return target, True
    if scheme:
        return "#", True
    if target.startswith("#"):
        return target, True
    return _local_url(target, source_path, root), False


def _render_inline(text: str, source_path: Path, root: Path) -> str:
    pattern = re.compile(
        r"!\[([^\]]*)\]\(([^)]+)\)|"
        r"\[([^\]]+)\]\(([^)]+)\)|"
        r"`([^`]+)`|"
        r"\*\*([^*]+)\*\*|"
        r"__([^_]+)__|"
        r"\*([^*]+)\*|"
        r"_([^_]+)_"
    )
    output = []
    position = 0
    for match in pattern.finditer(text):
        output.append(html.escape(text[position : match.start()]))
        groups = match.groups()
        if groups[0] is not None:
            alt, target = groups[0], groups[1]
            url, remote = _url(target, source_path, root)
            if remote:
                output.append(
                    f'<a href="{html.escape(url, quote=True)}">远程图片：{html.escape(alt)}</a>'
                )
            else:
                output.append(
                    f'<img src="{html.escape(url, quote=True)}" alt="{html.escape(alt, quote=True)}" loading="lazy">'
                )
        elif groups[2] is not None:
            label, target = groups[2], groups[3]
            url, _ = _url(target, source_path, root)
            output.append(
                f'<a href="{html.escape(url, quote=True)}">{html.escape(label)}</a>'
            )
        elif groups[4] is not None:
            output.append(f"<code>{html.escape(groups[4])}</code>")
        elif groups[5] is not None or groups[6] is not None:
            output.append(f"<strong>{html.escape(groups[5] or groups[6])}</strong>")
        else:
            output.append(f"<em>{html.escape(groups[7] or groups[8])}</em>")
        position = match.end()
    output.append(html.escape(text[position:]))
    return "".join(output)


def _table_cells(line: str) -> list[str]:
    return [cell.strip() for cell in line.strip().strip("|").split("|")]


def _is_table_separator(line: str) -> bool:
    cells = _table_cells(line)
    return bool(cells) and all(re.fullmatch(r":?-{3,}:?", cell) for cell in cells)


def render_markdown(text: str, source_path: Path, root: Path) -> str:
    lines = text.splitlines()
    output = []
    paragraph = []
    list_tag = ""

    def flush_paragraph():
        if paragraph:
            output.append(f"<p>{_render_inline(' '.join(paragraph), source_path, root)}</p>")
            paragraph.clear()

    def close_list():
        nonlocal list_tag
        if list_tag:
            output.append(f"</{list_tag}>")
            list_tag = ""

    index = 0
    while index < len(lines):
        line = lines[index]
        stripped = line.strip()
        if stripped.startswith("```"):
            flush_paragraph()
            close_list()
            language = stripped[3:].strip()
            code = []
            index += 1
            while index < len(lines) and not lines[index].strip().startswith("```"):
                code.append(lines[index])
                index += 1
            language_class = f' class="language-{html.escape(language, quote=True)}"' if language else ""
            output.append(f"<pre><code{language_class}>{html.escape(chr(10).join(code))}</code></pre>")
        elif not stripped:
            flush_paragraph()
            close_list()
        elif index + 1 < len(lines) and "|" in line and _is_table_separator(lines[index + 1]):
            flush_paragraph()
            close_list()
            headers = _table_cells(line)
            index += 2
            rows = []
            while index < len(lines) and "|" in lines[index] and lines[index].strip():
                rows.append(_table_cells(lines[index]))
                index += 1
            head = "".join(f"<th>{_render_inline(cell, source_path, root)}</th>" for cell in headers)
            body = "".join(
                "<tr>" + "".join(f"<td>{_render_inline(cell, source_path, root)}</td>" for cell in row) + "</tr>"
                for row in rows
            )
            output.append(f"<div class=\"table-wrap\"><table><thead><tr>{head}</tr></thead><tbody>{body}</tbody></table></div>")
            continue
        else:
            heading = re.match(r"^(#{1,6})\s+(.+)$", stripped)
            unordered = re.match(r"^[-+*]\s+(.+)$", stripped)
            ordered = re.match(r"^\d+[.)]\s+(.+)$", stripped)
            if heading:
                flush_paragraph()
                close_list()
                level = len(heading.group(1))
                output.append(f"<h{level}>{_render_inline(heading.group(2), source_path, root)}</h{level}>")
            elif unordered or ordered:
                flush_paragraph()
                tag = "ul" if unordered else "ol"
                if list_tag != tag:
                    close_list()
                    output.append(f"<{tag}>")
                    list_tag = tag
                item = (unordered or ordered).group(1)
                output.append(f"<li>{_render_inline(item, source_path, root)}</li>")
            elif stripped.startswith(">"):
                flush_paragraph()
                close_list()
                output.append(f"<blockquote>{_render_inline(stripped[1:].strip(), source_path, root)}</blockquote>")
            elif re.fullmatch(r"[-*_]{3,}", stripped):
                flush_paragraph()
                close_list()
                output.append("<hr>")
            else:
                close_list()
                paragraph.append(stripped)
        index += 1
    flush_paragraph()
    close_list()
    return "\n".join(output)


def _article_body(record: dict[str, object], root: Path) -> str:
    if record["error"]:
        return f'<div class="error"><strong>无法解析此文件</strong><p>{html.escape(str(record["error"]))}</p></div>'
    if record["kind"] == "html":
        url = quote(str(record["path"]), safe="/%:@?=&")
        return (
            f'<p><a class="demo-link" href="{url}">打开原示例 ↗</a></p>'
            f'<pre><code class="language-html">{record["content"]}</code></pre>'
        )
    source_path = root / str(record["path"])
    return render_markdown(str(record["content"]), source_path, root)


def render_site(records: list[dict[str, object]], root: Path) -> str:
    groups = {}
    for record in records:
        path = str(record["path"])
        group = path.split("/", 1)[0] if "/" in path else "根目录"
        groups.setdefault(group, []).append(record)
    navigation = []
    for group, group_records in groups.items():
        items = "".join(
            f'<button class="article-link" data-target="{html.escape(str(record["id"]), quote=True)}">'
            f'<span>{html.escape(str(record["title"]))}</span><small>{html.escape(str(record["kind"]))}</small></button>'
            for record in group_records
        )
        navigation.append(
            f'<section class="nav-group"><h2>{html.escape(group)} <span>{len(group_records)}</span></h2>{items}</section>'
        )
    articles = "".join(
        f'<article class="article" id="article-{html.escape(str(record["id"]), quote=True)}" hidden>'
        f'<header class="article-header"><span class="kind">{html.escape(str(record["kind"]))}</span>'
        f'<h1>{html.escape(str(record["title"]))}</h1><p>{html.escape(str(record["path"]))}</p></header>'
        f'<div class="prose">{_article_body(record, root)}</div></article>'
        for record in records
    )
    counts = {kind: sum(record["kind"] == kind for record in records) for kind in KINDS.values()}
    stats = "".join(
        f'<div class="stat"><strong>{count}</strong><span>{label}</span></div>'
        for label, count in (("Markdown", counts["markdown"]), ("HTML", counts["html"]), ("Notebook", counts["notebook"]))
    )
    empty = '<p class="empty">暂无可浏览的记录</p>' if not records else ""
    search_data = json.dumps(
        [
            {
                "id": record["id"],
                "title": record["title"],
                "path": record["path"],
                "text": record["text"],
            }
            for record in records
        ],
        ensure_ascii=False,
        separators=(",", ":"),
    ).replace("<", "\\u003c").replace("\u2028", "\\u2028").replace("\u2029", "\\u2029")
    return f"""<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Accumulating Process · 学习记录</title>
<style>
:root{{--bg:#f5f2eb;--panel:#fffdf8;--text:#25241f;--muted:#767268;--line:#ded8cc;--accent:#b84a2b;--code:#242a30;--shadow:0 18px 48px rgba(56,44,27,.09)}}
:root[data-theme="dark"]{{--bg:#171918;--panel:#202321;--text:#edf0eb;--muted:#a7ada5;--line:#383d39;--accent:#f0835f;--code:#111412;--shadow:none}}
*{{box-sizing:border-box}}html{{scroll-behavior:smooth}}body{{margin:0;background:var(--bg);color:var(--text);font:16px/1.72 ui-serif,Georgia,"Noto Serif SC",serif}}button,input{{font:inherit}}button{{color:inherit}}a{{color:var(--accent)}}.topbar{{position:fixed;inset:0 0 auto 0;height:64px;z-index:20;display:flex;align-items:center;gap:14px;padding:0 22px;background:color-mix(in srgb,var(--panel) 90%,transparent);border-bottom:1px solid var(--line);backdrop-filter:blur(14px)}}.brand{{font-weight:800;letter-spacing:.02em}}.brand span{{color:var(--accent)}}.topbar .spacer{{flex:1}}.icon-button{{border:1px solid var(--line);background:var(--panel);border-radius:10px;padding:7px 11px;cursor:pointer}}#menu-toggle{{display:none}}.layout{{display:grid;grid-template-columns:310px minmax(0,1fr);min-height:100vh;padding-top:64px}}.sidebar{{position:fixed;inset:64px auto 0 0;width:310px;overflow:auto;padding:22px 18px 40px;background:var(--panel);border-right:1px solid var(--line)}}.search{{width:100%;padding:10px 12px;border:1px solid var(--line);border-radius:10px;background:var(--bg);color:var(--text);outline:none}}.search:focus{{border-color:var(--accent)}}.nav-group h2{{display:flex;justify-content:space-between;margin:24px 8px 8px;font:700 12px/1.2 ui-sans-serif,system-ui,sans-serif;text-transform:uppercase;letter-spacing:.08em;color:var(--muted)}}.nav-group h2 span{{font-variant-numeric:tabular-nums}}.article-link{{display:flex;width:100%;justify-content:space-between;gap:10px;padding:9px 10px;border:0;border-radius:8px;background:transparent;text-align:left;cursor:pointer}}.article-link span{{overflow:hidden;text-overflow:ellipsis;white-space:nowrap}}.article-link small{{color:var(--muted)}}.article-link:hover,.article-link.active{{background:var(--bg);color:var(--accent)}}.content{{grid-column:2;padding:54px clamp(24px,6vw,88px) 100px;min-width:0}}.home,.article{{max-width:980px;margin:0 auto}}.eyebrow,.kind{{font:700 12px/1 ui-sans-serif,system-ui,sans-serif;letter-spacing:.11em;text-transform:uppercase;color:var(--accent)}}.home h1{{max-width:780px;margin:.4em 0 .3em;font-size:clamp(42px,7vw,82px);line-height:1.03;letter-spacing:-.045em}}.lede{{max-width:700px;color:var(--muted);font-size:19px}}.stats{{display:grid;grid-template-columns:repeat(3,1fr);gap:14px;margin-top:42px}}.stat{{padding:22px;border:1px solid var(--line);border-radius:16px;background:var(--panel);box-shadow:var(--shadow)}}.stat strong,.stat span{{display:block}}.stat strong{{font:800 32px/1 ui-sans-serif,system-ui,sans-serif}}.stat span{{margin-top:8px;color:var(--muted)}}.article-header{{padding-bottom:26px;border-bottom:1px solid var(--line)}}.article-header h1{{margin:.35em 0 .15em;font-size:clamp(34px,5vw,58px);line-height:1.1;letter-spacing:-.035em}}.article-header p{{margin:0;color:var(--muted);word-break:break-all}}.prose{{max-width:820px;padding-top:28px}}.prose h1,.prose h2,.prose h3{{line-height:1.25;margin:1.7em 0 .65em}}.prose img{{display:block;max-width:100%;height:auto;margin:24px auto;border-radius:10px}}.prose pre{{overflow:auto;padding:18px;border-radius:12px;background:var(--code);color:#edf1ef;font:13px/1.6 ui-monospace,SFMono-Regular,Menlo,monospace}}.prose :not(pre)>code{{padding:.15em .35em;border-radius:5px;background:color-mix(in srgb,var(--accent) 12%,transparent);font:90% ui-monospace,SFMono-Regular,Menlo,monospace}}.prose blockquote{{margin:24px 0;padding:8px 20px;border-left:3px solid var(--accent);color:var(--muted);background:var(--panel)}}.table-wrap{{overflow:auto}}table{{width:100%;border-collapse:collapse}}th,td{{padding:9px 12px;border:1px solid var(--line);text-align:left}}th{{background:var(--panel)}}.demo-link{{display:inline-block;padding:8px 12px;border:1px solid currentColor;border-radius:8px;text-decoration:none}}.error,.empty{{padding:18px;border:1px solid var(--line);border-radius:12px;background:var(--panel)}}.error strong{{color:var(--accent)}}
@media (max-width: 760px){{#menu-toggle{{display:block}}.layout{{display:block}}.sidebar{{transform:translateX(-102%);transition:transform .2s ease;z-index:15;box-shadow:var(--shadow)}}body.menu-open .sidebar{{transform:none}}.content{{padding:36px 20px 70px}}.stats{{grid-template-columns:1fr}}}}
</style>
</head>
<body>
<header class="topbar"><button id="menu-toggle" class="icon-button" aria-label="打开目录">目录</button><div class="brand"><span>Accumulating</span> Process</div><div class="spacer"></div><span>共 {len(records)} 篇记录</span><button id="theme-toggle" class="icon-button" aria-label="切换明暗主题">主题</button></header>
<div class="layout">
<aside id="sidebar" class="sidebar"><input id="search" class="search" type="search" placeholder="搜索标题、路径与正文" aria-label="全文搜索">{''.join(navigation)}</aside>
<main class="content"><section id="home" class="home"><span class="eyebrow">Repository Notes</span><h1>学习是一场持续积累。</h1><p class="lede">把仓库中的 Markdown、HTML 示例与 Jupyter Notebook 汇成一处，离线也能随时翻阅。</p><div class="stats">{stats}</div>{empty}</section>{articles}</main>
</div>
<script id="blog-data" type="application/json">{search_data}</script>
<script>
const records=JSON.parse(document.getElementById('blog-data').textContent);const links=[...document.querySelectorAll('.article-link')];const articles=[...document.querySelectorAll('.article')];const home=document.getElementById('home');const byId=new Map(records.map(record=>[String(record.id),record]));
function showArticle(id){{const article=document.getElementById('article-'+id);if(!article)return;home.hidden=true;articles.forEach(item=>item.hidden=item!==article);links.forEach(link=>link.classList.toggle('active',link.dataset.target===id));document.body.classList.remove('menu-open');history.replaceState(null,'','#'+encodeURIComponent(id));window.scrollTo(0,0)}}
links.forEach(link=>link.addEventListener('click',()=>showArticle(link.dataset.target)));
document.getElementById('search').addEventListener('input',event=>{{const query=event.target.value.trim().toLocaleLowerCase();links.forEach(link=>{{const record=byId.get(link.dataset.target);link.hidden=!!query&&!`${{record.title}} ${{record.path}} ${{record.text}}`.toLocaleLowerCase().includes(query)}});document.querySelectorAll('.nav-group').forEach(group=>group.hidden=![...group.querySelectorAll('.article-link')].some(link=>!link.hidden))}});
document.getElementById('menu-toggle').addEventListener('click',()=>document.body.classList.toggle('menu-open'));
const root=document.documentElement;try{{root.dataset.theme=localStorage.getItem('blog-theme')||''}}catch(error){{root.dataset.theme=''}}document.getElementById('theme-toggle').addEventListener('click',()=>{{root.dataset.theme=root.dataset.theme==='dark'?'':'dark';try{{localStorage.setItem('blog-theme',root.dataset.theme)}}catch(error){{}}}});
if(location.hash)showArticle(decodeURIComponent(location.hash.slice(1)));
</script>
</body>
</html>
"""


def build_site(root: Path) -> str:
    return render_site([extract_record(path, root) for path in discover_records(root)], root)
