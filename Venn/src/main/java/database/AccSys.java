package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;

import login.AlertBox;

public class AccSys {
	public List<Account> accounts;
	public SuperAcc superacc;
	
	public AccSys() throws IOException {
		accounts = new ArrayList<Account>();
		superacc = new SuperAcc("super", Account.gethash("iamsuper"));
		this.accounts = this.getaccounts();
		
	}
	
	private List<Account> getaccounts() throws IOException{
		List<Account> result = new ArrayList<Account>();
		String filepath = "src/main/java/database/users.txt";
		File file = new File(filepath);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
            	String [] arr = s.split("\\s+");
            	String name = arr[0];
            	long pwd = Long.parseLong(arr[1]);
                accounts.add(new User(name, pwd));
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
		return result;
	}
}
