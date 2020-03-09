package database;

import java.io.File;
import java.util.List;

public abstract class Account {
	String name;
	long pwd;
	public static int User = 0;
	public List<Venn> venn;
	public Account(String name, long pwd) {
		this.name = name;
		this.pwd = pwd;
		User ++;
	}
	
//	public static long gethash(String p) {
//		long result = 0L;
//		for (int i = 0; i < p.length(); i++) {
//			result += p.charAt(i);
//		}
//		return result;
//	}
	
	public String getname() {
		return this.name;
	};
	
	public long getpwd() {
		return this.pwd;
	}
}
