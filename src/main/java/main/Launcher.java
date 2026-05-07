package main;

/**
 * Entry point for the runnable fat JAR.
 *
 * JavaFX fat JARs require a plain (non-Application) launcher class to
 * bootstrap correctly — if the main class extends Application directly,
 * the JVM's module system interferes with startup when running from a
 * shaded JAR.
 */
public class Launcher {
    public static void main(String[] args) {
        MainController.main(args);
    }
}
