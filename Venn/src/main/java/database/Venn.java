package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;

public class Venn {

	public List<Item> left;
	public List<Item> right;
	public List<Item> middle;
	
	public class Item {
		public double x;
		public double y;
		
		public Item(Node node) {
			x = node.getLayoutX();
			y = node.getLayoutY();
		}
	}
	
	public Venn(List<Node> left,List<Node> middle, List<Node> right) {
		this.left = new ArrayList<>();
		this.middle = new ArrayList<>();
		this.right = new ArrayList<>();
		
		for (Node l : left) {
			this.left.add(new Item(l));
		}
		
		for (Node r : left) {
			this.right.add(new Item(r));
		}
		
		for (Node m : left) {
			this.middle.add(new Item(m));
		}
	}
	
}
