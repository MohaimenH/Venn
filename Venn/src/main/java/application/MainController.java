package application;

import java.awt.AWTException;
//import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;

import java.io.*;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.application.Application;


public class MainController {
	int x;
	int y;
	Boolean selected = false;
	String temp;
	int index;

	private Set<String> elements = new HashSet<>();
	private Set<String> leftElems = new HashSet<>();
	private Set<String> rightElems = new HashSet<>();
	private Set<String> midElems = new HashSet<>();

//	ArrayList<String> elements = new ArrayList<>();
//	ArrayList<String> leftElems = new ArrayList<>();
//	ArrayList<String> rightElems = new ArrayList<>();
//	ArrayList<String> midElems = new ArrayList<>();

	//////////////////
	@FXML
	private AnchorPane anchor;
	@FXML
	private Label title;
	@FXML
	private Button textExport;
	@FXML
	private Button left_label_button;
	@FXML
	private Button right_label_button;
	@FXML
	private Button clearLeft;
	@FXML
	private Button clearRight;
	@FXML
	private Button snapshot;
	@FXML
	private Label leftLabel;
	@FXML
	private Label rightLabel;
	@FXML
	private TextField right_label_input;
	@FXML
	private TextField left_label_input;
	// =============================================//

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
	private ListView<String> decoyRight;
	@FXML
	private ListView<String> decoyLeft;
	@FXML
	private Button submit;
	// =============================================//
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	// =============================================//
	public MainController() {
	}

	@FXML
	private void initialize() {
	}

	// =============================================// Helper Methods
	
	public boolean notBlank(String a) {
		if (a.isEmpty() || a.trim().isEmpty() || a == null) {
			selected = false;
			return false;
		}
		return true;
	}
	
	public void delElemsClick(ListView<String> list, Set<String> set) {
		index = list.getSelectionModel().getSelectedIndex();
		temp = list.getItems().get(index);
		list.getItems().remove(temp);
		set.remove(temp);
	}
	
	public void addToRight(String temp) {
		if (notBlank(temp)) {
			right.getItems().add(temp);
			rightElems.add(temp);
		}
	}
	
	public void addToLeft(String temp) {
		if(notBlank(temp)) {
			left.getItems().add(temp);
			leftElems.add(temp);
		}
	}
	
	public void addToMiddle(String temp) {
		if (notBlank(temp)) {
			middle.getItems().add(temp);
			midElems.add(temp);
		}
	}
	
	// =============================================// Master List Operations

	@FXML
	private void printOutput() { // gets input text and adds it to master list
		String place = inputText.getText();
		if (notBlank(place)) {
			holder.getItems().add(place);
			inputText.clear();
			elements.add(place);
		}
	}
	@FXML
	public void handle(KeyEvent keyEvent) {//checks for button enter
        if (keyEvent.getCode() == KeyCode.ENTER)  {
            printOutput();
        }
    }
	
	//===========================================// Deleting Elements Using 'Del' Key
	
	@FXML
	public void delElemLeft(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
			index = left.getSelectionModel().getSelectedIndex();
			leftElems.remove(left.getItems().get(index));
			left.getItems().remove(index);
			index=0;
		}
	}
	public void delElemRight(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
			index = right.getSelectionModel().getSelectedIndex();
			rightElems.remove(right.getItems().get(index));
			right.getItems().remove(index);
			index=0;
		}
	}
	public void delElemMiddle(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
			index = middle.getSelectionModel().getSelectedIndex();
			midElems.remove(middle.getItems().get(index));
			middle.getItems().remove(index);
			index=0;
		}
	}
	
	// =============================================//Drag/Drop Detection 
	
	@FXML
	private void detectDrop() {
		selected = true;
		
	}

	@FXML
	private void points() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		x=p.x;
		y=p.y;
//		System.out.print("x: " +y);
//		System.out.print("y: "+x);

	}
	
	@FXML
	private void detactDrag() {
		
		index = holder.getSelectionModel().getSelectedIndex();
		selected = true;
		temp = holder.getSelectionModel().getSelectedItem();
//		System.out.print(temp);
//		selected=false;
	}

	//==============================================// Set Operations
	
	@FXML
	private void detectLeft() {
		

		if (selected && (leftElems.contains(temp) != true) && (midElems.contains(temp) != true) && notBlank(temp)) {

			if (rightElems.contains(temp)) {
				detectMiddle();
//				 index=rightElems.indexOf(temp);
//				 System.out.println("Before: " + left.getItems().indexOf(temp));
//				 left.getItems().remove(index);
//				 System.out.println("After: " + left.getItems().indexOf(temp));
//				 holder.getItems().remove(index);
			}

			else {

				left.getItems().add(temp);
				holder.getItems().remove(temp);
				leftElems.add(temp);
				selected = false;
			}
		}

//		else if (selected && (leftElems.contains(temp) == true)) {
//		else {
		else if (selected) {
			holder.getItems().remove(temp);
//			holder.getItems().remove(index);
			selected = false;
		}
	}

	@FXML
	private void detectRight() {

		if (selected && (rightElems.contains(temp) != true) && (midElems.contains(temp) != true) && notBlank(temp)) {

			if (leftElems.contains(temp)) {
				detectMiddle();
			}

			else {
				right.getItems().add(temp);
				holder.getItems().remove(temp);
				rightElems.add(temp);
				selected = false;
			}
		}

//		else if (selected && (rightElems.contains(temp) == true)) {
//		else {
		else if (selected) {
		holder.getItems().remove(temp);
//			holder.getItems().remove(index);
			selected = false;
		}
	}

	@FXML
	private void detectMiddle() {
		if (selected && (midElems.contains(temp) != true) && notBlank(temp)) {
			middle.getItems().add(temp);
			holder.getItems().remove(temp);
			midElems.add(temp);

			left.getItems().remove(temp);
			leftElems.remove(temp);

			right.getItems().remove(temp);
			rightElems.remove(temp);

			selected = false;
		}

//		else if (selected && (midElems.contains(temp) == true)) {
//		else {
		else if (selected) {
			holder.getItems().remove(temp);
//			holder.getItems().remove(index);
			selected = false;
		}
	}

	// =============================================//label customization
	
	@FXML
	private void setLeftLabel() {
		String left = left_label_input.getText();
		leftLabel.setText(left);
		left_label_input.clear();
	}

	@FXML
	private void setRightLabel() {
		String right = right_label_input.getText();
		rightLabel.setText(right);
		right_label_input.clear();
	}
	
	// =============================================// Clear individual sets

	@FXML
	private void clearLeftSet() {
		left.getItems().clear();
		leftElems.clear();
		right.getItems().addAll(middle.getItems());
		rightElems.addAll(middle.getItems());
		middle.getItems().clear();
		midElems.clear();
//		int size = middle.getItems().size();
//		for(int i=0; i < size; i++) {
//			right.getItems().add(middle.getItems().get(i));
//			rightElems.addAll(right)
//		}
	}
	
	@FXML
	private void clearRightSet() {
		right.getItems().clear();
		rightElems.clear();
		left.getItems().addAll(middle.getItems());
		leftElems.addAll(middle.getItems());
		middle.getItems().clear();
		midElems.clear();
	}
	
	// =============================================// modify the title
	
	@FXML
	private void setTitle() {
		System.out.print("test");
	}
	
	//==============================================// Export Options
	
	@FXML
	public void exportAsText() throws IOException {
//		int arr=0;
//		String a = leftElems.iterator().next();
		int sizeL = leftElems.size();
		int sizeR = rightElems.size();
		int sizeM = midElems.size();
		FileWriter writer = new FileWriter("C:\\Users\\Mohaimen Hassan\\Desktop\\output.txt"); //Change to Your Directory of Choice - Preferably Desktop
//		ArrayList<Object> a = new ArrayList<>(leftElems.toArray());
		
		Object[] leftElements = leftElems.toArray();
		
		writer.write("Unique Elements of Set A:" + System.lineSeparator());
		writer.write("\n");
		for(int i=0; i < sizeL; i++) {
			writer.write(leftElements[i].toString() + System.lineSeparator());
		}
		
		Object[] rightElements = rightElems.toArray();
		
//		System.out.println("=============================");
		
//		String h = "Unique Elements of Set B:";
		
		writer.write("\n\n");
		writer.write("Unique Elements of Set B:" + System.lineSeparator());
		writer.write("\n");
		for(int i=0; i < sizeR; i++) {
			writer.write(rightElements[i].toString() + System.lineSeparator());
		}
		
		Object[] midElements = midElems.toArray();
		
//		System.out.println("=============================");
		
//		System.out.println("Intersection of Set A & Set B:");
		writer.write("\n\n");
		writer.write("Intersection of Set A & Set B:" + System.lineSeparator());
		writer.write("\n");
		for(int i=0; i < sizeM; i++) {
			writer.write(midElements[i].toString() + System.lineSeparator());
		}
		
		writer.close();
	}
	
	@FXML
	public void takeSnapshot() throws IOException, AWTException {
		WritableImage snap = new WritableImage(1000,611);
		anchor.snapshot(new SnapshotParameters(), snap);
		
		File file = new File("C:\\Users\\Mohaimen Hassan\\Desktop\\snap.png"); // Change Directory
	    
	    try {
	        ImageIO.write(SwingFXUtils.fromFXImage(snap, null), "png", file);
//	    	ImageIO.write(snap, "png", file);
	    } catch (IOException e) {
	        // TODO: handle exception here
	    }
	    
		/*
		Robot abc = new Robot();
		Rectangle screenRect = new Rectangle(934,611);
		BufferedImage snap = abc.createScreenCapture(screenRect);
		*/
	}
	
	// ============================================// Right Click Menus
	
	@FXML
	public void menuLeft(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseButton.SECONDARY && (left.getItems().size() > 0)) {
			
//			System.out.println("RIGHT CLICK!");
			
			ContextMenu contextMenu = new ContextMenu();
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");
			
			
			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsClick(left, leftElems);
			});
			
			moveRight.setOnAction((event) -> {
				addToRight(left.getSelectionModel().getSelectedItem());
				delElemsClick(left, leftElems);
			});
			
			moveMid.setOnAction((event) -> {
				addToMiddle(left.getSelectionModel().getSelectedItem());
				delElemsClick(left, leftElems);
			});
			
			contextMenu.getItems().addAll(del, moveMid, moveRight);
			left.setContextMenu(contextMenu);
			
		}	
	}
	
	@FXML
	public void menuRight(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseButton.SECONDARY && (right.getItems().size() > 0)) {
			
//			System.out.println("RIGHT CLICK!");
			
			ContextMenu contextMenu = new ContextMenu();
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");
			
			
			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsClick(right, rightElems);
			});
			
			moveLeft.setOnAction((event) -> {
				addToLeft(right.getSelectionModel().getSelectedItem());
				delElemsClick(right, rightElems);
			});
			
			moveMid.setOnAction((event) -> {
				addToMiddle(right.getSelectionModel().getSelectedItem());
				delElemsClick(right, rightElems);
			});
			
			contextMenu.getItems().addAll(del, moveMid, moveLeft);
			right.setContextMenu(contextMenu);
			
		}	
	}
	
	@FXML
	public void menuMiddle(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseButton.SECONDARY && (middle.getItems().size() > 0)) {
			
//			System.out.println("RIGHT CLICK!");
			
			ContextMenu contextMenu = new ContextMenu();
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			
			
			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsClick(middle, midElems);
			});
			
			moveLeft.setOnAction((event) -> {
				addToLeft(middle.getSelectionModel().getSelectedItem());
				delElemsClick(middle, midElems);
			});
			
			moveRight.setOnAction((event) -> {
				addToRight(middle.getSelectionModel().getSelectedItem());
				delElemsClick(middle, midElems);
			});
			
			contextMenu.getItems().addAll(del,moveLeft, moveRight);
			middle.setContextMenu(contextMenu);
			
		}	
	}
	
	@FXML
	public void menuHolder(MouseEvent mouseEvent) {
		if (mouseEvent.getButton() == MouseButton.SECONDARY) {

//			System.out.println("RIGHT CLICK!");

			ContextMenu contextMenu = new ContextMenu();
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");

			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
//				delElemsClick(middle, midElems);
				index = holder.getSelectionModel().getSelectedIndex();
				holder.getItems().remove(index);
				selected = false;
			});

			moveLeft.setOnAction((event) -> {
//				addToLeft(middle.getSelectionModel().getSelectedItem());
				selected=true;
				temp=holder.getSelectionModel().getSelectedItem();
				detectLeft();
//				delElemsClick(middle, midElems);
				selected=false;
			});

			moveRight.setOnAction((event) -> {
				selected=true;
				temp=holder.getSelectionModel().getSelectedItem();
				detectRight();
				selected=false;
//				addToRight(middle.getSelectionModel().getSelectedItem());
//				delElemsClick(middle, midElems);
			});

			moveMid.setOnAction((event) -> {
				selected=true;
				temp=holder.getSelectionModel().getSelectedItem();
				detectMiddle();
				selected=false;
//				addToRight(middle.getSelectionModel().getSelectedItem());
//				delElemsClick(middle, midElems);
			});

			
			contextMenu.getItems().addAll(del, moveLeft, moveRight, moveMid);
			holder.setContextMenu(contextMenu);

		}
	}
	
	
	
}