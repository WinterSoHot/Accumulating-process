package base.gudongxian.day07.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * SAX���������ν���xml�ĵ���
 * @author APPle
 *
 */
public class MyDefaultHandler extends DefaultHandler {
	
	/**
	 * ��ʼ�ĵ�ʱ����
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("MyDefaultHandler.startDocument()");
	}
	
	/**
	 * ��ʼ��ǩʱ����
	 * @param qName: ��ʾ��ʼ��ǩ�ı�ǩ��
	 * @param attributes: ��ʾ��ʼ��ǩ�ڰ����������б�
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("MyDefaultHandler.startElement()-->"+qName);
	}
	
	/**
	 * ������ǩʱ����
	 * @param qName: ������ǩ�ı�ǩ����
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("MyDefaultHandler.endElement()-->"+qName);
	}
	
	/**
	 * �����ı����ݵ�ʱ����
	 * @param ch: ��ʾ��ǰ����������ı�����
	 * @param start�� ��ʾ��ǰ�ı����ݵĿ�ʼλ��
	 * @param length: ��ʾ��ǰ�ı����ݵĳ���
	 * char[]��                                       ����              20��   100
	 *                              98 2   
	 */ 
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//�õ���ǰ�ı�����
		String content = new String(ch,start,length);
		System.out.println("MyDefaultHandler.characters()-->"+content);
	}
	
	/**
	 * �����ĵ�ʱ����
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("MyDefaultHandler.endDocument()");
	}
	
}
