package application;

import java.awt.Button;
import java.awt.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
	private TextArea Right;
	private TextArea Left;
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

	 public void setOther(ActionEvent e) {
		 System.out.print("test");
		 String thing= Left.getText();
		 Right.setText(thing);
	 }
	 @FXML

	 private void printOutput() 
	    {
	        Right.setText(Left.getText());
	    }
}
