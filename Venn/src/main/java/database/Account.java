package database;

import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public abstract class Account {
	String name;
	long pwd;
	public static int User = 0;
	public List<Record> venns;
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
// SAX reader method
//class MyHander extends DefaultHandler{
//
//	@Override
//	public void endElement(String uri, String localName, String qName) throws SAXException {
//		
//	}
//	
//	@Override
//	public void characters(char[] ch, int start, int length) throws SAXException {
//		String value = new String(ch, start, length);
//	}
//
//	@Override
//	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//		
//	}
//	
//}