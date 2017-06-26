package commonUtils;

import java.io.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Utils {
	public static String[] extractListFromXml(String filePath){
		try {
			File file = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			NodeList nl = doc.getElementsByTagName(Constants.NAME_TAG);
			int size = nl.getLength();
			String[] list = new String[size];
			for(int i=0; i<size; i++){
				list[i] = nl.item(i).getTextContent();
				System.out.println(list[i]);
			}
			
			return list;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
