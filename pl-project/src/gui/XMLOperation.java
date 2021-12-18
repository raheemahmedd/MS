package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLOperation {
	private static final String FILENAME = "applicationStaff.xml";

	public String validateUser(String username, String password) {
		System.out.println("trying to validate");
		// open XML
		// loop over users
		// Check user name pass
		// if exist return type break
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
				Element element = (Element) node;
				// get text
				String userNameText = element.getElementsByTagName("username").item(0).getTextContent();
				String passwordText = element.getElementsByTagName("password").item(0).getTextContent();
				if (userNameText.equals(username) && passwordText.equals(password)) {
					return element.getElementsByTagName("type").item(0).getTextContent();
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String[][] getUsersData() {
		System.out.println("trying to validate");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String[][] data = null;
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
			data = new String[list.getLength()][3];

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				Element element = (Element) node;
				// get text
				data[temp][0] = element.getElementsByTagName("id").item(0).getTextContent();
				data[temp][1] = element.getElementsByTagName("username").item(0).getTextContent();
				data[temp][2] = element.getElementsByTagName("password").item(0).getTextContent();

			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void updateUser(String id, String userName, String password) throws TransformerException {
		System.out.println("trying to validate");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String[][] data = null;
		try {
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			doc.getDocumentElement().normalize();
			/*
			 * 3nd mkan el id el b3thuli ydeni access 3la username password "a3dl fehum"
			 * 
			 * 
			 */
			NodeList list = doc.getElementsByTagName("id"); // 7tet kol ids fe list
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp); // el item bta3 id el mtlob etht f node
				if (id.equals(node.getTextContent())) {
					Node parentNode = node.getParentNode();
					Element element = (Element) parentNode; // 3mlna casting
					element.getElementsByTagName("username").item(0).setTextContent(userName);
					element.getElementsByTagName("password").item(0).setTextContent(password);
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
			         Transformer transformer = transformerFactory.newTransformer();
			         DOMSource source = new DOMSource(doc);
			         System.out.println("-----------Modified File-----------");
//			         StreamResult consoleResult = new StreamResult(System.out);
			         FileOutputStream output = new FileOutputStream(FILENAME);
			        StreamResult result = new StreamResult(output);

			         transformer.transform(source, result);
				}

			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws TransformerException {
//		String type = new XMLOperation().validateUser("ahmed", "1234");
		// String type = new XMLOperation().validateUser("ahmede", "1234");
//		System.out.println("type = " + type);
		updateUser("1", "NEW", "pass");
	}
	
}
