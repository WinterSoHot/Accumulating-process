# material_widget

## Material Widget 组件

> Flutter提供了许多widgets，可帮助您构建遵循Material Design的应用程序。Material应用程序以MaterialApp widget开始， 该widget在应用程序的根部创建了一些有用的widget，其中包括一个Navigator， 它管理由字符串标识的Widget栈（即页面路由栈）。Navigator可以让您的应用程序在页面之间的平滑的过渡。 是否使用MaterialApp完全是可选的，但是使用它是一个很好的做法。

## 处理手势

- 该GestureDetector widget并不具有显示效果，而是检测由用户做出的手势。 当用户点击Container时， GestureDetector会调用它的onTap回调， 在回调中，将消息打印到控制台。您可以使用GestureDetector来检测各种输入手势，包括点击、拖动和缩放。

- 许多widget都会使用一个GestureDetector为其他widget提供可选的回调。 例如，IconButton、 RaisedButton、 和FloatingActionButton ，它们都有一个onPressed回调，它会在用户点击该widget时被触发。