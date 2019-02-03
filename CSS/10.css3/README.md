# CSS3

CSS3 被划分为模块。

其中最重要的 CSS3 模块包括：

- 选择器
- 框模型
- 背景和边框
- 文本效果
- 2D/3D 转换
- 动画
- 多列布局
- 用户界面

##　CSS3 边框

通过 CSS3，您能够创建圆角边框，向矩形添加阴影，使用图片来绘制边框 - 并且不需使用设计软件，比如 PhotoShop。

边框属性：

- border-radius
- box-shadow
- border-image

## CSS3 背景

- background-size:规定背景图片的尺寸
- background-origin:属性规定背景图片的定位区域,content-box、padding-box 或 border-box

CSS3 允许您为元素使用多个背景图像。

```CSS
body
{
background-image:url(bg_flower.gif),url(bg_flower_2.gif);
}
```

新的背景属性
|属性|描述|CSS|
|---|---|---|
|background-clip|规定背景的绘制区域。|3|
|background-origin|规定背景图片的定位区域。|3|
|background-size|规定背景图片的尺寸。|3|

## CSS3 文本效果

- text-shadow:文本应用阴影,规定水平阴影、垂直阴影、模糊距离，以及阴影的颜色
- word-wrap:允许您允许文本强制文本进行换行 - 即使这意味着会对单词进行拆分

新的文本属性

|属性|描述|CSS|
|---|---|---|
|hanging-punctuation|规定标点字符是否位于线框之外。|3|
|punctuation-trim|规定是否对标点字符进行修剪。|3|
|text-align-last|设置如何对齐最后一行或紧挨着强制换行符之前的行。|3|
|text-emphasis|向元素的文本应用重点标记以及重点标记的前景色。|3|
|text-justify|规定当 text-align 设置为 "justify" 时所使用的对齐方法。|3|
|text-outline|规定文本的轮廓。|3|
|text-overflow|规定当文本溢出包含元素时发生的事情。|3|
|text-shadow|向文本添加阴影。|3|
|text-wrap|规定文本的换行规则。|3|
|word-break|规定非中日韩文本的换行规则。|3|
|word-wrap|允许对长的不可分割的单词进行分割并换行到下一行。|3|

## CSS3 字体

CSS3 @font-face 规则

在 CSS3 之前，web 设计师必须使用已在用户计算机上安装好的字体。

通过 CSS3，web 设计师可以使用他们喜欢的任意字体。

当您您找到或购买到希望使用的字体时，可将该字体文件存放到 web 服务器上，它会在需要时被自动下载到用户的计算机上。

您“自己的”的字体是在 CSS3 @font-face 规则中定义的。

```CSS
@font-face
{
    font-family: myFirstFont;
    src: url('Sansation_Light.ttf'),url('Sansation_Light.eot'); /* IE9+ */
}
```

您必须为粗体文本添加另一个包含描述符的 @font-face：

```CSS
@font-face
{
    font-family: myFirstFont;
    src: url('Sansation_Bold.ttf'),
        url('Sansation_Bold.eot'); /* IE9+ */
    font-weight:bold;
}
```

CSS3 字体描述符
下面的表格列出了能够在 @font-face 规则中定义的所有字体描述符：

|描述符|值|描述|
|---|---|---|
|font-family|name|必需。规定字体的名称。|
|src|URL|必需。定义字体文件的 URL。|
|font-stretch|normal;condensed;ultra-condensed;extra-condensed;semi-condensed;expanded;semi-expanded;extra-expanded;ultra-expanded|可选。定义如何拉伸字体。默认是 "normal"。|
|font-style|ormal;italic;oblique|可选。定义字体的样式。默认是 "normal"。|
|font-weight|normal;bold;100;200;300;400;500;600;700;800;900|可选。定义字体的粗细。默认是 "normal"。|
|unicode-range|unicode-range|可选。定义字体支持的 UNICODE 字符范围。默认是 "U+0-10FFFF"。|

## CSS3 2D 转换

通过 CSS3 转换，我们能够对元素进行移动、缩放、转动、拉长或拉伸。

2D 转换方法：

- translate()：元素从其当前位置移动，根据给定的 left（x 坐标） 和 top（y 坐标） 位置参数
- rotate()：元素顺时针旋转给定的角度。允许负值，元素将逆时针旋转。
- scale()：元素的尺寸会增加或减少，根据给定的宽度（X 轴）和高度（Y 轴）参数
- skew()：元素翻转给定的角度，根据给定的水平线（X 轴）和垂直线（Y 轴）参数
- matrix()：matrix() 方法把所有 2D 转换方法组合在一起。matrix() 方法需要六个参数，包含数学函数，允许您：旋转、缩放、移动以及倾斜元素

新的转换属性
下面的表格列出了所有的转换属性：

|属性|描述|CSS|
|---|---|---|
|transform|向元素应用 2D 或 3D 转换。|3|
|transform-origin|允许你改变被转换元素的位置|3|

2D Transform 方法
|函数|描述|
|---|---|
|matrix(n,n,n,n,n,n)|定义 2D 转换，使用六个值的矩阵。|
|translate(x,y)|定义 2D 转换，沿着 X 和 Y 轴移动元素。|
|translateX(n)|定义 2D 转换，沿着 X 轴移动元素。|
|translateY(n)|定义 2D 转换，沿着 Y 轴移动元素。|
|scale(x,y)|定义 2D 缩放转换，改变元素的宽度和高度。|
|scaleX(n)|定义 2D 缩放转换，改变元素的宽度。|
|scaleY(n)|定义 2D 缩放转换，改变元素的高度。|
|rotate(angle)|定义 2D 旋转，在参数中规定角度。|
|skew(x-angle,y-angle)|定义 2D 倾斜转换，沿着 X 和 Y 轴。|
|skewX(angle)|定义 2D 倾斜转换，沿着 X 轴。|
|skewY(angle)|定义 2D 倾斜转换，沿着 Y 轴。|

## CSS3 3D 转换

- rotateX():元素围绕其 X 轴以给定的度数进行旋转。
- rotateY():元素围绕其 Y 轴以给定的度数进行旋转。

转换属性
下面的表格列出了所有的转换属性：

|属性|描述|CSS|
|---|---|---|
|transform|向元素应用 2D 或 3D 转换。|3|
|transform-origin|允许你改变被转换元素的位置。|3|
|transform-style|规定被嵌套元素如何在 3D 空间中显示。|3|
|perspective|规定 3D 元素的透视效果。|3|
|perspective-origin|规定 3D 元素的底部位置。|3|
|backface-visibility|定义元素在不面对屏幕时是否可见。|3|

2D Transform 方法
|函数|描述|
|---|---|
|matrix3d(n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n)|定义 3D 转换，使用 16 个值的 4x4 矩阵。|
|translate3d(x,y,z)|定义 3D 转化。|
|translateX(x)|定义 3D 转化，仅使用用于 X 轴的值。|
|translateY(y)|定义 3D 转化，仅使用用于 Y 轴的值。|
|translateZ(z)|定义 3D 转化，仅使用用于 Z 轴的值。|
|scale3d(x,y,z)|定义 3D 缩放转换。|
|scaleX(x)|定义 3D 缩放转换，通过给定一个 X 轴的值。|
|scaleY(y)|定义 3D 缩放转换，通过给定一个 Y 轴的值。|
|scaleZ(z)|定义 3D 缩放转换，通过给定一个 Z 轴的值。|
|rotate3d(x,y,z,angle)|定义 3D 旋转。|
|rotateX(angle)|定义沿 X 轴的 3D 旋转。|
|rotateY(angle)|定义沿 Y 轴的 3D 旋转。|
|rotateZ(angle)|定义沿 Z 轴的 3D 旋转。|
|perspective(n)|定义 3D 转换元素的透视视图。|

## CSS3 过渡

通过 CSS3，我们可以在不使用 Flash 动画或 JavaScript 的情况下，当元素从一种样式变换为另一种样式时为元素添加效果。

CSS3 过渡是元素从一种样式逐渐改变为另一种的效果。

要实现这一点，必须规定两项内容：

- 规定您希望把效果添加到哪个 CSS 属性上
- 规定效果的时长

过渡属性
下面的表格列出了所有的转换属性：

|属性|描述|CSS|
|---|---|---|
|transition|简写属性，用于在一个属性中设置四个过渡属性。|3|
|transition-property|规定应用过渡的 CSS 属性的名称。|3|
|transition-duration|定义过渡效果花费的时间。默认是 0。|3|
|transition-timing-function|规定过渡效果的时间曲线。默认是 "ease"。|3|
|transition-delay|规定过渡效果何时开始。默认是0。|3|

## CSS3 动画

通过 CSS3，我们能够创建动画，这可以在许多网页中取代动画图片、Flash 动画以及 JavaScript。

----------

CSS3 @keyframes 规则

@keyframes 规则用于创建动画。在 @keyframes 中规定某项 CSS 样式，就能创建由当前样式逐渐改为新样式的动画效果

什么是 CSS3 中的动画？

- 动画是使元素从一种样式逐渐变化为另一种样式的效果。
- 您可以改变任意多的样式任意多的次数。
- 请用百分比来规定变化发生的时间，或用关键词 "from" 和 "to"，等同于 0% 和 100%。
- 0% 是动画的开始，100% 是动画的完成。

为了得到最佳的浏览器支持，您应该始终定义 0% 和 100% 选择器。

CSS3 动画属性
下面的表格列出了 @keyframes 规则和所有动画属性：

|属性|描述|CSS|
|---|---|---|
|@keyframes|规定动画。|3|
|animation|所有动画属性的简写属性，除了 animation-play-state 属性。|3|
|animation-name|规定 @keyframes 动画的名称。|3|
|animation-duration|规定动画完成一个周期所花费的秒或毫秒。默认是 0。|3|
|animation-timing-function|规定动画的速度曲线。默认是 "ease"。|3|
|animation-delay|规定动画何时开始。默认是 0。|3|
|animation-iteration-count|规定动画被播放的次数。默认是 1。|3|
|animation-direction|规定动画是否在下一周期逆向地播放。默认是 "normal"。|3|
|animation-play-state|规定动画是否正在运行或暂停。默认是 "running"。|3|
|animation-fill-mode|规定对象动画时间之外的状态。|3|

## CSS3 多列

通过 CSS3，您能够创建多个列来对文本进行布局 - 就像报纸那样！

- column-count
- column-gap
- column-rule

新的多列属性
下面的表格列出了所有的转换属性：

|属性|描述|CSS|
|---|---|---|
|column-count|规定元素应该被分隔的列数。|3|
|column-fill|规定如何填充列。|3|
|column-gap|规定列之间的间隔。|3|
|column-rule|设置所有 column-rule-* 属性的简写属性。|3|
|column-rule-color|规定列之间规则的颜色。|3|
|column-rule-style|规定列之间规则的样式。|3|
|column-rule-width|规定列之间规则的宽度。|3|
|column-span|规定元素应该横跨的列数。|3|
|column-width|规定列的宽度。|3|
|columns|规定设置 column-width 和 column-count 的简写属性。|3|

## CSS3 用户界面

CSS3 用户界面
在 CSS3 中，新的用户界面特性包括重设元素尺寸、盒尺寸以及轮廓等。

- resize：规定是否可由用户调整元素尺寸
- box-sizing：允许您以确切的方式定义适应某个区域的具体内容
- outline-offset：对轮廓进行偏移，并在超出边框边缘的位置绘制轮廓。

新的用户界面属性
下面的表格列出了所有的转换属性：

|属性|描述|CSS|
|---|---|---|
|appearance|允许您将元素设置为标准用户界面元素的外观|3|
|box-sizing|允许您以确切的方式定义适应某个区域的具体内容。|3|
|icon|为创作者提供使用图标化等价物来设置元素样式的能力。|3|
|nav-down|规定在使用 arrow-down 导航键时向何处导航。|3|
|nav-index|设置元素的 tab 键控制次序。|3|
|nav-left|规定在使用 arrow-left 导航键时向何处导航。|3|
|nav-right|规定在使用 arrow-right 导航键时向何处导航。|3|
|nav-up|规定在使用 arrow-up 导航键时向何处导航。|3|
|outline-offset|对轮廓进行偏移，并在超出边框边缘的位置绘制轮廓。|3|
|resize|规定是否可由用户对元素的尺寸进行调整。|3|
