package com.ipensee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class RssResource {
	private static final String sql = "SELECT ID, RssXml FROM RssResources WHERE IFNULL(Disabled, 0) = 0";
	
	public static HashMap<Integer, String> getRssResource() throws ClassNotFoundException, SQLException {
		HashMap<Integer, String> rssResource = new HashMap<Integer, String>();
		
		Connection conn = ConnectionNews.getConnectionNews();
		
		Statement stmt = conn.createStatement();
		
		ResultSet result = stmt.executeQuery(sql);
		
		while (result.next()) {
			rssResource.put(Integer.valueOf(result.getInt(1)), result.getString(2));
		}
		
		return rssResource;
	}

}
