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
	private OrderRepository orderRepository;
	
	public PlaceOrderResponse process(PlaceOrderRequest placeOrderRequest) {
		
		String orderNumber = this.getOrderRepository().placeOrder(placeOrderRequest);
		PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
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
