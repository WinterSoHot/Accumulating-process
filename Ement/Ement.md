# Ement 语法

1. 生成 html 框架

    - 使用！ 或者 html:5 生成 html5基本代码
    - 使用 html:xt 和 html:4s 生成html4过渡型和严格型

2. 生成帯class 和 id的标签

    - 生成class的标签 标签名.class名。例：div.aaa
    - 生成id的标签 标签名#id名。div#aaa

3. 生成后代：>

    - div.aaa>ul>li
  
4. 生成兄弟：+

    > 同级的标签

    - div.aaa+div.bbb

5. 生成上级:^

    - div>ul>li^span

6. 生成多个元素：*

    - div>ul>li*5

7. 生成分组：()

    > 用括号进行分组，这样可以更加明确要生成的结构，特别是层次关系，例如：
    - div>(header>ul>li*2>a)+footer>p

8. 生成自定义属性：[attr]

    - a[href="www.taobao.com" title="淘宝"]

9. 对生成内容编号：$

    - ul>li.item$*5
    - ul>li.item$$$*5
    - 指定倒序排序，加@-:ul>li.item$@-*5
    - 制定开始的编号，加@N:ul>li.item$@2*5
    - 倒序和制定编号合体：ul>li.item$@-3*5

10. 生成文本内容：{}

    - a{click}+span{text}

11. **注： 不要有空格** 
