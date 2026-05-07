package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
/**
 * Controller class for the password generator screen.
 */
public class PasswordGeneratorScreenController extends Controller {
    @FXML
    private CheckBox upperCheck;
    @FXML
    private CheckBox lowerCheck;
    @FXML
    private CheckBox numberCheck;
    @FXML
    private CheckBox specialCharCheck;
    @FXML
    private TextField lengthTextField;
    @FXML
    private Label generatedPasswordLabel;
    @FXML
    private Button backButtonPassGen;
    
    private String password = ""; // String to store the generated password


    /**
     * Generate password button click event.
     */
    @FXML
    private void generatePasswordButtonClicked() {
        boolean wantsUpperCase = upperCheck.isSelected();
        boolean wantsLowerCase = lowerCheck.isSelected();
        boolean wantsNumbers = numberCheck.isSelected();
        boolean wantsSpecialCharacters = specialCharCheck.isSelected();

        // Validate length input
        int length;
        try {
            length = Integer.parseInt(lengthTextField.getText());
            if (length < 1) {
                generatedPasswordLabel.setText("Please enter a length of at least 1.");
                return;
            }
        } catch (NumberFormatException e) {
            generatedPasswordLabel.setText("Please enter a valid number for the length.");
            return;
        }

        // Require at least one character type to be selected
        if (!wantsUpperCase && !wantsLowerCase && !wantsNumbers && !wantsSpecialCharacters) {
            generatedPasswordLabel.setText("Please select at least one character type.");
            return;
        }

        password = PasswordGenerator.generatePassword(wantsUpperCase, wantsLowerCase,
                wantsNumbers, wantsSpecialCharacters, length);
        generatedPasswordLabel.setText(password);
    }

    /**
     * Back button click event.
     */
    
    
    /**
     * Copy to clipboard button click event.
     * Copies the generated password to the clipboard.
     */
    @FXML
    private void copyToClipboardButtonClicked() {
    	Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(password);
        clipboard.setContent(content);
    }
}


