package main;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;



/**
 * Controller class for the email breaches screen.
 */
public class EmailBreachesScreenController extends Controller {

    @FXML
    private TextField emailBreachesTextField;

    @FXML
    private Label emailBreachesLabel;
    
    private List<Breach> breaches; // List to store breaches obtained from API
    private int currentBreachIndex; //index to keep track of the current breach being displayed

    @FXML
    private Button generateBreachButton;

    @FXML
    private Button nextBreachButton;

    @FXML
    private void initialize() {
        currentBreachIndex = 0;
    }

    @FXML
    private void emailBreachesButtonClicked() {
        // Get the user to input email
        String email = emailBreachesTextField.getText();
        
        
        //calls the method that calls the api 
        breaches = HIBPApiService.getBreachesForEmail(email);

        
        // If breaches are found, display the first breach; otherwise, show a message indicating no breaches were found
        if (!breaches.isEmpty()) {
            displayBreach();
        } else {
            emailBreachesLabel.setText("No breaches found for this email.");
        }
    }
    
    
    /**
     * Next breach button click event.
     */
    @FXML
    private void nextBreachButtonClicked() {
        try {
            // If breaches are available, cycle to the next breach and display it
            if (!breaches.isEmpty()) {
                currentBreachIndex = (currentBreachIndex + 1) % breaches.size();
                displayBreach();
            }
        } catch (NullPointerException e) {
            // Handle the case where breaches are null
            // This occurs when the "Next Breach" button is clicked before generating any breaches
            emailBreachesLabel.setText("No breaches have been generated yet.");
        }
    }


    /**
     * Displays the breach information on the label.
     */
    private void displayBreach() {
        Breach currentBreach = breaches.get(currentBreachIndex);
        String currentBreachText = String.format("Name: %s%nDate: %s%n# of users affected: %d%nDescription: %s",currentBreach.getName(),currentBreach.getBreachDate(), 
        currentBreach.getPwnCount(),currentBreach.getDescription());
        emailBreachesLabel.setText(currentBreachText);
    }
    
  
}
