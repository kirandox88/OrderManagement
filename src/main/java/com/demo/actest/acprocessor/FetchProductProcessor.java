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
	ProductRepository productRepository;

	public Product process(int productId) {
		Product product = productRepository.fetchProduct(productId);
		return product;
	}
	
}
