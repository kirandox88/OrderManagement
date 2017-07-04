/**
 * 
 */
package com.demo.actest.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Kiran
 *
 */

@Repository
public class DBConnection {
	
	private final Logger logger = LoggerFactory.getLogger(DBConnection.class);
	 	
	public Connection getConnection(){
		Properties prop = new Properties();
		InputStream input = null;
		Connection dbConnection = null;
		try {

			String filename = "application.properties";
			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			if(input==null){
		            System.out.println("Sorry, unable to find " + filename);
			    return null;
			}			
			prop.load(input);
			
		        String DB_DRIVER = prop.getProperty("spring.datasource.driver-class-name"); 
		        String DB_URL = prop.getProperty("spring.datasource.url");
		        String DB_USER = prop.getProperty("spring.datasource.username");
		        String DB_PASSWORD = prop.getProperty("spring.datasource.password");
		        
		        logger.info(DB_DRIVER);
		        logger.info(DB_URL);
		        logger.info(DB_USER);
		        logger.info(DB_PASSWORD);
		        
		        try {
		            Class.forName(DB_DRIVER);
		        } catch (ClassNotFoundException e) {
		            System.out.println(e.getMessage());
		        }
		        try {
		            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		        
		        

		} catch (IOException ex) {
			ex.printStackTrace();
	    } finally{
	    	if(input!=null){
	    		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	}
	    }
		return dbConnection;

	}

}
		

