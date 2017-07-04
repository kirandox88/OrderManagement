/**
 * 
 */
package com.demo.actest.acprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.actest.repository.OrderRepository;
import com.demo.actest.response.Order;

/**
 * @author Kiran
 *
 */

@Service
public class FetchOrderProcessor {

	@Autowired
	private OrderRepository orderRepository;

	public Order process(int orderId) {		
		Order order =  this.getOrderRepository().fetchOrderById(orderId);
		return order;
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
