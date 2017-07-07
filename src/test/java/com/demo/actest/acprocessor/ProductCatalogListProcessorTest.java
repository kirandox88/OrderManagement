package com.demo.actest.acprocessor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.actest.repository.ProductRepository;
import com.demo.actest.response.PlaceOrderResponse;
import com.demo.actest.response.Product;
import com.demo.actest.response.ProductListResponse;

public class ProductCatalogListProcessorTest {
	
	@Mock
	private ProductRepository mockProductRepository;
	
	private ProductCatalogListProcessor productCatalogListProcessor;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productCatalogListProcessor = new ProductCatalogListProcessor();
		
		mockProductRepository	=  Mockito.mock(ProductRepository.class);
		productCatalogListProcessor.setProductRepository(mockProductRepository);
	}
	@Test
	public void testProcessPositive() {
		ProductListResponse productListResponse = new ProductListResponse();
		List<Product> productList = new ArrayList<Product>();
		Product p1 = new Product();
		p1.setProductId(1);
		p1.setProductDescription("Nike Shoe");
		p1.setSeller("abc sports");
		p1.setProductPrice(80);
		productList.add(p1);
		
		Product p2 = new Product();
		p2.setProductId(2);
		p2.setProductDescription("Adidas Shoe");
		p2.setSeller("def sports");
		p2.setProductPrice(90);
		productList.add(p2);
		
		productListResponse.setProductList(productList);
		
		Mockito.when(this.getMockProductRepository().getProductsList()).thenReturn(productList);
		ProductListResponse output = this.getProductCatalogListProcessor().process();
		
		Assert.assertNotNull(output);
		Assert.assertEquals(output.getProductList().size(), 2);
	}
	/**
	 * @return the mockProductRepository
	 */
	public ProductRepository getMockProductRepository() {
		return mockProductRepository;
	}
	/**
	 * @param mockProductRepository the mockProductRepository to set
	 */
	public void setMockProductRepository(ProductRepository mockProductRepository) {
		this.mockProductRepository = mockProductRepository;
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

}
