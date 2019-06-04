package base.gudongxian.day01.io;

import java.io.*;

/**
 * Created by gudongxian on 2017/6/14.
 * <p>
 * IO解决问题：设备与设备之间的数据传递  比如:硬盘->内存
 * <p>
 * 字节流
 * <p>
 * 输入字节流：
 * ------------| InputStream  所以输入字节流的基类，抽象类
 * ---------------| FileInputStream 读取文件输入字节流
 * ---------------| BufferedInputStream 缓冲输入字节流，内部维护了一个8kb的数组，提高了对文件的输入效率
 * <p>
 * 输出字节流：
 * ------------| OutputStream 所有输出字节流的基类，抽象类
 * ---------------| FileOutputStream 向文件输入的文件输出字节流
 * ---------------| BufferedOutputStream 缓冲输出字节流，提高了向稳健写入的效率，内部也是维护了一个8kb的数组
 * <p>
 * 什么情况下使用字节流： 读取的数据不需要经过编码和解码的数据，比如：图片数据。
 * <p>
 * 字符流  =  字节流 + 编码（解码）
 * <p>
 * 输入字符流
 * ------------| Reader  所有输入字符流的基类，抽象类
 * ---------------| FileReader 读取文件的输入字符流 内部默认GBK编码
 * ---------------| BufferedReader 缓存输入字符流 目的，提高了读取文件字符的效率和扩展了ReadLine 内部维护了一个8192长度的字符数组
 * <p>
 * 输出字符流
 * ------------| Writer 所以输出字符流的基类，抽象类
 * ---------------| FileWriter 读取文件的输出字符流，内部默认GBK
 * ---------------| BufferedWriter 缓存输出字符流。。
 * <p>
 * <p>
 * 转换流  将字节流转换成字符流  可以指定编码
 * 输入转换流
 * ------|InputStreanmReader
 * 输出转换流
 * ------|OutputStreamWriter
 * <p>
 * 转换流的好处
 * 1. 将字节流转换成字符流
 * 2. 输入输出文件的时候可以指定码表
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        // testInput();

//        writeFile();

        readFile();
    }


    public static void readFile() throws Exception {

        FileInputStream fis = new FileInputStream("F://a.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fis, "utf-8");
        int content = 0;
        while ((content = inputStreamReader.read()) != -1) {
            System.out.print((char) content);
        }
        inputStreamReader.close();

        /*FileReader reader = new FileReader("F://a.txt");
        int content = 0;
        while ((content = reader.read()) != -1) {
            System.out.print((char) content);
        }
        reader.close();
         */
    }

    public static void writeFile() throws Exception {
        //建立和文件之间的通道
        FileOutputStream fis = new FileOutputStream("F://a.txt");
        //指定输入文件数据编码
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fis, "utf-8");
        outputStreamWriter.write("你好吗？");
        outputStreamWriter.close();
    }

    public static void testInput() throws IOException {
//        //读取控制台的输入字节流
        InputStream in = System.in;
//        //读取第一个字节
//        int content = in.read();
//        System.out.println(content);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //读取一行字符串
        String contentstr = bufferedReader.readLine();
        System.out.println(contentstr);
    }
}
