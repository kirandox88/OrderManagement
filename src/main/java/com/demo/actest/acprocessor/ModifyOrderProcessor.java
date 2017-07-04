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
	OrderRepository orderRepository;
	
	public ModifyOrderResponse process(ModifyOrderResponse modifyOrderResponse,
			ModifyOrderRequest modifyOrderRequest) {
		
		String status = orderRepository.modifyOrder(modifyOrderRequest);
		
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

}
