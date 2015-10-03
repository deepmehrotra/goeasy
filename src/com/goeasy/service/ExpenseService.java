package com.goeasy.service;

import java.util.List;

import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Expenses;

/**
 * @author Deep Mehrotra
 *
 */
public interface ExpenseService {
 
 public void addExpense(Expenses expense , int sellerId);
 
 public void addExpenseCategory(ExpenseCategory category , int sellerId);

 public List<Expenses> listExpenses(int sellerId);
 
 public List<ExpenseCategory> listExpenseCategories(int sellerId);
 
 public Expenses getExpense(int expenseId);
 
 public ExpenseCategory getExpenseCategory(int expensecatId);
 
 public void deleteExpense(Expenses expense,int sellerId);
 
 public void deleteExpenseCategory(ExpenseCategory expenseCat,int sellerId);
}