package com.goeasy.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
	 Session session=null;

	 System.out.println(" *** Inside add expese add");
	 try
	 {
		 session=sessionFactory.openSession();
		 session.beginTransaction();
		/* if(expense.getExpenseId()!=0)
		 {
			session.saveOrUpdate(expense);
		 }
		 else
		 {*/
		 	
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
			
		 //}
	 }
	 catch (Exception e) {
		 System.out.println("Inside exception  "+e.getLocalizedMessage());
		 e.printStackTrace();
		 session.getTransaction().rollback();
	 }
	 finally
	 {
		 session.getTransaction().commit();
		 session.close();
	 }


		
	 
 }
 
 @Override
 public double getMonthlyAmount(int catId ,int sellerId) {
		List<Long> returnlist=null;
		Session session=null;
		Date tommorrowDate=new Date();
		tommorrowDate.setDate(tommorrowDate.getDate()+1);
		Date monthStartDate=new Date();
		monthStartDate.setDate(1);
		Double returnAmount=0.0;
		try
		{
			session=sessionFactory.openSession();
		   session.beginTransaction();
		   returnlist=new ArrayList<Long>();
			 /*
			  * Code for caluclating sku count
			  */
			 Criteria criteriaforSkuCount=session.createCriteria(Expenses.class);
			 criteriaforSkuCount.createAlias("expenseCategory", "expCat", CriteriaSpecification.LEFT_JOIN)
			 .add(Restrictions.eq("expCat.expcategoryId", catId))
			 .add(Restrictions.between("createdOn", monthStartDate,tommorrowDate));
			 criteriaforSkuCount.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			 ProjectionList projList = Projections.projectionList();
			 projList.add(Projections.sum("amount"));
			criteriaforSkuCount.setProjection(projList);
			List<Object[]> expenseAmount = criteriaforSkuCount.list();
			 Iterator catIterator = expenseAmount.iterator();
			 if(expenseAmount != null){
				 while(catIterator.hasNext()){
					 //Object[] recordsRow = (Object[])catIterator.next();
					 Double recordsRow = (Double)catIterator.next();
					// System.out.println(" Length of record row : "+recordsRow.length);
					 /*if(recordsRow!=null&&recordsRow.length!=0&&recordsRow[0]!=null)
					 {
						 returnAmount=Double.parseDouble(recordsRow[0].toString());
					
					 }*/
					 returnAmount=recordsRow;
					 System.out.println("recordsRow:  "+recordsRow);
				 }
			 }
			 
		
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting category count :"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			session.getTransaction().commit();
			   session.close();
		}
		if(returnAmount!=null)
		return returnAmount;
		else
		return 0.0;
	}

 
 public void addExpenseCategory(ExpenseCategory category , int sellerId)
 {
	 //sellerId=4;
	 Seller seller=null;
	  try
	   {
		   Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   if(category.getExpcategoryId()==0)
		   {
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		   seller=(Seller)criteria.list().get(0);
		   category.setCreatedOn(new Date());
		  
		   System.out.println(" Inside expense category  add");
		   if(seller.getExpensecategories()!=null)
			   seller.getExpensecategories().add(category);
	   session.saveOrUpdate(seller);
		   }
		   else
		   {
			   category.setCreatedOn(new Date());
			   session.saveOrUpdate(category);
		   }
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	  System.out.println(" Saved Expense Group : "+category.getExpcategoryId());
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
 
 
 public int deleteExpense(Expenses expense,int sellerId)
 {
	 System.out.println(" In Expense delete cid "+expense.getExpenseId());
	 //sellerId=4;
	 Session session=null;
	 int catdelete=0;
	 try
	 {
		 session=sessionFactory.openSession();
		  session.beginTransaction();
		/*  Query deleteQuery = session.createSQLQuery(
				    "delete from ExpenseCategory_Expenses where expcategoryId=? and expenses_expenseId=?");
		  		deleteQuery.setInteger(0,expense.getExpenseCategory().getExpcategoryId());
				deleteQuery.setInteger(1, expense.getExpenseId());
				
				int updated = deleteQuery.executeUpdate();*/
				catdelete=session.createQuery("DELETE FROM Expenses WHERE expenseId = "+expense.getExpenseId()).executeUpdate();
		
				System.out.println("  Deleteing category catdelete :"+catdelete);
		  

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting order"+e.getLocalizedMessage());
		 e.printStackTrace();
		 session.getTransaction().rollback();
		 return 0;
	 }
	 finally
	 {
		 session.getTransaction().commit();
		  session.close();
	 }
	 return catdelete;
 }
 
 public int deleteExpenseCategory(ExpenseCategory expenseCat,int sellerId)
 {
	 System.out.println(" In Category delete cid "+expenseCat.getExpcategoryId());
	// sellerId=4;
	 int updated=0;
	 int catdelete=0;
	 Session session=null;
	 
	 
	 try
	 {
		
		session=sessionFactory.openSession();
		  session.beginTransaction();
		  expenseCat=(ExpenseCategory)session.get(ExpenseCategory.class, expenseCat.getExpcategoryId());
		  if(expenseCat.getExpenses()!=null&&expenseCat.getExpenses().size()!=0)
			 {
			return 0;
			 }
			 
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_ExpCat where Id="+sellerId+"and EXPCATID="+expenseCat.getExpcategoryId());
		  		/*deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1, expenseCat.getExpcategoryId());*/
				
				updated = deleteQuery.executeUpdate();
				catdelete=session.createQuery("DELETE FROM ExpenseCategory WHERE expcategoryId = "+expenseCat.getExpcategoryId()).executeUpdate();
		
				System.out.println("  Deleteing exp category updated:"+updated+" catdelete :"+catdelete);
		 

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting expcat"+e.getLocalizedMessage());
		 e.printStackTrace();
		 return 0;
	 }
	 finally
	 {
		 session.getTransaction().commit();
		  session.close();
	 }
	 if(updated!=0&&catdelete!=0)
	 {
		 return updated;
	 }
	 return 0;
 }
 




}