package login;


import database.AccSys;
import database.Account;
import database.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserInterface extends Application {
	
	AccSys sys;
	Stage window;
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.window = primaryStage;
		window.setOnCloseRequest(e ->{
			e.consume();
			this.closeprogram();
		});
		window.setResizable(false);
		window.setTitle("Manage Your Venn");
		window.getIcons().add(new Image("icon/icon.png"));
		
		AnchorPane anchor = new AnchorPane();
		// label say hello
		Label sayhello = new Label("Hello, "+ AccSys.current.getname() + "." );
		sayhello.setFont(new Font("Time new Romans", 24));
		
		AnchorPane.setLeftAnchor(sayhello, 10.0);
		AnchorPane.setTopAnchor(sayhello, 5.0);
		
		//Button "Log Out"
		Button logout = new Button("Log Out");
		logout.setOnAction(e ->{
			AccSys.current = null;
			window.close();
			Login login = new Login();
			login.sys = this.sys;
			try {
				login.run();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		// HBox
		HBox hb = new HBox();
		hb.getChildren().addAll(logout);
		AnchorPane.setRightAnchor(hb, 10.0);
		AnchorPane.setTopAnchor(hb, 10.0);
		
		// List View
		ListView<User> list = new ListView<>();
		AnchorPane.setTopAnchor(list, 40.0);
		AnchorPane.setLeftAnchor(list, 10.0);
		AnchorPane.setRightAnchor(list, 100.0);
		AnchorPane.setBottomAnchor(list, 10.0);
		
		
		Scene scene = new Scene(anchor, 400, 200);
		anchor.getChildren().addAll(sayhello, hb, list);
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] arg) {
		launch(arg);
	}
	
	public void run(Stage primaryStage) throws Exception{
		this.start(primaryStage);
	}
	
	private void closeprogram() {
		Boolean answer = ComfirmBox.display("Attention", "Sure you want to exit?");
		if (answer) {
			this.window.close();
		}
	}
}
