package database;

public class User extends Account {

	public User(String name, long pwd) {
		super(name, pwd);
		Account.User ++;
	}

	
}
