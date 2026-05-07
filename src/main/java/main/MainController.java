package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main controller class responsible for initializing the application and handling navigation between screens.
 */
public class MainController extends Application implements Initializable {

    @FXML
    private Button emailBreachesButton;

    @FXML
    private Text title;

    @FXML
    private Button passwordGeneratorButton;

    @FXML
    private Button passwordStrengthCheckButton;
    
    @FXML
    private Button aboutScreenButton;

    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Set the stage in ScreenSwitcher
        ScreenSwitcher.setStage(primaryStage);
        
        // Now load the initial scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Comp1050Project.fxml"));
        loader.setController(this); // Set this class as the controller
        Pane pane = loader.load(); // Load the layout from FXML
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Optional, depending on your needs
        primaryStage.show();
    }

    /**
     * Initializes the controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailBreachesButton.setOnAction(event -> loadFXML("/main/EmailBreachesScreen.fxml", new EmailBreachesScreenController()));
        passwordGeneratorButton.setOnAction(event -> loadFXML("/main/PasswordGeneratorScreen.fxml", new PasswordGeneratorScreenController()));
        passwordStrengthCheckButton.setOnAction(event -> loadFXML("/main/PasswordStrengthCheckScreen.fxml", new PasswordStrengthCheckScreenController()));
        aboutScreenButton.setOnAction(event -> loadFXML("/main/AboutScreen.fxml", new AboutScreenController()));
    }

    /**
     * Loads the specified FXML file with the given controller.
     *
     * @param fxmlPath  The path to the FXML file
     * @param controller The controller associated with the FXML file
     */
    private void loadFXML(String fxmlPath, Object controller) {
        // Use ScreenSwitcher to switch screens
        ScreenSwitcher.switchScreen(fxmlPath, controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
