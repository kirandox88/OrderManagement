/**
 * 
 */
package com.demo.actest.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kiran
 *
 */

@XmlType(name = "ListOrdersResponse", namespace = "com.demo.actest.response")
@XmlRootElement(name = "OrdersList")

public class ListOrdersResponse {
	private List<Order> orderList;
	private String statusMessage;

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the orderList
	 */
	public List<Order> getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}
