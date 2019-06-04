package base.gudongxian.day07.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * SAX�������
 * @author APPle
 *
 */
public class MyDefaultHandler3 extends DefaultHandler {
	//�洢������ϵ�˶���
	private List<Contact> list = new ArrayList<Contact>();
	
	public List<Contact> getList(){
		return list;
	}
	//����һ����ϵ����Ϣ
	private Contact contact;
	/**
	 * ˼·�� 
	 * 	1������Contact����
	 *  2����ÿ��contact��ǩ���ݴ��뵽Contact����
	 *  3����Contact�������List��
	 */
	//������ʱ�洢��ǰ�����ı�ǩ��
	private String curTag;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		curTag = qName;
		//��ȡ��contact�Ŀ�ʼ��ǩ����Contact����
		if("contact".equals(qName)){
			contact = new Contact();
			
			//����idֵ
			contact.setId(attributes.getValue("id"));
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//��ǰ�ı�����
		String content = new String(ch,start,length);
		
		if("name".equals(curTag)){
			contact.setName(content);
		}
		
		if("age".equals(curTag)){
			contact.setAge(content);
		}
		
		if("phone".equals(curTag)){
			contact.setPhone(content);
		}
		
		if("email".equals(curTag)){
			contact.setEmail(content);
		}
		
		if("qq".equals(curTag)){
			contact.setQq(content);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//���ÿ�ʱΪ�˱���ո������õ������������
		curTag = null;
		//����contact�Ľ�����ǩ����List��
		if("contact".equals(qName)){
			list.add(contact);
		}
	}
}
