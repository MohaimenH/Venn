package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application{
	
	@FXML
	private Label selectLabel;
	@FXML
	private Button twoSetSelect;
	@FXML
	private Button threeSetSelect;
	
	
	
	public void run(Stage primaryStage) throws IOException {

			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			primaryStage.getIcons().add(new Image("icon/icon.png"));
			primaryStage.setTitle("Venn: Simple Venn Diagrams");
			Scene scene = new Scene(root,1074,716);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setMinHeight(726);
			primaryStage.setMinWidth(1074);
			primaryStage.centerOnScreen();
			
			
			primaryStage.setOnCloseRequest((WindowEvent event1) -> {			
//			    System.out.println("Exit clicked!");
//			    int status = popUpExit(primaryStage);
//			    
//			    if (status == 1) {
//			    	event1.consume();
//			    }
				
				Stage popupwindow = new Stage();

				popupwindow.initModality(Modality.APPLICATION_MODAL);
				popupwindow.setTitle("Exit");

				Label label = new Label("Are you sure you want to exit?");

				VBox layout = new VBox(2);
				HBox layout2 = new HBox(2);
				
				Button button1 = new Button("Yes");

				button1.setOnAction((event) -> {
					popupwindow.close();
				});
				
				button1.setOnKeyPressed((KeyEvent keyEvent) -> {
					if (keyEvent.getCode() == KeyCode.ENTER) {
						popupwindow.close();
					}
					
					if (keyEvent.getCode() == KeyCode.ESCAPE) {
						event1.consume();
						popupwindow.close();
					}
				});
				
				Button button2 = new Button("No");
				
				button2.setOnAction((event) -> {
					event1.consume();
					popupwindow.close();
				});
				
				button2.setOnKeyPressed((KeyEvent keyEvent) -> {
					if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.ESCAPE) {
						event1.consume();
						popupwindow.close();
					}
				});
				
				
				layout2.getChildren().addAll(button1, button2);
				layout2.setAlignment(Pos.CENTER);
				layout2.setSpacing(10);
				
				layout.getChildren().addAll(label, layout2);
				layout.setAlignment(Pos.CENTER);
				
				layout.setSpacing(20.0);

				Scene scene1 = new Scene(layout, 350, 100);

				popupwindow.setScene(scene1);

				popupwindow.showAndWait();
				
			});
	}
	//=============================================================================
	//testing for gradle
	//test 2
	
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

		primaryStage.show();// TODO Auto-generated method stub
		primaryStage.setMinHeight(726);
		primaryStage.setMinWidth(1074);
		primaryStage.centerOnScreen();

		
		primaryStage.setOnCloseRequest((WindowEvent event1) -> {			
//		    System.out.println("Exit clicked!");
//		    int status = popUpExit(primaryStaglefte);
//		    
//		    if (status == 1) {
//		    	event1.consume();
//		    }
			
			Stage popupwindow = new Stage();

			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("Exit");

			Label label = new Label("Are you sure you want to exit?");
			Label label2 = new Label("Any unsaved changes will be discarded");
			label2.setTextFill(Color.RED);

			VBox layout = new VBox(3);
			HBox layout2 = new HBox(2);
			
			Button button1 = new Button("Yes");

			button1.setOnAction((event) -> {
				popupwindow.close();
			});
			
			button1.setOnKeyPressed((KeyEvent keyEvent) -> {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					popupwindow.close();
				}
				
				if (keyEvent.getCode() == KeyCode.ESCAPE) {
					event1.consume();
					popupwindow.close();
				}
			});
			
			Button button2 = new Button("No");
			
			button2.setOnAction((event) -> {
				event1.consume();
				popupwindow.close();
			});
			
			button2.setOnKeyPressed((KeyEvent keyEvent) -> {
				if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.ESCAPE) {
					event1.consume();
					popupwindow.close();
				}
			});
			
			layout2.getChildren().addAll(button1, button2);
			layout2.setAlignment(Pos.CENTER);
			layout2.setSpacing(10);
			
			layout.getChildren().addAll(label, label2, layout2);
			layout.setAlignment(Pos.CENTER);
			
			layout.setSpacing(20.0);

			Scene scene1 = new Scene(layout, 350, 130);

			popupwindow.setScene(scene1);

			popupwindow.showAndWait();
			
		});
	}
	
	
}
