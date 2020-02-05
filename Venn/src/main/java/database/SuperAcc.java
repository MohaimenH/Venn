package database;

public class SuperAcc extends Account {

	public SuperAcc(String name, long pwd) {
		super(name, pwd);
	}
	public String getname() {
		return this.name;
	}
}
