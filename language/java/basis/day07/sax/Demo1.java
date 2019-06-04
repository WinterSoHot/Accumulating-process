package base.gudongxian.day07.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * ��һ��SAX��ȡxml�ļ�����
 * @author APPle
 *
 */
public class Demo1 {

	
	public static void main(String[] args) throws Exception{
		//1.����SAXParser����
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		
		//2.����parse����
		/**
		 * ����һ�� xml�ĵ�
		 * �������� DefaultHandler������
		 */
		parser.parse(new File("./src/contact.xml"), new MyDefaultHandler());
	}

}
