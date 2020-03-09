package database;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Item {
	
	Label id;
	private String name;
	private int x;
	private int y;
	
	public Item(Label id, String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass() ) {
			return false;
		}
		if (this == other) {
			return true;
		}
		Item next = (Item) other;
		if (this.name.equals(next.name)){
			return true;
		}
		return false;
	}
	
	public String getname() {
		return this.name;
	}
}
