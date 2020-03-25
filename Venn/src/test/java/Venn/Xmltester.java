package Venn;

import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import database.AccSys;

public class Xmltester {

	public static void main(String[] args) {
		Xmltester test = new Xmltester();
		test.inilist();
	}

	public void inilist() {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(AccSys.filepath);
			
			
			Element ele = (Element) document.selectSingleNode("Users/User[1]//Venn");
			FileOutputStream out =new FileOutputStream(AccSys.filepath);
	        OutputFormat format=OutputFormat.createPrettyPrint(); 
	        format.setEncoding("UTF-8");
	        
	        XMLWriter writer=new XMLWriter(out,format);
	           
	        writer.write(document);
	        
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
