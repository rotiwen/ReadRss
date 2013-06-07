package com.ipensee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.ipensee.webservice.client.CustomerInfo;
import com.ipensee.webservice.client.CustomerList;
import com.ipensee.webservice.client.InsertRss;
import com.ipensee.webservice.client.Parameter;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class RssHandler {
	private static Connection conn;
	private static PreparedStatement stmtRssNews;
	private static List<CustomerInfo> customerList;

	private static final String sqlNews = "INSERT INTO rss_news (title, content, link, public_date, rss_resource_id, news_id, create_time) VALUES (?, ?, ?, ?, ?, ?, now())";

	public static void open() throws ClassNotFoundException, SQLException {
		if (conn != null)
			return;

		conn = ConnectionNews.getConnectionNews();
		stmtRssNews = conn.prepareStatement(sqlNews);
		customerList = CustomerList.getCustomerList();
	}

	public static void addRssToDB(List<Rss> rssList) {
		Iterator it = rssList.iterator();
		
		while (it.hasNext()) {
			UUID uuid = UUID.randomUUID();
			Rss rss = (Rss) it.next();

//			if (toDB(uuid, rss)) {
//				new InsertRss(new Parameter(rss)).executeSend(customerList);
//			}
			System.out.println(rss.getRssResourceId());
			System.out.println(rss.getTitle());
			System.out.println(rss.getLink());
			System.out.println("-----------------------------------------------");
		}
	}

	private static boolean toDB(UUID uuid, Rss rss) {
		int result = 0;
		
		try {
			stmtRssNews.setString(1, rss.getTitle());
			stmtRssNews.setString(2, rss.getContent());
			stmtRssNews.setString(3, rss.getLink());
			stmtRssNews.setTimestamp(4, rss.getPublicDate());
			stmtRssNews.setInt(5, rss.getRssResourceId());
			stmtRssNews.setString(6, uuid.toString());

			result = stmtRssNews.executeUpdate();
		}
		catch (MySQLIntegrityConstraintViolationException e) {
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result > 0;
	};
	
	public static void close() {
		try {
			if (conn != null) {
				stmtRssNews.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
