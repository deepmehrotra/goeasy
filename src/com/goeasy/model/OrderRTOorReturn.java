package com.goeasy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OrderReturn")
public class OrderRTOorReturn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	    private int returnId;
	@Column
	private String returnOrRTOreason;
	@Column
	private Date returnDate;
	@Column
	private double returnOrRTOCharges;
	@Column
	private int returnorrtoQty;
	@Column
	private String returnOrRTOId;
	@Column
	private Date returnUploadDate;
	@Column(name="estimateddeduction")
	private double returnOrRTOChargestoBeDeducted;
	@Column
	private String returnOrRTOstatus;
	
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
	public int getReturnId() {
		return returnId;
	}
	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}
	@Override
	public String toString() {
		return "OrderRTOorReturn [returnId=" + returnId
				+ ", returnOrRTOreason=" + returnOrRTOreason + ", returnDate="
				+ returnDate + ", returnOrRTOCharges=" + returnOrRTOCharges
				+ ", returnorrtoQty=" + returnorrtoQty + ", returnOrRTOId="
				+ returnOrRTOId + ", returnUploadDate=" + returnUploadDate
				+ "]";
	}
	public double getReturnOrRTOChargestoBeDeducted() {
		return returnOrRTOChargestoBeDeducted;
	}
	public void setReturnOrRTOChargestoBeDeducted(
			double returnOrRTOChargestoBeDeducted) {
		this.returnOrRTOChargestoBeDeducted = returnOrRTOChargestoBeDeducted;
	}
	public String getReturnOrRTOstatus() {
		return returnOrRTOstatus;
	}
	public void setReturnOrRTOstatus(String returnOrRTOstatus) {
		this.returnOrRTOstatus = returnOrRTOstatus;
	}
	
	
	
}
