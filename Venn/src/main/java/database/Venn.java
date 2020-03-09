package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Venn {

	public Set<Item> left;
	public Set<Item> right;
	public Set<Item> middle;
	
	public Venn(Set<Item> left,Set<Item> middle, Set<Item> right) {
		this.left = new HashSet<>(left);
		this.middle = new HashSet<>(middle);
		this.right = new HashSet<>(right);
	}
	
}
