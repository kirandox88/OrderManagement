package com.demo.actest.accontroller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.actest.acprocessor.FetchOrderProcessor;
import com.demo.actest.acprocessor.ListOrdersProcessor;
import com.demo.actest.acprocessor.ModifyOrderProcessor;
import com.demo.actest.acprocessor.PlaceOrderProcessor;
import com.demo.actest.request.ModifyOrderRequest;
import com.demo.actest.request.OrderIdRequest;
import com.demo.actest.request.PlaceOrderRequest;
import com.demo.actest.response.ListOrdersResponse;
import com.demo.actest.response.ModifyOrderResponse;
import com.demo.actest.response.Order;
import com.demo.actest.response.PlaceOrderResponse;

/**
 * @author Kiran
 *
 */
@Controller
@RestController
@RequestMapping(value = "/order")

public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	PlaceOrderProcessor placeOrderProcessor; 
	
	@Autowired
	ListOrdersProcessor listOrdersProcessor;
	
	@Autowired
	FetchOrderProcessor fetchOrderProcessor;
	
	@Autowired
	ModifyOrderProcessor modifyOrderProcessor;
	
	@RequestMapping(value = "/place_order", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public PlaceOrderResponse placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest){

		logger.info("getProductCatalogList() method ");
		PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
		placeOrderResponse = placeOrderProcessor.process(placeOrderResponse,placeOrderRequest);
		return placeOrderResponse;
		
	}
	
	
	@RequestMapping(value = "/modify_order", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	
	public ModifyOrderResponse modifyOrder(@RequestBody ModifyOrderRequest modifyOrderRequest){

		logger.info("modifyOrder() method ");
		ModifyOrderResponse modifyOrderResponse = new ModifyOrderResponse();
		modifyOrderResponse = modifyOrderProcessor.process(modifyOrderResponse,modifyOrderRequest);
		return modifyOrderResponse;
		
	}
	
	@RequestMapping(value = "/order_list", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public ListOrdersResponse ListOrders(){

		logger.info("ListOrders() method ");
		ListOrdersResponse listOrdersResponse = new ListOrdersResponse();
		listOrdersResponse = listOrdersProcessor.process();
		return listOrdersResponse;
		
	}
	
	@RequestMapping(value = "/fetch_Order", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public Order getOrderById(@RequestBody OrderIdRequest orderIdRequest){

		logger.info("ListOrders() method ");
		Order order = new Order();
		order = fetchOrderProcessor.process(orderIdRequest.getOrderId());
		return order;
		
	}
	
}
