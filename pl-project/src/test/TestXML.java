package test;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
public class TestXML {
	  private static final String FILENAME = "applicationStaff.xml";

	public static void main(String[] args) {
		// new TestXML().myfunc("filename.xml");
		 new TestXML().printXMLElement();
	}

//	void myfunc(String fileName) {
//
//		try {
//			File myObj = new File(fileName);
//			if (myObj.createNewFile()) {
//				System.out.println("File created: " + myObj.getName() + " Path =  " + myObj.getAbsolutePath());
//			} else {
//				System.out.println("File already exists.");
//			}
//		} catch (IOException e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}
//	}
	
	void printXMLElement() {
		
		 // Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try {

	          // optional, but recommended
	          // process XML securely, avoid attacks like XML External Entities (XXE)
	          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File(FILENAME));

	          // optional, but recommended
	          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	          doc.getDocumentElement().normalize();

	          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          System.out.println("------");

	          // get <staff>
	          NodeList list = doc.getElementsByTagName("user");

	          for (int temp = 0; temp < list.getLength(); temp++) {

	              Node node = list.item(temp);

	              if (node.getNodeType() == Node.ELEMENT_NODE) {

	                  Element element = (Element) node;

	                  // get staff's attribute
	                  String id = element.getAttribute("id");

	                  // get text
	                  String username = element.getElementsByTagName("username").item(0).getTextContent();
	                  String password = element.getElementsByTagName("password").item(0).getTextContent();
	                  String type = element.getElementsByTagName("type").item(0).getTextContent();
//
//	                  NodeList salaryNodeList = element.getElementsByTagName("salary");
//	                  String salary = salaryNodeList.item(0).getTextContent();

	                  // get salary's attribute
	                  //String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

	                  System.out.println("Current Element :" + node.getNodeName());
	                  System.out.println("Staff Id : " + id);
	                  System.out.println("username : " + username);
	                  System.out.println("password : " + password);
	                  System.out.println("type : " + type);
//	                  System.out.println("Salary "+ salary + " " + currency);
//	                  System.out.println("---------------------------------------------");

	              }
	          }

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }
		
	}
	
}
