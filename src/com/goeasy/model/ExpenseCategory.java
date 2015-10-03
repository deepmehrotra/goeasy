package com.goeasy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ExpCat")
public class ExpenseCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int expcategoryId;
	@Column
	private String expcatName;
	@Column
	private String expcatDescription;
	@Column
	private Date createdOn;
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy="expenseCategory")
	private List<Expenses> expenses = new ArrayList<Expenses>();
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
	
	public List<Expenses> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	

}
