package com.zsy.servlet;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.zsy.servlet.entity.Pe;
import com.zsy.servlet.entity.Pm;

public class XmlDemo {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//SAX解析
		//1、获取解析工厂
		SAXParserFactory factor = SAXParserFactory.newInstance();
		//2从解析工厂中获取解析器
		SAXParser parse = factor.newSAXParser();
		//3编写处理器
		//4加载文档Document 注册处理器
		WebHandler handler = new WebHandler();
		//5解析
		parse.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("com.zsy.servlet/p.xml"),handler);
	}
}
class WebHandler extends DefaultHandler{
	private List<Pm> pms ;
	private List<Pe> pes ;
	private Pm pm ;
	private Pe pe ;
	private String teg;
	private String tag; //存储操作标签
	private boolean isMapping = false;
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(null!=qName) {
			tag = qName; //存储标签名
			if(tag.equals("servlet")) {
				pe = new Pe();
				isMapping = false;
			}
			else if(tag.equals("servlet-mapping")) {
				pm = new Pm();
				isMapping = true;
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch,start,length).trim();
		if(null!=tag) { //处理了空
			if(isMapping) { //操作servlet-mapping
				if(tag.equals("servlet-name")) {
					pm.setName(contents);
				}
				else if(tag.equals("url-pattern")) {
					pm.addPattern(contents);
				}
			}
			else {
				if(tag.equals("servlet-name")) {
					pe.setName(contents);
				}else if(tag.equals("servlet-class")){
					pe.setClz(contents);
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(null!=qName) { 
			if(qName.equals("servlet")) {
				pes.add(pe);
			}
			else if(qName.equals("servlet-mapping")){
				pms.add(pm);
			}
		}
		tag = null; //tag丢弃了
	}

	public List<Pm> getPms() {
		return pms;
	}

	public void setPms(List<Pm> pms) {
		this.pms = pms;
	}

	public List<Pe> getPes() {
		return pes;
	}

	public void setPes(List<Pe> pes) {
		this.pes = pes;
	}


	
	
}

