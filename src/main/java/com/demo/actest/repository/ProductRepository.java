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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.actest.response.Product;
import com.demo.actest.util.DBConnection;

/**
 * @author Kiran
 *
 */
@Repository
public class ProductRepository {
	private final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
	
	@Autowired
	DBConnection dbConnection;

	@SuppressWarnings("null")
	public List<Product> getProductsList() {
		PreparedStatement stmt = null;
		Connection connection=null;
		List<Product> productList = new ArrayList<Product>();
		
		String DB_STRING = "SELECT PRODUCTID ,PRODUCTDESCRIPTION ,PRODCUTSELLER ,PRODUCTPRICE FROM PRODUCTDETAILS";
		try {
			connection = dbConnection.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(DB_STRING);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductDescription(rs.getString(2));
				product.setSeller(rs.getString(3));
				product.setProductPrice(rs.getLong(4));
				productList.add(product);
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
		
		return productList;
	}

	public Product fetchProduct(int productId) {
		PreparedStatement stmt = null;
		Connection connection=null;
		
		Product product = new Product();
		String DB_STRING = "SELECT PRODUCTID ,PRODUCTDESCRIPTION ,PRODCUTSELLER ,PRODUCTPRICE FROM PRODUCTDETAILS WHERE PRODUCTID=?";
		try {
			connection = dbConnection.getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(DB_STRING);
			stmt.setLong(1, productId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				product.setProductId(rs.getInt(1));
				product.setProductDescription(rs.getString(2));
				product.setSeller(rs.getString(3));
				product.setProductPrice(rs.getLong(4));
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
		
		return product;
	}
	
	
}
