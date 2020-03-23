package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;

public class Venn {

	public List<Item> nodes;
	
	public static class Item {
		public Object id;
		public double x;
		public double y;
		
		public Item(Node node) {
			id = node;
			x = node.getLayoutX();
			y = node.getLayoutY();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			else if(obj == null  || this.getClass() != obj.getClass()) {
				return false;
			}
			Item other = (Item) obj;
			if (this.id == other.id) {
				return true;
			}
			return false;
		}
		
	}
	
	public Venn(List<Node> nodes) {
		this.nodes = new ArrayList<>();
		for (Node n: nodes) {
			this.nodes.add(new Item(n));
		}
	}
	
}
