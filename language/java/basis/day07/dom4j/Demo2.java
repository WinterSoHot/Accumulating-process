package base.gudongxian.day07.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
<Students>
<Student id="1">
	<name>����</name>
	<gender>��</gender>
	<grade>�����1��</grade>
	<address>�������</address>
</Student>
<Student id="2">
	<name>����</name>
	<gender>Ů</gender>
	<grade>�����2��</grade>
	<address>����Խ��</address>
</Student>
</Students>
 */
public class Demo2 {

	/**
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		Document doc = DocumentHelper.createDocument();
		
		Element rootElem = doc.addElement("Students");
		Element studentElem1 = rootElem.addElement("Student");
		studentElem1.addAttribute("id", "1");
		studentElem1.addElement("name").setText("����");
		studentElem1.addElement("gender").setText("��");
		studentElem1.addElement("grade").setText("�����1��");
		studentElem1.addElement("address").setText("�������");
		
		Element studentElem2 = rootElem.addElement("Student");
		studentElem2.addElement("name").setText("����");
		studentElem2.addElement("gender").setText("Ů");
		studentElem2.addElement("grade").setText("�����2��");
		studentElem2.addElement("address").setText("����Խ��");
		
		
		FileOutputStream out = new FileOutputStream("e:/student.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		Document doc = new SAXReader().read(new File("e:/student.xml"));
		Iterator<Element> it = doc.getRootElement().elementIterator("Student");
		while(it.hasNext()){
			Element stuElem = it.next();
			if(stuElem.attributeValue("id").equals("2")){
				stuElem.element("name").setText("����");
				break;
			}
		}
	
		
		FileOutputStream out = new FileOutputStream("e:/student.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		Document doc = new SAXReader().read(new File("e:/student.xml"));
		Iterator<Element> it = doc.getRootElement().elementIterator("Student");
		while(it.hasNext()){
			Element stuElem = it.next();
			if(stuElem.attributeValue("id").equals("2")){
				stuElem.detach();
				break;
			}
		}
		FileOutputStream out = new FileOutputStream("e:/student.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
}
