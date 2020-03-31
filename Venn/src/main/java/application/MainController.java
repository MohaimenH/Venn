package application;

import java.awt.AWTException;
import java.awt.Desktop;
//import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;

//import database.AccSys;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import database.AccSys;
import database.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.SnapshotParameters;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MainController {
	boolean control;
	boolean zKey;
	//////////////////////// stack stuff
	int stackPointer = -1;
	int[] stack = new int[100];
	/////////////////////////
	double leftCircleSize = 261;
	double rightCircleSize = 261;
	/////////////////////////
	double x;
	double y;
	String left_label_String;
	String right_label_string;
	Boolean selected = false;
	String temp;
	int index;
	public static AccSys sys;
	static ContextMenu menuBarContextMenu = new ContextMenu();
	static boolean isDark = false;
	private ArrayList<Node> deleteID = new ArrayList<>();// holds id's of things that need to be deleted in all sets
	private ArrayList<Node> deleteIDLeft = new ArrayList<>();// holds id's of things that need to be deleted in all sets
	private ArrayList<Node> deleteIDRight = new ArrayList<>();// holds id's of things that need to be deleted in all
																// sets
	private ArrayList<Node> deleteIDIntersection = new ArrayList<>();// holds id's of things that need to be deleted in
																		// all sets

	private ArrayList<Object> free = new ArrayList<>();// holds array of text objects

	private Set<String> elements = new HashSet<>(); // All elements
	private Set<String> leftElems = new HashSet<>(); // Set A for 2 Sets Version ||| Set B for 3 Sets Version
	private Set<String> rightElems = new HashSet<>(); // Set B for 2 Sets Version ||| Set C for 3 Sets Version
	private Set<String> midElems = new HashSet<>(); // Intersection of all sets

//	ArrayList<String> elements = new ArrayList<>();
//	ArrayList<String> leftElems = new ArrayList<>();
//	ArrayList<String> rightElems = new ArrayList<>();
//	ArrayList<String> midElems = new ArrayList<>();

	//////////////////
	// leftCircle Center: x:570 y:398 r:261
	// right Circle center x:805 y:398 r:261
	//////////////////

	// @FXML
	// private Circle setA;

	// @FXML
	// private Circle setA;
	@FXML
	private Cursor mouse;

	@FXML
	private MenuButton TestMenu;
	@FXML
	private Label File_menu;
	@FXML
	private Label Edit_menu;
	@FXML
	private Label Export_menu;
	@FXML
	private Label About_menu;
	@FXML
	private AnchorPane MainAnchor;// the good one
	@FXML
	private AnchorPane secondAnchor;
	@FXML
	private Label title;
	@FXML
	private Button left_label_button;
	@FXML
	private Button right_label_button;
//	@FXML
//	private Button clearLeft;
//	@FXML
//	private Button clearRight;
	@FXML
	private Circle setA;
	@FXML
	private Circle setB;
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
	@FXML
	private TextField leftTextArea;
	@FXML
	private TextField rightTextArea;
	@FXML
	private TextField titleTextArea;
	@FXML
	private TextField referenceTextField;
	@FXML
	private ListView<String> referenceListView;
	@FXML
	private Label dragDropDummyText;

	// =============================================//
	@FXML
	private ListView<Text> testList;
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
	private ListView<String> top;
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
	private Label thing;

	@FXML
	private Label GlobalRef;

	@FXML
	private ResourceBundle resources;

	int elementNum = 0;
	int LeftNumOfElements = 0;
	int RightNumOfElements = 0;
	int count = 0;
	int LeftCount = 0;
	int RightCount = 0;
	int IntersectionCount = 0;
	Boolean MoveLeft = false;
	Boolean MoveRight = false;
	Boolean MoveIntersection = false;
	Boolean MoveAllLeft = false;
	Boolean MoveAllRight = false;
	Boolean MoveAllIntersection = false;
	Boolean ActionInCircle = false;// true if the user wants to move something inside the sets
	Boolean InLeft = false;
	Boolean InRight = false;
	Boolean InIntersection = false;
	Boolean AutoLeft = false;
	Boolean AutoRight = false;

	// =============================================//

	public MainController() {
		leftTextArea = new TextField();
		this.leftTextArea.setOpacity(0);
	}

	@FXML
	private void initialize() {

	}

	// ==============================================//Undo Stack
	/*
	 * table of operations 0:no operation 1:move to left 2:move to middle 3:move to
	 * right 4:delete left 5:delete middle 6:delete right 7.change label 1 8:change
	 * label 2 9:change title 10:change color of left 11:change color of right
	 * 12:clear set
	 */

	public void twoSetSelected(ActionEvent Event) throws IOException {
		Parent twoSets = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene twoSetScene = new Scene(twoSets);
		Stage Window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		Window.setScene(twoSetScene);
		Window.show();
	}

	public void threeSetSelected(ActionEvent Event) throws IOException {
		Parent threeSets = FXMLLoader.load(getClass().getResource("/application/TripleSet.fxml"));
		Scene threeSetScene = new Scene(threeSets);
		Stage Window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		Window.setScene(threeSetScene);
		Window.show();
	}

	public void goBackToSelect(ActionEvent Event) throws IOException {
		Parent selectScene = FXMLLoader.load(getClass().getResource("/application/SelectScene.fxml"));
		Scene SelectScene = new Scene(selectScene);
		Stage Window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		Window.setScene(SelectScene);
		Window.show();
	}

	public int size() {
		return stackPointer + 1;
	}

	public boolean isEmpty() {
		return (stackPointer == -1);
	}

	public void push(int x) {
		if (size() == stack.length) {
			System.out.println("stack if full,do something");
		}
		stack[++stackPointer] = x;
	}

	public int top() {
		if (isEmpty()) {
			return 0;
		}
		return stack[stackPointer];
	}

	public int pop() {
		int temp = stack[stackPointer];
		stack[stackPointer] = 0;
		stackPointer--;
		return temp;
	}

	public void view() {
		int n = size();
		for (int i = 0; i < n; i++) {
			System.out.print(" " + stack[i]);
		}
		System.out.println("");

	}

	@FXML
	public void detectUndo(KeyEvent e) {// on key pressed of main anchor
		if (e.getCode() == KeyCode.Z) {
			zKey = true;
			System.out.println("Pressed z");
		} else if (e.getCode() == KeyCode.CONTROL) {
			control = true;
			System.out.println("Pressed ctrl");

		}
		if (zKey && control) {
			undo(stack);
		}
	}

	@FXML
	public void resetBooleansForUndo(KeyEvent e) {// on button released for main anchor pane
		if (e.getCode() == KeyCode.Z) {
			zKey = false;
		} else if (e.getCode() == KeyCode.CONTROL) {
			control = false;
		}
	}

	public void undo(int[] thing) {// interprets the opcode passed to if from the stack
		int op = 0;
		if (stackPointer == -1) {
			System.out.println("nothing to undo");
		} else {
			op = thing[stackPointer];

		}

		if (op == 1) {
			System.out.println("Moved to left: opposite is move back to holder");
			pop();
		} else if (op == 2) {
			System.out.println("Moved to right: opposite is is move back to holder ");
			pop();

		} else if (op == 3) {
			System.out.println("Moved to middle: opposite is is move back to holder");
			pop();
		} else if (op == 4) {
			System.out.println("delete from left: opposite is add back to left ");
			pop();

		} else if (op == 5) {
			System.out.println("delete from middle: opposite is add back to middle");
			pop();
		} else if (op == 6) {
			System.out.println("delete from right: opposite is add back to right");
			pop();
		}
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

//		set.remove(list.getText());
//		set.remove(temp);
	}

	public void delElemsHelper(Label test, Set<String> set) {
		// index = list.getSelectionModel().getSelectedIndex();
		// temp = list.getItems().get(index);
		// list.getItems().remove(temp);

		set.remove(test.getText());
		set.remove(temp);
	}

	public void addToRight(String temp) {
		if (notBlank(temp)) {
			right.getItems().add(temp);
			rightElems.add(temp);
		}
	}

	public void addToLeft(String temp) {
		if (notBlank(temp)) {
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
		if (count < 0) {
			count = 0;
		} else {
			count++;// counts the number of elements in the holder
		}
		if (notBlank(place)) {
			holder.getItems().add(place);
			inputText.clear();
			elements.add(place);
		}
	}

	@FXML
	public void handle(KeyEvent keyEvent) {// checks for button enter
		if (keyEvent.getCode() == KeyCode.ENTER) {
			printOutput();
		}
	}

	// ===========================================// Deleting Elements Using 'Del'
	// Key

	@FXML
	public void keyPressLeft(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
//			index = left.getSelectionModel().getSelectedIndex();
//			leftElems.remove(left.getItems().get(index));
//			left.getItems().remove(index);
//			index=0;

			deleteIDLeft.remove(GlobalRef);
			MainAnchor.getChildren().remove(GlobalRef);

			// delElemsHelper(left, leftElems);
		}
	}

	public void keyPressRight(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
//			index = right.getSelectionModel().getSelectedIndex();
//			rightElems.remove(right.getItems().get(index));
//			right.getItems().remove(index);
//			index=0;

			deleteIDRight.remove(GlobalRef);
			MainAnchor.getChildren().remove(GlobalRef);

			// delElemsHelper(right, rightElems);
		}
	}

//
	public void keyPressMiddle(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.DELETE) {
			// Remove element
//			index = middle.getSelectionModel().getSelectedIndex();
//			midElems.remove(middle.getItems().get(index));
//			middle.getItems().remove(index);
//			index=0;

			deleteIDIntersection.remove(GlobalRef);
			MainAnchor.getChildren().remove(GlobalRef);

			// delElemsHelper(middle, midElems);
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
			temp = holder.getItems().get(index);
			selected = true;
			detectMiddle(null);
			holder.getItems().remove(temp);
			selected = false;
		}

		if (keyEvent.getCode() == KeyCode.L && holder.getItems().isEmpty() == false) {
			index = holder.getSelectionModel().getSelectedIndex();
			temp = holder.getItems().get(index);
			selected = true;
			detectLeft(null);
			holder.getItems().remove(temp);
			selected = false;
		}

		if (keyEvent.getCode() == KeyCode.R && holder.getItems().isEmpty() == false) {
			index = holder.getSelectionModel().getSelectedIndex();
			temp = holder.getItems().get(index);
			selected = true;
			detectRight(null);
			holder.getItems().remove(temp);
			selected = false;
		}
	}
	// =============================================//Drag/Drop Detection

	@FXML
	private void detectDrop() {

		selected = true;

		System.out.println("dropped");

	}

	@FXML
	private void detectRelease() {
		System.out.print("released");
	}

	@FXML
	private void detactDrag() {
		System.out.print("drag");// do some mouse stuff here
		// MainAnchor.getScene().setCursor(mouse.CLOSED_HAND);
		index = holder.getSelectionModel().getSelectedIndex();
		selected = true;
		temp = holder.getSelectionModel().getSelectedItem();
	}

	private void MovableText(MouseEvent e, Label input, int n) {
		Label test = new Label();// makes a new label object

		String id = elementNum + "";// label id
		deleteID.add(test);// adds label to a list in the case it needs to be deleted
		test.setId(id);// set id

		if (input == null) {// handles text setting if normal drag, import or right click option
			if (MoveAllLeft || MoveAllRight || MoveAllIntersection) {
				String tempMoveAll = this.holder.getItems().get(n);
				test.setText(tempMoveAll);
				System.out.println("Text" + tempMoveAll);
			}

			else {
				int i = holder.getSelectionModel().getSelectedIndex();// get text from master list
				String t = holder.getItems().get(i);
				test.setText(t);// set object text
			}
		} else {
			test.setText(input.getText());
			test.setFont(input.getFont());
			test.setLayoutX(input.getLayoutX());
			test.setLayoutY(input.getLayoutY());
		}

		free.add(test);// add label object to list, consider removing bc it may be useless now
		String s = test.getText();// text of label created above, for convienence

		if (MoveLeft) {// handles right click movement/shortcuts between holder and sets
			if (AutoLeft || AutoRight) {
				intersectionHelper(test);

			} else {
				double x = left.getLayoutX();
				double y = left.getLayoutY();
				test.setLayoutX(x + 300);
				test.setLayoutY(y + 100 + (20 * LeftCount + 1));
				LeftCount++;
				deleteIDLeft.add(test);// node set for left, mainly used for deletion
				deleteIDRight.remove(test);// node set for right
				leftElems.add(s);// string set for left
				rightElems.remove(s);
			}
			MoveLeft = false;// reset

		} else if (MoveRight) {
			if (AutoLeft || AutoRight) {
				intersectionHelper(test);

			} else {
				double x = right.getLayoutX();
				double y = right.getLayoutY();
				test.setLayoutX(x + 250);
				test.setLayoutY(y + 100 + (20 * RightCount));
				RightCount++;
				leftElems.remove(s);
				rightElems.add(s);
				deleteIDLeft.remove(test);
				deleteIDRight.add(test);
			}
			MoveRight = false;

		} else if (MoveIntersection) {
			double x = 600.0;
			double y = right.getLayoutY();
			test.setLayoutX(x);

			test.setLayoutY(y + 100 + (20 * IntersectionCount));
			IntersectionCount = IntersectionCount++;

			deleteIDIntersection.add(test);
			deleteIDLeft.remove(test);
			deleteIDRight.remove(test);
			midElems.add(s);
			leftElems.remove(s);
			rightElems.remove(s);
			MoveIntersection = false;
		} else if (MoveAllLeft) {
			if (AutoLeft || AutoRight) {
				intersectionHelper(test);

			} else {
				double x = left.getLayoutX();
				double y = left.getLayoutY();
				test.setLayoutX(x + 300);
				test.setLayoutY(y + 100 + (20 * LeftCount + 1));
				LeftCount++;
				deleteIDLeft.add(test);// node set for left, mainly used for deletion
				System.out.println(deleteIDLeft.get(n));// test
				leftElems.add(s);// string set for left
			}
		} else if (MoveAllRight) {
			if (AutoLeft || AutoRight) {
				intersectionHelper(test);

			} else {
				double x = right.getLayoutX();
				double y = right.getLayoutY();
				test.setLayoutX(x + 250);
				test.setLayoutY(y + 100 + (20 * RightCount));
				RightCount++;
				rightElems.add(s);
				deleteIDRight.add(test);
			}
		} else if (MoveAllIntersection) {
			double x = 600.0;
			double y = right.getLayoutY();
			test.setLayoutX(x);
			test.setLayoutY(y + 100 + (20 * IntersectionCount));
			IntersectionCount = IntersectionCount++;
			deleteIDIntersection.add(test);
			midElems.add(s);
		} else {// handles norma drag and stuff
			if (InLeft) {

				deleteIDLeft.add(test);
				leftElems.add(s);
				if (AutoLeft || AutoRight) {
					intersectionHelper(test);

				}
				InLeft = false;
			} else if (InRight) {
				deleteIDRight.add(test);
				rightElems.add(s);
				if (AutoLeft || AutoRight) {
					intersectionHelper(test);

				}
				InRight = false;
			} else {
				deleteIDIntersection.add(test);
				midElems.add(s);
				if (AutoLeft || AutoRight) {
					intersectionHelper(test);

				}
				InIntersection = false;

			}
			if (input == null) {
				test.setLayoutX(e.getSceneX());// default drag and drop
				test.setLayoutY(e.getSceneY());
			}

		}

		test.setOnMousePressed(event -> {// handles right click for individual label objects
			// MainAnchor.getScene().setCursor(mouse.CLOSED_HAND);

			String hold = test.getId();
			GlobalRef = test;// testing: global pointer to the currently selected moveable text thing
			if (deleteIDLeft.contains(test)) {
				menuLeft(event, test);
			} else if (deleteIDRight.contains(test)) {
				menuRight(event, test);
			} else if (deleteIDIntersection.contains(test)) {
				menuMiddle(event, test);
			}

		});

		test.setOnMouseReleased(event -> {// allows us to drag anywhere,here we can do detection into other sets
			if (event.getButton() == MouseButton.PRIMARY) {
				x = event.getSceneX();
				y = event.getSceneY();
				test.setLayoutX(x);
				test.setLayoutY(y);
				// MainAnchor.getScene().setCursor(mouse.OPEN_HAND);
			}
		});
		if (AutoLeft == false && AutoRight == false) {
			MainAnchor.getChildren().add(test);// ads to the main anchor,
		} else {
			AutoLeft = false;
			AutoRight = false;
		}
		elementNum++;// number of actual text elements create since start of program

	}

	// ==============================================// Set Operations
	private void MoveAllLeft(MouseEvent e) {// calles MovableText() n times, n being the number of elements in the
											// holder
		int s = this.holder.getItems().size();

		MoveAllLeft = true;
		for (int i = 0; i < s; i++) {
			MovableText(e, null, i);
		}
		this.holder.getItems().clear();
		MoveAllLeft = false;

	}

	private void MoveAllRight(MouseEvent e) {
		int s = this.holder.getItems().size();

		MoveAllRight = true;
		for (int i = 0; i < s; i++) {
			MovableText(e, null, i);
		}
		this.holder.getItems().clear();
		MoveAllRight = false;
	}

	private void MoveAllIntersection(MouseEvent e) {
		int s = this.holder.getItems().size();

		MoveAllIntersection = true;
		for (int i = 0; i < s; i++) {
			MovableText(e, null, i);
		}
		this.holder.getItems().clear();
		MoveAllIntersection = false;
	}

	private void intersectionHelper(Label test) {// makes new label element for the auto intersection
		String tempID = test.getId();
		String tempText = test.getText();

		Label temp = new Label();
		temp.setText(tempText);
		temp.setId(tempID);
		temp.setLayoutX(600);
		temp.setLayoutY(right.getLayoutY() + 100 + (20 * IntersectionCount));
		IntersectionCount++;
		deleteID.add(temp);
		deleteIDIntersection.add(temp);
		MainAnchor.getChildren().add(temp);// ads to the main anchor,

		/////////// because we create a new label object, it needs new event listeners

		temp.setOnMousePressed(event -> {// handles right click for individual label objects
			// MainAnchor.getScene().setCursor(mouse.CLOSED_HAND);

			String hold = temp.getId();
			GlobalRef = temp;// testing: global pointer to the currently selected moveable text thing
			if (deleteIDLeft.contains(temp)) {
				menuLeft(event, temp);
			} else if (deleteIDRight.contains(temp)) {
				menuRight(event, temp);
			} else if (deleteIDIntersection.contains(temp)) {
				menuMiddle(event, temp);
			}

		});

		temp.setOnMouseReleased(event -> {// allows us to drag anywhere,here we can do detection into other sets
			if (event.getButton() == MouseButton.PRIMARY) {
				x = event.getSceneX();
				y = event.getSceneY();
				temp.setLayoutX(x);
				temp.setLayoutY(y);
				// MainAnchor.getScene().setCursor(mouse.OPEN_HAND);
			}
		});

		///////// now we remove the orogional 2 duplicates from the main anchor
		if (AutoRight) {
			Node a = deleteIDLeft.get(deleteIDLeft.size() - 1);
			int first = MainAnchor.getChildren().indexOf(a);

			MainAnchor.getChildren().remove(first);

		}
		if (AutoLeft) {
			Node b = deleteIDRight.get(deleteIDRight.size() - 1);
			int second = MainAnchor.getChildren().indexOf(b);
			MainAnchor.getChildren().remove(second);

		}

		if (deleteIDLeft.size() > 0 && deleteIDRight.size() > 0) {
			deleteIDLeft.remove(deleteIDLeft.size() - 1);
			deleteIDRight.remove(deleteIDRight.size() - 1);

		}
		// MainAnchor.getChildren().add(test);//ads to the main anchor,
		// elementNum++;
	}

	private void InCircleActions(MouseEvent mouseEvent, Label test) {
		String s = test.getText();
		if (MoveRight) {
			deleteIDLeft.remove(test);
			deleteIDRight.add(test);
			leftElems.remove(s);
			rightElems.add(s);
			double x = right.getLayoutX();
			double y = right.getLayoutY();
			test.setLayoutX(x + 250);
			test.setLayoutY(y + 100 + (20 * RightCount));
			RightCount++;
			LeftCount--;
			MoveRight = false;
		} else if (MoveLeft) {
			leftElems.add(s);
			rightElems.remove(s);
			deleteIDLeft.add(test);
			deleteIDRight.remove(test);
			double x = left.getLayoutX();
			double y = left.getLayoutY();
			test.setLayoutX(x + 300);
			test.setLayoutY(y + 100 + (20 * LeftCount + 1));
			LeftCount++;
			RightCount--;
			MoveLeft = false;// reset
		} else if (MoveIntersection) {
			deleteIDIntersection.add(test);
			midElems.add(s);
			double x = 600.0;
			double y = right.getLayoutY();
			test.setLayoutX(x);

			test.setLayoutY(y + 100 + (20 * IntersectionCount));
			IntersectionCount++;
			LeftCount--;
			RightCount--;
			MoveIntersection = false;

		}
	}

	@FXML
	private void detectLeft(MouseEvent e) {

		if (selected && (leftElems.contains(temp) != true) && (midElems.contains(temp) != true) && notBlank(temp)) {

			if (rightElems.contains(temp)) {
				AutoLeft = true;// flag for auto intersection

				detectMiddle(e);

			}

			else {
				push(1);// push action to stack, 1: move to right set
				view();

				/////////////////////// movable text
				// MainAnchor.getScene().setCursor(mouse.OPEN_HAND);//cool cursor

				InLeft = true;
				MovableText(e, null, 0);
				///////////////////////

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
	private void detectRight(MouseEvent e) {

		if (selected && (rightElems.contains(temp) != true) && (midElems.contains(temp) != true) && notBlank(temp)) {

			if (leftElems.contains(temp)) {
				AutoRight = true;// flag for auto intersection
				detectMiddle(e);

			}

			else {
				push(2);
				view();
				//////////////////////// movable text
				InRight = true;
				MovableText(e, null, 0);
				////////////////////////

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
	private void detectMiddle(MouseEvent e) {
		if (selected && (midElems.contains(temp) != true) && notBlank(temp)) {
			push(3);
			view();
			////////////////// movable text things
			InIntersection = true;
			MovableText(e, null, 0);
			/////////////////
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
	// =============================================// Menu bar

	@FXML
	public void Menubar_File(MouseEvent e) {

		menuBarContextMenu.hide();
		menuBarContextMenu.getItems().clear();

		if (e.getButton() == MouseButton.PRIMARY) {

//			ContextMenu contextMenu = new ContextMenu();
			MenuItem Import = new MenuItem("Import Save File (.xml)");
//			MenuItem Snapshot=new MenuItem("Snapshot");

			Import.setOnAction((event) -> {
//				System.out.print("Import Clicked");
				try {
					importer(event);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			});

			menuBarContextMenu.getItems().addAll(Import);
//			File_menu.setContextMenu(contextMenu);
			menuBarContextMenu.show(File_menu, e.getScreenX(), e.getScreenY());
//			e.consume();
		}

//		e.consume();
//		System.out.print(e.isConsumed());
	}

	@FXML
	public void Menubar_edit(MouseEvent e) {
//		e.consume();

		menuBarContextMenu.hide();
		menuBarContextMenu.getItems().clear();

		if (e.getButton() == MouseButton.PRIMARY) {
//			ContextMenu contextMenu = new ContextMenu();
			MenuItem WipeClean = new MenuItem("Clear All");

			Menu title = new Menu("Diagram Title");
			MenuItem changeTitle = new MenuItem("Change Title");
			MenuItem changeTitleColor = new MenuItem("Change Color");
			MenuItem changeTitleFontSize = new MenuItem("Change Font Size");
			MenuItem changeTitleFont = new MenuItem("Change Font");

			Menu setA = new Menu(leftLabel.getText());
			MenuItem changeSetACircleColor = new MenuItem("Change Set Color");
			MenuItem changeNameA = new MenuItem("Change Label");
			MenuItem changeSetAColor = new MenuItem("Change Label Color");
			MenuItem changeSetAFontSize = new MenuItem("Change Label Size");
			MenuItem changeSetAFont = new MenuItem("Change Label Font");
			MenuItem changeSetASize = new MenuItem("Change Size");

			Menu setB = new Menu(rightLabel.getText());
			MenuItem changeSetBCircleColor = new MenuItem("Change Set Color");
			MenuItem changeSetBSize = new MenuItem("Change Size");
			MenuItem changeNameB = new MenuItem("Change Label");
			MenuItem changeSetBColor = new MenuItem("Change Label Color");
			MenuItem changeSetBFontSize = new MenuItem("Change Label Size");
			MenuItem changeSetBFont = new MenuItem("Change Label Font");

			WipeClean.setOnAction((event) -> {
				popUpClearElems("all");
				push(12);
				view();
				clearLeftSet();
				clearRightSet();
				System.out.print("clear all clicked");
				int len = deleteID.size();
				for (int i = 0; i < len; i++) {
					MainAnchor.getChildren().remove(deleteID.get(i));
					LeftCount = RightCount = IntersectionCount = 0;
				}
				deleteID.clear();
				elementNum = 0;
			});

			changeTitle.setOnAction((event) -> {
				popUpChangeTitle();
			});

			changeTitleColor.setOnAction((event) -> {
				popUpChangeTitleColor();
			});

			changeTitleFontSize.setOnAction((event) -> {
				popUpChangeTitleSize();
			});

			changeTitleFont.setOnAction((event) -> {
				popUpChangeTitleFont();
			});

			changeSetACircleColor.setOnAction((event) -> {
				popUpChangeSetACircleColor();
			});

			changeNameA.setOnAction((event) -> {
				popUpLeft();
			});

			changeSetAColor.setOnAction((event) -> {
				popUpChangeSetAColor();
			});

			changeSetAFontSize.setOnAction((event) -> {
				popUpChangeSetASize();
			});

			changeSetAFont.setOnAction((event) -> {
				popUpChangeSetAFont();
			});

			changeSetASize.setOnAction((event) -> {
				circleSlider("left");
			});

			changeSetBCircleColor.setOnAction((event) -> {
				popUpChangeSetBCircleColor();
			});

			changeNameB.setOnAction((event) -> {
				popUpRight();
			});

			changeSetBColor.setOnAction((event) -> {
				popUpChangeSetBColor();
			});

			changeSetBFontSize.setOnAction((event) -> {
				popUpChangeSetBSize();
			});

			changeSetBFont.setOnAction((event) -> {
				popUpChangeSetBFont();
			});

			changeSetBSize.setOnAction((event) -> {
				circleSlider("right");
			});

			title.getItems().addAll(changeTitle, changeTitleColor, changeTitleFontSize, changeTitleFont);
			setA.getItems().addAll(changeSetACircleColor, changeSetASize, changeNameA, changeSetAColor,
					changeSetAFontSize, changeSetAFont);
			setB.getItems().addAll(changeSetBCircleColor, changeSetBSize, changeNameB, changeSetBColor,
					changeSetBFontSize, changeSetBFont);

			menuBarContextMenu.getItems().addAll(WipeClean, title, setA, setB);

			menuBarContextMenu.show(Edit_menu, e.getScreenX(), e.getScreenY());

//			contextMenu.getItems().addAll(WipeClean);
//			Edit_menu.setContextMenu(contextMenu);
//			contextMenu.show(Edit_menu, e.getScreenX(), e.getScreenY());

//			e.consume();
		}
//		e.consume();
//		System.out.print(e.isConsumed());
	}

	@FXML
	public void Menubar_export(MouseEvent e) {
//		e.consume();
		menuBarContextMenu.hide();
		menuBarContextMenu.getItems().clear();
		if (e.getButton() == MouseButton.PRIMARY) {
//			ContextMenu contextMenu=new ContextMenu();

			MenuItem Snapshot = new MenuItem("As JPEG Image (.jpg)");
			MenuItem Export = new MenuItem("As Readble Report (.txt)");
			MenuItem Exportxml = new MenuItem("As Save File (.xml)");

			Export.setOnAction((event) -> {
				try {
					exportAsText();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

			Snapshot.setOnAction((event) -> {
				System.out.print("Snapshot Clicked");
				try {
					takeSnapshot();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			});

			Exportxml.setOnAction(event -> {
				try {
					this.exportAsXML();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});

//			contextMenu.getItems().addAll(Export);
			menuBarContextMenu.getItems().addAll(Snapshot, Export, Exportxml);
//			Export_menu.setContextMenu(contextMenu);
			menuBarContextMenu.show(Export_menu, e.getScreenX(), e.getScreenY());
//			contextMenu.show(Export_menu, e.getScreenX(), e.getScreenY());
//			e.consume();

		}

//		e.consume();
//		System.out.print(e.isConsumed());

	}

	@FXML
	public void Menubar_About(MouseEvent e) {

		menuBarContextMenu.hide();
		menuBarContextMenu.getItems().clear();

		if (e.getButton() == MouseButton.PRIMARY) {

//			ContextMenu contextMenu = new ContextMenu();
			MenuItem fontList = new MenuItem("Show Available Fonts");
			MenuItem projDevs = new MenuItem("Project Developers");
			MenuItem darkMode = new MenuItem("Switch to Dark Mode");

			if (isDark) {
				darkMode.setText("Switch to Light Mode");
			}

			MenuItem imageToSets = new MenuItem("Image to Sets");

//			MenuItem userman = new MenuItem("User Manual");
//			MenuItem proj = new MenuItem("URL");

//			MenuItem Snapshot=new MenuItem("Snapshot");

			fontList.setOnAction((event) -> {
				Stage popupwindow = new Stage();

				popupwindow.initModality(Modality.APPLICATION_MODAL);
				popupwindow.setTitle("Available Fonts");

				ListView<String> fontsAvailable = new ListView<String>();

				fontsAvailable.getItems().addAll(Font.getFamilies());

				VBox layout = new VBox(5);

				layout.getChildren().addAll(fontsAvailable);
				layout.setAlignment(Pos.CENTER);

				Scene scene1 = new Scene(layout, 300, 400);

				popupwindow.setScene(scene1);

				popupwindow.showAndWait();

			});

			projDevs.setOnAction((event) -> {
				popUpNames();
			});

			darkMode.setOnAction((event) -> {
				switchToDark();
			});

//			imageToSets.setOnAction((event) -> {
//				imageToSets();
//			});
//			
//			userman.setOnAction((event) -> {
//				File htmlFile = new File("UserMan.html");
//				try {
//					Desktop.getDesktop().browse(htmlFile.toURI());
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			});
//			
//			proj.setOnAction((event) -> {
//				try {
//					browse();
//				} catch (URISyntaxException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			});

			menuBarContextMenu.getItems().addAll(projDevs, fontList, darkMode);

//			File_menu.setContextMenu(contextMenu);
			menuBarContextMenu.show(About_menu, e.getScreenX(), e.getScreenY());
//			e.consume();
		}
	}
	// =============================================//label customization

	@FXML
	public void detectLabelClick(MouseEvent e) {
		if (e.getButton() == MouseButton.SECONDARY) {
			ContextMenu contextMenu = new ContextMenu();
			MenuItem editLeftLabel = new MenuItem("Edit left label");

			editLeftLabel.setOnAction((event) -> {
				System.out.print("yes");
			});

			contextMenu.getItems().addAll(editLeftLabel);
			leftLabel.setContextMenu(contextMenu);
		}
		if (e.getButton() == MouseButton.PRIMARY) {
			leftLabel.setOpacity(0);
			this.leftLabel.setDisable(true);
			leftname();

		}
	}

	@FXML
	public void detectTitleClick(MouseEvent e) {
		if (e.getButton() == MouseButton.SECONDARY) {
			ContextMenu contextMenu = new ContextMenu();
			MenuItem editTitleColor = new MenuItem("Change Title Color");
			MenuItem editTitleSize = new MenuItem("Change Title Size");

			editTitleColor.setOnAction((event) -> {
				popUpChangeTitleColor();
			});

			editTitleSize.setOnAction((event) -> {
				popUpChangeTitleSize();
			});

			contextMenu.getItems().addAll(editTitleColor, editTitleSize);
			title.setContextMenu(contextMenu);
		}
//		if (e.getButton() == MouseButton.PRIMARY) {
//			title.setOpacity(0);
//			this.title.setDisable(true);
//			leftname();
//			
//		}
	}

	@FXML
	public void detectLabel2Click(MouseEvent e) {

		if (e.getButton() == MouseButton.PRIMARY) {
			rightLabel.setOpacity(0);
			this.rightLabel.setDisable(true);
			rightname();

		}
	}

	@FXML
	public void leftname() {

		this.leftTextArea.setDisable(false);
		this.leftTextArea.setOpacity(1);
		this.leftTextArea.requestFocus();
		this.leftTextArea.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				this.leftLabel.setText(this.leftTextArea.getText());
				this.leftTextArea.setOpacity(0);
				leftLabel.setOpacity(1);
				leftTextArea.setText("");
				this.leftTextArea.setDisable(true);
				this.leftTextArea.setOpacity(0);
				this.leftLabel.setDisable(false);
			}

		});
		;

	};

	@FXML
	public void rightname() {

		this.rightTextArea.setDisable(false);
		this.rightTextArea.setOpacity(1);
		this.rightTextArea.requestFocus();
		this.rightTextArea.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				this.rightLabel.setText(this.rightTextArea.getText());
				this.rightTextArea.setOpacity(0);
				rightLabel.setOpacity(1);
				leftTextArea.setText("");

				this.rightTextArea.setDisable(true);
				this.rightTextArea.setOpacity(0);
				this.rightLabel.setDisable(false);
			}

		});
		;

	};

	@FXML
	public void donothing() {

	}

//	@FXML
//	private void setLeftLabel() {
//		String left = left_label_input.getText();
//		leftLabel.setText(left);
//		left_label_input.clear();
//	}

	private void setLeftLabel(String name) {
		leftLabel.setText(name);
//		left_label_input.clear();
	}

//	@FXML
//	private void setRightLabel() {
//		String right = right_label_input.getText();
//		rightLabel.setText(right);
//		right_label_input.clear();
//	}

	private void setRightLabel(String name) {
		rightLabel.setText(name);
//		right_label_input.clear();
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

//	@FXML
	private void setLabelFontSize(Label label, double size) {
		label.setFont(Font.font(size));
	}

	private void setLabelFont(Label label, String family) {
		if (notBlank(family)) {
			label.setFont(Font.font(family));

		}
	}

	private void setLabelColor(Label label, String c) {

		label.setTextFill(Color.valueOf(c));
	}

	private void setLabelColor(Label label, Color c) {

		label.setTextFill(c);
	}

	private void setTitle(String name) {
		title.setText(name);
	}

	// ==============================================// Export Options
	@FXML
	public void exportAsXML() throws Exception {
		DocumentFactory dbf = DocumentFactory.getInstance();
		// 创建DocumentBuilder对象
		Document doc = dbf.createDocument();
		Element root = dbf.createElement("Venn");
		doc.setRootElement(root);
		root.setName("Venn");
		for (Node n : this.deleteID) {
			Label l = (Label) n;
			Element sub = root.addElement(l.getText());
			if (this.deleteIDLeft.contains(n)) {
				sub.addAttribute("belong", "left");
			} else if (this.deleteIDRight.contains(n)) {
				sub.addAttribute("belong", "right");
			} else if (this.deleteIDIntersection.contains(n)) {
				sub.addAttribute("belong", "intersection");
			}
			sub.addElement("x").setText(l.getLayoutX() + "");
			sub.addElement("y").setText(l.getLayoutY() + "");
			Element font = sub.addElement("font");
			font.setText(l.getFont().getStyle());
			font.addAttribute("size", l.getFont().getSize() + "");
		}

		String path = "";
		try {
			path = this.getpath(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (path.length() == 0) {
			return;
		}

		try {
			FileOutputStream out = new FileOutputStream(path);

			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {

		}

	}

	@FXML
	public void exportAsText() throws IOException {
//		int arr=0;
//		String a = leftElems.iterator().next();
		String path = this.getpath(0);
		if (path.length() == 0) {
			return;
		}

		FileWriter writer = new FileWriter(path); // Change to Your Directory of Choice - Preferably Desktop
//		ArrayList<Object> a = new ArrayList<>(leftElems.toArray());

		writer.write("*Unique Elements of " + this.leftLabel.getText() + System.lineSeparator());
		writer.write("\n");
		for (int i = 0; i < this.deleteIDLeft.size(); i++) {
			writer.write(((Label) this.deleteIDLeft.get(i)).getText() + System.lineSeparator());
		}

//		System.out.println("=============================");

//		String h = "Unique Elements of Set B:";

		writer.write("\n\n");
		writer.write("*Unique Elements of " + this.rightLabel.getText() + System.lineSeparator());
		writer.write("\n");
		for (int i = 0; i < this.deleteIDRight.size(); i++) {
			writer.write(((Label) this.deleteIDRight.get(i)).getText() + System.lineSeparator());
		}

//		System.out.println("=============================");

//		System.out.println("Intersection of Set A & Set B:");
		writer.write("\n\n");
		writer.write("*Intersection of " + leftLabel.getText() + " & " + rightLabel.getText() + System.lineSeparator());
		writer.write("\n");
		for (int i = 0; i < this.deleteIDIntersection.size(); i++) {
			writer.write(((Label) this.deleteIDIntersection.get(i)).getText() + System.lineSeparator());
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
		File selectedFile = new File("");
		if (i == 0) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
			selectedFile = fileChooser.showSaveDialog(mainStage);

		} else if (i == 1) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
					new FileChooser.ExtensionFilter("PNG", "*.png"));
			selectedFile = fileChooser.showSaveDialog(mainStage);
		} else if (i == 2) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
			selectedFile = fileChooser.showSaveDialog(mainStage);
		} else if (i == 3) {
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
			selectedFile = fileChooser.showOpenDialog(mainStage);
		}

		String path = "";

		try {
			return path = selectedFile.getPath();
		} catch (NullPointerException e) {

		}
		return path;
	}

	@FXML
	public void takeSnapshot() throws IOException, AWTException {
//		WritableImage snap = new WritableImage(1000,611);

		WritableImage snap = new WritableImage(781, 624);

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
		 * Robot abc = new Robot(); Rectangle screenRect = new Rectangle(934,611);
		 * BufferedImage snap = abc.createScreenCapture(screenRect);
		 */
	}

	// ============================================// Right Click Menus

	public void menuLeft(MouseEvent mouseEvent, Label test) {
		if (mouseEvent.getButton() == MouseButton.SECONDARY && (left.getItems().size() > 0)) {

//			System.out.println("RIGHT CLICK!");

			ContextMenu contextMenu = new ContextMenu();
			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");

			MenuItem editLabel = new MenuItem("Edit Element");
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");
			MenuItem delAll = new MenuItem("Delete All Elements In This Set");

			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsHelper(test, leftElems);

				int id = Integer.parseInt(test.getId());
				MainAnchor.getChildren().remove(test);

			});

			moveRight.setOnAction((event) -> {
				addToRight(test.getText());// VERY IMPORTANT, adds to other set for later use
				delElemsHelper(test, leftElems);
				MoveRight = true;
				InCircleActions(mouseEvent, test);

			});

			moveMid.setOnAction((event) -> {
				addToMiddle(test.getText());
				delElemsHelper(test, leftElems);
				MoveIntersection = true;
				InCircleActions(mouseEvent, test);
			});

			delAll.setOnAction((event) -> {
				popUpClearElems("left");
				int size = deleteIDLeft.size();
				for (int i = 0; i < size; i++) {
					MainAnchor.getChildren().remove(deleteIDLeft.get(i));
					LeftCount = 0;
				}
			});

			editLabel.setOnAction((event) -> {
				popUpLabelsEdit(test);
			});

			delMenu.getItems().addAll(del, delAll);
			moveMenu.getItems().addAll(moveMid, moveRight);

			contextMenu.getItems().addAll(editLabel, delMenu, moveMenu);

//			contextMenu.getItems().addAll(del, moveMid, moveRight, delAll);
			test.setContextMenu(contextMenu);

		}
	}

	public void menuRight(MouseEvent mouseEvent, Label test) {
		if (mouseEvent.getButton() == MouseButton.SECONDARY && (right.getItems().size() > 0)) {

//			System.out.println("RIGHT CLICK!");

			ContextMenu contextMenu = new ContextMenu();

			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");

			MenuItem editLabel = new MenuItem("Edit Element");
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveMid = new MenuItem("Move to Intersection");
			MenuItem delAll = new MenuItem("Delete All Elements In This Set");

			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsHelper(test, rightElems);
				int id = Integer.parseInt(test.getId());
				MainAnchor.getChildren().remove(test);
			});

			moveLeft.setOnAction((event) -> {
				addToLeft(test.getText());
				delElemsHelper(test, rightElems);
				MoveLeft = true;
				InCircleActions(mouseEvent, test);
			});

			moveMid.setOnAction((event) -> {
				addToMiddle(test.getText());
				delElemsHelper(test, rightElems);
				MoveIntersection = true;
				InCircleActions(mouseEvent, test);
			});

			delAll.setOnAction((event) -> {
				popUpClearElems("right");
				int size = deleteIDRight.size();
				for (int i = 0; i < size; i++) {
					MainAnchor.getChildren().remove(deleteIDRight.get(i));
					RightCount = 0;
				}
			});

			editLabel.setOnAction((event) -> {
				popUpLabelsEdit(test);
			});

			delMenu.getItems().addAll(del, delAll);
			moveMenu.getItems().addAll(moveMid, moveLeft);

			contextMenu.getItems().addAll(editLabel, delMenu, moveMenu);

//			contextMenu.getItems().addAll(del, moveMid, moveLeft, delAll);
			test.setContextMenu(contextMenu);

		}
	}

	public void menuMiddle(MouseEvent mouseEvent, Label test) {
		if (mouseEvent.getButton() == MouseButton.SECONDARY && (middle.getItems().size() > 0)) {

//			System.out.println("RIGHT CLICK!");

			ContextMenu contextMenu = new ContextMenu();

			Menu delMenu = new Menu("Delete Elements");
			Menu moveMenu = new Menu("Move Elements");

			MenuItem editLabel = new MenuItem("Edit Element");
			MenuItem del = new MenuItem("Delete Element");
			MenuItem moveLeft = new MenuItem("Move to First Set");
			MenuItem moveRight = new MenuItem("Move to Second Set");
			MenuItem delAll = new MenuItem("Delete All Elements In This Set");

			del.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
				delElemsHelper(test, midElems);
				int id = Integer.parseInt(test.getId());
				MainAnchor.getChildren().remove(test);
			});

			moveLeft.setOnAction((event) -> {
				addToLeft(test.getText());
				delElemsHelper(test, midElems);
				MoveLeft = true;
				InCircleActions(mouseEvent, test);

			});

			moveRight.setOnAction((event) -> {
				addToRight(test.getText());
				delElemsHelper(test, midElems);
				MoveRight = true;
				InCircleActions(mouseEvent, test);
			});

			delAll.setOnAction((event) -> {
				popUpClearElems("mid");
				int size = deleteIDIntersection.size();
				for (int i = 0; i < size; i++) {
					MainAnchor.getChildren().remove(deleteIDIntersection.get(i));
					IntersectionCount = 0;
				}
			});

			editLabel.setOnAction((event) -> {
				popUpLabelsEdit(test);
			});

			delMenu.getItems().addAll(del, delAll);
			moveMenu.getItems().addAll(moveLeft, moveRight);

			contextMenu.getItems().addAll(editLabel, delMenu, moveMenu);

//			contextMenu.getItems().addAll(del,moveLeft, moveRight, delAll);
			test.setContextMenu(contextMenu);

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

				index = holder.getSelectionModel().getSelectedIndex();
				holder.getItems().remove(index);
				count--;
				if (count < 0) {
					count = 0;
				}
				selected = false;
			});

			moveLeft.setOnAction((event) -> {
//				addToLeft(middle.getSelectionModel().getSelectedItem());
				selected = true;
				temp = holder.getSelectionModel().getSelectedItem();
				MoveLeft = true;// must be before detectLeft call!!!!!
				detectLeft(mouseEvent);
				LeftCount++;

//				delElemsClick(middle, midElems);
				selected = false;
			});

			moveRight.setOnAction((event) -> {
				selected = true;
				temp = holder.getSelectionModel().getSelectedItem();
				MoveRight = true;
				detectRight(mouseEvent);
				RightCount++;
				selected = false;
//				addToRight(middle.getSelectionModel().getSelectedItem());
//				delElemsClick(middle, midElems);
			});

			moveMid.setOnAction((event) -> {
				selected = true;
				temp = holder.getSelectionModel().getSelectedItem();
				MoveIntersection = true;
				detectMiddle(mouseEvent);
				IntersectionCount++;
				selected = false;

			});

			moveAllLeft.setOnAction((event) -> {
				Object[] arr = holder.getItems().toArray();
				///////////////////////////// method MoveAll testing
				MoveAllLeft(mouseEvent);// calls helper method for moving everything to left
				/////////////////////////////

			});

			moveAllRight.setOnAction((event) -> {

				MoveAllRight(mouseEvent);
			});

			moveAllMid.setOnAction((event) -> {

				MoveAllIntersection(mouseEvent);
			});

			delAll.setOnAction((event) -> {
//			    System.out.println("Delete clicked!");
//				delElemsClick(middle, midElems);
				holder.getItems().clear();
				selected = false;
				count = 0;
			});

			delMenu.getItems().addAll(del, delAll);
			moveAll.getItems().addAll(moveAllLeft, moveAllRight, moveAllMid);
			moveMenu.getItems().addAll(moveLeft, moveRight, moveMid, moveAll);

			contextMenu.getItems().addAll(delMenu, moveMenu);

//			contextMenu.getItems().addAll(del, moveLeft, moveRight, moveMid, delAll);
			holder.setContextMenu(contextMenu);

		}
	}

	// =========================================import file chooser//
	public void importer(ActionEvent event) throws IOException {

		String line;
		String path = getpath(3);
		if (path.length() == 0) {
			return;
		}
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			Element root = doc.getRootElement();

			for (Iterator<Element> rootIter = root.elementIterator(); rootIter.hasNext();) {
				Element venn = rootIter.next();

				if (checkcontain(venn.getName())) {
					Label label = new Label();
					String name = venn.getName();
					String set = venn.attributeValue("belong");
					Double x = Double.parseDouble((venn.element("x").getText()));
					Double y = Double.parseDouble((venn.element("y").getText()));
					Font font = new Font(venn.element("font").getText(),
							Double.parseDouble(venn.element("font").attributeValue("size")));

					label.setText(name);
					label.setLayoutX(x);
					label.setLayoutY(y);
					label.setFont(font);
					if (set.equals("left")) {
						this.deleteIDLeft.add(label);
					} else if (set.equals("right")) {
						this.deleteIDRight.add(label);
					} else if (set.equals("intersection")) {
						this.deleteIDIntersection.add(label);
					}
					this.deleteID.add(label);
					this.MovableText(null, label,0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method check duplication: Return flase if contain : true if not contain
	 */
	private boolean checkcontain(String name) {
		for (Node n : this.deleteID) {
			Label l = (Label) n;
			if (l.getText().equals(name)) {
				return false;
			}
		}
		return true;
	}

	// =========================================Label Changer pop-ups//

	public void popUpLeft() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Label For Set A");

		Label label1 = new Label("Please Enter A Name For The Set:");

		TextField test = new TextField();
		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				setLeftLabel(test.getText());
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Name");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				setLeftLabel(test.getText());
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 100);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpRight() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Label For Set B");

		Label label1 = new Label("Please Enter A Name For The Set:");

		TextField test = new TextField();
		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				setRightLabel(test.getText());
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Name");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				setRightLabel(test.getText());
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 100);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	// ======================================== Title Change Pop-ups
	public void popUpChangeTitle() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Title of Diagram");

		Label label1 = new Label("Please Enter A Name For The Venn Diagram:");

		TextField test = new TextField();
		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				setTitle(test.getText());
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Name");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				setTitle(test.getText());
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 100);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeTitleSize() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Font Size of Title");

		Label current = new Label("Current Font Size Is " + title.getFont().getSize());
		Label label1 = new Label("Please Enter A Font Size: ");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFontSize(title, Double.valueOf(test.getText()));
				}

				catch (Exception e) {

					// TODO

				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Font Size");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFontSize(title, Double.valueOf(test.getText()));
				}

				catch (Exception e) {
					// TODO

				}
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeTitleFont() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Font of Title");

		Label current = new Label("Current Font Is " + title.getFont().getFamily());
		Label label1 = new Label("Please Enter A Font Name: ");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {

					setLabelFont(title, test.getText());
					setLabelFontSize(title, 30);

				}

				catch (Exception e) {
					// TODO

				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Font");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFont(title, test.getText());
					setLabelFontSize(title, 30);
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetASize() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Font Size of Label");

		Label current = new Label("Current Font Size Is " + leftLabel.getFont().getSize());
		Label label1 = new Label("Please Enter A Font Size: ");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFontSize(leftLabel, Double.valueOf(test.getText()));
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Font Size");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFontSize(leftLabel, Double.valueOf(test.getText()));
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetBSize() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Font Size of Label");

		Label current = new Label("Current Font Size Is " + rightLabel.getFont().getSize());
		Label label1 = new Label("Please Enter A Font Size: ");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFontSize(rightLabel, Double.valueOf(test.getText()));
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Font Size");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFontSize(rightLabel, Double.valueOf(test.getText()));
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetAFont() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Font of Title");

		Label current = new Label("Current Font Is " + leftLabel.getFont().getFamily());
		Label label1 = new Label("Please Enter A Font Name: ");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFont(leftLabel, test.getText());
					setLabelFontSize(leftLabel, 25);
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Font");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFont(leftLabel, test.getText());
					setLabelFontSize(leftLabel, 25);
				}

				catch (Exception e) {
					// TODO

				}
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetBFont() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Font of Title");

		Label current = new Label("Current Font Is " + rightLabel.getFont().getFamily());
		Label label1 = new Label("Please Enter A Font Name: ");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFont(rightLabel, test.getText());
					setLabelFontSize(rightLabel, 25);
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Font");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setLabelFont(rightLabel, test.getText());
					setLabelFontSize(rightLabel, 25);
				}

				catch (Exception e) {
					// TODO

				}
			}
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeTitleColor() {

		Stage popupwindow = new Stage();
		Color initialColor = (Color) title.getTextFill();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Color of Title");

		HBox current = colorName(title.getTextFill().toString());
		Label label1 = new Label("Please Choose A Color: ");

		ColorPicker cp = new ColorPicker((Color) title.getTextFill());

		EventHandler<ActionEvent> cpEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Color c = cp.getValue();
				setLabelColor(title, c);
			}
		};

		cp.setOnAction(cpEvent);

		Button button1 = new Button("Set Color");

		button1.setOnAction((event) -> {
			popupwindow.close();
		});

		button1.setOnKeyPressed((keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ESCAPE) {
				title.setTextFill(initialColor);
			}
			popupwindow.close();
		}));

		popupwindow.setOnCloseRequest((event) -> {
			title.setTextFill(initialColor);
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, cp, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetAColor() {

		Color initialColor = (Color) leftLabel.getTextFill();

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Color");

		HBox current = colorName(leftLabel.getTextFill().toString());
		VBox test = colorPicker(leftLabel);

		Button button1 = new Button("Set Color");

		button1.setOnAction((event) -> {
			popupwindow.close();
		});

		button1.setOnKeyPressed((keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ESCAPE) {
				leftLabel.setTextFill(initialColor);
			}
			popupwindow.close();
		}));

		popupwindow.setOnCloseRequest((event) -> {
			leftLabel.setTextFill(initialColor);
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetBColor() {

		Color initialColor = (Color) rightLabel.getTextFill();

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Color");

		HBox current = colorName(rightLabel.getTextFill().toString());
		VBox test = colorPicker(rightLabel);

		Button button1 = new Button("Set Color");

		button1.setOnAction((event) -> {
			popupwindow.close();
		});

		button1.setOnKeyPressed((keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ESCAPE) {
				rightLabel.setTextFill(initialColor);
			}
			popupwindow.close();
		}));

		popupwindow.setOnCloseRequest((event) -> {
			rightLabel.setTextFill(initialColor);
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, test, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public void popUpChangeSetACircleColor() {

		double initialOp = setA.getOpacity();

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Color of Set");

		HBox current = colorName(setA.getFill().toString());

		Label opacity = new Label(" (Opacity: " + (Math.round(setA.getOpacity() * 100.0) / 100.0) + ") ");
		current.getChildren().add(2, opacity);

		Label label1 = new Label("Please Enter A Color: ");
		Label label2 = new Label("Use Slider Below To Adjust Opacity: ");

		Slider opacitySlider = circleOpacitySlider("left");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setA.setFill(Color.valueOf(test.getText()));
				}

				catch (Exception e) {
					// ExceptionCaught
				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Color / Opacity");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setA.setFill(Color.valueOf(test.getText()));
				}

				catch (Exception e) {
					// ExceptionCaught
				}
			}
			popupwindow.close();
		});

		button1.setOnKeyPressed((event) -> {
			setA.setOpacity(initialOp);
			popupwindow.close();
		});

		popupwindow.setOnCloseRequest((event) -> {
			setA.setOpacity(initialOp);
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, label2, opacitySlider, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 310, 200);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();
	}

	public void popUpChangeSetBCircleColor() {

		double initialOp = setB.getOpacity();

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Color of Set");

		HBox current = colorName(setB.getFill().toString());
		Label opacity = new Label(" (Opacity: " + (Math.round(setB.getOpacity() * 100.0) / 100.0) + ") ");
		current.getChildren().add(2, opacity);
		Label label1 = new Label("Please Enter A Color: ");

		Label label2 = new Label("Use Slider Below To Adjust Opacity: ");

		Slider opacitySlider = circleOpacitySlider("right");

		TextField test = new TextField();

		test.setAlignment(Pos.CENTER);
		test.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setB.setFill(Color.valueOf(test.getText()));
				}

				catch (Exception e) {
					// ExceptionCaught
				}
			}
			popupwindow.close();
		});

		Button button1 = new Button("Set Color / Opacity");

		button1.setOnAction((event) -> {
			if (notBlank(test.getText())) {
				try {
					setB.setFill(Color.valueOf(test.getText()));
				}

				catch (Exception e) {
					// ExceptionCaught
				}
			}
			popupwindow.close();
		});

		button1.setOnKeyPressed((event) -> {
			setB.setOpacity(initialOp);
			popupwindow.close();
		});

		popupwindow.setOnCloseRequest((event) -> {
			setB.setOpacity(initialOp);
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(current, label1, test, label2, opacitySlider, button1);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 310, 200);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();
	}

	public void popUpNames() {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Project Developers");

		Label dev = new Label("This project was developed by: ");
		Label Mohaimen = new Label("Mohaimen Hassan");
		Label Luke = new Label("Luke Linigari");
		Label Ratul = new Label("Ratul Momen");
		Label Jiahao = new Label("Jiahao Li");

		VBox layout = new VBox(10);

		layout.getChildren().addAll(dev, Mohaimen, Luke, Ratul, Jiahao);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 150);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();
	}

	public void popUpClearElems(String set) {

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Clear elements");

		Label label = new Label("Are you sure you want to clear the element(s)?");

		VBox layout = new VBox(2);
		HBox layout2 = new HBox(2);

		Button button1 = new Button("Yes");

		button1.setOnAction((event) -> {
			if (set.equals("all")) {
				clearLeftSet();
				clearRightSet();
				popupwindow.close();
			}

			if (set.equals("left")) {
				clearLeftSet();
				popupwindow.close();
			}

			if (set.equals("right")) {
				clearRightSet();
				popupwindow.close();
			}

			if (set.equals("mid")) {
				middle.getItems().clear();
				midElems.clear();
				popupwindow.close();
			}
		});

		Button button2 = new Button("No");
		button2.setOnAction((event) -> {
			popupwindow.close();
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
	}

	public void imageToSets() {
		Image img = new Image("icon/fb.jpg");
		Image img2 = new Image("icon/twitter.jpg");

		setA.setFill(new ImagePattern(img));
		setB.setFill(new ImagePattern(img2));
	}

	public void switchToDark() {

		if (isDark == false) {
			isDark = true;
			MainAnchor.setBackground(
					new Background(new BackgroundFill(Color.gray(0.3), CornerRadii.EMPTY, Insets.EMPTY)));
			secondAnchor.setBackground(
					new Background(new BackgroundFill(Color.gray(0.3), CornerRadii.EMPTY, Insets.EMPTY)));

			setLabelColor(leftLabel, Color.gray(0.8));
			setLabelColor(rightLabel, Color.gray(0.8));
			setLabelColor(title, Color.gray(0.8));
			setLabelColor(dragDropDummyText, Color.gray(0.8));
			if (setB.getFill().equals(Color.RED)) {
				setB.setFill(Color.DARKSEAGREEN);
			}
//				setA.setStroke(Color.GREY);
//				setB.setStroke(Color.GREY);

			holder.setBackground(new Background(new BackgroundFill(Color.gray(0.6), CornerRadii.EMPTY, Insets.EMPTY)));
			inputText.setBackground(
					new Background(new BackgroundFill(Color.gray(0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		}

		else {
			isDark = false;
			MainAnchor.setBackground(
					new Background(new BackgroundFill(Color.web("0xf4f4f4ff"), CornerRadii.EMPTY, Insets.EMPTY)));
			secondAnchor.setBackground(
					new Background(new BackgroundFill(Color.web("0xf4f4f4ff"), CornerRadii.EMPTY, Insets.EMPTY)));

			setLabelColor(leftLabel, Color.gray(0));
			setLabelColor(rightLabel, Color.gray(0));
			setLabelColor(title, Color.gray(0));
			setLabelColor(dragDropDummyText, Color.web("#515151"));

			if (setB.getFill().equals(Color.DARKSEAGREEN)) {
				setB.setFill(Color.RED);
			}

			holder.setBackground(referenceListView.getBackground());
			inputText.setBackground(referenceTextField.getBackground());
		}

	}

	public void circleSlider(String set) {
		Circle temp;

		if (set.equals("left")) {
			temp = setA;
		}

		else {
			temp = setB;
		}

		leftCircleSize = setA.getRadius();
		rightCircleSize = setB.getRadius();

		Slider circleSize = new Slider();

		circleSize.setMax(280);
		circleSize.setMin(180);
		circleSize.setValue(temp.getRadius());

		circleSize.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				temp.setRadius(newValue.doubleValue());
			}

		});

		Stage popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Change Size of Circle");

		Label prompt = new Label("Use the slider below to adjust the size of the circle:");

		Button button1 = new Button("Done");

		button1.setOnAction((event) -> {
			popupwindow.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(prompt, circleSize, button1);
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(20);

		Scene scene1 = new Scene(layout, 400, 120);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public Slider circleOpacitySlider(String set) {

		Circle temp;

		if (set.equals("left")) {
			temp = setA;
		}

		else {
			temp = setB;
		}

		Slider circleOpacity = new Slider();

		circleOpacity.setMax(0.8);
		circleOpacity.setMin(0.3);
		circleOpacity.setValue(temp.getOpacity());

		circleOpacity.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				temp.setOpacity(newValue.doubleValue());
			}

		});

		return circleOpacity;

	}

	public void popUpLabelsEdit(Label label) {

		Stage popupwindow = new Stage();
		TabPane tabPane = new TabPane();

		Tab tab0 = new Tab("Text");
		Tab tab1 = new Tab("Color");
		Tab tab2 = new Tab("Font");
		tab0.setClosable(false);
		tab1.setClosable(false);
		tab2.setClosable(false);

		tabPane.getTabs().add(tab0);
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);

		// Text Changes
		Label label2 = new Label("Please Enter New Text");

		TextField newText = new TextField();

		newText.setAlignment(Pos.CENTER);
		newText.setOnAction((event) -> {
			if (notBlank(newText.getText())) {
				try {
					label.setText(newText.getText());
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		Button button2 = new Button("Set Text");

		button2.setOnAction((event) -> {
			if (notBlank(newText.getText())) {
				try {
					label.setText(newText.getText());
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		VBox textVBox = new VBox(3);

		textVBox.setSpacing(10);
		textVBox.setAlignment(Pos.CENTER);
		textVBox.setPadding(new Insets(20));
		textVBox.getChildren().addAll(label2, newText, button2);

		tab0.setContent(textVBox);

		// Color changes

		Color initialColor = (Color) label.getTextFill();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Edit Element");

		HBox current = colorName(label.getTextFill().toString());

		VBox test = colorPicker(label);

		Button button1 = new Button("Set Color");

		button1.setOnAction((event) -> {
			popupwindow.close();
		});

		button1.setOnKeyPressed((keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ESCAPE) {
				label.setTextFill(initialColor);
			}
			popupwindow.close();
		}));

		popupwindow.setOnCloseRequest((event) -> {
			label.setTextFill(initialColor);
			popupwindow.close();
		});

		VBox colorVBox = new VBox(3);

		colorVBox.setSpacing(10);
		colorVBox.setAlignment(Pos.CENTER);
		colorVBox.setPadding(new Insets(20));
		colorVBox.getChildren().addAll(current, test, button1);

		tab1.setContent(colorVBox);

		// Font Size Change

		Label currentFont = new Label("Current Font Size Is " + label.getFont().getSize());
		Label fontSizeLabel = new Label("Please Enter A Font Size: ");

		TextField fontSize = new TextField();

		fontSize.setAlignment(Pos.CENTER);
		fontSize.setOnAction((event) -> {
			if (notBlank(fontSize.getText())) {
				try {
					setLabelFontSize(label, Double.valueOf(fontSize.getText()));
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		Button fontButton = new Button("Set Font Size");

		button1.setOnAction((event) -> {
			if (notBlank(fontSize.getText())) {
				try {
					setLabelFontSize(label, Double.valueOf(fontSize.getText()));
				}

				catch (Exception e) {
					// TODO
				}
			}
			popupwindow.close();
		});

		VBox fontVBox = new VBox(3);

		fontVBox.setSpacing(10);
		fontVBox.setAlignment(Pos.CENTER);
		fontVBox.setPadding(new Insets(20));
		fontVBox.getChildren().addAll(currentFont, fontSizeLabel, fontSize, fontButton);

		tab2.setContent(fontVBox);

		VBox layout = new VBox(tabPane);

//			layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 200);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();

	}

	public HBox colorName(String color) {

		HBox colorHBox = new HBox(3);

		Label currentColor = new Label("Current Color: ");
		currentColor.setStyle("-fx-font-weight: bold");

//			Rectangle colorBlock = new Rectangle(25,15);
		Circle colorBlock = new Circle(7);

		colorBlock.setFill(Color.valueOf(color));

		colorHBox.getChildren().addAll(currentColor, colorBlock);
		colorHBox.setAlignment(Pos.CENTER);

		return colorHBox;

	}

	public VBox colorPicker(Label label) {

		Label label1 = new Label("Please Choose A Color: ");

		ColorPicker cp = new ColorPicker((Color) label.getTextFill());

		EventHandler<ActionEvent> cpEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Color c = cp.getValue();
				setLabelColor(label, c);
			}
		};

		cp.setOnAction(cpEvent);

		VBox layout = new VBox(3);
		layout.getChildren().addAll(label1, cp);
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(10);

		return layout;
	}
//	public void browse() throws URISyntaxException { 
//		Desktop desktop = Desktop.getDesktop();
//		try {
//			URI url = new URI("http://google.com");
//			desktop.browse(url);
//		}
//		catch (Exception e) {
//			//random changes
//		}
//		
//	}

}