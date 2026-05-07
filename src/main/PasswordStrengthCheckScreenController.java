package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


/**
 * Controller class for the password strength check screen.
 */
public class PasswordStrengthCheckScreenController extends Controller {


    @FXML
    private TextField enterPasswordTextField;

    @FXML
    private Label passwordStrengthLabel;
    

    
    /**
     * Check strength button click event.
     */
    @FXML
    private void checkStrengthButtonClicked() {
    	
        // Get the password entered by the user
    	String password = enterPasswordTextField.getText();

        // Check the strength of the password
        String passwordStrength = PasswordStrengthCheck.checkStrength(password);

        // Set the result of checkStrength to the text of the label
        passwordStrengthLabel.setText(passwordStrength);
    }
    
   

}
