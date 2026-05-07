package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



/**
 * A class for switching screens in our JavaFX application.
 */
public class ScreenSwitcher {
	
    private static Stage stage;
    
    
    /**
     * Sets the stage of the application.
     *
     * @param stage you want to switch to
     */
    public static void setStage(Stage stage) {
        ScreenSwitcher.stage = stage;
    }

    /**
     * Switches the screen to the one specified by the FXML file path and controller.
     *
     * @param fxmlPath   The path to the FXML file representing the new screen
     * @param controller The controller associated with the new screen
     */
    public static void switchScreen(String fxmlPath, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(fxmlPath));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Failed to load screen: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
