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

import com.demo.actest.acprocessor.FetchProductProcessor;
import com.demo.actest.acprocessor.ProductCatalogListProcessor;
import com.demo.actest.request.ProductIdRequest;
import com.demo.actest.response.Product;
import com.demo.actest.response.ProductListResponse;

/**
 * @author Kiran
 *
 */
@Controller
@RestController
@RequestMapping(value = "/product")

public class ProductsController {
	
	private final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	ProductCatalogListProcessor productCatalogListProcessor; 
	
	@Autowired
	FetchProductProcessor fetchProductProcessor;
	
	@RequestMapping(value = "/product_catalog_list", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public ProductListResponse getProductCatalogList(){
		
		logger.info("getProductCatalogList() method ");
		ProductListResponse productList = null;		
		productList = productCatalogListProcessor.process();		
		return productList;
		
	}
	
	
	@RequestMapping(value = "/fetch_Product", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public Product getProductByID(@RequestBody ProductIdRequest productIdRequest){
		
		logger.info("getProductCatalogList() method ");
		//int productId = 2;
		Product product = fetchProductProcessor.process(productIdRequest.getProductId());		
		return product;	
	}
	
	
}
