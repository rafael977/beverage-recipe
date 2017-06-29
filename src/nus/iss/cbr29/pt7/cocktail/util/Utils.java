package nus.iss.cbr29.pt7.cocktail.util;

import java.io.*;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nus.iss.cbr29.pt7.cocktail.model.IngredientItem;

public class Utils {
	public static ObservableList<IngredientItem> extractIngredientListFromXml(String filePath){
		try {
			File file = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			NodeList nl = doc.getElementsByTagName(Constants.INGREDIENT_CONFIG_ATTRIBUTE__TAG);
			
			ObservableList<IngredientItem> list = FXCollections.observableArrayList();
			for(int i=0; i<nl.getLength(); i++){
				Node item = nl.item(i);
				list.add(new IngredientItem(
						item.getAttributes().getNamedItem(Constants.INGREDIENT_CONFIG_ATTRIBUTE_NAME).getNodeValue(), 
						item.getAttributes().getNamedItem(Constants.INGREDIENT_CONFIG_ATTRIBUTE_VALUE).getNodeValue()
						));
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
	
	public static void addDefaultValueIfEmpty(List<String> list){
		if(list == null || list.size() == 0){
			list.add(Constants.INGREDIENT_NONSELECT_DEFAULT);
		}
	}
}
