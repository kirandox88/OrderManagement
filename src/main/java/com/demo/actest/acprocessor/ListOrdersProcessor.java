/**
 * 
 */
package com.demo.actest.acprocessor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.actest.repository.OrderRepository;
import com.demo.actest.response.ListOrdersResponse;
import com.demo.actest.response.Order;

/**
 * @author Kiran
 *
 */

@Service
public class ListOrdersProcessor {
	@Autowired
	OrderRepository orderRepository;

	public ListOrdersResponse process() {
		ListOrdersResponse listOrdersResponse = new ListOrdersResponse();
		List<Order> ordersList = orderRepository.getOrdersList();
		if(ordersList != null){
			listOrdersResponse.setOrderList(ordersList);
			listOrdersResponse.setStatusMessage("Success");
		}else{
			listOrdersResponse.setOrderList(null);
			listOrdersResponse.setStatusMessage("Failure: Error Occured,Please try again later");
		}
		listOrdersResponse.setOrderList(ordersList);
		return listOrdersResponse;
	}
	
	
}
