/**
 * 
 */
package com.demo.actest.response;

/**
 * @author Kiran
 *
 */
public class Product {
	private int productId;
	private String productDescription;
	private String seller;
	private double productPrice;
	
	/**
	 * @return the productiId
	 */
	public int getProductiId() {
		return productId;
	}
	/**
	 * @param productiId the productiId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * @return the seller
	 */
	public String getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public void setSeller(String seller) {
		this.seller = seller;
	}
	/**
	 * @return the productPrice
	 */
	public double getProductPrice() {
		return productPrice;
	}
	/**
	 * @param d the productPrice to set
	 */
	public void setProductPrice(double d) {
		this.productPrice = d;
	}
}
