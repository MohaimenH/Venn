package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	Boolean selected=false;
	String temp;
	@FXML
	private ListView<String> holder;
	 @FXML
	private TextField inputText;
	 @FXML
	private TextArea right;
	 @FXML 
	private ListView<String> left;
	 @FXML
	private Button submit;
	 @FXML
	    private URL location;
	     
	    @FXML
	    private ResourceBundle resources;
	
	public  MainController() {}

	
	 @FXML
	    private void initialize() 
	    {
	    }
	
	 @FXML

	 private void printOutput() 
	    {
		 	String place=inputText.getText();

		 holder.getItems().add(place);
	    }
	 @FXML
	 private Boolean detectDrop() {
		 selected=true;
		 return selected;
	 }
	 @FXML
	 private void detactDrag() {
		 //what we can do is detect when an item is selected, then depending on weather its selected we 
		 //detect where the mouse is and set that text to the appropriate text area
		 selected=false;
             temp=holder.getSelectionModel().getSelectedItem();
             System.out.print(temp);

	 }
	 @FXML
	 private void detectLeft() {
		 Boolean detection=detectDrop();
		 if(detection) {
			 left.getItems().add(temp);
		 }
	 }
}