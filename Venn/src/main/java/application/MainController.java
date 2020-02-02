package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	Boolean selected=false;
	String temp;
	int index;
	//////////////////
	@FXML
	private Button left_label_button;
	@FXML
	private Button right_label_button;
	@FXML
	private Label leftLabel;
	@FXML
	private Label rightLabel;
	@FXML
	private TextField right_label_input;
	@FXML
	private TextField left_label_input;
	//=============================================//

	 @FXML
	 private ListView<String> holder;
	 @FXML
	 private TextField inputText;
	 @FXML
	 private TextArea inputArea;
	 @FXML
	 private ListView<String> right;
	 @FXML 
	 private ListView<String> left;
	 @FXML
	 private ListView<String> middle;
	 @FXML
	 private Button submit;
	 //=============================================//
	    @FXML
	    private URL location;
	     
	    @FXML
	    private ResourceBundle resources;
	 //=============================================//
	public  MainController() {}
	 @FXML
	    private void initialize() 
	    {
	    }
	//=============================================//

	 @FXML
	 private void printOutput()//gets input text and adds it to master list
	    {
		 	String place=inputText.getText();
		 holder.getItems().add(place);
		 inputText.clear();
	    }
	//=============================================//drag and drop detection
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
	//=============================================//label customization
	 @FXML
	 private void setLeftLabel() {
		 String left=left_label_input.getText();
		 leftLabel.setText(left);
		 left_label_input.clear();
	 }
	 @FXML
	 private void setRightLabel() {
		 String right=right_label_input.getText();
		 rightLabel.setText(right);
		 right_label_input.clear();
	 }
	 //=============================================//

}