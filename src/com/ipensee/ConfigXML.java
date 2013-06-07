package com.ipensee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ConfigXML {
	public static HashMap<String, String> getConfig(String xml) {
		SAXBuilder sax = new SAXBuilder();
		HashMap<String, String> config = new HashMap<String, String>();
		
		try {
			Document doc = sax.build(xml);
			
			List childList = doc.getRootElement().getChildren();
			
			Iterator listIt = childList.iterator();
			
			while (listIt.hasNext()) {
				Element element = (Element) listIt.next();
				
				config.put(element.getName(), element.getValue());
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
}
