package com.ipensee;

import java.sql.Timestamp;

public class Rss {
	private String title;
	private String content;
	private String link;
	private Timestamp publicDate;
	private int rssResourceId;
	
	public int getRssResourceId() {
		return rssResourceId;
	}
	public void setRssResourceId(int rssResourceId) {
		this.rssResourceId = rssResourceId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public Timestamp getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Timestamp publicDate) {
		this.publicDate = publicDate;
	}
	
}
