package cbr;

import java.util.Collection;

import javax.annotation.Generated;

import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.selection.SelectCases;
import representation.Recipe;

public class CBRApplication implements StandardCBRApplication {

	@Generated(value = { "ColibriStudio" })
	Connector connector;
	
	@Generated(value = { "ColibriStudio" })
	CBRCaseBase casebase;



	//******************************************************************/
	// Configuration
	//******************************************************************/

	@Override
	public void configure() throws ExecutionException {
		try{
			configureConnector();
			configureCaseBase();
		} catch (Exception e){
			throw new ExecutionException(e);
		}
	}

	/** Configures the connector */
	@Generated(value = { "CS-PTConector" })	
	private void configureConnector() throws InitializingException{
		connector = new jcolibri.connector.PlainTextConnector();
		connector.initFromXMLfile(jcolibri.util.FileIO
				.findFile("config/plainTextConnectorConfig.xml"));
	}

	/** Configures the case base */
	@Generated(value = { "CS-CaseManager" })	
	private void configureCaseBase() throws InitializingException{
		casebase = new jcolibri.casebase.LinearCaseBase();
	}

	//******************************************************************/
	// Similarity
	//******************************************************************/
	
	/** Configures the similarity */
	@Generated(value = { "CS-Similarity" })	
	private NNConfig getSimilarityConfig() {
		NNConfig simConfig = new NNConfig();
		simConfig
				.setDescriptionSimFunction(new jcolibri.method.retrieve.NNretrieval.similarity.global.Average());
		Attribute attribute0 = new Attribute("ingredients", Recipe.class);
		simConfig
				.addMapping(
						attribute0,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.Equal());
		simConfig.setWeight(attribute0, 1.00);
		Attribute attribute1 = new Attribute("recipeId", Recipe.class);
		simConfig
				.addMapping(
						attribute1,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.Equal());
		simConfig.setWeight(attribute1, 0.00);
		return simConfig;
	}

	//******************************************************************/
	// Methods
	//******************************************************************/
	

	@Generated(value = { "ColibriStudio" })
	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		casebase.init(connector);
		return casebase;
	}
		
	@Generated(value = { "ColibriStudio" })	
	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		NNConfig simConfig = getSimilarityConfig();
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
				casebase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);
		System.out.println("Retrieved cases:");
		for (RetrievalResult nse : eval)
			System.out.println(nse);
	}

	@Generated(value = { "ColibriStudio" })
	@Override
	public void postCycle() throws ExecutionException {
		connector.close();
	}

	/**
	 * Main method
	 */
	@Generated(value = { "ColibriStudio" })
	public static void main(String[] args) {
		CBRApplication cbrApp = new CBRApplication();
		try {
			cbrApp.configure();
			cbrApp.preCycle();
			CBRQuery query = new CBRQuery();
			query.setDescription(new Recipe());
			jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod.obtainQueryWithoutInitialValues(query, null, null);
			cbrApp.cycle(query);
			cbrApp.postCycle();
//			CBRCaseBase caseBase = cbrApp.preCycle();
//			
//			for(CBRCase c: caseBase.getCases())
//				System.out.println(c);
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
}