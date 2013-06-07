package com.ipensee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnectionNews {
	public static Connection getConnectionNews() throws SQLException, ClassNotFoundException {
		HashMap<String, String> config = ConfigXML.getConfig("resources/db-config.xml");

		String host = (String) config.get("host");
		String port = (String) config.get("port");
		String user = (String) config.get("user");
		String password = (String) config.get("password");
		String database = (String) config.get("database");

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf8";
		return DriverManager.getConnection(url, user, password);
	}
}
