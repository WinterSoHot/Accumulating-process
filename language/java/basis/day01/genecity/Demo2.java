package base.gudongxian.day01.genecity;

/**
 * Created by gudongxian on 2017/6/14.
 * 自定义泛型： 可以理解为一个数据类型的占位符 或者理解为一个数据类型的变量
 * 泛型方法：
 *      格式：  修饰符  <声明泛型变量>  返回值类型 方法名（形参列表..）{
 *
 *      }
 *
 * 需求： 要求方法的返回值类型要与传入的参数要一致
 */
public class Demo2 {
    public static void main(String[] args) {
        Integer i1 = print(12);
        String s = print("12");
    }

    // <T> 把T字母声明为自定义泛型
    public static <T> T print (T t){
        return t;
    }
}
