package com.goeasy.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentUploadBean {
	
	
	private int uploadId;
	private String uploadDesc;
	private Date uploadDate;
	private double totalpositivevalue;
	private double totalnegativevalue;
	private double netRecievedAmount;
	private String uploadStatus;
	private List<OrderBean> orders =new ArrayList<OrderBean>();
	public int getUploadId() {
		return uploadId;
	}
	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}
	public String getUploadDesc() {
		return uploadDesc;
	}
	public void setUploadDesc(String uploadDesc) {
		this.uploadDesc = uploadDesc;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public double getTotalpositivevalue() {
		return totalpositivevalue;
	}
	public void setTotalpositivevalue(double totalpositivevalue) {
		this.totalpositivevalue = totalpositivevalue;
	}
	public double getNetRecievedAmount() {
		return netRecievedAmount;
	}
	public void setNetRecievedAmount(double netRecievedAmount) {
		this.netRecievedAmount = netRecievedAmount;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public List<OrderBean> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderBean> orders) {
		this.orders = orders;
	}
	public double getTotalnegativevalue() {
		return totalnegativevalue;
	}
	public void setTotalnegativevalue(double totalnegativevalue) {
		this.totalnegativevalue = totalnegativevalue;
	}
	

}
