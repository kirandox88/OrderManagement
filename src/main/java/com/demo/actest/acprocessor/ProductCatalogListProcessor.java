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
	ProductRepository productRepository;
	
	public ProductListResponse process() {
		ProductListResponse productListResponse = new ProductListResponse();
		/*Product p1 = new Product();
		p1.setProductiId(123);
		p1.setProductDescription("Adidas Shoe");
		p1.setProductPrice(80.9);
		p1.setSeller("ABC seller");
		
		Product p2 = new Product();
		p2.setProductiId(345);
		p2.setProductDescription("Nike Shoe");
		p2.setProductPrice(85.9);
		p2.setSeller("DEF seller");
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(p1);
		productList.add(p2);
		productListResponse.setProductList(productList);
		*/
		
		List<Product> productList = productRepository.getProductsList();
		productListResponse.setProductList(productList);
		return productListResponse;
	}
}
