/**
 * 
 */
package com.demo.actest.acprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.actest.repository.OrderRepository;
import com.demo.actest.request.PlaceOrderRequest;
import com.demo.actest.response.PlaceOrderResponse;

/**
 * @author Kiran
 *
 */
@Service
public class PlaceOrderProcessor {

	@Autowired
	OrderRepository orderRepository;
	
	public PlaceOrderResponse process(PlaceOrderResponse placeOrderResponse,PlaceOrderRequest placeOrderRequest) {
		
		String orderNumber = orderRepository.placeOrder(placeOrderRequest);
		
		if(orderNumber != null){
			placeOrderResponse.setResponseStatus("Success");
			placeOrderResponse.setResponseMessage("Successfully placed the order");
			placeOrderResponse.setOrderNumber(orderNumber);
		}else{
			placeOrderResponse.setResponseStatus("Failure");
			placeOrderResponse.setResponseMessage("Error Occured while placing the order,please try again");
			placeOrderResponse.setOrderNumber(null);
		}
		
		return placeOrderResponse;
	}

}
