package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//import org.junit.platform.commons.util.StringUtils;

import login.AlertBox;

public class AccSys {
	public List<Account> accounts;
	public SuperAcc superacc;
	public static String filepath = "src/main/java/config/users.xml";
	public static Record record;
	public static boolean valid = false;
	public static Account current;
	public static String v;
	
	public AccSys() throws IOException {
		accounts = new ArrayList<Account>();
		superacc = new SuperAcc("super", getpwcode("iamsuper"));
		this.accounts = this.getaccounts();
		record = new Record();
		
	}
	
	private List<Account> getaccounts() throws IOException{
		List<Account> result = new ArrayList<Account>();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(filepath);
			Element root = document.getRootElement();
			
			for (Iterator<Element> rootIter = root.elementIterator(); rootIter.hasNext();) {
				Element userElt = rootIter.next();
				Iterator<Element> InnerIter = userElt.elementIterator(); 
				Element innerEle = InnerIter.next();
				String name = innerEle.getStringValue();
				innerEle = InnerIter.next();
				long pwd = Long.parseLong(innerEle.getStringValue());
				result.add(new User(name, pwd));
			}
				
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return result;
	}
	
	public static long getpwcode(String pw) {
		long result = 0;
		for (int i = 0; i < pw.length(); i++) {
    		result += pw.charAt(i);
    	}
		return result;
	}
}
