package nus.iss.cbr29.pt7.cocktail.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Recipe for UI display.
 */
public class Recipe {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty ingredients;
    private final StringProperty adaptedIngredients;
    private final StringProperty steps;
    private final DoubleProperty similarityScore; 

    /**
     * Constructor with some initial data.
     * 
     */
    public Recipe(String id, String title, String ingredients, String adaptedIngredients, String steps, Double similarityScore) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.ingredients = new SimpleStringProperty(ingredients);
        this.adaptedIngredients = new SimpleStringProperty(adaptedIngredients);
        this.steps = new SimpleStringProperty(steps);
        this.similarityScore = new SimpleDoubleProperty(similarityScore);
    }

    public String getId() {
        return id.get();
    }

    public void setFirstName(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getIngredients() {
        return ingredients.get();
    }

    public void setIngredients(String ingredients) {
        this.ingredients.set(ingredients);
    }

    public StringProperty ingredientsProperty() {
        return ingredients;
    }
    
    public String getAdaptedIngredients(){
    	return adaptedIngredients.get();
    }
    
    public void setAdaptedIngredients(String value){
    	this.adaptedIngredients.set(value);
    }
    
    public StringProperty adaptedIngredientsProperty(){
    	return adaptedIngredients;
    }

    public String getSteps() {
        return steps.get();
    }

    public void setSteps(String steps) {
        this.steps.set(steps);
    }

    public StringProperty stepsProperty() {
        return steps;
    }
    
    public Double getSimilarityScore() {
        return similarityScore.get();
    }

    public void setSimilarityScore(Double similarityScore) {
        this.similarityScore.set(similarityScore);
    }

    public DoubleProperty similarityScoreProperty() {
        return similarityScore;
    }
}