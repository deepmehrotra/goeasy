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

import com.goeasy.model.Category;
import com.goeasy.model.Product;
import com.goeasy.model.Seller;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

 @Autowired
 private SessionFactory sessionFactory;
 

@Override
public void addCategory(Category category,int sellerId) {
	//sellerId=4;
	Seller seller=null;
	Category parentcategory=null;
	   try
	   {
		   Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		  
		   if(category.isSubCategory())
		   {
			   System.out.println("Saving a sub category  parent is:"+category.getParentCatName());
			   
			   List<Category> listparents=listParentCategories(sellerId);
			   
			   for(Category cat:listparents)
			   {
				   System.out.println(" got Parents :"+cat.getCatName());
				   if(cat.getCatName().equalsIgnoreCase(category.getParentCatName()))
				   {
					  // category.setParent(cat);
					   System.out.println("Got parent **");
					   parentcategory=cat;
				   }
			   }
			   System.out.println("Geting parent category");
			   //parentcategory=(Category)session.get(Category.class, parentcategory.getCategoryId());
			   if(parentcategory.getSubCategory()!=null)
			   parentcategory.getSubCategory().add(category);
			   
			   System.out.println("Added sub categ to parent");
			   category.setParent(parentcategory);
			   category.setCreatedOn(new Date());
			   //session.saveOrUpdate(parentcategory);
			   System.out.println("saved parent category");
		   }
		   category.setCreatedOn(new Date());
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

@Override
public List<Category> listCategories(int sellerId) {
	//sellerId=4;
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

@Override
public List<Category> listParentCategories(int sellerId) {
	//sellerId=4;
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
}

@Override
public List<Long> getSKuCount(String catname ,int catId ,int sellerId) {
	List<Long> returnlist=null;
	Session session=null;
	//Date todayDate=new Date();
	try
	{
		session=sessionFactory.openSession();
	   session.beginTransaction();
	   returnlist=new ArrayList<Long>();
		 /*
		  * Code for caluclating sku count
		  */
		 Criteria criteriaforSkuCount=session.createCriteria(Seller.class);
		 criteriaforSkuCount.createAlias("categories", "category", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("id", sellerId))
		  .add(Restrictions.eq("category.isSubCategory",true))
		 .add(Restrictions.eq("category.parentCatName", catname));
		 criteriaforSkuCount.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList projList = Projections.projectionList();
		 projList.add(Projections.sum("category.skuCount"));
		 projList.add(Projections.sum("category.productCount"));
		 projList.add(Projections.sum("category.openingStock"));
		 criteriaforSkuCount.setProjection(projList);
		List<Object[]> catSKUCount = criteriaforSkuCount.list();
		 Iterator catIterator = catSKUCount.iterator();
		 if(catSKUCount != null){
			 while(catIterator.hasNext()){
				 Object[] recordsRow = (Object[])catIterator.next();
				 System.out.println(" Length of record row : "+recordsRow.length);
				 if(recordsRow!=null&&recordsRow.length!=0&&recordsRow[0]!=null&&recordsRow[1]!=null)
				 {
				 System.out.println(" Getting category sku count and product count : "+recordsRow[0] +" : "+recordsRow[1]);
				 returnlist.add(Long.parseLong(recordsRow[0].toString()));
				 returnlist.add(Long.parseLong(recordsRow[1].toString()));
				 returnlist.add(Long.parseLong(recordsRow[2].toString()));
				 }
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
	return returnlist;
}

@Override
public Category getCategory(int categoryId) {
	return (Category) sessionFactory.getCurrentSession().get(Category.class, categoryId);
}

@Override
public Category getCategory(String catname ,int sellerId)
{
	Category returnObject=null;
	 Seller seller=null;
	  
	 System.out.println(" ***Insid get category from catname ***"+catname);
	try
	 {
  Session session=sessionFactory.openSession();
  session.beginTransaction();
  Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
  criteria.createAlias("categories", "category", CriteriaSpecification.LEFT_JOIN)
 .add(Restrictions.eq("category.isSubCategory",true))
 .add(Restrictions.eq("category.catName", catname));
  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	
  if(criteria.list()!=null&&criteria.list().size()!=0)
  {
  seller=(Seller)criteria.list().get(0);
  returnObject=seller.getCategories().get(0);
  }
  else
  {
	   System.out.println("Product sku "+catname+" not found");
  }
  session.getTransaction().commit();
  session.close();
	 }
	 catch (Exception e) {
		System.out.println(" Categroy DAO IMPL :"+e.getLocalizedMessage());
		e.printStackTrace();
	}
	
	return returnObject;
	 
}


@Override
public int deleteCategory(Category category,int sellerId) {
	 System.out.println(" In Category delete cid "+category.getCategoryId());
	 int catId=category.getCategoryId();
	 int catdelete=0;
	 int updated=0;
	 Session session=null;
	 //sellerId=4;
	 try
	 {
		 session=sessionFactory.openSession();
		  session.beginTransaction();
		  Object obj=session.get(Category.class, catId);
		  System.out.println(" Getting category object from Id : "+obj);
		  if(obj!=null)
		  {
		  Category cat=(Category)obj;
		  System.out.println(" sub Cat size : "+cat.getSubCategory().size());
		  if(cat.isSubCategory()||cat.getSubCategory().size()==0)
		  {
			  if(cat.isSubCategory())
			  {
				 /* for(Product prod:cat.getProducts())
				  {
					  prod.setCategory(null);
				  }*/
				  cat.getProducts().clear();
				  cat.getSubCategory().clear();
				  session.saveOrUpdate(cat);
				//  session.delete(cat);
			  }
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_Category where seller_Id=? and CATEGORIES_CATEGORYID=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1, category.getCategoryId());
				
				updated = deleteQuery.executeUpdate();
				catdelete=session.createQuery("DELETE FROM Category WHERE categoryId = "+category.getCategoryId()).executeUpdate();
		
				System.out.println("  Deleteing category updated:"+updated+" catdelete :"+catdelete);
		 
		  }
		
		 /* session.delete(obj);
		  session.flush();*/

	 }
	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting order"+e.getLocalizedMessage());
		 session.getTransaction().rollback();
		 e.printStackTrace();
		 return 0;
	 }
	 finally 
	 {
		 session.getTransaction().commit();
		  session.close();
	 }
	 if(updated!=0&&catdelete!=0)
	return 1;
	 else
		 return 0;
	
}


}