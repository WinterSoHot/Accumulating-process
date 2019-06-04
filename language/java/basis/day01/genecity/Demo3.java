package base.gudongxian.day01.genecity;

/**
 * Created by gudongxian on 2017/6/14.
 * 泛型类
 * 泛型类的自定义格式
 * class 类名 <声明自定义泛型>
 * <p>
 * 注意事项：
 * 1.在创建对象的时候指定泛型的类型
 * 2.创建对象没有指定类型，默认为Object
 */

//自定义泛型集合类

class MyList<T> {

    Object[] arr;
    int index = 0;

    MyList() {
        arr = new Object[10];
    }

    public void add(T o) {
        arr[index++] = o;
    }
}

public class Demo3 {

    public static void main(String[] args) {
        MyList<String> myList = new MyList<String>();
        myList.add("123");
    }
}
