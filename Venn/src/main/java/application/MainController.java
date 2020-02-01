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
	int index;
	@FXML
	private ListView<String> holder;
	 @FXML
	private TextField inputText;
	 @FXML
	private ListView<String> right;
	 @FXML 
	private ListView<String> left;
	 @FXML
	 private ListView<String> middle;
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
		 inputText.clear();
	    }
	 @FXML
	 private void detectDrop() {
		selected =true;
	 }
	 @FXML
	 private void detactDrag() {
		 	 index=holder.getSelectionModel().getSelectedIndex();
		 	 selected=true;
             temp=holder.getSelectionModel().getSelectedItem();
             System.out.print(temp);
	 }
	 @FXML
	 private void detectLeft() {
		 
		 if(selected) {
			 left.getItems().add(temp);
			 holder.getItems().remove(index);
			 selected=false;
			 
		 }
	 }
	 @FXML
	 private void detectRight() {
		 
		 if(selected) {
			 right.getItems().add(temp);
			 holder.getItems().remove(index);
			 selected=false;
			 
		 }
	 }
	 @FXML
	 private void detectMiddle() {
		 
		 if(selected) {
			 middle.getItems().add(temp);
			 holder.getItems().remove(index);
			 selected=false;
			 
		 }
	 }
}