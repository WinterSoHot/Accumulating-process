package base.gudongxian.day06.dom;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

/**
 * Created by gudongxian on 2017/6/24.
 * Dom4j 解析 xml
 */
public class Test {

    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        try {

            //读取文件
            Document doc = reader.read(Test.class.getResourceAsStream("/03.contact.xml"));

            //读取所有节点
            Iterator<Node> iterator = doc.nodeIterator();
            while (iterator.hasNext()) {
                Node node = iterator.next();
                System.out.println(node.getName());
                if (node instanceof Element) {
                    Element element = (Element) node;
                    Iterator<Node> iterator1 = element.nodeIterator();
                    while (iterator1.hasNext()) {
                        Node item = iterator1.next();
                        System.out.println(item.getName());
                    }
                }

            }

        } catch (DocumentException e) {

            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void test1() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(Test.class.getResourceAsStream("/03.contact.xml"));
        getChildrenElem(doc.getRootElement());

    }

    /**
     * 获取文本
     *
     * @throws Exception
     */
    @org.junit.Test
    public void test2() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(Test.class.getResourceAsStream("/03.contact.xml"));
        Element rootElement = doc.getRootElement();
        String text = rootElement.element("contact").elementText("phone");
        System.out.println(text);
    }

    /*
    属性
     */
    @org.junit.Test
    public void test3() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(Test.class.getResourceAsStream("/03.contact.xml"));
        Element contact = doc.getRootElement().element("contact");
        //根据名字获取
        Attribute id = contact.attribute("id");
        //遍历获取
        Iterator<Attribute> iterator = contact.attributeIterator();
        while (iterator.hasNext()) {
            Attribute next = iterator.next();
            System.out.println("属性名：" + next.getName() + "  值为：" + next.getValue());
        }
    }

    @org.junit.Test
    public void test4() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(Test.class.getResourceAsStream("/03.contact.xml"));
    }

    public void getChildrenElem(Element element) {
        Iterator<Node> iterator = element.nodeIterator();
        while (iterator.hasNext()) {
            Node item = iterator.next();
            if (item instanceof Element) {
                Element element1 = (Element) item;
                String name = element1.getName();
                if ("contact".equals(name)) {

                    System.out.println("==" + element1.attribute("id").getData());
                }
                System.out.println(name);
                getChildrenElem(element1);
            }
        }
    }
}
