package com.demo.actest.request;

import java.io.Serializable;

/**
 * @author Kiran
 *
 */
public class ProductIdRequest implements Serializable {
	
	private static final long serialVersionUID = -5030739433014315391L;
	private int productId;

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	} 
}
