package login;

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

public class Main extends Application{
	
	Stage window;
	
	@Override
	public void start(Stage pri) throws Exception {
		this.window = pri;
		window.setTitle("Main");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label label1 = new Label("User Name:");
		Label label2 = new Label("Password:");
		GridPane.setConstraints(label1,0, 0);
		GridPane.setConstraints(label2,0, 1);
		
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 1, 0);
		
		TextField pwInput = new TextField();
		GridPane.setConstraints(pwInput, 1, 1);
		pwInput.setPromptText("password");
		
		Button loginButton = new Button("Log In");
		GridPane.setConstraints(loginButton, 1, 2);
		
		grid.getChildren().addAll(label1, label2,nameInput, pwInput, loginButton);
		
		Scene scene = new Scene(grid, 100, 100);
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
