package main;

import java.security.SecureRandom;

/**
 * Utility class for generating random passwords.
 * Uses SecureRandom for cryptographically strong randomness.
 */
public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS   = "0123456789";
    private static final String SPECIAL   = "!@#$%^&*?";

    /**
     * Generates a random password based on the selected character sets and length.
     *
     * @param wantsUpperCase    Include uppercase letters.
     * @param wantsLowerCase    Include lowercase letters.
     * @param wantsNumbers      Include digits.
     * @param wantsSpecialChars Include special characters.
     * @param length            Desired password length.
     * @return A randomly generated password string.
     */
    public static String generatePassword(boolean wantsUpperCase, boolean wantsLowerCase,
            boolean wantsNumbers, boolean wantsSpecialChars, int length) {

        SecureRandom random = new SecureRandom();
        StringBuilder allChars = new StringBuilder();
        StringBuilder password = new StringBuilder();

        // Build the pool of allowed characters and guarantee at least one of each selected type
        if (wantsUpperCase) {
            allChars.append(UPPERCASE);
            password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        if (wantsLowerCase) {
            allChars.append(LOWERCASE);
            password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        if (wantsNumbers) {
            allChars.append(NUMBERS);
            password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        if (wantsSpecialChars) {
            allChars.append(SPECIAL);
            password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));
        }

        // Fill remaining characters from the combined pool
        String pool = allChars.toString();
        for (int i = password.length(); i < length; i++) {
            password.append(pool.charAt(random.nextInt(pool.length())));
        }

        // Shuffle so the guaranteed characters aren't always at the front
        for (int i = password.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = password.charAt(j);
            password.setCharAt(j, password.charAt(i));
            password.setCharAt(i, temp);
        }

        return password.toString();
    }
}
