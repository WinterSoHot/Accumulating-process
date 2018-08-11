import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main() => runApp(new MyApp());

// 无状态的Widget 任何属性都是不可改变的
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "Startup Name Generator",
      theme: new ThemeData(
        primaryColor: Colors.white,
      ),
      home: new RandomWords(),
    );
  }
}

// 构建有状态的Widget，需要两个类
class RandomWords extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new RandomWordsState();
  }
}

class RandomWordsState extends State<RandomWords> {
  // 声明变量 前缀下斜杠表示变量私有
  final _suggestions = <WordPair>[]; //建议的列表
  final _save = Set<WordPair>();//标记为喜欢的单词
  final _biggerFont = const TextStyle(fontSize: 18.0);//字体大小

  //构建列表
  Widget _buildSuggestions() {
    //返回一个ListView
    return new ListView.builder(
      padding: const EdgeInsets.all(16.0), //内边距
      itemBuilder: (context, i) {
        if (i.isOdd) return new Divider(); //如果是奇数就添加分割线
        final index = i ~/ 2; //除2取整
        if (index >= _suggestions.length) {//如果当前显示的索引大于数组的长度，生成十个新的单词
          _suggestions.addAll(generateWordPairs().take(10));
        }
        //构建一行的数据
        return _buildRow(_suggestions[index]);
      },
    );
  }

  Widget _buildRow(WordPair pair) {
    //判断当前是否被选中
    final alreadySaved = _save.contains(pair);
    
    return new ListTile(
      title: new Text(
        pair.asPascalCase,
        style: _biggerFont,
      ),
      trailing: new Icon(//构建每个列表显示的爱心图标
        alreadySaved ? Icons.favorite : Icons.favorite_border,
        color: alreadySaved ? Colors.red : null,
      ),
      onTap: () { //加入点击事件
        //调用setState() 会为State对象触发build()方法，从而导致对UI的更新
        setState(() {
          if (alreadySaved) {
            _save.remove(pair);
          } else {
            _save.add(pair);
          }
        });
      },
    );
  }

  void _pushSaved() {
    //切换页面（路由导航）,push 就将新的页面入栈。
    //新的路由会自动添加返回按钮，不需要显式的pop
    Navigator.of(context).push(new MaterialPageRoute(builder: (context) {
      //构建 已经选中的列表
      final tiles = _save.map((pair) {
        return new ListTile(
            title: new Text(
          pair.asPascalCase,
          style: _biggerFont,
        ));
      });
      //divideTiles() 在每个tiles之间加入分割线，并返回新的列表
      final divided = ListTile.divideTiles(
        context: context,
        tiles: tiles
      ).toList();
      // 新的路由页面布局
      return new Scaffold(
        appBar: new AppBar(
          title: new Text('Saved suggestion'),
        ),
        body: new ListView(children: divided,),
      );
    }));
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('Startup Name Generator'),
        // 给导航栏添加图标，并添加事件
        actions: <Widget>[
          new IconButton(
            icon: new Icon(Icons.list),
            onPressed: _pushSaved,
          )
        ],
      ),
      body: _buildSuggestions(),
    );
  }
}
