package login;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import application.Main;
import application.MainController;
import database.AccSys;
import database.Account;
import database.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserInterface extends Application {
	
	AccSys sys;
	Stage window;
	String xpath;
	Document document;
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.window = primaryStage;
		window.setOnCloseRequest(e ->{
			e.consume();
			this.closeprogram();
		});
		window.setResizable(false);
		window.setTitle("Manage Your Venn");
		window.getIcons().add(new Image("icon/icon.png"));
		
		AnchorPane anchor = new AnchorPane();
		// label say hello
		Label sayhello = new Label("Hello, "+ AccSys.current.getname() + "." );
		sayhello.setFont(new Font("Time new Romans", 24));
		
		AnchorPane.setLeftAnchor(sayhello, 10.0);
		AnchorPane.setTopAnchor(sayhello, 5.0);
		
		//Button "Log Out"
		Button logout = new Button("Log Out");
		logout.setPrefWidth(70.0);
		logout.setOnAction(e ->{
			AccSys.current = null;
			window.close();
			Login login = new Login();
			login.sys = this.sys;
			try {
				login.run();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		// HBox
		HBox hb = new HBox();
		hb.getChildren().addAll(logout);
		AnchorPane.setRightAnchor(hb, 10.0);
		AnchorPane.setTopAnchor(hb, 10.0);
		
		// List View
		ListView<String> list = new ListView<>();
		AnchorPane.setTopAnchor(list, 40.0);
		AnchorPane.setLeftAnchor(list, 10.0);
		AnchorPane.setRightAnchor(list, 100.0);
		AnchorPane.setBottomAnchor(list, 10.0);
		ObservableList<String> litems = FXCollections.observableArrayList();
		xpath = "Users/User[@name='" + AccSys.current.getname() +"']/Venns";
		SAXReader reader = new SAXReader();
		document = reader.read(AccSys.filepath);
		Element venns = (Element) document.selectSingleNode(xpath);
		for (Iterator<Element> rootIter = venns.elementIterator(); rootIter.hasNext();) {
			Element venn = rootIter.next();
			litems.add(venn.getName());
		}
		list.setItems(litems);
		list.setEditable(true);
		list.setCellFactory(TextFieldListCell.forListView());
		list.setOnEditCommit(e->{
			String s = e.getNewValue();
			int i = 0;
			while (i < s.length()) {
				if (s.charAt(i++) == ' ') {
					AlertBox.display("Error", "Venn name can't contain space character");
					return;
				}
			}
			litems.set(list.getSelectionModel().getSelectedIndex(), e.getNewValue());
			try {
				this.update(litems);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		// Button's
		Button add = new Button("Add");
		add.setPrefWidth(70);

		AnchorPane.setTopAnchor(add, 60.0);
		AnchorPane.setRightAnchor(add, 10.0);
		add.setOnAction(e->{
			if (list.getItems().size() < 10) {
				if (!litems.contains("NewVenn")) {;
					litems.add("NewVenn");
				}
				else {
					for(int i = 1; i < 10; i++) {
						if (litems.contains("NewVenn (" + i + ")")) {
							continue;
						}
						list.getItems().add("NewVenn (" + i + ")");
						break;
					}
					
				}
				try {
					this.update(litems);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				AlertBox.display("Error", "You can only store at most 10 Venn graph");
			}
		});
		
		Button delete = new Button("Delete");

		AnchorPane.setTopAnchor(delete, 110.0);
		AnchorPane.setRightAnchor(delete, 10.0);
		delete.setPrefWidth(70);
		delete.setOnAction(e->{
			litems.remove(list.getSelectionModel().getSelectedIndex());
			try {
				this.update(litems);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		Button modify = new Button("Modify");
		modify.setOnAction(e->{
			if (!list.getSelectionModel().isEmpty()) {
				Main main = new Main();
				try {
					main.run(new Stage());
					MainController.sys = this.sys;
					AccSys.v = list.getSelectionModel().getSelectedItem();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				AlertBox.display("Error", "You didn't choose any venn graph.");
			}
		});
		AnchorPane.setTopAnchor(modify, 160.0);
		AnchorPane.setRightAnchor(modify, 10.0);
		
		modify.setPrefWidth(70);
		Scene scene = new Scene(anchor, 400, 200);
		anchor.getChildren().addAll(sayhello, hb, list, add, delete, modify);
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] arg) {
		launch(arg);
	}
	
	public void run(Stage primaryStage) throws Exception{
		this.start(primaryStage);
	}
	
	private void closeprogram() {
		Boolean answer = ComfirmBox.display("Attention", "Sure you want to exit?");
		if (answer) {
			this.window.close();
		}
	}
	
	private void update (ObservableList<String> items) throws Exception{
		ObservableList<String> l = FXCollections.observableArrayList(items);
		List<String> c = new ArrayList<>();
		Element venns = (Element) document.selectSingleNode(xpath);
		String ln =  null;
		String cn = null;
		for (Iterator<Element> rootIter = venns.elementIterator(); rootIter.hasNext();) {
			Element venn = rootIter.next();
			c.add(venn.getName());
		}
		for (String s : l) {
			if (!c.contains(s)) {
				ln = s;
			}
		}
		for (String s : c) {
			if (!l.contains(s)) {
				cn = s;
			}
		}
		if (ln != null && cn != null) {
			for (Iterator<Element> rootIter = venns.elementIterator(); rootIter.hasNext();) {
				Element venn = rootIter.next();
				if (venn.getName() == cn) {
					venn.setName(ln);
				}
			}
		}
		else if (ln != null) {
			venns.addElement(""+ln);
		}
		else {
			for (Iterator<Element> rootIter = venns.elementIterator(); rootIter.hasNext();) {
				Element venn = rootIter.next();
				if (venn.getName() == cn) {
					venns.remove(venn);
				}
			}
		}
		FileOutputStream out = new FileOutputStream(AccSys.filepath);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(document);
		writer.close();
	}
}
