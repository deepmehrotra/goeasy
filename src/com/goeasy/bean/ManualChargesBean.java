package com.goeasy.bean;

import java.util.Date;

public class ManualChargesBean {
	
	private int mcId;
	
	private String chargesCategory;
	private String chargesDesc;
	private String partner;
	private String particular;
	private Double paidAmount;
	private Date dateOfPayment;
	private String paymentCycle;
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
