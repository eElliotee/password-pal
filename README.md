# Password Pal

A JavaFX desktop application for password security. Check if your email has appeared in a data breach, generate strong passwords, and verify password strength against NIST standards.

## Features

- **Email Breach Checker** — Queries the [Have I Been Pwned](https://haveibeenpwned.com/) API to see if an email address appears in any known data breaches
- **Password Generator** — Generates cryptographically random passwords with customizable character sets and length
- **Password Strength Checker** — Evaluates passwords against [NIST SP 800-63B](https://pages.nist.gov/800-63-3/sp800-63b.html) guidelines
- **About Screen** — App info and usage tips

## Prerequisites

- Java 17+
- Maven 3.6+
- A free [Have I Been Pwned API key](https://haveibeenpwned.com/API/Key) (required for the breach checker feature)

## Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/password-pal.git
   cd password-pal
   ```

2. **Add your API key**

   Copy the example environment file and fill in your key:
   ```bash
   cp .env.example .env
   ```
   Then open `.env` and replace `your_api_key_here` with your actual HIBP API key.

3. **Build and run**
   ```bash
   mvn clean javafx:run
   ```

## Project Structure

```
src/main/
├── MainController.java                   # App entry point and main menu
├── PasswordGenerator.java                # Password generation logic
├── PasswordStrengthCheck.java            # NIST strength evaluation logic
├── HIBPApiService.java                   # Have I Been Pwned API client
├── Breach.java                           # Data model for a breach record
├── ScreenSwitcher.java                   # JavaFX screen navigation utility
├── Controller.java                       # Base controller (shared back button)
├── EmailBreachesScreenController.java
├── PasswordGeneratorScreenController.java
├── PasswordStrengthCheckScreenController.java
├── AboutScreenController.java
└── *.fxml                                # UI layouts
```

## Built With

- [JavaFX 17](https://openjfx.io/) — UI framework
- [Have I Been Pwned API v3](https://haveibeenpwned.com/API/v3) — Breach data
- [dotenv-java](https://github.com/cdimascio/dotenv-java) — Environment variable loading
- [org.json](https://github.com/stleary/JSON-java) — JSON parsing
- [jsoup](https://jsoup.org/) — HTML sanitization for breach descriptions

## License

MIT — see [LICENSE](LICENSE) for details.
