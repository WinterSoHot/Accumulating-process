package base.gudongxian.day01.genecity;

/**
 * Created by gudongxian on 2017/6/14.
 * 泛型接口的定义:
 * 格式
 * interface 接口名<声明自定义泛型>｛
 * <p>
 * ｝
 * <p>
 * 注意事项：
 * 1.在实现接口的时候指定泛型的类型
 * 2.没有指定泛型，默认为Object
 * <p>
 * 在创建接口实现类的时候指定自定义泛型的数据类型
 */

interface Dao<T> {

    void add(T o);

    void remove(T o);
}

public class Demo4<T> implements Dao<T> {


    public static void main(String[] args) {
        Demo4<String> xx = new Demo4<String>();
    }

    public void add(T o) {

    }

    public void remove(T o) {

    }
}
