package com.demo.actest.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Kiran
 *
 */

@XmlType(name = "ProductListResponse", namespace = "com.demo.actest.response")
@XmlRootElement(name = "ProductList")
public class ProductListResponse {
	private List<Product> productList;

	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
