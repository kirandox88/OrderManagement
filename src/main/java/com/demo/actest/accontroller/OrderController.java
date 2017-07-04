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
	private PlaceOrderProcessor placeOrderProcessor; 
	
	@Autowired
	private ListOrdersProcessor listOrdersProcessor;
	
	@Autowired
	private FetchOrderProcessor fetchOrderProcessor;
	
	@Autowired
	private ModifyOrderProcessor modifyOrderProcessor;
	
	
	

	/**
	 * Place Order.
	 * 
	 * @param PlaceOrderRequest
	 *            the input request
	 * @return PlaceOrderResponse 
	 * 
	 */
	
	@RequestMapping(value = "/place_order", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public PlaceOrderResponse placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest){

		logger.info("placeOrder() method ");
		PlaceOrderResponse placeOrderResponse = this.getPlaceOrderProcessor().process(placeOrderRequest);
		return placeOrderResponse;
		
	}
	
	/**
	 * Modify Order.
	 * 
	 * @param ModifyOrderRequest
	 *            the input request
	 * @return ModifyOrderResponse 
	 * 
	 */
	@RequestMapping(value = "/modify_order", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	
	public ModifyOrderResponse modifyOrder(@RequestBody ModifyOrderRequest modifyOrderRequest){

		logger.info("modifyOrder() method ");
		ModifyOrderResponse modifyOrderResponse = this.getModifyOrderProcessor().process(modifyOrderRequest);
		return modifyOrderResponse;
		
	}
	
	
	/**
	 * List all the Orders.
	 * 
	 * @param No any input parameters
	 * @return  ListOrdersResponse
	 * 
	 */
	@RequestMapping(value = "/order_list", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public ListOrdersResponse listOrders(){

		logger.info("ListOrders() method ");
		ListOrdersResponse listOrdersResponse = this.getListOrdersProcessor().process();
		return listOrdersResponse;
		
	}
	
	
	
	/**
	 * Fetch Order by its id
	 * 
	 * @param OrderIdRequest in the request
	 * @return  Order details
	 * 
	 */
	@RequestMapping(value = "/fetch_Order", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public Order getOrderById(@RequestBody OrderIdRequest orderIdRequest){

		logger.info("getOrderById() method ");
		Order order = new Order();
		order = this.getFetchOrderProcessor().process(orderIdRequest.getOrderId());
		return order;
		
	}
	
	/**
	 * @return the placeOrderProcessor
	 */
	public PlaceOrderProcessor getPlaceOrderProcessor() {
		return placeOrderProcessor;
	}

	/**
	 * @param placeOrderProcessor the placeOrderProcessor to set
	 */
	public void setPlaceOrderProcessor(PlaceOrderProcessor placeOrderProcessor) {
		this.placeOrderProcessor = placeOrderProcessor;
	}

	/**
	 * @return the listOrdersProcessor
	 */
	public ListOrdersProcessor getListOrdersProcessor() {
		return listOrdersProcessor;
	}

	/**
	 * @param listOrdersProcessor the listOrdersProcessor to set
	 */
	public void setListOrdersProcessor(ListOrdersProcessor listOrdersProcessor) {
		this.listOrdersProcessor = listOrdersProcessor;
	}

	/**
	 * @return the fetchOrderProcessor
	 */
	public FetchOrderProcessor getFetchOrderProcessor() {
		return fetchOrderProcessor;
	}

	/**
	 * @param fetchOrderProcessor the fetchOrderProcessor to set
	 */
	public void setFetchOrderProcessor(FetchOrderProcessor fetchOrderProcessor) {
		this.fetchOrderProcessor = fetchOrderProcessor;
	}

	/**
	 * @return the modifyOrderProcessor
	 */
	public ModifyOrderProcessor getModifyOrderProcessor() {
		return modifyOrderProcessor;
	}

	/**
	 * @param modifyOrderProcessor the modifyOrderProcessor to set
	 */
	public void setModifyOrderProcessor(ModifyOrderProcessor modifyOrderProcessor) {
		this.modifyOrderProcessor = modifyOrderProcessor;
	}
	
}
