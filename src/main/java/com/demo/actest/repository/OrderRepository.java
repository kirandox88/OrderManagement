/**
 * 
 */
package com.demo.actest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.actest.request.ModifyOrderRequest;
import com.demo.actest.request.PlaceOrderRequest;
import com.demo.actest.response.ModifyOrderResponse;
import com.demo.actest.response.Order;
import com.demo.actest.response.Product;
import com.demo.actest.util.DBConnection;

/**
 * @author Kiran
 *
 */

@Repository
public class OrderRepository {
	private final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
	
	@Autowired
	DBConnection dbConnection;
	
	public String placeOrder(PlaceOrderRequest placeOrderRequest){
		PreparedStatement stmt = null;
		Connection connection=null;
		String OrderNumber = null;
		//String DB_STRING = "INSERT INTO PRODUCTDETAILS (PRODUCTID ,PRODUCTDESCRIPTION ,PRODCUTSELLER ,PRODUCTPRICE ) VALUES"+"(?,?,?,?)";
		String DB_STRING = "INSERT INTO OrderDetails(OrderNumber,OrderDescription,OrderDate,ShippingAddress,OrderStatus,productId) values ("+ "?, ?, CURRENT_DATE(),?, ?, ?)";
		try {
			connection = dbConnection.getConnection();
			connection.setAutoCommit(false);
			
			Random rand = new Random();
			OrderNumber =  Integer.toString(rand.nextInt(Integer.MAX_VALUE));
			
			stmt = connection.prepareStatement(DB_STRING);
			stmt.setString(1, OrderNumber);
			stmt.setString(2,placeOrderRequest.getOrderDescription());
			stmt.setString(3,placeOrderRequest.getShippingAddress());
			stmt.setString(4,"Placed");
			stmt.setInt(5,placeOrderRequest.getProductId());
			stmt.executeUpdate();
			
			
			logger.info("Successfully Placed the order");
			stmt.close();
			connection.commit();
			
			return OrderNumber;
			
		} catch (SQLException e) {
			logger.info("Exception Occured"+e.getMessage());
			return null;
			
		} finally {
            try {
				connection.close();
			} catch (SQLException e) {
				logger.info("Exception Occured"+e.getMessage());
				return OrderNumber;
			}
        }

	}

	public List<Order> getOrdersList() {
		PreparedStatement stmt = null;
		Connection connection=null;
		List<Order> orderList = new ArrayList<Order>();
		
		String DB_STRING = "SELECT ORDERID ,ORDERNUMBER ,ORDERDESCRIPTION ,ORDERDATE ,SHIPPINGADDRESS ,ORDERSTATUS ,PRODUCTID  FROM ORDERDETAILS";
		try {
			connection = dbConnection.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(DB_STRING);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()){
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setOrderNumber(rs.getString(2));
				order.setOrderDescription(rs.getString(3));
				order.setOrderDate(rs.getString(4));
				order.setShippingAddress(rs.getString(5));
				order.setOrderStatus(rs.getString(6));
				order.setProductId(rs.getInt(7));
				orderList.add(order);
			}
			
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			logger.error("Exception Occured"+e.getMessage());
		}finally {
            try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Exception Occured"+e.getMessage());
				return null;
			}
        }
		
		return orderList;
	}

	public Order fetchOrderById(int orderId) {
		PreparedStatement stmt = null;
		Connection connection=null;
		
		Order order = new Order();
		String DB_STRING = "SELECT ORDERID ,ORDERNUMBER ,ORDERDESCRIPTION ,ORDERDATE ,SHIPPINGADDRESS ,ORDERSTATUS ,PRODUCTID  FROM ORDERDETAILS WHERE ORDERID=?";
		try {
			connection = dbConnection.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(DB_STRING);
			stmt.setLong(1, orderId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				order.setOrderId(rs.getInt(1));
				order.setOrderNumber(rs.getString(2));
				order.setOrderDescription(rs.getString(3));
				order.setOrderDate(rs.getString(4));
				order.setShippingAddress(rs.getString(5));
				order.setOrderStatus(rs.getString(6));
				order.setProductId(rs.getInt(7));
			}
			
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			logger.error("Exception Occured"+e.getMessage());
		}finally {
            try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Exception Occured"+e.getMessage());
				return null;
			}
        }
		
		return order;
	}

	public String modifyOrder(ModifyOrderRequest modifyOrderRequest) {
		PreparedStatement stmt = null;
		Connection connection=null;
		String OrderNumber = null;
		String DB_STRING = "UPDATE OrderDetails SET OrderDescription = ?, ShippingAddress = ?, productId = ? WHERE OrderNumber = ?";
		try {
			connection = dbConnection.getConnection();
			connection.setAutoCommit(false);
			
			stmt = connection.prepareStatement(DB_STRING);
			stmt.setString(1, modifyOrderRequest.getOrderDescription());
			stmt.setString(2,modifyOrderRequest.getShippingAddress());
			stmt.setInt(3,modifyOrderRequest.getProductId());
			stmt.setString(4,modifyOrderRequest.getOrderNumber());
			
			stmt.executeUpdate();
			
			logger.info("Successfully Modified the order");
			stmt.close();
			connection.commit();
			
			return "Success";
			
		} catch (SQLException e) {
			logger.info("Exception Occured"+e.getMessage());
			return null;
			
		} finally {
            try {
				connection.close();
			} catch (SQLException e) {
				logger.info("Exception Occured"+e.getMessage());
				return "Success";
			}
        }

	}
}
