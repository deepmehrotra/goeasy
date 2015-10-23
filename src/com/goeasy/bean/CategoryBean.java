package com.goeasy.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goeasy.model.Category;

public class CategoryBean {
	private int id;
	private String catName;
	private String catDescription;
	private boolean isSubCategory;
	private String parentCatName;
	private Category parent;
	private Date createdOn;
	private long productCount;
	private long skuCount;
	private long openingStock;
	private Date osUpdate;
	private List<CategoryBean> subCategory = new ArrayList<CategoryBean>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}
	
	public boolean isSubCategory() {
		return isSubCategory;
	}
	public void setSubCategory(boolean isSubCategory) {
		this.isSubCategory = isSubCategory;
	}
	public String getParentCatName() {
		return parentCatName;
	}
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public long getProductCount() {
		return productCount;
	}
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	public List<CategoryBean> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<CategoryBean> subCategory) {
		this.subCategory = subCategory;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public long getSkuCount() {
		return skuCount;
	}
	public void setSkuCount(long skuCount) {
		this.skuCount = skuCount;
	}
	public long getOpeningStock() {
		return openingStock;
	}
	public void setOpeningStock(long openingStock) {
		this.openingStock = openingStock;
	}
	public Date getOsUpdate() {
		return osUpdate;
	}
	public void setOsUpdate(Date osUpdate) {
		this.osUpdate = osUpdate;
	}
	

}
