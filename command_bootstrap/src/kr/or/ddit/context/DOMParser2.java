package kr.or.ddit.context;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser2 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		Map<String, String> xmlParserMap = new HashMap<String, String>();
		
		// XML 문서 파싱
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(new File("src/application-context.xml"));
		
		// root 구하기
		document.getDocumentElement().normalize();
		NodeList nList = document.getElementsByTagName("bean");
		
		for(int temp = 0 ; temp < nList.getLength() ; temp++) {
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String id = eElement.getAttribute("id");
				String klass = eElement.getAttribute("class");
				xmlParserMap.put(id+"Bean", klass);
				
				NodeList properties = eElement.getElementsByTagName("property");
				if(properties.getLength() > 0) {
					for(int count = 0 ; count < properties.getLength() ; count++) {
						Node pNode = properties.item(count);
						if(pNode.getNodeType() == Node.ELEMENT_NODE) {
							Element pElement = (Element) pNode;
							String name = pElement.getAttribute("name");
							String ref_value = pElement.getAttribute("ref-value");
							xmlParserMap.put(name+"Property", ref_value);
						}
					}
				}
			}
			
		}
		Set<String> keySet = xmlParserMap.keySet();
		
		for(String key : keySet) {
			System.out.println("★"+ key + " : " + xmlParserMap.get(key));
		}
	}
}