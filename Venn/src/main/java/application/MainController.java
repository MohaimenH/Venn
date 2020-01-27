package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	 @FXML

	private TextField inputText;
	 @FXML

	private TextArea right;
	 @FXML

	 
	private TextArea left;
	
	 @FXML
	 private TextArea light;
	 @FXML
	private Button submit;
	 @FXML
	    private URL location;
	     
	    @FXML
	    private ResourceBundle resources;
	
	public  MainController() {
	}
	 @FXML
	    private void initialize() 
	    {
	    }
	
	 @FXML

	 private void printOutput() 
	    {
	        light.setText(inputText.getText());
	    }
}