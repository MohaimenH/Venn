package application;

import java.net.URL;
import java.util.*;

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
	
	Set<String> elements = new HashSet<>();
	Set<String> leftElems = new HashSet<>();
	Set<String> rightElems = new HashSet<>();
	Set<String> midElems = new HashSet<>();
	
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
             
             elements.add(temp);
	 }
	 @FXML
	 private void detectLeft() {
		 if(selected && (leftElems.contains(temp) != true)) {
			 
			 if (rightElems.contains(temp)) {
				 detectMiddle();
				 left.getItems().remove(temp);
//				 holder.getItems().remove(index);
			 }
			 
			 else {
				 left.getItems().add(temp);
				 holder.getItems().remove(index);
				 leftElems.add(temp);
				 selected=false;
			 }
		 }
		 
		 else if ((leftElems.contains(temp) == true)) {
			 holder.getItems().remove(index);
		 }
	 }
	 @FXML
	 private void detectRight() {
		 
		 if(selected && (rightElems.contains(temp) != true)) {
			 
			 if (leftElems.contains(temp)) {
				 detectMiddle();
			 }
			 
			 else {
				 right.getItems().add(temp);
				 holder.getItems().remove(index);
				 rightElems.add(temp);
				 selected=false;
			 }
		 }
		 
		 else if ((rightElems.contains(temp) == true)) {
			 holder.getItems().remove(index);
		 }
	 }
	 @FXML
	 private void detectMiddle() {
		 if(selected && (midElems.contains(temp) != true)) {
			 middle.getItems().add(temp);
			 holder.getItems().remove(index);
			 midElems.add(temp);
			 selected=false;
		 }
		 
		 else if ((midElems.contains(temp) == true)) {
			 holder.getItems().remove(index);
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