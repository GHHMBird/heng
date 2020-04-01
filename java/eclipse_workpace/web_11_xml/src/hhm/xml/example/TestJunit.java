package hhm.xml.example;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class TestJunit {
	@Test
	public void testMyServlet() {
		try {
			
			SAXReader saxReader = new SAXReader();
			String url = "src/hhm/xml/example/web.xml";
			Document document = saxReader.read(url);
			Element rootElement = document.getRootElement();
			
			Element servlet1 = rootElement.element("servlet");
			Element servlet1_class = servlet1.element("servlet-class");
			String servlet1_class_text = servlet1_class.getText();
			System.out.println(servlet1_class_text);
			
			Class clazz1 = Class.forName(servlet1_class_text);
			MyServlet1 myServlet1 = (MyServlet1) clazz1.newInstance();
			myServlet1.init();
			myServlet1.service();
			myServlet1.destory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
