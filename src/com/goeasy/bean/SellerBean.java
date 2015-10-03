package com.goeasy.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.goeasy.model.TaxCategory;

public class SellerBean {

	private int id;
	private String name;
	private String address;
	private String email;
	private String companyName;
	private String contactNo;
	private String password;
	private String tinNumber;
	private String tanNumber;
	private String brandName;
	private String logoUrl;
	private List<OrderBean> orders = new ArrayList<OrderBean>();
	private List<CategoryBean> categories = new ArrayList<CategoryBean>();
	private List<ProductBean> products = new ArrayList<ProductBean>();
	private List<ExpenseCategoryBean> expensecategories = new ArrayList<ExpenseCategoryBean>();
	private List<TaxCategory> taxCategories = new ArrayList<>();
	
	public List<OrderBean> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderBean> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade=CascadeType.ALL)	
	private List<PartnerBean> partners = new ArrayList<PartnerBean>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	

	
	public List<PartnerBean> getPartners() {
		return partners;
	}

	public void setPartners(List<PartnerBean> partners) {
		this.partners = partners;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<CategoryBean> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryBean> categories) {
		this.categories = categories;
	}

	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}

	public List<ExpenseCategoryBean> getExpensecategories() {
		return expensecategories;
	}

	public void setExpensecategories(List<ExpenseCategoryBean> expensecategories) {
		this.expensecategories = expensecategories;
	}

	public String getTinNumber() {
		return tinNumber;
	}

	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}

	public String getTanNumber() {
		return tanNumber;
	}

	public void setTanNumber(String tanNumber) {
		this.tanNumber = tanNumber;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public List<TaxCategory> getTaxCategories() {
		return taxCategories;
	}

	public void setTaxCategories(List<TaxCategory> taxCategories) {
		this.taxCategories = taxCategories;
	}

	
}
