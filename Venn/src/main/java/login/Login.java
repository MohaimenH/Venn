package login;
import java.io.FileWriter;
import java.io.IOException;

import application.Main;
import application.MainController;
import database.AccSys;
import database.Account;
import database.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
		
		sys = new AccSys();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.setVgap(8);
		grid.setHgap(10);
		
		// labels
		Label label1 = new Label("UserName:");
		GridPane.setConstraints(label1, 0, 0);
		Label label2 = new Label("Password:");
		GridPane.setConstraints(label2, 0, 1);
		
		// Input field
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		GridPane.setConstraints(nameInput, 2, 0, 2, 1);
		nameInput.setPrefWidth(170);
		PasswordField pwInput = new PasswordField();
		pwInput.setPromptText("password");
		GridPane.setConstraints(pwInput, 2, 1, 2, 1);
		pwInput.setPrefWidth(170);
		
		//check boxes
		CheckBox box1 = new CheckBox("Venn type:");
		
		// Button filed
		// Button login
		Button loginButton = new Button("Log In");
		loginButton.setPrefWidth(100);
		GridPane.setConstraints(loginButton, 2, 2);
		loginButton.setOnAction(e -> {
			int i = -1;
			for (Account a : sys.accounts) {
				if (a.getname().equals(nameInput.getText()))
				i = sys.accounts.indexOf(a);
			}
			if (i != -1 && sys.accounts.get(i).getpwd() == AccSys.getpwcode(pwInput.getText())) {
				Main main = new Main();
				try {
				window.close();
				MainController.sys = this.sys;
				main.run(new Stage());
				} catch (IOException e1) {
				AlertBox.display("Error", "Unknown Error occurs.");
				e1.printStackTrace();
				}
				window.close();
			}
			else {
				AlertBox.display("Sorry", "You have wrong username or password!");
			}
			
		});
		
		// Button register
		Button register = new Button("Register");
		GridPane.setConstraints(register, 2, 3);
		register.setPrefWidth(100);
		register.setOnAction(e -> {
			boolean flag = false;
			for (Account a : sys.accounts) {
				if (a.getname().equals(nameInput.getText()))
				flag = true;
			}
					
			if (flag) {
					AlertBox.display("Alert", "The username is already exiset, please try a new one");
			}
			else if(pwInput.getText().length() == 0) {
				AlertBox.display("Alert", "You haven't input your password. Please try again");
			}
			else {
				try {
						FileWriter write = new FileWriter(AccSys.filepath, true);
						this.sys.accounts.add(new User(nameInput.getText(), AccSys.getpwcode(pwInput.getText())));
						write.write(nameInput.getText() + "\t" + AccSys.getpwcode(pwInput.getText()) + "\n");
						write.close();
						nameInput.clear();
						pwInput.clear();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		
		
		grid.getChildren().addAll(label1, label2,nameInput, pwInput, loginButton, register);
		Scene scene = new Scene(grid, 260, 120);
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
	
}


