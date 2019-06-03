import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

void main() {
  debugPaintSizeEnabled = true;
  runApp(new MaterialApp(title: "Flutter Tutorial", home: new TutorialHome()));
}

class TutorialHome extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        leading: new IconButton(
          icon: new Icon(Icons.menu),
          tooltip: "Navigation menu",
          onPressed: null,
        ),
        title: new Text("Example title"),
        actions: <Widget>[
          new IconButton(
            icon: new Icon(Icons.search),
            tooltip: 'Search',
            onPressed: null,
          )
        ],
      ),
      body: new Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[new MyButton(), new Counter()],
      ),
      floatingActionButton: new FloatingActionButton(
        child: new Icon(Icons.add),
        tooltip: 'Add',
        onPressed: null,
      ),
    );
  }
}

// 处理手势
class MyButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: () {
        print('MyButton was tapped.');
      },
      child: new Container(
        height: 36.0,
        padding: EdgeInsets.all(8.0),
        margin: EdgeInsets.symmetric(horizontal: 8.0),
        decoration: new BoxDecoration(
            borderRadius: new BorderRadius.circular(5.0),
            color: Colors.lightGreen[500]),
        child: new Center(
          child: new Text('Engage'),
        ),
      ),
    );
  }
}

//根据用户输入改变widget

class Counter extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _CounterState();
  }
}

class _CounterState extends State<Counter> {
  int _counter = 0;

  void _increment() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Row(
      children: <Widget>[
        new RaisedButton(
          onPressed: _increment,
          child: new Text('increment'),
        ),
        new Text('Count: $_counter')
      ],
    );
  }
}
