/**
 * 
 */
package com.demo.actest.acprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.actest.repository.OrderRepository;
import com.demo.actest.request.ModifyOrderRequest;
import com.demo.actest.response.ModifyOrderResponse;

/**
 * @author Kiran
 *
 */

@Service
public class ModifyOrderProcessor {

	@Autowired
	private OrderRepository orderRepository;
	
	public ModifyOrderResponse process(ModifyOrderRequest modifyOrderRequest) {
		
		String status = this.getOrderRepository().modifyOrder(modifyOrderRequest);
		ModifyOrderResponse modifyOrderResponse = new ModifyOrderResponse();
		
		if(status == "Success"){
			modifyOrderResponse.setOrderNumber(modifyOrderRequest.getOrderNumber());
			modifyOrderResponse.setResponseMessage("Successfully modified the order.");
			modifyOrderResponse.setResponseStatus("Success");
		}else{
			modifyOrderResponse.setOrderNumber(modifyOrderRequest.getOrderNumber());
			modifyOrderResponse.setResponseMessage("Error occured while modifying the order.");
			modifyOrderResponse.setResponseStatus("Failure");
		}
		return modifyOrderResponse;
	}

	/**
	 * @return the orderRepository
	 */
	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	/**
	 * @param orderRepository the orderRepository to set
	 */
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

}
