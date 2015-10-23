package com.goeasy.bean;



import java.util.Date;


public class ExpenseBean {

	private int expenseId;
	private String expenseName;
	private String expenseDescription;
	private String expenseCatName;
	private Date createdOn;
	private double amount;
	private int sellerId;
	private String expenditureByperson;
	private String paidTo;
	private ExpenseCategoryBean expenseCategory;
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseDescription() {
		return expenseDescription;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public ExpenseCategoryBean getExpenseCategory() {
		return expenseCategory;
	}
	public void setExpenseCategory(ExpenseCategoryBean expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getExpenseCatName() {
		return expenseCatName;
	}
	public void setExpenseCatName(String expenseCatName) {
		this.expenseCatName = expenseCatName;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getExpenditureByperson() {
		return expenditureByperson;
	}
	public void setExpenditureByperson(String expenditureByperson) {
		this.expenditureByperson = expenditureByperson;
	}
	public String getPaidTo() {
		return paidTo;
	}
	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}
	
}
