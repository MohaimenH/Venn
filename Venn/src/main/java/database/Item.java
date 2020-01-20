package database;

public class Item {

	private String name;
	private boolean attribute;
	
	private Item(String name) {
		this.name = name;
		this.attribute = false;
	}
	
	public static Item newItem(String name) {
		return new Item(name);
	}
	
	public void hasCategory() {
		this.attribute = true;
	}
	
	public void exitCategory() {
		this.attribute = false;
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
