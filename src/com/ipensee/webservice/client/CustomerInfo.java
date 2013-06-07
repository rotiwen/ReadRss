package com.ipensee.webservice.client;

public class CustomerInfo {
	private String CustomerID;
	private String CustomerName;
	private String SendUrl;	
	private String SendFunc;
	private String SendNamespace;
	
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getSendUrl() {
		return SendUrl;
	}
	public void setSendUrl(String sendUrl) {
		SendUrl = sendUrl;
	}
	public String getSendFunc() {
		return SendFunc;
	}
	public void setSendFunc(String sendFunc) {
		SendFunc = sendFunc;
	}
	public String getSendNamespace() {
		return SendNamespace;
	}
	public void setSendNamespace(String sendNamespace) {
		SendNamespace = sendNamespace;
	}
}
