package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
 import colibristudio.annotations.JCOLIBRIAttributeType;


public class Recipe implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.String ingredients;


	public java.lang.String getIngredients()
		{
			return ingredients;
		}
	public void setIngredients(java.lang.String ingredients0)
		{
			this.ingredients = ingredients0;
		}

	private java.lang.Integer recipeId;


	public java.lang.Integer getRecipeId()
		{
			return recipeId;
		}
	public void setRecipeId(java.lang.Integer recipeId1)
		{
			this.recipeId = recipeId1;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("recipeId",this.getClass());
		} 

	public String toString()		{
			return "["+ ingredients + " , " + recipeId +"]";
		}

}
