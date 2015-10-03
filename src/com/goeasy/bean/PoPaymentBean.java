package com.goeasy.bean;

import java.util.Date;

public class PoPaymentBean {

	private String poOrderId;
	private String invoiceID;
	private String pcName;
	private String gatePassId;
	private double positiveAmount;
	private double negativeAmount;
	private Date paymentDate;
	private int quantity;
	public String getPoOrderId() {
		return poOrderId;
	}
	public void setPoOrderId(String poOrderId) {
		this.poOrderId = poOrderId;
	}
	public String getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public String getGatePassId() {
		return gatePassId;
	}
	public void setGatePassId(String gatePassId) {
		this.gatePassId = gatePassId;
	}
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPositiveAmount() {
		return positiveAmount;
	}
	public void setPositiveAmount(double positiveAmount) {
		this.positiveAmount = positiveAmount;
	}
	public double getNegativeAmount() {
		return negativeAmount;
	}
	public void setNegativeAmount(double negativeAmount) {
		this.negativeAmount = negativeAmount;
	}
	
}
