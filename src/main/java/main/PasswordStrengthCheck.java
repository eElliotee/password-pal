package main;

/**
 * Utility class for checking password strength against NIST SP 800-63B guidelines.
 *
 * Criteria checked:
 *   - Length between 8 and 64 characters
 *   - At least one uppercase letter
 *   - At least one lowercase letter
 *   - At least one digit
 */
public class PasswordStrengthCheck {

    /**
     * Evaluates the strength of the given password.
     *
     * @param password The password string to evaluate.
     * @return A message stating whether the password meets NIST standards,
     *         or a description of which criteria it fails.
     */
    public static String checkStrength(String password) {
        if (password.isEmpty()) {
            return "Please enter a password.";
        }

        boolean hasUpperCase   = false;
        boolean hasLowerCase   = false;
        boolean hasNumber      = false;
        boolean validLength    = password.length() >= 8 && password.length() <= 64;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch))          hasNumber    = true;
            else if (Character.isUpperCase(ch)) hasUpperCase = true;
            else if (Character.isLowerCase(ch)) hasLowerCase = true;
        }

        if (validLength && hasNumber && hasUpperCase && hasLowerCase) {
            return "Your password meets NIST standards.";
        }

        StringBuilder feedback = new StringBuilder("Your password does not meet NIST standards:");
        if (!validLength)    feedback.append("\n• Must be between 8 and 64 characters");
        if (!hasNumber)      feedback.append("\n• Must contain at least one number");
        if (!hasUpperCase)   feedback.append("\n• Must contain at least one uppercase letter");
        if (!hasLowerCase)   feedback.append("\n• Must contain at least one lowercase letter");

        return feedback.toString();
    }
}
