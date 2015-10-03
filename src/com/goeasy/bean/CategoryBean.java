package com.goeasy.bean;

import java.util.ArrayList;
import java.util.List;

import com.goeasy.model.Category;

public class CategoryBean {
	private int id;
	private String catName;
	private String catDescription;
	private boolean isSubCategory;
	private String parentCatName;
	private Category parent;
	private int productCount;
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
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public List<CategoryBean> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<CategoryBean> subCategory) {
		this.subCategory = subCategory;
	}
	

}
