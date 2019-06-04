package base.gudongxian.day01.list;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gudongxian on 2017/6/14.
 * 集合 存取对象数据的集合容器
 * ----- 单列集合
 * ----------| Collection 接口 所以单列集合的根
 * --------------| List 特点： 有序的，可重复的
 * -------------------| ArrayList  底层使用Object 数组实现的，具有查询快，增删慢 （地址是连续的）
 * -------------------| LinkedList 底层使用链表数据结构实现，具有查询慢，增删快
 * -------------------| Vector  同样采用ArrayList相同实现的，线程安全的，效率低。
 * --------------| Set  特点： 无序的，不可重复的
 * -------------------| HashSet  底层采用哈希表实现的 (插入元素，执行HashCode()方法) 存取速度快
 * 情况1： 如果HashSet 不存在 当前哈希码，直接和插入
 * 情况2： 如果HashSet 存在，则执行equal() 相等的话就不执行插入，不想等，一样插入。
 * -------------------| TreeSet 底层采用二叉树（红黑树）数据结构 特点：对集合进行排序存储
 * 存入的元素必须实现自然排序或者传入比较器
 * ----- 双列集合
 * ----------| Map 所有的值以键值对的形式存在 键不重复，值可以重复
 * -------------| HashMap  底层使用哈希表
 */


class Person implements Comparable<Person> {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        return this.id == person.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    public int compareTo(Person o) {
        return this.id > o.id ? 1 : this.id < o.id ? -1 : 0;
    }
}

public class Demo1 {


    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<Person>();
        set.add(new Person(100, "小王"));
        set.add(new Person(100, "小王"));
        System.out.println("集合内的元素为： " + set);


        Set<Person> treeSet = new TreeSet<Person>();
        treeSet.add(new Person(111, "小张"));
        System.out.println(treeSet);
    }
}
