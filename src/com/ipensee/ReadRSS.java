package com.ipensee;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class ReadRSS extends Thread {
	private boolean isStop = false;
	private List<Rss> rssList = new ArrayList<Rss>();
	
	public List<Rss> getRssList() {
		return rssList;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
//	public static void printRss(URL rssUrl) {		
//		URL feedSource = rssUrl;
//		
//		SyndFeedInput input = new SyndFeedInput();
//		SyndFeed feed = null;
//		try {
//			feed = input.build(new XmlReader(feedSource));
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (FeedException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		List<SyndEntry> syndEntries = (List<SyndEntry>) feed.getEntries();
//		for (SyndEntry syndEntry : syndEntries) {
//			String entryTitle = syndEntry.getTitle();
//			String entryLink = syndEntry.getLink();
//			SyndContent entryDescription = syndEntry.getDescription();
//			String content = entryDescription.getValue().replaceAll("<[^>]*>", "");
//			System.out.println("标题：" + entryTitle + "\n" + "链接：" + entryLink
//					+ "\n" + "内容：" + content.substring(0, content.length() > 300 ? 300 : content.length()));
//			System.out.println("--------------------------------------");
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public void readRss(int rssId, URL rssUrl) {
		rssList.clear();
		
		URL feedSource = rssUrl;
		
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		try {
			feed = input.build(new XmlReader(feedSource));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		Date publishedDate = feed.getPublishedDate();

		List<SyndEntry> syndEntries = (List<SyndEntry>) feed.getEntries();
		for (SyndEntry syndEntry : syndEntries) {
			String entryTitle = syndEntry.getTitle();
			String entryLink = syndEntry.getLink();
			SyndContent entryDescription = syndEntry.getDescription();
			String content = entryDescription.getValue();
			content = content.replaceAll("<[^>]*>", "");
			content = content.substring(0, content.length() > 300 ? 300 : content.length());
			Date entryDate = syndEntry.getPublishedDate();
			Date date = publishedDate != null?publishedDate:(entryDate != null?entryDate:new Date());
			Rss rss = new Rss();
			rss.setTitle(entryTitle.trim());
			rss.setContent(content);
			rss.setLink(entryLink);
			rss.setPublicDate(new java.sql.Timestamp(date.getTime()));
			rss.setRssResourceId(rssId);
			
			rssList.add(rss);
		}
	}

	public void run() {
		try {			
			HashMap<Integer, String> rssResource = RssResource.getRssResource();
			
			Set<Integer> rssSet = rssResource.keySet();
			Iterator it = rssSet.iterator();
			while(!isStop&&it.hasNext()) {
				Integer key = (Integer) it.next();
				int rssId = key.intValue();
				String rssXml = rssResource.get(key);
				
				readRss(rssId, new URL(rssXml));
				RssHandler.addRssToDB(getRssList());
			}
			
			System.out.println("Rss 导入完成！");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
