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

import database.AccSys;

//import database.AccSys;

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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	static AccSys sys;

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
	private AnchorPane MainAnchor;
	@FXML 
	private AnchorPane secondAnchor;
	@FXML
	private Label title;
	@FXML
	private Button textExport;
	@FXML
	private Button left_label_button;
	@FXML
	private Button right_label_button;
//	@FXML
//	private Button clearLeft;
//	@FXML
//	private Button clearRight;
	@FXML
	private Button snapshot;
	@FXML
	private Button importer;
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
	
	public void delElemsHelper(ListView<String> list, Set<String> set) {
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
	public void keyPressLeft(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
//			index = left.getSelectionModel().getSelectedIndex();
//			leftElems.remove(left.getItems().get(index));
//			left.getItems().remove(index);
//			index=0;
			
			delElemsHelper(left, leftElems);
		}
	}
	public void keyPressRight(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
//			index = right.getSelectionModel().getSelectedIndex();
//			rightElems.remove(right.getItems().get(index));
//			right.getItems().remove(index);
//			index=0;
			
			delElemsHelper(right, rightElems);
		}
	}
	public void keyPressMiddle(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
//			index = middle.getSelectionModel().getSelectedIndex();
//			midElems.remove(middle.getItems().get(index));
//			middle.getItems().remove(index);
//			index=0;
			
			delElemsHelper(middle, midElems);
		}
	}
	public void keyPressHolder(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			index = holder.getSelectionModel().getSelectedIndex();
			holder.getItems().remove(index);
			selected = false;
		}
		
		if (keyEvent.getCode() == KeyCode.I && holder.getItems().isEmpty() == false) {
			index = holder.getSelectionModel().getSelectedIndex();
			temp=holder.getItems().get(index);
			selected=true;
			detectMiddle();
			holder.getItems().remove(temp);
			selected = false;
		}
		
		if (keyEvent.getCode() == KeyCode.L && holder.getItems().isEmpty() == false) {
			index = holder.getSelectionModel().getSelectedIndex();
			temp=holder.getItems().get(index);
			selected=true;
			detectLeft();
			holder.getItems().remove(temp);
			selected = false;
		}
		
		if (keyEvent.getCode() == KeyCode.R && holder.getItems().isEmpty() == false) {
			index = holder.getSelectionModel().getSelectedIndex();
			temp=holder.getItems().get(index);
			selected=true;
			detectRight();
			holder.getItems().remove(temp);
			selected = false;
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
		String path = this.getpath(0);
		if (path.length() == 0) {
			return;
		}
		
		
		FileWriter writer = new FileWriter(path); //Change to Your Directory of Choice - Preferably Desktop
//		ArrayList<Object> a = new ArrayList<>(leftElems.toArray());
		
		Object[] leftElements = leftElems.toArray();
		
		writer.write("*Unique Elements of " + leftLabel.getText() + System.lineSeparator());
		writer.write("\n");
		for(int i=0; i < sizeL; i++) {
			writer.write(leftElements[i].toString() + System.lineSeparator());
		}
		
		Object[] rightElements = rightElems.toArray();
		
//		System.out.println("=============================");
		
//		String h = "Unique Elements of Set B:";
		
		writer.write("\n\n");
		writer.write("*Unique Elements of " + rightLabel.getText() + System.lineSeparator());
		writer.write("\n");
		for(int i=0; i < sizeR; i++) {
			writer.write(rightElements[i].toString() + System.lineSeparator());
		}
		
		Object[] midElements = midElems.toArray();
		
//		System.out.println("=============================");
		
//		System.out.println("Intersection of Set A & Set B:");
		writer.write("\n\n");
		writer.write("*Intersection of " + leftLabel.getText() + " & " + rightLabel.getText() + System.lineSeparator());
		writer.write("\n");
		for(int i=0; i < sizeM; i++) {
			writer.write(midElements[i].toString() + System.lineSeparator());
		}
		
		writer.close();
	}
	
	/*
	 * This method can help the export or snapshot get the path the expected
	 */
	
	private String getpath(int i) {
		Stage mainStage = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose your path");
		File selectedFile = new File("src/main/java/users");
		if (i == 0) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
			selectedFile = fileChooser.showSaveDialog(mainStage);
		               
		}
		else if (i == 1) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                								     new FileChooser.ExtensionFilter("PNG", "*.png"));
			selectedFile = fileChooser.showSaveDialog(mainStage);
		}
		else if (i == 2) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
			selectedFile = fileChooser.showOpenDialog(mainStage);
		}
		
		String path = "";

		try {
			return path = selectedFile.getPath();
		}
		catch (NullPointerException e) {
			
		}
		return path;
	}

	@FXML
	public void takeSnapshot() throws IOException, AWTException {
//		WritableImage snap = new WritableImage(1000,611);
		WritableImage snap = new WritableImage(781,624);
		secondAnchor.snapshot(new SnapshotParameters(), snap);
//		MainAnchor.snapshot(new SnapshotParameters(), snap);

		String path = this.getpath(1);
		if (path.length() == 0) {
			return;
		}
		File file = new File(path);
//		File file = new File("C:\\Users\\RM\\Pictures\\snap.png"); // Change Directory
	    
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
			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");

			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");
			MenuItem delAll = new MenuItem("Delete All Elements In This Set");
			
			
			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsHelper(left, leftElems);
			});
			
			moveRight.setOnAction((event) -> {
				addToRight(left.getSelectionModel().getSelectedItem());
				delElemsHelper(left, leftElems);
			});
			
			moveMid.setOnAction((event) -> {
				addToMiddle(left.getSelectionModel().getSelectedItem());
				delElemsHelper(left, leftElems);
			});
			
			delAll.setOnAction((event) -> {
				clearLeftSet();
			});
			
			delMenu.getItems().addAll(del, delAll);
			moveMenu.getItems().addAll(moveMid, moveRight);
			
			contextMenu.getItems().addAll(delMenu, moveMenu);
			
//			contextMenu.getItems().addAll(del, moveMid, moveRight, delAll);
			left.setContextMenu(contextMenu);
			
		}	
	}
	
	@FXML
	public void menuRight(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseButton.SECONDARY && (right.getItems().size() > 0)) {
			
//			System.out.println("RIGHT CLICK!");
			
			ContextMenu contextMenu = new ContextMenu();
			
			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");
			
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");
			MenuItem delAll = new MenuItem("Delete All Elements In This Set");
			
			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsHelper(right, rightElems);
			});
			
			moveLeft.setOnAction((event) -> {
				addToLeft(right.getSelectionModel().getSelectedItem());
				delElemsHelper(right, rightElems);
			});
			
			moveMid.setOnAction((event) -> {
				addToMiddle(right.getSelectionModel().getSelectedItem());
				delElemsHelper(right, rightElems);
			});
			
			delAll.setOnAction((event) -> {
				clearRightSet();
			});
			
			delMenu.getItems().addAll(del, delAll);
			moveMenu.getItems().addAll(moveMid, moveLeft);
			
			contextMenu.getItems().addAll(delMenu, moveMenu);
			
//			contextMenu.getItems().addAll(del, moveMid, moveLeft, delAll);
			right.setContextMenu(contextMenu);
			
		}	
	}
	
	@FXML
	public void menuMiddle(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseButton.SECONDARY && (middle.getItems().size() > 0)) {
			
//			System.out.println("RIGHT CLICK!");
			
			ContextMenu contextMenu = new ContextMenu();
			
			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");
			
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			MenuItem delAll = new MenuItem("Delete All Elements In This Set");
			
			
			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsHelper(middle, midElems);
			});
			
			moveLeft.setOnAction((event) -> {
				addToLeft(middle.getSelectionModel().getSelectedItem());
				delElemsHelper(middle, midElems);
			});
			
			moveRight.setOnAction((event) -> {
				addToRight(middle.getSelectionModel().getSelectedItem());
				delElemsHelper(middle, midElems);
			});
			
			delAll.setOnAction((event) -> {
				middle.getItems().clear();
				midElems.clear();
			});
			
			delMenu.getItems().addAll(del, delAll);
			moveMenu.getItems().addAll(moveLeft, moveRight);
			
			contextMenu.getItems().addAll(delMenu, moveMenu);
			
//			contextMenu.getItems().addAll(del,moveLeft, moveRight, delAll);
			middle.setContextMenu(contextMenu);
			
		}	
	}
	
	@FXML
	public void menuHolder(MouseEvent mouseEvent) {
		if (mouseEvent.getButton() == MouseButton.SECONDARY) {

//			System.out.println("RIGHT CLICK!");

			ContextMenu contextMenu = new ContextMenu();
			
			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");
			Menu moveAll = new Menu("Move All");
			
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move Element To First Set");
			MenuItem moveRight = new MenuItem("Move Element To Second Set");
			MenuItem moveMid = new MenuItem("Move Element To Intersection");
			MenuItem moveAllLeft = new MenuItem("Move All To First Set");
			MenuItem moveAllRight = new MenuItem("Move All To Second Set");
			MenuItem moveAllMid = new MenuItem("Move All to Intersection");
			MenuItem delAll = new MenuItem("Delete All The Elements");

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
			
			moveAllLeft.setOnAction((event) -> {
//				addToLeft(middle.getSelectionModel().getSelectedItem());
				Object[] arr = holder.getItems().toArray();
				int size = arr.length;
				for(int i=0; i < size; i++) {
					selected=true;
					temp=arr[i].toString();
					detectLeft();
					selected=false;
				}
			});
			
			moveAllRight.setOnAction((event) -> {
				
				Object[] arr = holder.getItems().toArray();
				int size = arr.length;
				for(int i=0; i < size; i++) {
					selected=true;
					temp=arr[i].toString();
					detectRight();
					selected=false;
				}
				
				
//				addToRight(middle.getSelectionModel().getSelectedItem());
//				delElemsClick(middle, midElems);
			});
			
			moveAllMid.setOnAction((event) -> {
				
				Object[] arr = holder.getItems().toArray();
				int size = arr.length;
				for(int i=0; i < size; i++) {
					selected=true;
					temp=arr[i].toString();
					detectMiddle();
					selected=false;
				}
//				addToRight(middle.getSelectionModel().getSelectedItem());
//				delElemsClick(middle, midElems);
			});
			
			delAll.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
//				delElemsClick(middle, midElems);
				holder.getItems().clear();
				selected = false;
			});
			
			delMenu.getItems().addAll(del, delAll);
			moveAll.getItems().addAll(moveAllLeft, moveAllRight, moveAllMid);
			moveMenu.getItems().addAll(moveLeft, moveRight, moveMid, moveAll);
			
			contextMenu.getItems().addAll(delMenu, moveMenu);
			
//			contextMenu.getItems().addAll(del, moveLeft, moveRight, moveMid, delAll);
			holder.setContextMenu(contextMenu);

		}
	}
	
	//=========================================import file chooser//
	public void importer (ActionEvent event) throws IOException {
		
		String line;
		String path = getpath(2);
		if (path.length() == 0) {
			return;
		}
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		int flag = -1;
		
			while ((line = reader.readLine()) != null) {
				String[] str = line.split("\\s+");
				if (line.length() != 0 && line.charAt(0) == '*') {
					switch(++flag) {
					case 0 :
						leftLabel.setText(str[str.length - 1]);break;
					case 1 :
						rightLabel.setText(str[str.length - 1]);break;
					}
				}
				else if (!line.equals("")) {
					switch(flag) {
					case 0 :
						left.getItems().add(line);
						this.leftElems.add(line);break;
					case 1 :
						right.getItems().add(str[0]);
						this.rightElems.add(line);break;
					case 2 :
						middle.getItems().add(str[0]);
						this.rightElems.add(line);break;
					}
					
				}
			}
		
		reader.close();
	}
	
	
	
}