# CSS 选择器

1. 元素选择器

   最常见的 CSS 选择器
   如果设置 HTML 的样式，选择器通常将是某个 HTML 元素，比如 p、h1、em、a，甚至可以是 html 本身：

   ```CSS
       html {color:black;}
       h1 {color:blue;}
       h2 {color:silver;}
   ```

   在 W3C 标准中，元素选择器又称为类型选择器（type selector）。

2. 选择器分组

    选择器可以分组，声明也可以分组，结合选择器和声明分组可以实现更加复杂的CSS。

3. 类选择器

    > 只有适当地标记文档后，才能使用这些选择器，所以使用这两种选择器通常需要先做一些构想和计划。
    > 要应用样式而不考虑具体设计的元素，最常用的方法就是使用类选择器

4. ID 选择器

    在某些方面，ID 选择器类似于类选择器，不过也有一些重要差别。

    ID选择器和类选择器的区别

    1. 区别 1：只能在文档中使用一次
        与类不同，在一个 HTML 文档中，ID 选择器会使用一次，而且仅一次。

    2. 区别 2：不能使用 ID 词列表
        不同于类选择器，ID 选择器不能结合使用，因为 ID 属性不允许有以空格分隔的词列表。

    3. 区别 3：ID 能包含更多含义
        类似于类，可以独立于元素来选择 ID。有些情况下，您知道文档中会出现某个特定 ID 值，但是并不知道它会出现在哪个元素上，所以您想声明独立的ID选择器。
        例如，您可能知道在一个给定的文档中会有一个 ID 值为 mostImportant 的元素。
        您不知道这个最重要的东西是一个段落、一个短语、一个列表项还是一个小节标题。您只知道每个文档都会有这么一个最重要的内容，它可能在任何元素中，而且只能出现一个。在这种情况下，可以编写如下规则：

5. 属性选择器

    CSS 2 引入了属性选择器。属性选择器可以根据元素的属性及属性值来选择元素

    |选择器|描述|
    |---|---|
    |[attribute]|用于选取带有指定属性的元素。|
    |[attribute=value]|用于选取带有指定属性和值的元素。|
    |[attribute~=value]|用于选取属性值中包含指定词汇的元素。|
    |[attribute\|=value]|用于选取带有以指定值开头的属性值的元素，该值必须是整个单词。|
    |[attribute^=value]|匹配属性值以指定值开头的每个元素。|
    |[attribute$=value]|匹配属性值以指定值结尾的每个元素。|
    |[attribute*=value]|匹配属性值中包含指定值的每个元素。|

6. 后代选择器

    后代选择器（descendant selector）又称为包含选择器。

    后代选择器可以选择作为某元素后代的元素。

    后代选择器的功能极其强大。有了它，可以使 HTML 中不可能实现的任务成为可能。

    假设有一个文档，其中有一个边栏，还有一个主区。边栏的背景为蓝色，主区的背景为白色，这两个区都包含链接列表。不能把所有链接都设置为蓝色，因为这样一来边栏中的蓝色链接都无法看到。

    解决方法是使用后代选择器。在这种情况下，可以为包含边栏的 div 指定值为 sidebar 的 class 属性，并把主区的 class 属性值设置为 maincontent。然后编写以下样式：

    ```CSS
    div.sidebar {background:blue;}
    div.maincontent {background:white;}
    div.sidebar a:link {color:white;}
    div.maincontent a:link {color:blue;}
    ```
    > tip:有关后代选择器有一个易被忽视的方面，即两个元素之间的层次间隔可以是无限的。

7. 子元素选择器

    与后代选择器相比，子元素选择器（Child selectors）只能选择作为某元素子元素的元素。

    `h1 > strong {color:red;}`

8. 相邻兄弟选择器

    相邻兄弟选择器（Adjacent sibling selector）可选择紧接在另一元素后的元素，且二者有相同父元素。

    `h1 + p {margin-top:50px;}`

9. 伪类

    > CSS 伪类用于向某些选择器添加特殊的效果。

    |属性|描述|CSS|
    |---|---|---|
    |:active|向被激活的元素添加样式。|1|
    |:focus|向拥有键盘输入焦点的元素添加样式。|2|
    |:hover|当鼠标悬浮在元素上方时，向元素添加样式。|1|
    |:link|向未被访问的链接添加样式。|1|
    |:visited|向已被访问的链接添加样式。|1|
    |:first-child|向元素的第一个子元素添加样式。|2|
    |:lang|向带有指定 lang 属性的元素添加样式。|2|

10. 伪元素

    :first-line 伪元素 :first-letter 伪元素
    都只能用于块级元素。

    注释：下面的属性可应用于 "first-line" 伪元素：

    - font
    - color
    - background
    - word-spacing
    - letter-spacing
    - text-decoration
    - vertical-align
    - text-transform
    - line-height
    - clear

    注释：下面的属性可应用于 "first-letter" 伪元素：

    - font
    - color
    - background
    - margin
    - padding
    - border
    - text-decoration
    - vertical-align (仅当 float 为 none 时)
    - text-transform
    - line-height
    - float
    - clear

    |属性|描述|CSS|
    |---|---|
    |:first-letter|向文本的第一个字母添加特殊样式。|1|
    |:first-line|向文本的首行添加特殊样式。|1|
    |:before|在元素之前添加内容。|2|
    |:after|在元素之后添加内容。|2|