package com.goeasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.Category;
import com.goeasy.model.Customer;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

 @Autowired
 private SessionFactory sessionFactory;
 

 /*@Override
public void addCustomer(Customer customer,int sellerId) {
	sellerId=4;
	Seller seller=null;
	   try
	   {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   seller=(Seller)criteria.list().get(0);
	   if(seller.getCategories()!=null)
	   seller.getCategories().add(category);
	   session.saveOrUpdate(seller);
	   session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
		// TODO: handle exception
	}
	
	
}
*/
/*@Override
public List<Category> listCategories(int sellerId) {
	sellerId=4;
	List<Category> returnlist=null;
	try
	{
	Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Seller seller=(Seller)session.get(Seller.class, sellerId);
	   if(seller.getCategories()!=null&&seller.getCategories().size()!=0)
		   returnlist=seller.getCategories();
	   session.getTransaction().commit();
	   session.close();
	}
	catch(Exception e)
	{
		System.out.println(" Exception in getting order list :"+e.getLocalizedMessage());
	}
	return returnlist;
}
*/

/*
 * @Override
 * public List<Category> listParentCategories(int sellerId) {
	sellerId=4;
	List<Category> returnlist=null;
	Seller seller=null;
	try
	{
	Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   criteria.createAlias("categories", "category",CriteriaSpecification.LEFT_JOIN)
	   .add(Restrictions.eq("category.isSubCategory",false))
	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	   seller=(Seller)criteria.list().get(0);
	   System.out.println(" Size of parent category list :"+seller.getCategories().size());
	   if(seller.getCategories()!=null&&seller.getCategories().size()!=0)
		   returnlist=seller.getCategories();
	   session.getTransaction().commit();
	   session.close();
	}
	catch(Exception e)
	{
		System.out.println(" Exception in getting order list :"+e.getLocalizedMessage());
	}
	return returnlist;
}*/

@Override
public Customer getCustomer(int customerId) {
	return (Customer) sessionFactory.getCurrentSession().get(Category.class, customerId);
}

@Override
public Customer getCustomer(String customerEmail,int sellerId) {
	Customer returncustomer=null;
	try
	{
	Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   //Seller seller=(Seller)session.get(Seller.class, sellerId);
	  /* System.out.println(" order from seller"+seller);
	   System.out.println(" order from seller size"+seller.getOrders().size());*/
	   Criteria criteria=session.createCriteria(Customer.class).add(Restrictions.eq("sellerId", sellerId))
			   .add(Restrictions.eq("customerEmail", customerEmail));
	  
	@SuppressWarnings("unchecked")
	List<Customer> customerlist=(List<Customer>)criteria.list(); 
	System.out.println(" Getting customer list :"+customerlist.size());
	if(customerlist!=null&&customerlist.size()!=0)
		returncustomer=customerlist.get(0);

	   session.getTransaction().commit();
	   session.close();
	}
	catch(Exception e)
	{
		System.out.println(" Exception in getting cutomer list :"+e.getLocalizedMessage());
	}
	return returncustomer;
}

/*@Override
public void deleteCategory(Category category,int sellerId) {
	 System.out.println(" In Category delete cid "+category.getCatName());
	 sellerId=4;
	 try
	 {
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_Category where seller_Id=? and category_categoryId=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1, category.getCategoryId());
				
				int updated = deleteQuery.executeUpdate();
				int catdelete=session.createQuery("DELETE FROM Category WHERE categoryId = "+category.getCategoryId()).executeUpdate();
		
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
*/

}