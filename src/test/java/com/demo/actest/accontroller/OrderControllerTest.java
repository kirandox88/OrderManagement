package com.demo.actest.accontroller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

public class OrderControllerTest {

	@Mock
	private PlaceOrderProcessor mockPlaceOrderProcessor;
	
	@Mock
	private ListOrdersProcessor mockListOrdersProcessor;
	
	@Mock
	private FetchOrderProcessor mockFetchOrderProcessor;
	
	@Mock
	private ModifyOrderProcessor mockModifyOrderProcessor;
	
	private OrderController orderController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		orderController = new OrderController();
		
		mockPlaceOrderProcessor	=  Mockito.mock(PlaceOrderProcessor.class);
		orderController.setPlaceOrderProcessor(mockPlaceOrderProcessor);
		
		mockListOrdersProcessor	=  Mockito.mock(ListOrdersProcessor.class);
		orderController.setListOrdersProcessor(mockListOrdersProcessor);
		
		mockFetchOrderProcessor	=  Mockito.mock(FetchOrderProcessor.class);
		orderController.setFetchOrderProcessor(mockFetchOrderProcessor);
		
		mockModifyOrderProcessor =  Mockito.mock(ModifyOrderProcessor.class);
		orderController.setModifyOrderProcessor(mockModifyOrderProcessor);
		
	}
	
	@Test
	public void testPlaceOrderPositive() {
		PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
		placeOrderRequest.setOrderDescription("Shoe order");
		placeOrderRequest.setProductId(2);
		placeOrderRequest.setShippingAddress("123 Las Gatos");
		
		PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse(); 
		placeOrderResponse.setOrderNumber("1234");
		placeOrderResponse.setResponseMessage("Successfully Placed the order");
		placeOrderResponse.setResponseStatus("Success");
		
		Mockito.when(this.getMockPlaceOrderProcessor().process(placeOrderRequest)).thenReturn(placeOrderResponse);
		PlaceOrderResponse output = orderController.placeOrder(placeOrderRequest);
		Assert.assertNotNull(output);
		Assert.assertEquals(output.getOrderNumber(), "1234");
	}

	@Test
	public void testModifyOrderPositive(){
		ModifyOrderRequest modifyOrderRequest = new ModifyOrderRequest();
		modifyOrderRequest.setOrderDescription("Shoe Order");
		modifyOrderRequest.setOrderNumber("1234");
		modifyOrderRequest.setProductId(2);
		modifyOrderRequest.setShippingAddress("456 San Jose");
		
		ModifyOrderResponse modifyOrderResponse = new ModifyOrderResponse();
		modifyOrderResponse.setOrderNumber("1234");
		modifyOrderResponse.setResponseMessage("Successfully modified the order.");
		modifyOrderResponse.setResponseStatus("Success");
		
		Mockito.when(this.getMockModifyOrderProcessor().process(modifyOrderRequest)).thenReturn(modifyOrderResponse);
		ModifyOrderResponse output = orderController.modifyOrder(modifyOrderRequest);
		Assert.assertNotNull(output);
		Assert.assertEquals(output.getOrderNumber(), "1234");
	}
	
	@Test
	public void testListOrders(){
		ListOrdersResponse listOrdersResponse = new ListOrdersResponse();
		List<Order> orderList = new ArrayList<Order>();
		Order o1 = new Order();
		o1.setOrderDate("2017-01-01");
		o1.setOrderDescription("Shoe Order");
		o1.setOrderId(8);
		o1.setOrderNumber("1234");
		o1.setOrderStatus("Placed");
		o1.setShippingAddress("456 San Jose");
		
		Order o2 = new Order();
		o2.setOrderDate("2017-02-02");
		o2.setOrderDescription("Cleats Order");
		o2.setOrderId(9);
		o2.setOrderNumber("1234");
		o2.setOrderStatus("Shipped");
		o2.setShippingAddress("888 San Jose");
		orderList.add(o1);
		orderList.add(o2);
		
		listOrdersResponse.setOrderList(orderList);
		listOrdersResponse.setStatusMessage("Success");
		
		Mockito.when(this.getMockListOrdersProcessor().process()).thenReturn(listOrdersResponse);
		ListOrdersResponse output = orderController.listOrders();
		Assert.assertNotNull(output);
		Assert.assertEquals(output.getStatusMessage(), "Success");
	}
	
	
	@Test
	public void testGetOrderByIdPositive() {
		Order o1 = new Order();
		o1.setOrderDate("2017-01-01");
		o1.setOrderDescription("Shoe Order");
		o1.setOrderId(8);
		o1.setOrderNumber("1234");
		o1.setOrderStatus("Placed");
		o1.setShippingAddress("456 San Jose");
		
		OrderIdRequest orderIdRequest = new OrderIdRequest();
		orderIdRequest.setOrderId(8);
		Mockito.when(this.getMockFetchOrderProcessor().process(orderIdRequest.getOrderId())).thenReturn(o1);
		Order output = orderController.getOrderById(orderIdRequest); 
		Assert.assertNotNull(output);
		Assert.assertEquals(output.getOrderStatus(), "Placed");
		
	}
	
	/**
	 * @return the placeOrderProcessor
	 */
	public PlaceOrderProcessor getPlaceOrderProcessor() {
		return mockPlaceOrderProcessor;
	}


	/**
	 * @param placeOrderProcessor the placeOrderProcessor to set
	 */
	public void setPlaceOrderProcessor(PlaceOrderProcessor mockPlaceOrderProcessor) {
		this.mockPlaceOrderProcessor = mockPlaceOrderProcessor;
	}


	/**
	 * @param orderController the orderController to set
	 */
	public void setOrderController(OrderController orderController) {
		this.orderController = orderController;
	}


	/**
	 * @return the orderController
	 */
	public OrderController getOrderController() {
		return orderController;
	}

	/**
	 * @return the mockPlaceOrderProcessor
	 */
	public PlaceOrderProcessor getMockPlaceOrderProcessor() {
		return mockPlaceOrderProcessor;
	}

	/**
	 * @param mockPlaceOrderProcessor the mockPlaceOrderProcessor to set
	 */
	public void setMockPlaceOrderProcessor(
			PlaceOrderProcessor mockPlaceOrderProcessor) {
		this.mockPlaceOrderProcessor = mockPlaceOrderProcessor;
	}

	/**
	 * @return the mockListOrdersProcessor
	 */
	public ListOrdersProcessor getMockListOrdersProcessor() {
		return mockListOrdersProcessor;
	}

	/**
	 * @param mockListOrdersProcessor the mockListOrdersProcessor to set
	 */
	public void setMockListOrdersProcessor(
			ListOrdersProcessor mockListOrdersProcessor) {
		this.mockListOrdersProcessor = mockListOrdersProcessor;
	}

	/**
	 * @return the mockFetchOrderProcessor
	 */
	public FetchOrderProcessor getMockFetchOrderProcessor() {
		return mockFetchOrderProcessor;
	}

	/**
	 * @param mockFetchOrderProcessor the mockFetchOrderProcessor to set
	 */
	public void setMockFetchOrderProcessor(
			FetchOrderProcessor mockFetchOrderProcessor) {
		this.mockFetchOrderProcessor = mockFetchOrderProcessor;
	}

	/**
	 * @return the mockModifyOrderProcessor
	 */
	public ModifyOrderProcessor getMockModifyOrderProcessor() {
		return mockModifyOrderProcessor;
	}

	/**
	 * @param mockModifyOrderProcessor the mockModifyOrderProcessor to set
	 */
	public void setMockModifyOrderProcessor(
			ModifyOrderProcessor mockModifyOrderProcessor) {
		this.mockModifyOrderProcessor = mockModifyOrderProcessor;
	}

}
