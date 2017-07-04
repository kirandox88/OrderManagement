package com.demo.actest.acprocessor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.actest.repository.ProductRepository;
import com.demo.actest.response.Product;
import com.demo.actest.response.ProductListResponse;

/**
 * @author Kiran
 *
 */


@Service
public class ProductCatalogListProcessor {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductListResponse process() {
		ProductListResponse productListResponse = new ProductListResponse();
		List<Product> productList = productRepository.getProductsList();
		productListResponse.setProductList(productList);
		return productListResponse;
	}

	/**
	 * @return the productRepository
	 */
	public ProductRepository getProductRepository() {
		return productRepository;
	}

	/**
	 * @param productRepository the productRepository to set
	 */
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
}
