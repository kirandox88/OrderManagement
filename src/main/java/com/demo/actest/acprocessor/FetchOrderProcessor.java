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
	OrderRepository orderRepository;

	public Order process(int orderId) {
		
		Order order =  orderRepository.fetchOrderById(orderId);
		return order;


	}

}
