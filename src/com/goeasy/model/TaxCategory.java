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
public class TaxCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int taxCatId;
	@Column
	private String taxCatName;
	@Column
	private String taxCatDescription;
	@Column
	private String partner;
	@Column
	private Date uploadDate;
	@Column
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
