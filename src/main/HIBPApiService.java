package main;

import io.github.cdimascio.dotenv.Dotenv;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Service class for interacting with the Have I Been Pwned (HIBP) API.
 * Checks for data breaches associated with a given email address.
 *
 * Requires a HIBP API key stored in a .env file at the project root:
 *   HIBP_API_KEY=your_key_here
 * Get a key at https://haveibeenpwned.com/API/Key
 */
public class HIBPApiService {

    private static final String API_KEY;
    private static final String EMAIL_CHECK_URL = "https://haveibeenpwned.com/api/v3/breachedaccount/";

    static {
        // Loads from .env file if present, falls back to system environment variables
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        API_KEY = dotenv.get("HIBP_API_KEY", "");
    }

    /**
     * Fetches breach information for the specified email address from the HIBP API.
     *
     * @param email The email address to check for breaches.
     * @return A list of Breach objects representing each breach the email was found in.
     *         Returns an empty list if no breaches are found or if an error occurs.
     */
    public static List<Breach> getBreachesForEmail(String email) {
        List<Breach> breaches = new ArrayList<>();

        if (API_KEY.isEmpty()) {
            System.err.println("HIBP_API_KEY not set. Add it to a .env file in the project root.");
            return breaches;
        }

        HttpClient client = HttpClient.newHttpClient();

        // truncateResponse=false returns full breach details, not just names
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EMAIL_CHECK_URL + email + "?truncateResponse=false"))
                .header("hibp-api-key", API_KEY)
                .header("User-Agent", "PasswordPal-Java-App")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray breachesArray = new JSONArray(response.body());
                for (int i = 0; i < breachesArray.length(); i++) {
                    JSONObject breachObj = breachesArray.getJSONObject(i);
                    Breach breach = new Breach(
                        breachObj.getString("Name"),
                        breachObj.optString("Title", "N/A"),
                        breachObj.optString("Domain", "N/A"),
                        breachObj.optString("BreachDate", "N/A"),
                        breachObj.optLong("PwnCount", 0),
                        breachObj.optString("Description", "N/A")
                    );
                    breaches.add(breach);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return breaches;
    }
}
