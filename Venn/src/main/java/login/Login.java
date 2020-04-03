package login;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import application.Main;
import application.MainController;
import database.AccSys;
import database.Account;
import database.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
		if (!AccSys.valid) {
			sys = new AccSys();
			AccSys.valid = true;
		}
		
		AnchorPane grid = new AnchorPane();
		
		// labels------------------------------------------------------------------------
		Label label1 = new Label("UserName:");
		AnchorPane.setTopAnchor(label1, 10.0);
		AnchorPane.setLeftAnchor(label1, 10.0);
		Label label2 = new Label("Password:");
		AnchorPane.setTopAnchor(label2, 45.0);
		AnchorPane.setLeftAnchor(label2, 10.0);
		
		
		// Input field---------------------------------------------------------------------
		TextField nameInput = new TextField();
		nameInput.setPromptText("username");
		AnchorPane.setTopAnchor(nameInput, 10.0);
		AnchorPane.setLeftAnchor(nameInput, 100.0);
		AnchorPane.setRightAnchor(nameInput, 10.0);
		nameInput.setPrefWidth(100);
		PasswordField pwInput = new PasswordField();

		pwInput.setPromptText("password");
		AnchorPane.setTopAnchor(pwInput, 45.0);
		AnchorPane.setLeftAnchor(pwInput, 100.0);
		AnchorPane.setRightAnchor(pwInput, 35.0);
		pwInput.setPrefWidth(100);
		CheckBox cb = new CheckBox();
		AnchorPane.setTopAnchor(cb, 47.5);
		AnchorPane.setRightAnchor(cb, 5.0);
		
		Tooltip tip = new Tooltip();
		tip.setText(pwInput.getText());
		tip.setAutoHide(false);
        tip.setMinWidth(50);
        
        cb.setOnAction(e ->{
        	if (cb.isSelected()) {
        		Point2D p = pwInput.localToScene(pwInput.getBoundsInLocal().getMaxX(), pwInput.getBoundsInLocal().getMaxY());
            tip.setText(pwInput.getText());
            tip.show(pwInput,
                    p.getX() + window.getScene().getX() + window.getX(),
                    p.getY() + window.getScene().getY() + window.getY());
        	}
        	else {
        		tip.hide();
        	}
        });
        
        
        pwInput.setOnKeyReleased(e ->{
        	tip.setText(pwInput.getText());
        });;
        pwInput.setOnKeyTyped(e ->{
        	tip.setText(pwInput.getText());
		});
		
		// Button filed ----------------------------------------------------------------------------------------
		// Button login
		Button loginButton = new Button("Log In");
		loginButton.setPrefWidth(100);
		AnchorPane.setTopAnchor(loginButton, 80.0);
		AnchorPane.setLeftAnchor(loginButton, 10.0);
		loginButton.setOnAction(e -> {
			int i = -1;
			for (Account a : sys.accounts) {
				if (a.getname().equals(nameInput.getText()))
				i = sys.accounts.indexOf(a);
			}
			if (i != -1 && sys.accounts.get(i).getpwd() == AccSys.getpwcode(pwInput.getText())) {
				UserInterface ui = new UserInterface();
				ui.sys = this.sys;
				AccSys.current = this.sys.accounts.get(i);
				AccSys.current.inilist(AccSys.current.getname());
				window.close();
				try {
					ui.run(new Stage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				AlertBox.display("Sorry", "You have wrong username or password!");
			}
			
		});
		
		// Button register------------------------------------------------------------------------------
		Button register = new Button("Register");
		AnchorPane.setTopAnchor(register, 80.0);
		AnchorPane.setLeftAnchor(register, 120.0);
		AnchorPane.setRightAnchor(register, 115.0);
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
				if (!this.insertnewUser(nameInput.getText(), pwInput.getText())) {
					AlertBox.display("Alert", "Miss database!");
				}
				else {
					AlertBox.display("Success", "Registion success!");
					sys.accounts.add(new User(nameInput.getText(), AccSys.getpwcode(pwInput.getText())));
					nameInput.clear();
					pwInput.clear();
				}
			}
		});
		
		// Button login-------------------------------------------------------------------------------------
				Button visitor = new Button("Visitor Access");
				AnchorPane.setTopAnchor(visitor, 80.0);
				loginButton.setPrefWidth(100);
				AnchorPane.setRightAnchor(visitor, 10.0);
				visitor.setOnAction(e ->{
					Main main = new Main();
					try {
					window.close();
					MainController.sys = this.sys;
					AccSys.current = null;
					main.run(new Stage());
					} catch (IOException e1) {
					AlertBox.display("Error", "Unknown Error occurs.");
					e1.printStackTrace();
					}
				});
		
		grid.getChildren().addAll(cb,label1, label2,nameInput, pwInput, loginButton, register, visitor);
		Scene scene = new Scene(grid, 330, 100);
		scene.setOnMouseMoved(e ->{
			if (cb.isSelected()) {
				Point2D p = pwInput.localToScene(pwInput.getBoundsInLocal().getMaxX(), pwInput.getBoundsInLocal().getMaxY());
            tip.setText(pwInput.getText());
            tip.show(pwInput,
                    p.getX() + window.getScene().getX() + window.getX(),
                    p.getY() + window.getScene().getY() + window.getY());
			}
			
		});
		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void run() throws Exception {
		this.start(new Stage());
	}
	
	private void closeprogram() {
		Boolean answer = ComfirmBox.display("Attention", "Sure you want to exit?");
		if (answer) {
			window.close();
		}
	}
	
	private boolean insertnewUser(String user, String pw){
		try {
			Document doc = new SAXReader().read(AccSys.filepath);
			Element root = doc.getRootElement();
			Element sub = root.addElement("User");
			Element name = sub.addElement("name");
			sub.addAttribute("name", user);
			name.setText(user);
			sub.addElement("pwd").setText("" + AccSys.getpwcode(pw));
			sub.addElement("Venns");
			
			FileOutputStream out = new FileOutputStream(AccSys.filepath);
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
			return true;
		}
		catch (Exception e){
			return false;
		}
		
		
	}
}


