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
public class ManualCharges {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int mcId;
	@Column
	private String chargesCategory;
	@Column
	private String chargesDesc;
	@Column
	private String partner;
	@Column
	private String particular;
	@Column
	private Double paidAmount;
	@Column
	private Date dateOfPayment;
	@Column
	private String paymentCycle;
	@Column
	private Date uploadDate;
	public int getMcId() {
		return mcId;
	}
	public void setMcId(int mcId) {
		this.mcId = mcId;
	}
	public String getChargesCategory() {
		return chargesCategory;
	}
	public void setChargesCategory(String chargesCategory) {
		this.chargesCategory = chargesCategory;
	}
	public String getChargesDesc() {
		return chargesDesc;
	}
	public void setChargesDesc(String chargesDesc) {
		this.chargesDesc = chargesDesc;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
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
	public String getPaymentCycle() {
		return paymentCycle;
	}
	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	

}
