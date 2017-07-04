/**
 * 
 */
package com.demo.actest.acprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.actest.repository.ProductRepository;
import com.demo.actest.response.Product;

/**
 * @author Kiran
 *
 */

@Service
public class FetchProductProcessor {

	@Autowired
	private ProductRepository productRepository;

	public Product process(int productId) {
		Product product = this.getProductRepository().fetchProduct(productId);
		return product;
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
