package com.goeasy.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseCategoryBean {

	private int expcategoryId;
	private String expcatName;
	private String expcatDescription;
	private Date createdOn;
	private double monthlyAmount;
	private List<ExpenseBean> expenses = new ArrayList<ExpenseBean>();
	public int getExpcategoryId() {
		return expcategoryId;
	}
	public void setExpcategoryId(int expcategoryId) {
		this.expcategoryId = expcategoryId;
	}
	public String getExpcatName() {
		return expcatName;
	}
	public void setExpcatName(String expcatName) {
		this.expcatName = expcatName;
	}
	public String getExpcatDescription() {
		return expcatDescription;
	}
	public void setExpcatDescription(String expcatDescription) {
		this.expcatDescription = expcatDescription;
	}
	
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public List<ExpenseBean> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<ExpenseBean> expenses) {
		this.expenses = expenses;
	}
	public double getMonthlyAmount() {
		return monthlyAmount;
	}
	public void setMonthlyAmount(double monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}
}
