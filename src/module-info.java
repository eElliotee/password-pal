module JavaFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires org.json;
	requires org.jsoup;
	requires java.net.http;
	requires io.github.cdimascio.dotenv.java;

	opens main to javafx.graphics, javafx.fxml;
}
