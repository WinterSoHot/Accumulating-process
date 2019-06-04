package base.gudongxian.day01.path;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by gudongxian on 2017/6/19.
 * <p>
 * <p>
 * 配置文件的路径应该如何写 呢？
 * <p>
 * 绝对路径：一个文件的完整路径信息。一般绝对路径是包含有盘符 的。  绝对路径的缺陷： 因为绝对路径是有盘符开头的，有些系统是没有盘符的。
 * <p>
 * 相对路径: 相对路径是相对于当前程序的路径。当前路径就是执行java命令的时候，控制台所在的路径。
 * <p>
 * 类文件路径 :类文件路径就是使用了classpath的路径找对应的资源文件。
 * <p>
 * 如果需要使用到类文件路径首先先要获取到一个Class对象。
 */
public class DBUtil {
    static Properties properties;

    static {
        properties = new Properties();
        try {
            // / 代表当前classpath
            properties.load(DBUtil.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("当前路径：" + new File(".").getAbsolutePath());
        System.out.println("用户名：" + properties.getProperty("username") + ",密码：" + properties.getProperty("password"));
    }
}
