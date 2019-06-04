package base.gudongxian.day07.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * ��ȡcontact.xml�ļ�����������ĵ�����
 * @author APPle
 *
 */
public class Demo2 {

	public static void main(String[] args)throws Exception {
		//1.����SAXParser
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		//2.��ȡxml�ļ�
		MyDefaultHandler2 handler = new MyDefaultHandler2();
		parser.parse(new File("./src/contact.xml"), handler);
		String content = handler.getContent();
		System.out.println(content);
	}

}
