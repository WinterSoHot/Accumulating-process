package base.gudongxian.day06.dom;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Demo2 {
	
	@Test
	public void test1() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/contact.xml"));
		
		Iterator<Node> it = doc.nodeIterator();
		while(it.hasNext()){
			Node node = it.next();
			String name = node.getName();

			//System.out.println(node.getClass());
			if(node instanceof Element){
				Element elem = (Element)node;
				Iterator<Node> it2 = elem.nodeIterator();
				while(it2.hasNext()){
					Node n2 = it2.next();
					System.out.println(n2.getName());
				}
			}
		}
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/contact.xml"));
		
		Element rooElem = doc.getRootElement();
		
		getChildNodes(rooElem);

	}
	
	/**
	 * @param elem
	 */
	private void getChildNodes(Element elem){
		System.out.println(elem.getName());
		
		Iterator<Node> it = elem.nodeIterator();
		while(it.hasNext()){
			Node node = it.next();
			
			if(node instanceof Element){
				Element el = (Element)node;
				getChildNodes(el);
			}
		};
	}
	
	@Test
	public void test3() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/contact.xml"));
		
		Element  rootElem = doc.getRootElement();
		String name = rootElem.getName();
		System.out.println(name);
		
		/*
		Element contactElem = rootElem.element("contact");
		System.out.println(contactElem.getName());
		*/
		
		/*
		Iterator<Element> it = rootElem.elementIterator("contact");
		while(it.hasNext()){
			Element elem = it.next();
			System.out.println(elem.getName());
		}
		*/
		
		List<Element> list = rootElem.elements();
		/*for(int i=0;i<list.size();i++){
			Element e = list.get(i);
			System.out.println(e.getName());
		}*/
		
	/*	for(Element e:list){
			System.out.println(e.getName());
		}*/
		/*
		Iterator<Element> it = list.iterator(); //ctrl+2 �ɿ� l
		while(it.hasNext()){
			Element elem = it.next();
			System.out.println(elem.getName());
		}*/
		
		Element nameElem = doc.getRootElement().
					element("contact").element("name");
		System.out.println(nameElem.getName());
		
	}
	
	@Test
	public void test4() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/contact.xml"));
		
		Element contactElem = doc.getRootElement().element("contact");
		/*
		String idValue = contactElem.attributeValue("id");
		System.out.println(idValue);
		*/
		
		/*Attribute idAttr = contactElem.attribute("id");
		//getName�� ��������    getValue������ֵ
		System.out.println(idAttr.getName() +"=" + idAttr.getValue());*/
		
		/*List<Attribute> list = contactElem.attributes();
		for (Attribute attr : list) {
			System.out.println(attr.getName()+"="+attr.getValue());
		}*/
		
		Iterator<Attribute> it = contactElem.attributeIterator();
		while(it.hasNext()){
			Attribute attr = it.next();
			System.out.println(attr.getName()+"="+attr.getValue());
		}
		
	}
	
	@Test
	public void test5() throws Exception{
		SAXReader reader = new SAXReader();
					
		Document doc = reader.read(new File("./src/contact.xml"));
		
		
		String content = doc.getRootElement().getText();
		System.out.println(content);
		
		
		Element nameELem =
			doc.getRootElement().element("contact").element("name");
		String text = nameELem.getText();
		System.out.println(text);
		
		String text2 =
			doc.getRootElement().element("contact").elementText("phone");
		System.out.println(text2);
		
	}
	
	
}
