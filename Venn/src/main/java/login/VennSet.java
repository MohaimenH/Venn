package login;
import java.io.IOException;

import application.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VennSet extends Application{
	
	Stage window;
	private static void body() {
		Stage window = new Stage();
		window.setTitle("Setup Your Venn Diagram");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label label1 = new Label("VennName:");
		GridPane.setConstraints(label1,0, 0);
		
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 1, 0);
		
		
		
		
		Button button = new Button("Get Diagram");
		GridPane.setConstraints(button, 1, 2);
		button.setOnAction(e -> {
			Main main = new Main();
			try {
				window.close();
				main.run(new Stage());
			} catch (IOException e1) {
				AlertBox.display("Error", "Unknown Error occurs.");
				e1.printStackTrace();
			}
		});
		
		grid.getChildren().addAll(label1, nameInput, button);
		
		Scene scene = new Scene(grid, 260, 100);
		window.setScene(scene);
		window.show();
	}
	
	
	
	public static void run() {
		VennSet.body();
	}
	
	
	@Override
	public void start(Stage pri) throws Exception {
		body();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
