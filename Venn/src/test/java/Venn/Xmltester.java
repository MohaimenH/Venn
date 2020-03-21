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
	        // 指定文本的写出的格式：
			FileOutputStream out =new FileOutputStream(AccSys.filepath);
	        OutputFormat format=OutputFormat.createPrettyPrint();   //漂亮格式：有空格换行
	        format.setEncoding("UTF-8");
	        
	        //1.创建写出对象
	        XMLWriter writer=new XMLWriter(out,format);
	           
	        //2.写出Document对象
	        writer.write(document);
	        
	        //3.关闭流
	        writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
