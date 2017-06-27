package generator;

import de.dfki.mycbr.core.Project;


/**
 *  @author the cbr team 
 */

public class CBREngine {
	// set path to myCBR projects	
	private static String data_path = System.getProperty("user.dir") + "\\mycbrproj\\";  
	/* project specific: NewExampleProject*/
	// name of the project file
	private static String projectName = "project_recipe.prj";
	// name of the central concept 
	private static String conceptName = "recipe";
	// name of the case base that should be used; the default name in myCBR is CB_csvImport
	private static String casebase = "rcb";
	
	// Getter for the Project meta data
	public String getCaseBase() {
		return casebase;
	}

	public void setCasebase(String casebase) {
		CBREngine.casebase = casebase;
	}

	public String getProjectName() {
		return projectName;
	}	

	public void setProjectName(String projectName) {
		CBREngine.projectName = projectName;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
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
			ex.printStackTrace();
			System.out.println("Failed:createProjectFromPRJ");
		}		
		return project;		
	}	

}