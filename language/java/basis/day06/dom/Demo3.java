package base.gudongxian.day06.dom;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 *
 * @author APPle
 */
public class Demo3 {

    @Test
    public void test() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(Demo3.class.getResourceAsStream("/03.contact.xml"));

        Element rootELem = doc.getRootElement();

        StringBuffer sb = new StringBuffer();

        getChildNodes(rootELem, sb);

        System.out.println(sb.toString());

    }

    /**
     */
    private void getChildNodes(Element elem, StringBuffer sb) {
        //System.out.println(elem.getName());

        sb.append("<" + elem.getName());

        List<Attribute> attrs = elem.attributes();
        if (attrs != null) {
            for (Attribute attr : attrs) {
                sb.append(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
            }
        }
        sb.append(">");

        //String content = elem.getText();
        //System.out.println(content);
        Iterator<Node> it = elem.nodeIterator();
        while (it.hasNext()) {
            Node node = it.next();

            if (node instanceof Element) {
                Element el = (Element) node;
                getChildNodes(el, sb);
            }

            if (node instanceof Text) {
                Text text = (Text) node;
                sb.append(text.getText());
            }
        }

        sb.append("</" + elem.getName() + ">");
    }
}
