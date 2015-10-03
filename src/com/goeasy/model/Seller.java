package com.goeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;





@Entity
@Table(name="Seller")
public class Seller {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String email;
	@Column
	private String CompanyName;
	@Column
	private String contactNo;
	@Column
	private String password;
	@Column
	private String tinNumber;
	@Column
	private String tanNumber;
	@Column
	private String brandName;
	@Column
	private String logoUrl;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="seller_roles",
		joinColumns = {@JoinColumn(name="seller_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private Role role;
	@OneToMany(mappedBy = "seller",cascade=CascadeType.ALL)	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<Order> orders = new ArrayList<Order>();
	
	@OneToMany(cascade=CascadeType.ALL)	
	private List<Category> categories = new ArrayList<Category>();
	
	@OneToMany(cascade=CascadeType.ALL)	
	private List<Product> products = new ArrayList<Product>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
	            name = "Seller_ExpCat",
	            joinColumns = @JoinColumn(name = "ID"),
	            inverseJoinColumns = @JoinColumn(name = "EXPCATID")
	    )
	private List<ExpenseCategory> expensecategories = new ArrayList<ExpenseCategory>();
	@OneToMany(cascade=CascadeType.ALL)	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<Partner> partners = new ArrayList<Partner>();
	
	@OneToMany(cascade=CascadeType.ALL)	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<PaymentUpload> paymentUploads = new ArrayList<PaymentUpload>();
	
	@OneToMany(cascade=CascadeType.ALL)	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<ManualCharges> manualCharges = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<TaxDetail> taxDetails = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private List<TaxCategory> taxCategories = new ArrayList<>();
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

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	

	
	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ExpenseCategory> getExpensecategories() {
		return expensecategories;
	}

	public void setExpensecategories(List<ExpenseCategory> expensecategories) {
		this.expensecategories = expensecategories;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<PaymentUpload> getPaymentUploads() {
		return paymentUploads;
	}

	public void setPaymentUploads(List<PaymentUpload> paymentUploads) {
		this.paymentUploads = paymentUploads;
	}

	public List<ManualCharges> getManualCharges() {
		return manualCharges;
	}

	public void setManualCharges(List<ManualCharges> manualCharges) {
		this.manualCharges = manualCharges;
	}

	public List<TaxDetail> getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(List<TaxDetail> taxDetails) {
		this.taxDetails = taxDetails;
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
