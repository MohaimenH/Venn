package database;

public class User extends Account {

	public User(String name, long pwd) {
		super(name, pwd);
		Account.User ++;
	}
	
	@Override
	public String getname() {
		return super.name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		if (this == o) {
			return true;
		}
		Account other = (User) o;
		if (this.getname() == other.getname()) {
			return true;
		}
		
		return false;
	}
}
