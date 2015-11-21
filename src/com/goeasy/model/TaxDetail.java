package com.goeasy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TaxDetail {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int taxId;
	@Column
	private String taxortds;
	@Column
	private String description;
	@Column
	private String status;
	@Column
	private String taxortdsCycle;
	@Column
	//used as tax category
	private String particular;
	@Column
	private Double paidAmount;
	@Column
	private Date dateOfPayment;
	@Column
	private Double balanceRemaining;
	@Column
	private Date uploadDate;
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getTaxortds() {
		return taxortds;
	}
	public void setTaxortds(String taxortds) {
		this.taxortds = taxortds;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTaxortdsCycle() {
		return taxortdsCycle;
	}
	public void setTaxortdsCycle(String taxortdsCycle) {
		this.taxortdsCycle = taxortdsCycle;
	}
	public String getParticular() {
		return particular;
	}
	public void setParticular(String particular) {
		this.particular = particular;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public Double getBalanceRemaining() {
		return balanceRemaining;
	}
	public void setBalanceRemaining(Double balanceRemaining) {
		this.balanceRemaining = balanceRemaining;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}
