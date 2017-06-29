package nus.iss.cbr29.pt7.cocktail.view;

import java.util.List;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import nus.iss.cbr29.pt7.cocktail.generator.CaseModel;
import nus.iss.cbr29.pt7.cocktail.generator.Retriever;
import nus.iss.cbr29.pt7.cocktail.model.IngredientItem;
import nus.iss.cbr29.pt7.cocktail.model.Recipe;
import nus.iss.cbr29.pt7.cocktail.util.Constants;
import nus.iss.cbr29.pt7.cocktail.util.Utils;

public class SystemOverviewController {
	@FXML
    private JFXListView<IngredientItem> listViewAlcohol;
	@FXML
    private JFXListView<IngredientItem> listViewNonAlcohol;
	@FXML
    private JFXListView<IngredientItem> listViewFruit;
	@FXML
    private JFXListView<IngredientItem> listViewFlavour;
	@FXML
    private JFXRadioButton radioChilledYes;
	@FXML
    private JFXRadioButton radioChilledNo;
	@FXML
    private JFXRadioButton radioPercentHigh;
	@FXML
    private JFXRadioButton radioPercentLow;
	@FXML
    private JFXRadioButton radioPercentNone;
	@FXML
    private TextArea txtSelectedIngredients;
    @FXML
    private TableView<Recipe> recipeTable;
    @FXML
    private TableColumn<Recipe, String> idColumn;
    @FXML
    private TableColumn<Recipe, String> titleColumn;
    @FXML
    private TableColumn<Recipe, Number> similarityScoreColumn;
	@FXML
    private TextArea txtOriginalIngredients;
	@FXML
    private TextArea txtOriginalSteps;
	@FXML
    private TextArea txtAdaptedIngredients;
	@FXML
	private JFXTextField txtNoOfLiquids;
	
    ToggleGroup groupChilled = new ToggleGroup();
    ToggleGroup groupPercentage = new ToggleGroup();
    
    private ObservableList<IngredientItem> _alcoholList;
    private ObservableList<IngredientItem> _nonAlcoholList;
    private ObservableList<IngredientItem> _fruitList;
    private ObservableList<IngredientItem> _flavourList;
    
    private Retriever retriever;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public SystemOverviewController() {
    	// Populate data from xml files
		_alcoholList = Utils.extractIngredientListFromXml(Constants.XML_PATH_ALCOHOLICLIQUEUR);
		_nonAlcoholList = Utils.extractIngredientListFromXml(Constants.XML_PATH_NONALCOHOLICLIQUEUR);
		_fruitList = Utils.extractIngredientListFromXml(Constants.XML_PATH_FRUIT);
		_flavourList = Utils.extractIngredientListFromXml(Constants.XML_PATH_FLAVOUR);
		
		retriever = new Retriever();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the table with the three columns.    	
    	listViewAlcohol.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	listViewNonAlcohol.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	listViewFruit.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	listViewFlavour.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	radioChilledYes.selectedProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	radioChilledNo.selectedProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	radioPercentHigh.selectedProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	radioPercentLow.selectedProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	radioPercentNone.selectedProperty().addListener(
                (observable, oldValue, newValue) -> populatePreferredText());
    	
    	recipeTable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> populateSelectedRecipe(newValue));
    	idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    	titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    	similarityScoreColumn.setCellValueFactory(cellData -> cellData.getValue().similarityScoreProperty());
    }

    private void populateSelectedRecipe(Recipe recipe) {
    	if(recipe != null){
        	txtOriginalIngredients.setText(recipe.getIngredients());
        	txtAdaptedIngredients.setText(recipe.getAdaptedIngredients());
        	txtOriginalSteps.setText(recipe.getSteps());
    	}
	}

	/**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp() {
        listViewAlcohol.setItems(_alcoholList);  
        listViewAlcohol.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listViewNonAlcohol.setItems(_nonAlcoholList);
        listViewNonAlcohol.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listViewFruit.setItems(_fruitList);
        listViewFruit.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listViewFlavour.setItems(_flavourList);
        listViewFlavour.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        groupChilled = new ToggleGroup();
        radioChilledYes.setToggleGroup(groupChilled);
        radioChilledYes.setUserData(Constants.USERDATA_RADIO_CHILLED_YES);
        radioChilledNo.setToggleGroup(groupChilled);
        radioChilledNo.setUserData(Constants.USERDATA_RADIO_CHILLED_NO);

        groupPercentage = new ToggleGroup();
        radioPercentHigh.setToggleGroup(groupPercentage);
        radioPercentHigh.setUserData(Constants.USERDATA_RADIO_DEGREE_HIGH);
        radioPercentLow.setToggleGroup(groupPercentage);
        radioPercentLow.setUserData(Constants.USERDATA_RADIO_DEGREE_LOW);
        radioPercentNone.setToggleGroup(groupPercentage);
        radioPercentNone.setUserData(Constants.USERDATA_RADIO_DEGREE_NONE);
        
        reset();
    }
    
    private void populatePreferredText() {
    	String selectedAlcohol = listViewAlcohol.getSelectionModel().getSelectedItems().size() == 0 ?
    			Constants.NO_SELECT_TEXT : String.join(", ", listViewAlcohol.getSelectionModel().getSelectedItems().stream().map(x -> x.getValue()).collect(Collectors.toList()));
    	String selectedNonAlcohol = listViewNonAlcohol.getSelectionModel().getSelectedItems().size() == 0 ?
    			Constants.NO_SELECT_TEXT : String.join(", ", listViewNonAlcohol.getSelectionModel().getSelectedItems().stream().map(x -> x.getValue()).collect(Collectors.toList()));
    	String selectedFruit = listViewFruit.getSelectionModel().getSelectedItems().size() == 0 ?
    			Constants.NO_SELECT_TEXT : String.join(", ", listViewFruit.getSelectionModel().getSelectedItems().stream().map(x -> x.getValue()).collect(Collectors.toList()));
    	String selectedFlavor = listViewFlavour.getSelectionModel().getSelectedItems().size() == 0 ?
    			Constants.NO_SELECT_TEXT : String.join(", ", listViewFlavour.getSelectionModel().getSelectedItems().stream().map(x -> x.getValue()).collect(Collectors.toList()));
    	String noofliquid = txtNoOfLiquids.getText();
    	String chilled = groupChilled.getSelectedToggle() != null ? ((RadioButton)groupChilled.getSelectedToggle()).getText() : Constants.NO_SELECT_TEXT;
    	String degree = groupPercentage.getSelectedToggle() != null ? ((RadioButton)groupPercentage.getSelectedToggle()).getText() : Constants.NO_SELECT_TEXT;
    	
    	String preferredList = String.format(Constants.PREFERRED_TEXT_TEMPLATE, 
    			selectedAlcohol,
    			selectedNonAlcohol,
    			selectedFruit,
    			selectedFlavor,
    			noofliquid,
    			chilled,
    			degree);
    	
    	txtSelectedIngredients.setText(preferredList);
    }
    
    @FXML
    private void retrieveCases() {
    	List<String> selectedAlcohols = listViewAlcohol.getSelectionModel().getSelectedItems()
    			.stream().map(x -> x.getValue()).collect(Collectors.toList());
    	Utils.addDefaultValueIfEmpty(selectedAlcohols);
    	List<String> selectedNonAlcohols = listViewNonAlcohol.getSelectionModel().getSelectedItems()
    			.stream().map(x -> x.getValue()).collect(Collectors.toList());
    	Utils.addDefaultValueIfEmpty(selectedNonAlcohols);
    	List<String> selectedFruits = listViewFruit.getSelectionModel().getSelectedItems()
    			.stream().map(x -> x.getValue()).collect(Collectors.toList());
    	Utils.addDefaultValueIfEmpty(selectedFruits);
    	List<String> selectedFlavours = listViewFlavour.getSelectionModel().getSelectedItems()
    			.stream().map(x -> x.getValue()).collect(Collectors.toList());
    	Utils.addDefaultValueIfEmpty(selectedFlavours);
    	Boolean chilled = radioChilledYes.isSelected() ? true : false;
    	String degree = groupPercentage.getSelectedToggle().getUserData().toString();
    	int noOfLiquids = Integer.parseInt(txtNoOfLiquids.getText());
    	
    	CaseModel query = new CaseModel(selectedAlcohols.toArray(new String[selectedAlcohols.size()]), 
    			selectedNonAlcohols.toArray(new String[selectedNonAlcohols.size()]), 
    			selectedFruits.toArray(new String[selectedFruits.size()]), 
    			selectedFlavours.toArray(new String[selectedFlavours.size()]), 
    			chilled, degree, noOfLiquids);
    	CaseModel[] result = retriever.solveQuery(query);

    	ObservableList<Recipe> retrieveResult = FXCollections.observableArrayList();
    	for(CaseModel c : result){
    		retrieveResult.add(new Recipe(Integer.toString(c.getRecipeId()), c.getTitle(), c.getIngredient(), c.getResultIngredients(), c.getStep(), c.getSim()));
    	}

    	recipeTable.setItems(retrieveResult);
    }
    
    @FXML
    private void reset(){
    	listViewAlcohol.getSelectionModel().clearSelection();
    	listViewNonAlcohol.getSelectionModel().clearSelection();
    	listViewFruit.getSelectionModel().clearSelection();
    	listViewFlavour.getSelectionModel().clearSelection();
    	
    	txtNoOfLiquids.setText("1");
    	groupChilled.selectToggle(radioChilledYes);
    	groupPercentage.selectToggle(radioPercentHigh);
    	
    	txtOriginalIngredients.clear();
    	txtAdaptedIngredients.clear();
    	txtOriginalSteps.clear();
    	recipeTable.getItems().clear();
    }
    
    @FXML
    private void validateNumber(){
    	String value = txtNoOfLiquids.getText();
    	try {
    		Integer.parseInt(value);
		} catch (Exception e) {
			txtNoOfLiquids.setText("1");
		}
    }
}