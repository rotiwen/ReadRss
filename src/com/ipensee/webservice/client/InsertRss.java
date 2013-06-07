package com.ipensee.webservice.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class InsertRss {
	public static final String SUCCESS = "1";
	
	private Parameter parameter;

	public InsertRss(Parameter parameter) {
		this.parameter = parameter;
	}
	
	private boolean doSend(CustomerInfo customer) {
		String url = customer.getSendUrl();
		String method = customer.getSendFunc();
		String namespace = customer.getSendNamespace();
		
		try {
			Call call = (Call) new Service().createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			
			// 设置要调用的方法 WebServiceInfo.METHOD
			// WebServiceInfo.NAMESPACE 是wsdl中definitions根节点的targetNamespace属性值
			call.setOperationName(new QName(namespace, method));
			
			// 该方法需要的参数
			call.addParameter(new QName(namespace, Parameter.TITLE),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, Parameter.CONTENT),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, Parameter.LINK),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, Parameter.PUBLIC_DATE),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, Parameter.RSS_RESOURCE_ID),
					XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, Parameter.SERVICE_ID),
					XMLType.XSD_STRING, ParameterMode.IN);
			
			// 方法的返回值类型
			call.setReturnType(XMLType.XSD_STRING);
			
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace + method);
			
			String result =
				call.invoke(new Object[] {
						parameter.getParameter().get(Parameter.TITLE),
						parameter.getParameter().get(Parameter.CONTENT),
						parameter.getParameter().get(Parameter.LINK),
						parameter.getParameter().get(Parameter.PUBLIC_DATE),
						parameter.getParameter().get(Parameter.RSS_RESOURCE_ID),
						parameter.getParameter().get(Parameter.SERVICE_ID)
				}).toString();

			return result == SUCCESS;
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void executeSend(List<CustomerInfo> customerList) {
		for (CustomerInfo customer: customerList) {
			doSend(customer);
		}
	}
	
}
