package database;

import java.util.List;
import java.util.Set;

import database.Category;
import database.Category.CType;
import database.Item;

import java.util.ArrayList;
import java.util.HashSet;

public class Venn {

	private int noc;
	private String name;
	private Category[] categories;
	private Set<String> namecheck;
	private List<Item> items;
	
	private Venn(String name, int noc) {
		if (noc != 2 || noc != 3) {
			throw new IllegalArgumentException();
		}
		this.noc = noc;
		this.name = name;
		this.categories = new Category[Venn.NoC(noc)];
		this.namecheck = new HashSet<>();
		this.items = new ArrayList<>();
	}
	
	public static Venn newVenn(String name, int noc) {
		return new Venn(name, noc);
	}
	
	public String showname() {
		return this.name;
	}
	
	private void initializeCatNames(String C1, String C2, String C3) {
		for (int i = 0; i < this.categories.length; i ++) {
			switch (i) {
			case 0:
				this.categories[i] = Category.newCategory(C1, CType.Pure);
				break;
			case 1:
				this.categories[i] = Category.newCategory(C2, CType.Pure);
				break;
			case 2:
				if (this.noc == 2) {
					this.categories[i] = Category.newCategory(C1 + "&" + C2, CType.Intersection);
				}
				else {
					this.categories[i] = Category.newCategory(C3, CType.Pure);
				}
				break;
            case 3:
            	this.categories[i] = Category.newCategory(C1 + "&" + C2, CType.Intersection);
				break;
			case 4:
				this.categories[i] = Category.newCategory(C2 + "&" + C3, CType.Intersection);
				break;
			case 5:
				this.categories[i] = Category.newCategory(C1 + "&" + C3, CType.Intersection);
				break;
			case 6:
				this.categories[i] = Category.newCategory("Center", CType.Center);
				break;
			}
		}
	}
	
	private static int NoC(int n) {
		if (n == 2) {
			return 3;
		}
		else {
			return 7;
		}
	}
	
	public void insertItem(String name) {
		if (this.namecheck.add(name)) {
			System.out.println("This item is already existed");
		}
		else {
			this.items.add(Item.newItem(name));
		}
	}
	
	public boolean removeItem(String name) {
		if (this.namecheck.remove(name)) {
			System.out.println("This item does not already exist");
			return false;
		}
		else {
			return this.items.remove(Item.newItem(name));
		}
	}
	
	public static boolean changeC(Item item, Category C1, Category C2) {
		C2.addItem(item);
		return C1.remove(item);
	}
}
