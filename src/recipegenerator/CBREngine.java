package recipegenerator;

import de.dfki.mycbr.core.Project;
import de.dfki.mycbr.core.model.Concept;
import de.dfki.mycbr.io.CSVImporter;


/**
 *  @author the cbr team 
 */

public class CBREngine {

	// set path to myCBR projects	
	//private static String data_path = "C:\\Users\\bach\\IIS\\dev\\workspace\\myCBRWebDemo\\project\\";
	public static String data_path = "D:\\Home\\Download\\CBR_Project_RecipeRecommender\\Recipe\\";  
	/* project specific: NewExampleProject*/
	// name of the project file
	private static String projectName = "new.prj";
	// name of the central concept 
	private static String conceptName = "recipe";
	
	// name of the case base that should be used; the default name in myCBR is CB_csvImport
	private static String casebase = "NEW CASE BASE";
	// Getter for the Project meta data
	public static String getCaseBase() {
		return casebase;
	}

	public static void setCasebase(String casebase) {
		CBREngine.casebase = casebase;
	}

	public static String getProjectName() {
		return projectName;
	}	

	public static void setProjectName(String projectName) {
		CBREngine.projectName = projectName;
	}

	public static String getConceptName() {
		return conceptName;
	}

	public static void setConceptName(String conceptName) {
		CBREngine.conceptName = conceptName;
	}

	/**
	 * This methods creates a myCBR project and loads the project from a .prj file
	 */	
	public Project createProjectFromPRJ(){

		System.out.println("Trying to load prj file with : "+data_path+ " "+projectName+" "+conceptName+" "); 

		Project project = null;

		try{

			project = new Project(data_path + projectName);
			//project.addSpecialValue(Project.UNDEFINED_SPECIAL_ATTRIBUTE );
			// Sehr wichtig hier das Warten einzubauen, sonst gibts leere 
			// Retrieval Results, weil die Faelle noch nicht geladen sind wenn das 
			// Erste Retrieval laueft		
			while (project.isImporting()){
				Thread.sleep(1000);
				System.out.print(".");
			}		
			System.out.print("\n");	//console pretty print			
		}
		catch(Exception ex){

			System.out.println("Failed:createProjectFromPRJ");
		}		
		return project;		
	}	

}