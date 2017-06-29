package nus.iss.cbr29.pt7.cocktail.model;

public class IngredientItem {
	private String _name;
	private String _value;
	
	public IngredientItem(String name, String value){
		_name = name;
		_value = value;
	}
	
	public void setName(String name){
		_name = name;
	}
	public String getName(){
		return _name;
	}
	
	public void setValue(String value){
		_value = value;
	}
	public String getValue(){
		return _value;
	}
	
	public String toString(){
		return _name;
	}
}
