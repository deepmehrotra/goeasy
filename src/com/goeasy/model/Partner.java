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
public class Partner {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int pcId;
	@Column
	private String pcName;
	@Column
	private String pcDesc;
	@Column
	private String pcLogoUrl;
	@Column
	private String paymentType;
	@Column
	private boolean isshippeddatecalc;
	@Column
	private int noofdaysfromshippeddate;
	@Column
	private int startcycleday;
	@Column
	private int paycycleduration;
	@Column
	private int paydaysfromstartday;
	@Column
	private int maxRTOAcceptance;
	@Column
	private int maxReturnAcceptance;
	@Column
	private String taxcategory;
	@Column
	private float taxrate;
	@Column
	private boolean tdsApplicable;
	@Column
	private boolean paycyclefromshipordel;
	@Column
	private int monthlypaydate;
	
	@OneToMany(cascade=CascadeType.ALL)	
	private List<Order> orders = new ArrayList<Order>();
	public int getPcId() {
		return pcId;
	}
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public String getPcDesc() {
		return pcDesc;
	}
	public void setPcDesc(String pcDesc) {
		this.pcDesc = pcDesc;
	}
	public String getPcLogoUrl() {
		return pcLogoUrl;
	}
	public void setPcLogoUrl(String pcLogoUrl) {
		this.pcLogoUrl = pcLogoUrl;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public boolean isIsshippeddatecalc() {
		return isshippeddatecalc;
	}
	public void setIsshippeddatecalc(boolean isshippeddatecalc) {
		this.isshippeddatecalc = isshippeddatecalc;
	}
	public int getNoofdaysfromshippeddate() {
		return noofdaysfromshippeddate;
	}
	public void setNoofdaysfromshippeddate(int noofdaysfromshippeddate) {
		this.noofdaysfromshippeddate = noofdaysfromshippeddate;
	}
	
	public int getStartcycleday() {
		return startcycleday;
	}
	public void setStartcycleday(int startcycleday) {
		this.startcycleday = startcycleday;
	}
	public int getPaycycleduration() {
		return paycycleduration;
	}
	public void setPaycycleduration(int paycycleduration) {
		this.paycycleduration = paycycleduration;
	}
	public int getPaydaysfromstartday() {
		return paydaysfromstartday;
	}
	public void setPaydaysfromstartday(int paydaysfromstartday) {
		this.paydaysfromstartday = paydaysfromstartday;
	}
	public int getMaxRTOAcceptance() {
		return maxRTOAcceptance;
	}
	public void setMaxRTOAcceptance(int maxRTOAcceptance) {
		this.maxRTOAcceptance = maxRTOAcceptance;
	}
	public int getMaxReturnAcceptance() {
		return maxReturnAcceptance;
	}
	public void setMaxReturnAcceptance(int maxReturnAcceptance) {
		this.maxReturnAcceptance = maxReturnAcceptance;
	}
	public String getTaxcategory() {
		return taxcategory;
	}
	public void setTaxcategory(String taxcategory) {
		this.taxcategory = taxcategory;
	}
	public float getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(float taxrate) {
		this.taxrate = taxrate;
	}
	public boolean isTdsApplicable() {
		return tdsApplicable;
	}
	public void setTdsApplicable(boolean tdsApplicable) {
		this.tdsApplicable = tdsApplicable;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public boolean isPaycyclefromshipordel() {
		return paycyclefromshipordel;
	}
	public void setPaycyclefromshipordel(boolean paycyclefromshipordel) {
		this.paycyclefromshipordel = paycyclefromshipordel;
	}
	public int getMonthlypaydate() {
		return monthlypaydate;
	}
	public void setMonthlypaydate(int monthlypaydate) {
		this.monthlypaydate = monthlypaydate;
	}
	
	
}
