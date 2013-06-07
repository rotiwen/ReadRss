package com.ipensee.webservice.client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipensee.ConnectionNews;

public class CustomerList {
//	private List<CustomerInfo> customerList;
	private static final String sql =
		"SELECT customer_id, customer, send_url, send_func, send_namespace FROM SendConfig WHERE IFNULL(disable, 0) = 0";
	
	public static List<CustomerInfo> getCustomerList() throws ClassNotFoundException, SQLException {
		List<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
		
		Connection conn = ConnectionNews.getConnectionNews();
		
		Statement stmt = conn.createStatement();
		
		ResultSet result = stmt.executeQuery(sql);
		
		while (result.next()) {
			CustomerInfo customer = new CustomerInfo();
			
			customer.setCustomerID(result.getString(1));
			customer.setCustomerName(result.getString(2));
			customer.setSendUrl(result.getString(3));
			customer.setSendFunc(result.getString(4));
			customer.setSendNamespace(result.getString(5));
			
			customerList.add(customer);
		}
		
		return customerList;
	}

}
