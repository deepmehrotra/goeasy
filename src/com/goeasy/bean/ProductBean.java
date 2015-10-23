package com.goeasy.bean;

import java.util.Date;

public class ProductBean {
	private int productId;
	private String productName;
	private Date productDate;
	private String productSkuCode;
	private float productPrice;
	private long quantity;
	private long threholdLimit;
	private String channelSKU;
	private String categoryName;
	private CategoryBean category;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public String getProductSkuCode() {
		return productSkuCode;
	}
	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public CategoryBean getCategory() {
		return category;
	}
	public void setCategory(CategoryBean category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getThreholdLimit() {
		return threholdLimit;
	}
	public void setThreholdLimit(long threholdLimit) {
		this.threholdLimit = threholdLimit;
	}
	public String getChannelSKU() {
		return channelSKU;
	}
	public void setChannelSKU(String channelSKU) {
		this.channelSKU = channelSKU;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getQuantity() {
		return quantity;
	}
	

}
