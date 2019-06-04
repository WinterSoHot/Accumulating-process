package base.gudongxian.day07.xpath;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gudongxian on 2017/6/25.
 * Xpath 技术 作用快速获取节点对象
 * 问题：当使用dom4j查询比较深的层次结构的节点（标签，属性，文本），比较麻烦！！！
 */
public class Demo1 {

    Document doc = null;

    @Before
    public void testBefore() throws Exception {
        doc = new SAXReader().read(Demo1.class.getResourceAsStream("/03.contact.xml"));
    }

    @After
    public void testAfter() {
        doc = null;
    }


    @Test
    public void test1() throws Exception {
//        List<Node>  selectNodes("xpath表达式");   查询多个节点对象
//        Node       selectSingleNode("xpath表达式");  查询一个节点对象
//        /      绝对路径      表示从xml的根位置开始或子元素（一个层次结构）
//        //     相对路径       表示不分任何层次结构的选择元素。
//		  *      通配符         表示匹配所有元素
//		  []     条件           表示选择什么条件下的元素
//        @      属性            表示选择属性节点
//        and     关系          表示条件的与关系（等价于&&）
//        text()    文本           表示选择文本内容
        //1.查询id为001的contact
        Element contact = (Element) doc.selectSingleNode("//contact[@id='001']");
        System.out.println(contact.elementText("name"));

    }

    @Test
    public void test2() throws Exception {
        String xpath = "";

        /**
         * 1.  	/      绝对路径      表示从xml的根位置开始或子元素（一个层次结构）
         */
        xpath = "/contactList";
        xpath = "/contactList/contact";

        /**
         * 2. //     相对路径       表示不分任何层次结构的选择元素。
         */
        xpath = "//contact/name";
        xpath = "//name";

        /**
         * 3. *      通配符         表示匹配所有元素
         */
        xpath = "/contactList/*"; //根标签contactList下的所有子标签
        xpath = "/contactList//*";//根标签contactList下的所有标签（不分层次结构）

        /**
         * 4. []      条件           表示选择什么条件下的元素
         */
        //带有id属性的contact标签
        xpath = "//contact[@id]";
        //第二个的contact标签
        xpath = "//contact[2]";
        //选择最后一个contact标签
        xpath = "//contact[last()]";

        /**
         * 5. @     属性            表示选择属性节点
         */
        xpath = "//@id"; //选择id属性节点对象，返回的是Attribute对象
        xpath = "//contact[not(@id)]";//选择不包含id属性的contact标签节点
        xpath = "//contact[@id='002']";//选择id属性值为002的contact标签
        xpath = "//contact[@id='001' and @name='eric']";//选择id属性值为001，且name属性为eric的contact标签

        /**
         *6.  text()   表示选择文本内容
         */
        //选择name标签下的文本内容，返回Text对象
        xpath = "//name/text()";
        xpath = "//contact/name[text()='张三']";//选择姓名为张三的name标签


        List<Node> list = doc.selectNodes(xpath);
        for (Node node : list) {
            System.out.println(node);
        }
    }


    public static void main(String[] args) throws Exception {
        Document doc = new SAXReader().read(Demo1.class.getResourceAsStream("/03.contact.xml"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入Id：");
        String id = bufferedReader.readLine();
        System.out.println("请输入名字：");
        String name = bufferedReader.readLine();

        //查id
        String xpath = "//contact[@id="+id+"]";
        Element resultElement = (Element) doc.selectSingleNode(xpath);
        if (resultElement!=null){
            System.out.println("登录成功！！");
        }else {
            System.out.println("登录失败！！");
        }
    }

    /**
     *
     * 适应Xpath读取一个html
     * @throws Exception
     */
    @Test
    public void test3() throws Exception{
        Document doc = new SAXReader().read(Demo1.class.getResourceAsStream("/table.html"));

        Element tableElement = (Element) doc.selectSingleNode("//table[@name='mm']");
        if (tableElement!=null){

            List<Element> list = tableElement.selectNodes("//tr");
            Iterator<Element> iterator = list.iterator();
            while (iterator.hasNext()){
                Element item = iterator.next();
//                item.selectSingleNode("/td[1]");
                String id = ((Element)item.elements().get(0)).getText();
                String name = ((Element)item.elements().get(1)).getText();
                String age = ((Element)item.elements().get(2)).getText();
                System.out.println(id+"\t"+name+"\t"+age);
            }

            return;
        }
        System.out.println("读取table失败");
    }
}
