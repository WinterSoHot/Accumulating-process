package base.gudongxian.day01.junit;

import org.junit.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by gudongxian on 2017/6/15.
 */
public class Demo2 {


    @Before    // 每个测试方法之前都执行一次
    // @BeforeClass // 只在所以测试方法执行前执行一次
    public void beforeRead() {
        System.out.println("准备测试环境");
    }

    @Test
    public void readFile() throws Exception {
        FileInputStream fis = new FileInputStream("F://a.txt");
        int content = fis.read();
        System.out.println("读取第一个数据:" + content);
    }

    @After
    //@AfterClass
    public void afterRead() {
        System.out.println("清理测试环境");
    }
}
