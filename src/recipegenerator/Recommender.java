package recipegenerator;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;

import de.dfki.mycbr.core.DefaultCaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.model.AttributeDesc;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.FloatDesc;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.retrieval.Retrieval.RetrievalMethod;
import de.dfki.mycbr.core.similarity.AmalgamationFct;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;


/**
 *  @author the cbr team 
 */

public class Recommender {

	public CBREngine engine;
	public Project rec;
	public DefaultCaseBase cb;
	public Concept myConcept;

	
	public void loadengine () {

		engine = new CBREngine();	
		rec = engine.createProjectFromPRJ();
		// create case bases and assign the case bases that will be used for submitting a query 
		cb = (DefaultCaseBase)rec.getCaseBases().get(engine.getCaseBase());
		// create a concept and get the main concept of the project; 
		myConcept = rec.getConceptByID(engine.getConceptName());	
	}

	public Object[][] solveOuery(String vegetable, String dishType, String staple, String meat, String seafood, 
			String fruit, String vegetarian, String typeOfCuisine, String flavour,Integer difficultyLevel,Integer cookTime, Integer numberofcases) {
	
		if(staple.equals("")) {
			staple = "_unknown_";
		}
		if(vegetable.equals("")) {
			vegetable = "_unknown_";
		}
		if(meat.equals("")) {
			meat = "_unknown_";
		}
		if(seafood.equals("")) {
			seafood = "_unknown_";
		}
		if(fruit.equals("")) {
			fruit = "_unknown_";
		}
		
		String answer="";		
		// create a new retrieval
		Retrieval ret = new Retrieval(myConcept, cb);
		// specify the retrieval method
		ret.setRetrievalMethod(RetrievalMethod.RETRIEVE_SORTED);		
		// create a query instance
		Instance query = ret.getQueryInstance();	
		
		
		// Insert VEGETARIAN OR NOT into the query: Symbolic Description
		SymbolDesc vegetarianDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("VEGETARIAN OR NOT");
		query.addAttribute(vegetarianDesc,vegetarianDesc.getAttribute(vegetarian));
			
		// Insert DISH TYPE into the query: Symbolic Description
		SymbolDesc dishTypeDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("DISH TYPE");
		query.addAttribute(dishTypeDesc,dishTypeDesc.getAttribute(dishType));
		
		// Insert TYPE OF CUISINE into the query: Symbolic Description
		SymbolDesc typeOfCuisineDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("TYPE OF CUISINE");
		query.addAttribute(typeOfCuisineDesc,typeOfCuisineDesc.getAttribute(typeOfCuisine));
		
		// Insert FLAVOUR into the query: Symbolic Description
		SymbolDesc flavourDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("FLAVOUR");
		query.addAttribute(flavourDesc,flavourDesc.getAttribute(flavour));
		
		
		// Insert DIFFICULTY LEVEL into the query: Integer Description
		IntegerDesc difficultyLevelDesc = (IntegerDesc) myConcept.getAllAttributeDescs().get("DIFFICULTY LEVEL");
		try {
			query.addAttribute(difficultyLevelDesc,difficultyLevelDesc.getAttribute(difficultyLevel));
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		// Insert COOK TIME into the query: Integer Description
		IntegerDesc cookTimeDesc = (IntegerDesc) myConcept.getAllAttributeDescs().get("COOK TIME");
		try {
			query.addAttribute(cookTimeDesc,cookTimeDesc.getAttribute(cookTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		
		// Insert STAPLE FOOD into the query: Symbolic Description
		SymbolDesc stapleDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("STAPLE FOOD");
		query.addAttribute(stapleDesc,stapleDesc.getAttribute(staple));
			
		
		// Insert VEGETABLE into the query: Symbolic Description
		SymbolDesc vegetableDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("VEGETABLE");
		query.addAttribute(vegetableDesc,vegetableDesc.getAttribute(vegetable));
	
		// Insert MEAT into the query: Symbolic Description
		SymbolDesc meatDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("MEAT");
		query.addAttribute(meatDesc,meatDesc.getAttribute(meat));
		
		// Insert SEAFOOD into the query: Symbolic Description
		SymbolDesc seafoodDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("SEAFOOD");
		query.addAttribute(seafoodDesc,seafoodDesc.getAttribute(seafood));
			
		// Insert FRUIT into the query: Symbolic Description
		SymbolDesc fruitDesc = (SymbolDesc) myConcept.getAllAttributeDescs().get("FRUIT");
		query.addAttribute(fruitDesc,fruitDesc.getAttribute(fruit));
		
		

		// perform retrieval
		ret.start();	
		// get the retrieval result
		List <Pair<Instance, Similarity>> result = ret.getResult(); 
		// get the case name	
		if(result.size()>0){

			// get the best case's name
			String casename = result.get(0).getFirst().getName();	
			// get the similarity value
			Double sim = result.get(0).getSecond().getValue();		
			//answer = "I found "+casename+" with a similarity of "+sim+" as the best match.";
			//answer = answer+"The "+numberofcases+" best cases shown in a table: <br /> <br /> <table border=\"1\">";	
			ArrayList<Hashtable<String, String>> liste = new ArrayList<Hashtable<String, String>>();	
			// if more case results are requested than we have in our case base at all:
			if(numberofcases>=cb.getCases().size()){numberofcases = cb.getCases().size();}

			Object[][] tableData =  new Object[numberofcases][12];
			
			for(int i = 0; i<numberofcases; i++){

				liste.add(getAttributes(result.get(i), rec.getConceptByID(engine.getConceptName())));
				
				System.out.println("liste "+liste.get(i).toString());
		
				//answer=answer+"<tr><td>"+result.get(i).getFirst().getName()
						//+"</td><td>"+liste.get(i).get("FOOD NAME").toString()+"</td></tr>";
				
				tableData[i][0] = result.get(i).getFirst().getName();
				tableData[i][1] = liste.get(i).get("FOOD NAME").toString();
				tableData[i][2] = liste.get(i).get("DISH TYPE").toString();
				tableData[i][3] = liste.get(i).get("TYPE OF CUISINE").toString();
				tableData[i][4] = liste.get(i).get("FLAVOUR").toString();
				tableData[i][5] = liste.get(i).get("COOK TIME").toString()+" min";
				tableData[i][6] = liste.get(i).get("Sim").toString();
				
				tableData[i][7] = liste.get(i).get("STAPLE FOOD").toString();
				tableData[i][8] = liste.get(i).get("VEGETABLE").toString();
				tableData[i][9] = liste.get(i).get("MEAT").toString();
				tableData[i][10] = liste.get(i).get("SEAFOOD").toString();
				tableData[i][11] = liste.get(i).get("FRUIT").toString();
				

			}

			//answer= answer+"</table>";
			return tableData;
			
		}	
		else{System.out.println("Retrieval Result is empty");}

		//return answer;
		return null;
	}
	/**
	 * This method delivers a Hashtable which contains the Attributs names (Attributes of the case) combined with their respective values.
	 * @author weber,koehler,namuth
	 * @param r = An Instance.
	 * @param concept = A Concept
	 * @return List = List containing the Attributes of a case with their values.
	 */
	public static Hashtable<String, String> getAttributes(Pair<Instance, Similarity> r, Concept concept) {

		Hashtable<String, String> table = new Hashtable<String, String>();
		ArrayList<String> cats = getCategories(r);
		// Add the similarity of the case
		table.put("Sim", String.valueOf(r.getSecond().getValue()));
		for (String cat : cats) {
			// Add the Attribute name and its value into the Hashtable
			table.put(cat, r.getFirst().getAttForDesc(concept.getAllAttributeDescs().get(cat)).getValueAsString());
		}
		return table;
	}
	/**
	 * This Method generates an ArrayList, which contains all Categories of aa Concept.
	 * @author weber,koehler,namuth
	 * @param r  =  An Instance.
	 * @return List = List containing the Attributes names.
	 */
	public static ArrayList<String> getCategories(Pair<Instance, Similarity> r) {

		ArrayList<String> cats = new ArrayList<String>();

		// Read all Attributes of a Concept
		Set<AttributeDesc> catlist = r.getFirst().getAttributes().keySet();

		for (AttributeDesc cat : catlist) {
			if (cat != null) {
				// Add the String literals for each Attribute into the ArrayList
				cats.add(cat.getName());
			}
		}
		return cats;
	}

	public String displayAmalgamationFunctions() {

		ArrayList <String> amalgam = new ArrayList<String>(); 
		String listoffunctions="Currently available Amalgamationfunctions: <br /> <br />";		 
		AmalgamationFct current = myConcept.getActiveAmalgamFct();		 
		System.out.println("Amalgamation Function is used = "+current.getName());		 
		List<AmalgamationFct> liste = myConcept.getAvailableAmalgamFcts();

		for (int i = 0; i < liste.size(); i++){
			System.out.println(liste.get(i).getName());
			listoffunctions=listoffunctions+liste.get(i).getName()+"<br />";			 
		}

		listoffunctions=listoffunctions+(" <br /> <br /> Currently selected Amalgamationfunction: "+current.getName()+"\n");
		listoffunctions=listoffunctions+(" <br /> <br /> Please type the name of the Amalgamationfunction to use in the " +
				" Field \"Amalgamationfunction\" it will be automatically used during the next retrieval");
		System.out.println(listoffunctions);	 
		return listoffunctions;		 
	}	 
}