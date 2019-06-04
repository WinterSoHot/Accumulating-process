package base.gudongxian.day07.sax;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * ʹ��sax������ xml�ĵ���װ�ɶ���
 * @author APPle
 *
 */
public class Demo3 {

	public static void main(String[] args)throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		MyDefaultHandler3 handler = new MyDefaultHandler3();
		parser.parse(new File("./src/contact.xml"), handler);
		List<Contact> list = handler.getList();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
