package nus.iss.cbr29.pt7.cocktail;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nus.iss.cbr29.pt7.cocktail.util.Constants;
import nus.iss.cbr29.pt7.cocktail.view.SystemOverviewController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<String> alcoholList;
    private ObservableList<String> nonAlcoholList;
    private ObservableList<String> fruitList;
    private ObservableList<String> flavourList;

    public ObservableList<String> getAlcoholList() {
        return alcoholList;
    }
    
    public ObservableList<String> getNonAlcoholList() {
        return nonAlcoholList;
    }
    
    public ObservableList<String> getFruitList() {
        return fruitList;
    }
    
    public ObservableList<String> getFlavourList() {
        return flavourList;
    }
    
    /**
     * Constructor
     */
    public MainApp() {

    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Constants.WINDOW_TITLE);

        initRootLayout();

        showSystemOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the system overview inside the root layout.
     */
    public void showSystemOverview() {
        try {
            // Load system overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SystemOverview.fxml"));
            AnchorPane systemOverview = (AnchorPane) loader.load();

            // Set system overview into the center of root layout.
            rootLayout.setCenter(systemOverview);
            
            // Give the controller access to the main app.
            SystemOverviewController controller = loader.getController();
            controller.setMainApp();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}