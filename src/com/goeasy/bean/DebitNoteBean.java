package com.goeasy.bean;

import java.util.Date;


public class DebitNoteBean {
	
	private String POOrderId;
	private String gatePassId;
	private String SKU;
	private String pcName;
	private String invoiceId;
	private Double amount;
	private int quantity;
	private String returnReason;
	private Date returnDate;
	public String getPOOrderId() {
		return POOrderId;
	}
	public void setPOOrderId(String pOOrderId) {
		POOrderId = pOOrderId;
	}
	public String getGatePassId() {
		return gatePassId;
	}
	public void setGatePassId(String gatePassId) {
		this.gatePassId = gatePassId;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
