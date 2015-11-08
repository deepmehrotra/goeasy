package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.ExpenseDao;
import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Expenses;

/**
 * @author Deep Mehortra
 *
 */
@Service("expenseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ExpenseServiceImpl implements ExpenseService {

 @Autowired
 private ExpenseDao expenseDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addExpense(Expenses expense ,int sellerId) {
	expenseDao.addExpense(expense,sellerId);
	
}

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addExpenseCategory(ExpenseCategory expenseCat ,int sellerId) {
	expenseDao.addExpenseCategory(expenseCat,sellerId);
	
}

@Override
public List<Expenses> listExpenses(int sellerId) {
	return expenseDao.listExpenses(sellerId);
}

@Override
public List<ExpenseCategory> listExpenseCategories(int sellerId)
{
	return expenseDao.listExpenseCategories(sellerId);
}
@Override
public Expenses getExpense(int expenseId) {
	return expenseDao.getExpense(expenseId);
}

@Override
public ExpenseCategory getExpenseCategory(int expenseCatId) {
	return expenseDao.getExpenseCategory(expenseCatId);
}

@Override
public int deleteExpenseCategory(ExpenseCategory expenseCat,int sellerId) {
	
	return expenseDao.deleteExpenseCategory(expenseCat,sellerId);
}

@Override
public int deleteExpense(Expenses expenses,int sellerId) {
	
	return expenseDao.deleteExpense(expenses,sellerId);
}

@Override
public double getMonthlyAmount(int catId, int sellerId)
{
	return expenseDao.getMonthlyAmount(catId, sellerId);
}
}