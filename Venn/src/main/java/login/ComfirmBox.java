package login;
import database.*;
import application.Main;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ComfirmBox {
	
	static boolean answer;
	
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		
		//take care of this window first!
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label1 = new Label(message);
		
		
		
		Button YesButton = new Button("Yes!");
		Button NoButton = new Button("No!");
		YesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		NoButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label1, YesButton, NoButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
}
