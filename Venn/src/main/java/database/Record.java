package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;

public class Record {
	public List<Venn> venns;
	public int i;
	public int size;
	
	public Record(){
		venns = new ArrayList<Venn>();
		this.i = -1;
		this.size = 0;
	}
	
	public void newop(List<Node> nodes) {
		this.venns.add(new Venn(nodes));
		this.i++;
		this.size = i + 1;
	}
	
	public Venn undo() {
		if (i == 0) {
			i = -1;
			return null;
		}
		return venns.get(i--);
	}
	
	public Venn redo() {
		return venns.get(++i);
	}
}
