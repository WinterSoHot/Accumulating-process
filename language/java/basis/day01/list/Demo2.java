package base.gudongxian.day01.list;

import java.util.*;

/**
 * Created by gudongxian on 2017/6/14.
 */
public class Demo2 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        System.out.println("list 数组 采用get 方法进行遍历");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ",");
        }

        // 采用迭代器进行变量 注意： 不能在 遍历的时候操作集合元素，只能使用迭代器的方法
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("小王");
        hashSet.add("小李");
        hashSet.add("小张");
        System.out.println("\r\nset 采用迭代器进行遍历");

        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ",");
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("张三", "001");
        map.put("李四", "002");
        map.put("王五", "003");
        System.out.println("\r\n 采用EntrySet 遍历");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> item : entries) {
            System.out.print("item = " + item.getKey() + "|" + item.getValue() + ",");
        }

    }
}
