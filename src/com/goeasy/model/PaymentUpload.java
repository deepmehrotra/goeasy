package com.goeasy.model;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name="PaymentUpload")
public class PaymentUpload {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int uploadId;
	@Column
	private String uploadDesc;
	@Column
	private Date uploadDate;
	@Column
	private double totalpositivevalue;
	@Column
	private double totalnegativevalue;
	@Column
	private double netRecievedAmount;
	@Column
	private String uploadStatus;
	@OneToMany(cascade=CascadeType.ALL)  
	private List<Order> orders =new ArrayList<Order>();
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public double getTotalnegativevalue() {
		return totalnegativevalue;
	}
	public void setTotalnegativevalue(double totalnegativevalue) {
		this.totalnegativevalue = totalnegativevalue;
	}

	

	

}
