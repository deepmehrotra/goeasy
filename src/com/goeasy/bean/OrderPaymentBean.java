package com.goeasy.bean;

import java.util.Date;

public class OrderPaymentBean {
	
	
	private int orderpayId;
	private String paymentdesc;
	private Date dateofPayment;
	private Date uploadDate;
	private double negativeAmount;
	private double positiveamout;
	private double actualrecived2;
	private double netPaymentResult;
	private double paymentDifference;
	private int paymentUploadId;
	private int sellerId;
	private String paymentCycle;
	private Date paymentCycleStart;
	private Date paymentCycleEnd;
	public int getOrderpayId() {
		return orderpayId;
	}
	public void setOrderpayId(int orderpayId) {
		this.orderpayId = orderpayId;
	}
	public String getPaymentdesc() {
		return paymentdesc;
	}
	public void setPaymentdesc(String paymentdesc) {
		this.paymentdesc = paymentdesc;
	}
	public Date getDateofPayment() {
		return dateofPayment;
	}
	public void setDateofPayment(Date dateofPayment) {
		this.dateofPayment = dateofPayment;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getPaymentUploadId() {
		return paymentUploadId;
	}
	public void setPaymentUploadId(int paymentUploadId) {
		this.paymentUploadId = paymentUploadId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public double getActualrecived2() {
		return actualrecived2;
	}
	public void setActualrecived2(double actualrecived2) {
		this.actualrecived2 = actualrecived2;
	}

	public String getPaymentCycle() {
		return paymentCycle;
	}
	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	
	public double getNegativeAmount() {
		return negativeAmount;
	}
	public void setNegativeAmount(double negativeAmount) {
		this.negativeAmount = negativeAmount;
	}
	public double getPositiveamout() {
		return positiveamout;
	}
	public void setPositiveamout(double positiveamout) {
		this.positiveamout = positiveamout;
	}
	public double getNetPaymentResult() {
		return netPaymentResult;
	}
	public void setNetPaymentResult(double netPaymentResult) {
		this.netPaymentResult = netPaymentResult;
	}
	public double getPaymentDifference() {
		return paymentDifference;
	}
	public void setPaymentDifference(double paymentDifference) {
		this.paymentDifference = paymentDifference;
	}
	public Date getPaymentCycleStart() {
		return paymentCycleStart;
	}
	public void setPaymentCycleStart(Date paymentCycleStart) {
		this.paymentCycleStart = paymentCycleStart;
	}
	public Date getPaymentCycleEnd() {
		return paymentCycleEnd;
	}
	public void setPaymentCycleEnd(Date paymentCycleEnd) {
		this.paymentCycleEnd = paymentCycleEnd;
	}

}
