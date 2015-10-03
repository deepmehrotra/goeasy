package com.goeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int categoryId;
	@Column
	private String catName;
	@Column
	private String catDescription;
	@Column
	private boolean isSubCategory;
	@Column
	private String parentCatName;
	@ManyToOne
	private Category parent;
	@OneToMany(cascade=CascadeType.ALL)	
	private List<Product> products = new ArrayList<Product>();
	@OneToMany(mappedBy = "parent",fetch=FetchType.EAGER,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private List<Category> subCategory = new ArrayList<Category>();
	@Column
	private int productCount;
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
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public boolean isSubCategory() {
		return isSubCategory;
	}
	public void setSubCategory(boolean isSubCategory) {
		this.isSubCategory = isSubCategory;
	}
	
	public List<Category> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
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
	public String getParentCatName() {
		return parentCatName;
	}
	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
	}

}
