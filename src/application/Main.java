// Main class
// Jack Guebert
// 14jg58
// April 7, 2017

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

// Just a basic main class that sets up the application
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("PizzaGUI2.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.setCursor(new ImageCursor(new Image("application/za.png")));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pizza Order");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
