package com.goeasy.bean;

import java.util.Date;

public class OrderRTOorReturnBean {
	 int returnId;
	private String returnOrRTOreason;
	private Date returnDate;
	private double returnOrRTOCharges;
	private int returnorrtoQty;
	private String returnOrRTOId;
	private Date returnUploadDate;
	private double returnOrRTOChargestoBeDeducted;
	private int sellerId;
	
	public int getReturnId() {
		return returnId;
	}
	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}
	public String getReturnOrRTOreason() {
		return returnOrRTOreason;
	}
	public void setReturnOrRTOreason(String returnOrRTOreason) {
		this.returnOrRTOreason = returnOrRTOreason;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public double getReturnOrRTOCharges() {
		return returnOrRTOCharges;
	}
	public void setReturnOrRTOCharges(double returnOrRTOCharges) {
		this.returnOrRTOCharges = returnOrRTOCharges;
	}
	public int getReturnorrtoQty() {
		return returnorrtoQty;
	}
	public void setReturnorrtoQty(int returnorrtoQty) {
		this.returnorrtoQty = returnorrtoQty;
	}
	public String getReturnOrRTOId() {
		return returnOrRTOId;
	}
	public void setReturnOrRTOId(String returnOrRTOId) {
		this.returnOrRTOId = returnOrRTOId;
	}
	public Date getReturnUploadDate() {
		return returnUploadDate;
	}
	public void setReturnUploadDate(Date returnUploadDate) {
		this.returnUploadDate = returnUploadDate;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public double getReturnOrRTOChargestoBeDeducted() {
		return returnOrRTOChargestoBeDeducted;
	}
	public void setReturnOrRTOChargestoBeDeducted(
			double returnOrRTOChargestoBeDeducted) {
		this.returnOrRTOChargestoBeDeducted = returnOrRTOChargestoBeDeducted;
	}
	
	
	
}
