package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application{
	
	@FXML
	private Label selectLabel;
	@FXML
	private Button twoSetSelect;
	@FXML
	private Button threeSetSelect;
	
	
	
	public void run(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/SelectScene.fxml"));
		primaryStage.getIcons().add(new Image("icon/icon.png"));
		primaryStage.setTitle("Venn: Simple Venn Diagrams");
		Scene scene = new Scene(root,1074,716);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
}
	
	
	// This method is called by other page
	
	//=============================================================================
	
	public static void main(String[] arg) {
		launch(arg);
	}
    
	
	// This following method is only for test make sure to change the code on both 
	// <run> and <main>
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("SelectScene.fxml"));
		primaryStage.getIcons().add(new Image("icon/icon.png"));
		primaryStage.setTitle("Venn: Simple Venn Diagrams");
		Scene scene = new Scene(root,1074,716);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}
