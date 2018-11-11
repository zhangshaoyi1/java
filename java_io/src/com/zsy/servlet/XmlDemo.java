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
		//SAX����
		//1����ȡ��������
		SAXParserFactory factor = SAXParserFactory.newInstance();
		//2�ӽ��������л�ȡ������
		SAXParser parse = factor.newSAXParser();
		//3��д������
		//4�����ĵ�Document ע�ᴦ����
		WebHandler handler = new WebHandler();
		//5����
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
	private String tag; //�洢������ǩ
	private boolean isMapping = false;
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(null!=qName) {
			tag = qName; //�洢��ǩ��
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
		if(null!=tag) { //�����˿�
			if(isMapping) { //����servlet-mapping
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
		tag = null; //tag������
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

