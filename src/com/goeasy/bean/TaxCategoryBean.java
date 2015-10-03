package com.goeasy.bean;

import java.util.Date;


public class TaxCategoryBean {
	private int taxCatId;
	private String taxCatName;
	private String taxCatDescription;
	private String partner;
	private Date uploadDate;
	private float taxPercent;
	public int getTaxCatId() {
		return taxCatId;
	}
	public void setTaxCatId(int taxCatId) {
		this.taxCatId = taxCatId;
	}
	public String getTaxCatName() {
		return taxCatName;
	}
	public void setTaxCatName(String taxCatName) {
		this.taxCatName = taxCatName;
	}
	public String getTaxCatDescription() {
		return taxCatDescription;
	}
	public void setTaxCatDescription(String taxCatDescription) {
		this.taxCatDescription = taxCatDescription;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public float getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(float taxPercent) {
		this.taxPercent = taxPercent;
	}
	

}
