package com.goeasy.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Expenses;
import com.goeasy.model.Seller;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("expenseDao")
public class ExpenseDaoImpl implements ExpenseDao {

 @Autowired
 private SessionFactory sessionFactory;
 

 @Override
 public void addExpense(Expenses expense , int sellerId)
 {

	// sellerId=4;
	 Seller seller=null;
	 ExpenseCategory parentcategory=null;

	 System.out.println(" *** Inside add expese add");
	 try
	 {
		/* if(expense.getExpenseId()!=0)
		 {
			 saveExpense(expense);
		 }
		 else
		 {*/
			 Session session=sessionFactory.openSession();
			 session.beginTransaction();
			 Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			 seller=(Seller)criteria.list().get(0);
			 expense.setCreatedOn(new Date());
			 expense.setSellerId(sellerId);
			 System.out.println(" Inside expese add");
			 List<ExpenseCategory> expcat=seller.getExpensecategories();
			 if(expcat!=null)
			 {
				 for(ExpenseCategory expCat:expcat)
				 {
					 if(expCat.getExpcatName().equalsIgnoreCase(expense.getExpenseCatName()))
					 {
						 parentcategory=expCat;
					 }
				 }
			 }
			 System.out.println(" getting  parent category ");

			 if(parentcategory!=null&&expense.getExpenseId()==0)
			 {
				 expense.setExpenseCategory(parentcategory);
				 parentcategory.getExpenses().add(expense);
			 }
			 System.out.println(" Added parent category to seller");
			 session.saveOrUpdate(parentcategory);
			// session.saveOrUpdate(expense);
			 session.getTransaction().commit();
			 session.close();
		// }
	 }
	 catch (Exception e) {
		 System.out.println("Inside exception  "+e.getLocalizedMessage());
		 e.printStackTrace();
	 }


		
	 
 }
 
 public void addExpenseCategory(ExpenseCategory category , int sellerId)
 {
	 //sellerId=4;
		Seller seller=null;
		   try
		   {
			   Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   seller=(Seller)criteria.list().get(0);
			   System.out.println("checkign if exp cat is null");
			   if(seller.getExpensecategories()!=null)
			   {
				   System.out.println("Adding expense cat");
				   seller.getExpensecategories().add(category);
			   }
			   System.out.println("saving seller cat");
		   session.saveOrUpdate(seller);
		  
		   session.getTransaction().commit();
		   session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
			   e.printStackTrace();
		}
 }

 @SuppressWarnings("unchecked")
public List<Expenses> listExpenses(int sellerId)
 {
	 //sellerId=4;
		List<Expenses> returnlist=null;
		try
		{
		Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Expenses.class).add(Restrictions.eq("sellerId", sellerId));
		   returnlist=criteria.list();
		   session.getTransaction().commit();
		   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting Expenses list :"+e.getLocalizedMessage());
		}
		return returnlist;
 }
 
 public List<ExpenseCategory> listExpenseCategories(int sellerId)
 {
	 //sellerId=4;
		List<ExpenseCategory> returnlist=null;
		try
		{
		Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Seller seller=(Seller)session.get(Seller.class, sellerId);
		   if(seller.getExpensecategories()!=null&&seller.getExpensecategories().size()!=0)
			   returnlist=seller.getExpensecategories();
		   session.getTransaction().commit();
		   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting Expense cat list :"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return returnlist;
 }
 
 public Expenses getExpense(int expenseId)
 {
	 return (Expenses) sessionFactory.getCurrentSession().get(Expenses.class, expenseId);
 }
 
 public ExpenseCategory getExpenseCategory(int expensecatId)
 {
	 return (ExpenseCategory) sessionFactory.getCurrentSession().get(ExpenseCategory.class, expensecatId);
 }
 
 public void saveExpense(Expenses expense)
 {
	  sessionFactory.getCurrentSession().saveOrUpdate(expense);
 }
 
 
 public void deleteExpense(Expenses expense,int sellerId)
 {
	 System.out.println(" In Category delete cid "+expense.getExpenseName());
	 //sellerId=4;
	 try
	 {
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  Query deleteQuery = session.createSQLQuery(
				    "delete from ExpenseCategory_Expenses where expcategoryId=? and expenses_expenseId=?");
		  		deleteQuery.setInteger(0,expense.getExpenseCategory().getExpcategoryId());
				deleteQuery.setInteger(1, expense.getExpenseId());
				
				int updated = deleteQuery.executeUpdate();
				int catdelete=session.createQuery("DELETE FROM Expenses WHERE expenseId = "+expense.getExpenseId()).executeUpdate();
		
				System.out.println("  Deleteing category updated:"+updated+" catdelete :"+catdelete);
		  session.getTransaction().commit();
		  session.close();

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting order"+e.getLocalizedMessage());
		 e.printStackTrace();
	 }
 }
 
 public void deleteExpenseCategory(ExpenseCategory expenseCat,int sellerId)
 {
	 System.out.println(" In Category delete cid "+expenseCat.getExpcatName());
	// sellerId=4;
	 try
	 {
		 if(expenseCat.getExpenses()!=null)
		 {
		 for(Expenses exp:expenseCat.getExpenses())
		 {
			 deleteExpense(exp , sellerId);
		 }
		 }
		 
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_ExpenseCategory where seller_Id=? and ExpenseCategory_expcategoryId=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1, expenseCat.getExpcategoryId());
				
				int updated = deleteQuery.executeUpdate();
				int catdelete=session.createQuery("DELETE FROM ExpenseCategory WHERE expcategoryId = "+expenseCat.getExpcategoryId()).executeUpdate();
		
				System.out.println("  Deleteing exp category updated:"+updated+" catdelete :"+catdelete);
		  session.getTransaction().commit();
		  session.close();

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting expcat"+e.getLocalizedMessage());
		 e.printStackTrace();
	 }
 }
 




}