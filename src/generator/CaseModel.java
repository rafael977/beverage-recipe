package generator;

public class CaseModel {
	private int _recipeId;
	private String[] _alcohol;
	private String[] _nonalcohol;
	private String[] _fruit;
	private String[] _flavour;
	private Boolean _chilled;
	private String _degree;
	private int _noOfLiquid;
	private String _title;
	private String _ingredient;
	private String _step;
	private float _sim;
	
	public CaseModel (String[] alcohol, String[] nonalcohol, String[] fruit, String[] flavour, Boolean chilled, String degree, int noOfLiquid) {
		_alcohol = alcohol;
		_nonalcohol = nonalcohol;
		_fruit = fruit;
		_flavour = flavour;
		_chilled = chilled;
		_degree = degree;
		_noOfLiquid = noOfLiquid;
	}
	
	public CaseModel (int recipeId, String[] alcohol, String[] nonalcohol, String[] fruit, String[] flavour, Boolean chilled, String degree, int noOfLiquid,
			String title, String ingredient, String step, float sim) {
		_recipeId = recipeId;
		_alcohol = alcohol;
		_nonalcohol = nonalcohol;
		_fruit = fruit;
		_flavour = flavour;
		_chilled = chilled;
		_degree = degree;
		_noOfLiquid = noOfLiquid;
		_title = title;
		_ingredient = ingredient;
		_step = step;
		_sim = sim;
	}
	
	public void setRecipeId(int value){
		_recipeId = value;
	}
	public int getRecipeId(){
		return _recipeId;
	}
	
	public void setAlcohol(String[] value){
		_alcohol = value;
	}
	public String[] getAlcohol(){
		return _alcohol;
	}
	public String getAlcoholSet(){
		return combineString(_alcohol);
	}
	
	public void setNonalcohol(String[] value){
		_nonalcohol = value;
	}
	public String[] getNonalcohol(){
		return _nonalcohol;
	}
	public String getNonalcoholSet(){
		return combineString(_nonalcohol);
	}
	
	public void setFruit(String[] value){
		_fruit = value;
	}
	public String[] getFruit(){
		return _fruit;
	}
	public String getFruitSet(){
		return combineString(_fruit);
	}
	
	public void setFlavour(String[] value){
		_flavour = value;
	}
	public String[] getFlavour(){
		return _flavour;
	}
	public String getFlavourSet(){
		return combineString(_flavour);
	}
	
	public void setChilled(Boolean value){
		_chilled = value;
	}
	public String getChilled(){
		return _chilled ? "true" : "false";
	}
	
	public void setDegree(String value){
		_degree = value;
	}
	public String getDegree(){
		return _degree;
	}
	
	public void setNoOfLiquid(int value){
		_noOfLiquid = value;
	}
	public int getNoOfLiquid(){
		return _noOfLiquid;
	}
	
	public void setTile(String value){
		_title = value;
	}
	public String getTitle(){
		return _title;
	}
	
	public void setIngredient(String value){
		_ingredient = value;
	}
	public String getIngredient(){
		return _ingredient;
	}
	
	public void setStep(String value){
		_step = value;
	}
	public String getStep(){
		return _step;
	}
	
	public void setSim(float value){
		_sim = value;
	}
	public float getSim(){
		return _sim;
	}
	
	private String combineString(String[] set){
		StringBuilder sb = new StringBuilder();
		for(String v : set){
			sb.append(v);
			sb.append(";");
		}
		
		return sb.substring(0, sb.length());
	}
}
