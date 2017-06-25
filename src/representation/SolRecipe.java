package representation;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
 import colibristudio.annotations.JCOLIBRIAttributeType;


public class SolRecipe implements CaseComponent { 


	/* Generated Class. Please Do Not Modify... */ 


	private java.lang.Integer solId;


	public java.lang.Integer getSolId()
		{
			return solId;
		}
	public void setSolId(java.lang.Integer solId2)
		{
			this.solId = solId2;
		}

	private java.lang.String solTitle;


	public java.lang.String getSolTitle()
		{
			return solTitle;
		}
	public void setSolTitle(java.lang.String solTitle3)
		{
			this.solTitle = solTitle3;
		}

	private java.lang.String solIngredients;


	public java.lang.String getSolIngredients()
		{
			return solIngredients;
		}
	public void setSolIngredients(java.lang.String solIngredients4)
		{
			this.solIngredients = solIngredients4;
		}

	private java.lang.String solSteps;


	public java.lang.String getSolSteps()
		{
			return solSteps;
		}
	public void setSolSteps(java.lang.String solSteps5)
		{
			this.solSteps = solSteps5;
		}


	@Override
	public Attribute getIdAttribute()
		{
			return new Attribute("solId",this.getClass());
		} 

	public String toString()		{
			return "["+ solId + " , " + solSteps + " , " + solIngredients + " , " + solTitle +"]";
		}

}
