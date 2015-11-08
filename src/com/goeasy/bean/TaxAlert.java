package com.goeasy.bean;

public class TaxAlert {
	private String quarter;
	private double taxAmount;
	private boolean isTax;
	private double paidAmount;
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public boolean isTax() {
		return isTax;
	}
	public void setTax(boolean isTax) {
		this.isTax = isTax;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

}
