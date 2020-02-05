package login;
import java.io.File;
import java.io.IOException;

import application.Main;
import database.AccSys;
import database.Account;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Login extends Application {
	//implements EventHandler<ActionEvent>
	Button log_in, exit;
	Stage window;
	Scene scene1, scene2;
	AccSys sys;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.window = primaryStage;
		window.setTitle("Log In");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeprogram();
		});
		window.getIcons().addAll(new Image("icon/icon.png"));
		sys = new AccSys();
		
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
		loginButton.setPrefWidth(100);
		loginButton.setOnAction(e -> {
			String name = nameInput.getText();
			long pwd = Account.gethash(pwInput.getText());
			System.out.println(sys.accounts.size());
			
			for (Account a : sys.accounts ) {
				System.out.println(name.equals(a.getname()) && pwd == a.getpwd());
				if (name.equals(a.getname()) && pwd == a.getpwd()) {
					// jump to new User panel
					window.close();
				}
			}
		});
		
		Button visitor = new Button("Visitor");
		GridPane.setConstraints(visitor, 1, 4);
		visitor.setPrefWidth(100);
		visitor.setOnAction(e -> {
			try {
				VennSet.run(sys);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			window.close();
		});
		
		Button register = new Button("Register");
		register.setPrefWidth(100);
		GridPane.setConstraints(register, 1, 3);
		register.setOnAction(e -> {
			for (Account a : sys.accounts) {
				if (a.getname().equals(nameInput.getText())) {
					
				}
				else {
					AlertBox.display("Alert", "The username is already exiset, please try a new one");
					continue;
				}
			}
		});
		
		grid.getChildren().addAll(label1, label2,nameInput, pwInput, loginButton, visitor, register);
		
		Scene scene = new Scene(grid, 260, 160);
		window.setMinHeight(100);
		window.setMinWidth(270);
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
		}
		else {
			
		}
	}
	
	private Account getAccount(String name, int pwd) {
		String filepath = "database/users.txt";
		File file = new File(filepath);
		
		
		
		return null;
	}
}


