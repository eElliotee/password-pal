package main;

import javafx.fxml.FXML;

/**
 * Base controller providing shared navigation functionality for all screens.
 */
public class Controller {
	/**
     * Method to handle back button clicked event.
     */
    public void backButtonClicked() {
        ScreenSwitcher.switchScreen("/main/Comp1050Project.fxml", new MainController());
    }
    
    /**
     * Method to switch to another screen.
     * @param fxmlPath The path to the FXML file representing the new screen
     * @param controller The controller associated with the new screen
     */
    public void switchScreen(String fxmlPath, Object controller) {
        ScreenSwitcher.switchScreen(fxmlPath, controller);
    }
}

