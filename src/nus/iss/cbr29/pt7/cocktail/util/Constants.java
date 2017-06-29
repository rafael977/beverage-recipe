package nus.iss.cbr29.pt7.cocktail.util;

public class Constants {
	public static final String WINDOW_TITLE = "Cocktail Recipe Generator";
	
	public static final String INGREDIENT_CONFIG_ATTRIBUTE__TAG = "item";
	public static final String INGREDIENT_CONFIG_ATTRIBUTE_NAME = "name";
	public static final String INGREDIENT_CONFIG_ATTRIBUTE_VALUE = "value";
	
	public static final String XML_PATH_ALCOHOLICLIQUEUR = "config\\AlcoholicLiqueurList.xml";
	public static final String XML_PATH_NONALCOHOLICLIQUEUR = "config\\NonalcoholicLiqueurList.xml";
	public static final String XML_PATH_FRUIT = "config\\FruitList.xml";
	public static final String XML_PATH_FLAVOUR = "config\\FlavourList.xml";
	
	public static final int LIST_VISIBLE_COUNT = 5;
	
	public static final String ATTRIBUTE_NAME_ALCOHOL = "Alcohol";
	public static final String ATTRIBUTE_NAME_CHILLED = "Chilled";
	public static final String ATTRIBUTE_NAME_DEGREE = "Degree";
	public static final String ATTRIBUTE_NAME_FLAVOUR = "Flavour";
	public static final String ATTRIBUTE_NAME_FRUIT = "Fruit";
	public static final String ATTRIBUTE_NAME_INGREDIENT = "Ingredient";
	public static final String ATTRIBUTE_NAME_NOOFLIQUID = "NoOfLiquid";
	public static final String ATTRIBUTE_NAME_NONALCOHOL = "NonAlcohol";
	public static final String ATTRIBUTE_NAME_RECIPEID = "RecipeId";
	public static final String ATTRIBUTE_NAME_STEP = "Step";
	public static final String ATTRIBUTE_NAME_TITLE = "Title";
	
	public static final int NO_OF_CASES_TO_RETRIEVE = 5;
	
	public static final String FUNCNAME_ALCOHOL = "AlcoholFunct";
	public static final String FUNCNAME_NONALCOHOL = "NonAlcohol";
	public static final String FUNCNAME_FRUIT = "FruitFunct";
	public static final String FUNCNAME_FLAVOUR = "FlavourFunct";
	
	public static final double TH_ALCOHOL = 0.75d;
	public static final double TH_NONALCOHOL = 0.67d;
	public static final double TH_FRUIT = 0.67d;
	public static final double TH_FLAVOUR = 0.67d;
	
	public static final String INGREDIENT_NONSELECT_DEFAULT = "BLANK";
	
	public static final String PREFERRED_TEXT_TEMPLATE = String.join("\n", "Alcohol: %s",
			"Non Alcohol: %s",
			"Fruit: %s",
			"Flavor: %s",
			"No. of liquids: %s",
			"Chilled: %s",
			"ABV: %s");
	public static final String NO_SELECT_TEXT = "No selection";
	
	public static final String USERDATA_RADIO_CHILLED_YES = "yes";
	public static final String USERDATA_RADIO_CHILLED_NO = "no";
	public static final String USERDATA_RADIO_DEGREE_HIGH = "high";
	public static final String USERDATA_RADIO_DEGREE_LOW = "low";
	public static final String USERDATA_RADIO_DEGREE_NONE = "none";
}