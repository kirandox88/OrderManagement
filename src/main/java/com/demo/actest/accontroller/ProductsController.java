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
	private ProductCatalogListProcessor productCatalogListProcessor; 
	
	@Autowired
	private FetchProductProcessor fetchProductProcessor;
	
	
	/**
	 * Fetch Product Catalog List.
	 * 
	 * @param no input parameters needed in the request
	 * @return  ProductListResponse
	 * 
	 */
	@RequestMapping(value = "/product_catalog_list", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public ProductListResponse getProductCatalogList(){
		
		logger.info("getProductCatalogList() method ");
		ProductListResponse productList = null;		
		productList = this.getProductCatalogListProcessor().process();		
		return productList;
		
	}
	
	
	
	/**
	 * Fetch Product by its id.
	 * 
	 * @param ProductIdRequest in the request
	 * @return  Product
	 * 
	 */
	@RequestMapping(value = "/fetch_Product", method = RequestMethod.POST)
	@Consumes("application/json")
	@Produces("application/json")
	public Product getProductByID(@RequestBody ProductIdRequest productIdRequest){
		
		logger.info("getProductByID() method ");
		Product product = this.getFetchProductProcessor().process(productIdRequest.getProductId());		
		return product;	
	}



	/**
	 * @return the productCatalogListProcessor
	 */
	public ProductCatalogListProcessor getProductCatalogListProcessor() {
		return productCatalogListProcessor;
	}



	/**
	 * @param productCatalogListProcessor the productCatalogListProcessor to set
	 */
	public void setProductCatalogListProcessor(
			ProductCatalogListProcessor productCatalogListProcessor) {
		this.productCatalogListProcessor = productCatalogListProcessor;
	}



	/**
	 * @return the fetchProductProcessor
	 */
	public FetchProductProcessor getFetchProductProcessor() {
		return fetchProductProcessor;
	}



	/**
	 * @param fetchProductProcessor the fetchProductProcessor to set
	 */
	public void setFetchProductProcessor(FetchProductProcessor fetchProductProcessor) {
		this.fetchProductProcessor = fetchProductProcessor;
	}
	
	
}
