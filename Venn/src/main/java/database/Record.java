package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;

public class Record {
	List<Venn> venns;
	int i;
	int size;
	
	public Record(){
		venns = new ArrayList<Venn>();
		this.i = 0;
		this.size = 0;
	}
	
	public void newop(List<Node> left,List<Node> middle,List<Node> right) {
		if (i == size) {
		venns.add(new Venn(left, middle, right));
		this.i++;
		this.size++;
		}
		else {
			venns.set(++ this.i, new Venn(left, middle, right));
			this.size = this.i;
		}
	}
	
	public Venn undo() {
		return venns.get(--i);
	}
	
	public Venn redo() {
		return venns.get(++i);
	}
}
