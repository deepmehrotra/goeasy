package com.goeasy.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.Seller;
import com.goeasy.model.TaxCategory;
import com.goeasy.model.TaxDetail;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("taxDetailDao")
public class TaxDetailsDaoImpl implements TaxDetailsDao {

 
 @Autowired
 private SessionFactory sessionFactory;
 

 @Override
 public TaxDetail addTaxDetail(TaxDetail taxDetail , int sellerId)
 {
	
	// sellerId=4;
		Seller seller=null;
		  try
		   {
			   Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   if(taxDetail.getTaxId()==0)
			   {
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   seller=(Seller)criteria.list().get(0);
			   taxDetail.setUploadDate(new Date());
			   System.out.println(" Inside manual charges  add");
			   if(seller.getTaxDetails()!=null)
				   seller.getTaxDetails().add(taxDetail);
		 System.out.println(" taxdetail : "+taxDetail.getTaxId());
		   session.saveOrUpdate(seller);
			   }
			   else
			   {
				   taxDetail.setUploadDate(new Date());
				   session.saveOrUpdate(taxDetail);
			   }
		    session.getTransaction().commit();
		   session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
		}
		  return taxDetail;
		
	 
 }
 
 @Override
 public TaxCategory addTaxCategory(TaxCategory taxCategory , int sellerId)
 {
	
	// sellerId=4;
		Seller seller=null;
		  try
		   {
			   Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   if(taxCategory.getTaxCatId()==0)
			   {
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   seller=(Seller)criteria.list().get(0);
			   taxCategory.setUploadDate(new Date());
			   System.out.println(" Inside tax category  add");
			   if(seller.getTaxCategories()!=null)
				   seller.getTaxCategories().add(taxCategory);
		   session.saveOrUpdate(seller);
			   }
			   else
			   {
				   taxCategory.setUploadDate(new Date());
				   session.saveOrUpdate(taxCategory);
			   }
		    session.getTransaction().commit();
		   session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
		}
		  return taxCategory;
		
	 
 }
 
 
 public List<TaxDetail> listTaxDetails(int sellerId)
 {
	 //sellerId=4;
		List<TaxDetail> returnlist=null;
		try
		{
			Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   Seller seller=(Seller)session.get(Seller.class, sellerId);
			   if(seller.getTaxDetails()!=null&&seller.getTaxDetails().size()!=0)
				   returnlist=seller.getTaxDetails();
			   session.getTransaction().commit();
			   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting Manual Charges list :"+e.getLocalizedMessage());
		}
		return returnlist;
 }
 
 @Override
 public List<TaxCategory> listTaxCategories(int sellerId)
 {
	 //sellerId=4;
		List<TaxCategory> returnlist=null;
		try
		{
			Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   Seller seller=(Seller)session.get(Seller.class, sellerId);
			   if(seller.getTaxCategories()!=null&&seller.getTaxCategories().size()!=0)
				   returnlist=seller.getTaxCategories();
			   session.getTransaction().commit();
			   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting Manual Charges list :"+e.getLocalizedMessage());
		}
		return returnlist;
 }
 @Override
 public TaxDetail getTaxDetail(int mcId)
 {
	 return (TaxDetail) sessionFactory.getCurrentSession().get(TaxDetail.class, mcId);
 }
 
 @Override
 public TaxCategory getTaxCategory(int tcId)
 {
	 return (TaxCategory) sessionFactory.getCurrentSession().get(TaxCategory.class, tcId);
 }
 
 @Override
 public TaxCategory getTaxCategory(String catName, int sellerId)
 {
	 TaxCategory returnObject=null;
	 Seller seller=null;
	  
	 System.out.println(" ***Insid get taxcategory ***"+catName);
	
		 try
		 {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   criteria.createAlias("taxCategories", "taxCategory", CriteriaSpecification.LEFT_JOIN)
	   .add(Restrictions.eq("taxCategory.taxCatName", catName))
	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	   
	   if(criteria.list()!=null&&criteria.list().size()!=0)
	   {
	   seller=(Seller)criteria.list().get(0);
	   returnObject=seller.getTaxCategories().get(0);
	   }
	   session.getTransaction().commit();
	   session.close();
		 }
		 catch (Exception e) {
			System.out.println(" Taxdetails  DAO IMPL :"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		 
		 return returnObject;
	 
 }
 
 @Override
 public void deleteTaxDetail(TaxDetail taxDetail,int sellerId)
 {
	 System.out.println(" In Tax Detail delete cid "+taxDetail.getTaxId());
	//sellerId=4;
	 try
	 {
		
		 
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_TaxDetail where seller_Id=? and TaxDetails_taxId=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1,taxDetail.getTaxId());
				
				int updated = deleteQuery.executeUpdate();
				int catdelete=session.createQuery("DELETE FROM TaxDetail WHERE taxId = "+taxDetail.getTaxId()).executeUpdate();
		
				System.out.println("  Deleteing manual charges updated:"+updated+" catdelete :"+catdelete);
		  session.getTransaction().commit();
		  session.close();

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting taxdetail"+e.getLocalizedMessage());
		 e.printStackTrace();
	 }
 }
 

 @Override
 public void deleteTaxCategory(TaxCategory taxCategory,int sellerId)
 {
	 System.out.println(" In Tax Detail delete cid "+taxCategory.getTaxCatId());
	//sellerId=4;
	 try
	 {
		
		 
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_TaxCategory where seller_Id=? and TaxCategory_taxCatId=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1,taxCategory.getTaxCatId());
				
				int updated = deleteQuery.executeUpdate();
				int catdelete=session.createQuery("DELETE FROM TaxCategory WHERE taxCatId = "+taxCategory.getTaxCatId()).executeUpdate();
		
				System.out.println("  Deleteing manual charges updated:"+updated+" catdelete :"+catdelete);
		  session.getTransaction().commit();
		  session.close();

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting taxdetail"+e.getLocalizedMessage());
		 e.printStackTrace();
	 }
 }



}