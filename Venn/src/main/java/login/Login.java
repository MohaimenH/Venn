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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Login extends Application {
	//implements EventHandler<ActionEvent>
	Button log_in, exit;
	Stage window;
	Scene scene1, scene2;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		window.setTitle("Log In");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeprogram();
		});
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(12);
		
		Label label1 = new Label("UserName:");
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
		loginButton.setOnAction(e -> {
			Main main = new Main();
			try {
				window.close();
				main.run(new Stage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		grid.getChildren().addAll(label1, label2,nameInput, pwInput, loginButton);
		
		Scene scene = new Scene(grid, 260, 100);
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private void closeprogram() {
		Boolean answer = ComfirmBox.display("Attention", "Sure you want to exit?");
		if (answer) {
			window.close();
			System.out.println("Close");
		}
		else {
			
		}
	}
//	@Override
//	public void handle(ActionEvent event) {
//		if (event.getSource() == log_in) {
//			System.out.print("t");
//		}
//	}
	
}
