package com.goeasy.bean;


import java.util.Date;


public class ProductStockListBean {
	private int prodStockId;
	private Date updateDate;
	private long stockAvailable;
	private int month;
	private int date;
	private int year;

	public int getProdStockId() {
		return prodStockId;
	}

	public void setProdStockId(int prodStockId) {
		this.prodStockId = prodStockId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public long getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(long stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
