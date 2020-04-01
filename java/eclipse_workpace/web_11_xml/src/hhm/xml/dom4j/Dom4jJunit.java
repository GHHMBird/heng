package hhm.xml.dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4jJunit {
	@Test
	public void testDom4j() {
		try {
			//获取解析器
			SAXReader saxReader = new SAXReader();
			//获取document文档
			String url = "src/hhm/xml/dom4j/web.xml";
			Document document = saxReader.read(url);
			//获取根元素
			Element rootElement = document.getRootElement();
//			System.out.println(rootElement.getName());
//			System.out.println(rootElement.attributeValue("version"));
//			System.out.println(rootElement.attributeValue("schemaLocation"));
			//获取根元素下的子元素
			List<Element> list = rootElement.elements();
			//遍历子元素
			for (Element element : list) {
				if ("servlet".equals(element.getName())) {
					Element servletName = element.element("servlet-name");
					Element servletClass = element.element("servlet-class");
					System.out.println(servletName.getName());
					System.out.println(servletClass.getName());
					System.out.println(servletName.getText());
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
