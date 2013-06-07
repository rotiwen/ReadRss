package com.ipensee.webservice.client;

//import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.ipensee.Rss;

public class Parameter {
	public static final String TITLE           = "title";
	public static final String CONTENT         = "content";
	public static final String LINK            = "link";
	public static final String PUBLIC_DATE     = "public_date";
	public static final String RSS_RESOURCE_ID = "rss_resource_id";
	public static final String SERVICE_ID      = "serviceid";
	
	private Rss rss;
//	private SimpleDateFormat format;
	
	public Parameter(Rss rss) {
		this.rss = rss;
//		this.format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public HashMap<String, String> getParameter() {
		HashMap<String, String> parameter = new HashMap<String, String>();
		
		parameter.put(TITLE, rss.getTitle());
		parameter.put(CONTENT, rss.getContent());
		parameter.put(LINK, rss.getLink());
		parameter.put(PUBLIC_DATE, rss.getPublicDate().toString());
		parameter.put(RSS_RESOURCE_ID, String.valueOf(rss.getRssResourceId()));
		parameter.put(SERVICE_ID, WebServiceInfo.SERVICE_ID);
		
		return parameter;
	}

}
