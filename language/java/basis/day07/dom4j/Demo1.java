package base.gudongxian.day07.dom4j;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gudongxian on 2017/6/25.
 */
public class Demo1 {

    public static void main(String[] args) throws IOException, DocumentException {

        SAXReader reader = new SAXReader();
        //获取文档
        Document doc = reader.read(Demo1.class.getResourceAsStream("/03.contact.xml"));
        //文档写出的位置
        FileOutputStream fos = new FileOutputStream("G://ss.xml");
        //创建xml写出
        XMLWriter writer = new XMLWriter(fos);
        writer.write(doc);
        writer.close();
    }

    Document doc = null;

    @Before
    public void testBefore() throws Exception {
        doc = new SAXReader().read(Demo1.class.getResourceAsStream("/03.contact.xml"));
    }

    @After
    public void testAfter() {
        doc = null;
    }

    /**
     * 输出帯格式的xml文件
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        FileOutputStream fos = new FileOutputStream("G://ss1.xml");
        OutputFormat format = OutputFormat.createCompactFormat();//创建经凑的xml适合上线
//      OutputFormat format = OutputFormat.createPrettyPrint();//创建漂亮的xml 适合开发 有换行和空格
        /**
         * 2.指定生成的xml文档的编码
         *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
         *    结论： 使用该方法生成的xml文档避免中文乱码问题。
         */
        format.setEncoding("utf-8");

        XMLWriter writer = new XMLWriter(fos, format);

        writer.write(doc);
        writer.close();

    }

    /**
     * 修改xml内容
     * 增加：文档，标签 ，属性
     * 修改：属性值，文本
     * 删除：标签，属性
     *
     * @author APPle
     */
    @Test
    public void test2() throws Exception {

        //创建文档
        Document mm = DocumentHelper.createDocument();
        //增加根节点
        Element root = mm.addElement("root");

        Element student = root.addElement("student");
        student.addAttribute("id","1");
        student.addElement("name").setText("谷东先");
        student.addElement("age").setText("18");
        FileOutputStream fos = new FileOutputStream("G://ss2.xml");
//        OutputFormat format = OutputFormat.createCompactFormat();//创建经凑的xml适合上线
      OutputFormat format = OutputFormat.createPrettyPrint();//创建漂亮的xml 适合开发 有换行和空格
        /**
         * 2.指定生成的xml文档的编码
         *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
         *    结论： 使用该方法生成的xml文档避免中文乱码问题。
         */
        format.setEncoding("utf-8");

        XMLWriter writer = new XMLWriter(fos, format);

        writer.write(mm);
        writer.close();

    }

    /**
     * 修改：属性值，文本
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        Element rootElement = doc.getRootElement();
        rootElement.element("contact").element("name").setText("尼玛");
        FileOutputStream fos = new FileOutputStream("G://ss3.xml");
//        OutputFormat format = OutputFormat.createCompactFormat();//创建经凑的xml适合上线
        OutputFormat format = OutputFormat.createPrettyPrint();//创建漂亮的xml 适合开发 有换行和空格
        /**
         * 2.指定生成的xml文档的编码
         *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
         *    结论： 使用该方法生成的xml文档避免中文乱码问题。
         */
        format.setEncoding("utf-8");

        XMLWriter writer = new XMLWriter(fos, format);

        writer.write(doc);
        writer.close();

    }

    /**
     * 删除：标签，属性
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        Element rootElement = doc.getRootElement();
        //获取第二个Contact
        Element element = (Element) rootElement.elements().get(1);

        //获取id属性
        Attribute id = element.attribute("id");
        //删除属性
        id.detach();
        //获取Element
        Element element1 = (Element) element.elements().get(1);
        element1.detach();
        FileOutputStream fos = new FileOutputStream("G://ss4.xml");
//        OutputFormat format = OutputFormat.createCompactFormat();//创建经凑的xml适合上线
        OutputFormat format = OutputFormat.createPrettyPrint();//创建漂亮的xml 适合开发 有换行和空格
        /**
         * 2.指定生成的xml文档的编码
         *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
         *    结论： 使用该方法生成的xml文档避免中文乱码问题。
         */
        format.setEncoding("utf-8");

        XMLWriter writer = new XMLWriter(fos, format);

        writer.write(doc);
        writer.close();

    }
}
