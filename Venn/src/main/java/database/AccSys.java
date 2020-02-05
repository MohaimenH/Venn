package database;

import java.util.ArrayList;
import java.util.List;

public class AccSys {
	List<Account> accounts;
	public SuperAcc superacc;
	
	public AccSys() {
		accounts = new ArrayList<Account>();
		superacc = new SuperAcc("super", Account.gethash("iamsuper"));
	}
}
