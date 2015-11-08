package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Expenses;

/**
 * @author Deep Mehrotra
 *
 */
public interface ExpenseDao {
 
 public void addExpense(Expenses expense , int sellerId);
 
 public void addExpenseCategory(ExpenseCategory category , int sellerId);

 public List<Expenses> listExpenses(int sellerId);
 
 public List<ExpenseCategory> listExpenseCategories(int sellerId);
 
 public Expenses getExpense(int expenseId);
 
 public ExpenseCategory getExpenseCategory(int expensecatId);
 
 public int deleteExpense(Expenses expense,int sellerId);
 
 public int deleteExpenseCategory(ExpenseCategory expenseCat,int sellerId);

double getMonthlyAmount(int catId, int sellerId);
}