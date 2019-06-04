package base.gudongxian.day01.genecity;

import java.util.ArrayList;

/**
 * Created by gudongxian on 2017/6/14.
 * 泛型
 * 泛型的好处
 * 1：将运行时的错误在编译的时候展示
 * 2：避免了无谓的强制转换
 *
 * 注意： 泛型没有多态的概念，两边的数据必须一致
 * 建议两边都写一样的类型
 */
public class Demo1 {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add(123);
        for (int i = 0; i < list.size(); i++){
            String value = (String) list.get(i);
            System.out.println(value.toUpperCase());
        }
    }
}
