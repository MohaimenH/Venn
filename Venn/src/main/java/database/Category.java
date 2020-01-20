package database;

import java.util.ArrayList;
import java.util.List;

import database.Item;

public class  Category {

	private List<Item> items;
	private String name;
	private CType type;
	
	public enum CType{
		Pure(1),
		Intersection(2),
		Center(3);
		
		private int num;
		private CType(int order) {
			this.num = order;
		}
	}
	
	private Category(String name, CType type) {
		this.items = new ArrayList<>();
		this.name = name;
		this.type = type;
	}
	
	public String showCategoryname() {
		return this.name;
	}
	
	public static Category newCategory(String name, CType type) {
		return new Category(name, type);
	}
	
	public void addItem(Item item) {
		this.items.add(item);
		item.hasCategory();
	}
	
	public boolean remove(Item item) {
		return this.items.remove(item);
	}
}
