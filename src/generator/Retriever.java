package generator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import commonUtils.Constants;
import de.dfki.mycbr.core.DefaultCaseBase;
import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.casebase.Attribute;
import de.dfki.mycbr.core.casebase.Instance;
import de.dfki.mycbr.core.casebase.MultipleAttribute;
import de.dfki.mycbr.core.model.AttributeDesc;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.core.model.IntegerDesc;
import de.dfki.mycbr.core.model.StringDesc;
import de.dfki.mycbr.core.model.SymbolDesc;
import de.dfki.mycbr.core.retrieval.Retrieval;
import de.dfki.mycbr.core.retrieval.SequentialRetrieval;
import de.dfki.mycbr.core.similarity.Similarity;
import de.dfki.mycbr.util.Pair;


public class Retriever {
	private CBREngine _engine;
	private Project _proj;
	private DefaultCaseBase _caseBase;
	private Concept _concept;

	private SymbolDesc alcoholDesc;
	private SymbolDesc nonalcoholDesc;
	private SymbolDesc flavourDesc;
	private SymbolDesc fruitDesc;
	private SymbolDesc chilledDesc;
	private SymbolDesc degreeDesc;
	private IntegerDesc noOfLiquidDesc;
	private IntegerDesc recipeIdDesc;
	private StringDesc ingredientDesc;
	private StringDesc stepDesc;
	private StringDesc titleDesc;
	
	public Retriever () {
		_engine = new CBREngine();	
		_proj = _engine.createProjectFromPRJ();
		// create case bases and assign the case bases that will be used for submitting a query 
		_caseBase = (DefaultCaseBase)_proj.getCaseBases().get(_engine.getCaseBase());
		// create a concept and get the main concept of the project; 
		_concept = _proj.getConceptByID(_engine.getConceptName());
		
		alcoholDesc = (SymbolDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_ALCOHOL);
		nonalcoholDesc = (SymbolDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_NONALCOHOL);
		flavourDesc = (SymbolDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_FLAVOUR);
		fruitDesc = (SymbolDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_FRUIT);
		chilledDesc = (SymbolDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_CHILLED);
		degreeDesc = (SymbolDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_DEGREE);
		noOfLiquidDesc = (IntegerDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_NOOFLIQUID);
		recipeIdDesc = (IntegerDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_RECIPEID);
		ingredientDesc = (StringDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_INGREDIENT);
		stepDesc = (StringDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_STEP);
		titleDesc = (StringDesc) _concept.getAllAttributeDescs().get(Constants.ATTRIBUTE_NAME_TITLE);
	}

	public CaseModel[] solveQuery(CaseModel input){
		int noOfCases = Constants.NO_OF_CASES_TO_RETRIEVE <= _caseBase.getCases().size() ? Constants.NO_OF_CASES_TO_RETRIEVE : _caseBase.getCases().size();
		List<Pair<Instance, Similarity>> retrievedCases = retrieveCases(input, noOfCases);
		
		Vector<CaseModel> cases = new Vector<>();
		if(retrievedCases.size() > 0){
			for(Pair<Instance, Similarity> c : retrievedCases){
				cases.add(adaptCase(c, input));
			}
		}
		
		return cases.toArray(new CaseModel[cases.size()]);
	}

	private List<Pair<Instance, Similarity>> retrieveCases(CaseModel input, int noOfCases) {
		// create a new retrieval
		Retrieval ret = new Retrieval(_concept, _caseBase);
		SequentialRetrieval sr = new SequentialRetrieval(_proj, ret);
		
		// Construct query
		Instance query = ret.getQueryInstance();
		
		// Set input for alcohol
		LinkedList<Attribute> list = new LinkedList<Attribute>();
		for(String s : input.getAlcohol()){
			list.add(alcoholDesc.getAttribute(s));
		}
		query.addAttribute(alcoholDesc, new MultipleAttribute<SymbolDesc>(alcoholDesc, list));
		
		// Set input for nonalcohol
		list = new LinkedList<Attribute>();
		for(String s : input.getNonalcohol()){
			list.add(nonalcoholDesc.getAttribute(s));
		}
		query.addAttribute(nonalcoholDesc, new MultipleAttribute<SymbolDesc>(nonalcoholDesc, list));
		
		// Set input for flavour
		list = new LinkedList<Attribute>();
		for(String s : input.getFlavour()){
			list.add(flavourDesc.getAttribute(s));
		}
		query.addAttribute(flavourDesc, new MultipleAttribute<SymbolDesc>(flavourDesc, list));
		
		// Set input for fruit
		list = new LinkedList<Attribute>();
		for(String s : input.getFruit()){
			list.add(fruitDesc.getAttribute(s));
		}
		query.addAttribute(fruitDesc, new MultipleAttribute<SymbolDesc>(fruitDesc, list));
		
		// Set input for chilled
		query.addAttribute(chilledDesc, chilledDesc.getAttribute(input.getChilled()));
		// Set input for degree
		query.addAttribute(degreeDesc, degreeDesc.getAttribute(input.getDegree()));
		// Set input for no of liquids
		query.addAttribute(noOfLiquidDesc, noOfLiquidDesc.getIntegerAttribute(input.getNoOfLiquid()));
		
		// perform retrieval
		List<Pair<Instance, Similarity>> result;
		try {
			result = sr.retrieveKSorted(_caseBase, query, noOfCases);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private CaseModel adaptCase(Pair<Instance, Similarity> pair, CaseModel input) {
		HashMap<AttributeDesc, Attribute> attrs = pair.getFirst().getAttributes();
		Similarity sim = pair.getSecond();
		
		System.out.println(pair.getFirst().getName());
		System.out.println(attrs.get(alcoholDesc).getValueAsString());
		System.out.println(attrs.get(recipeIdDesc).getValueAsString());
		System.out.println(attrs.get(titleDesc).getValueAsString());
		System.out.println(sim.getRoundedValue());
		System.out.println();
		return null;
	}
}