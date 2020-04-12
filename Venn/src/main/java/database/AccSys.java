package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//import org.junit.platform.commons.util.StringUtils;

import login.AlertBox;

public class AccSys {
	public List<Account> accounts;
	public SuperAcc superacc;
	public static String filepath = "/config/users.xml";
	public static Record record;
	public static Account current;
	public static String v;
	public static String xpath;
	
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
			Document document;
			if (!checkfile()) {
				document = reader.read(this.getClass().getResource(filepath));
				System.out.print("du");
				AccSys.filepath = System.getProperty("user.dir") + "/users.xml";
				FileOutputStream out = new FileOutputStream(filepath);
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				XMLWriter writer = new XMLWriter(out, format);
				writer.write(document);
				writer.close();
			}
			else {
				AccSys.filepath = System.getProperty("user.dir") + "/users.xml";
				document = reader.read(AccSys.filepath);
			}
			
			
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
			
		}
        
		return result;
	}
	
	public boolean checkfile() {
		try {
			File file = new File(System.getProperty("user.dir") + "/users.xml");
			return file.exists();
		}
		catch(Exception e) {
			return false;
		}
	}

	public static long getpwcode(String pw) {
		long result = 0;
		for (int i = 0; i < pw.length(); i++) {
    		result += pw.charAt(i);
    	}
		return result;
	}
}
