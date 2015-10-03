package com.goeasy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int customerId;
	@Column
	private String customerName;
	@Column
	private String customerPhnNo;
	@Column
	private String customerEmail;
	@Column
	private String customerCity;
	@Column
	private String customerAddress;
	@Column
	private int sellerId;
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)  
	private List<Order> orders =new ArrayList<Order>();
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhnNo() {
		return customerPhnNo;
	}
	public void setCustomerPhnNo(String customerPhnNo) {
		this.customerPhnNo = customerPhnNo;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
