package com.goeasy.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
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
 
 private final SimpleDateFormat formatter = new SimpleDateFormat("MMMM,yy");
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
 public TaxDetail addMonthlyTaxDetail(Session session,TaxDetail taxDetail , int sellerId)
 {
	 System.out.println(" Add Monthly TDS : cat : "+taxDetail.getParticular()+" Amoun :"+taxDetail.getBalanceRemaining());
		
	
	// sellerId=4;
		Seller seller=null;
		TaxDetail existingObj=null;
		Date todaysDate=new Date();
		double amount=taxDetail.getBalanceRemaining();
		  try
		   {
			  // Session session=sessionFactory.openSession();
			   session.beginTransaction();
			  
				  /* DetatchedCriteria subquerry=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
				   subquerry.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
				   .add(Restrictions.eq("taxDetail.particular", taxDetail.getParticular()))
				   .setProjection(Projections.max("uploadDate"))
				   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);  */ 
				   
				   DetachedCriteria maxQuery = DetachedCriteria.forClass( Seller.class );
				   maxQuery.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
				    .add(Restrictions.eq("taxDetail.particular", taxDetail.getParticular()))
				   .setProjection(Projections.max("taxDetail.uploadDate"))
				   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);   
				   
				  
System.out.println(" Go detached criteria in monthly tax detail  : ");
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   criteria.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
			   .add(Restrictions.eq("taxDetail.particular", taxDetail.getParticular()))
			   //.add(Subqueries.eq("taxDetail.uploadDate", maxQuery))
			   .add( Property.forName( "taxDetail.uploadDate" ).eq( maxQuery ))
			   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			   
			  
			   
			   System.out.println(" Got criteria in monthy add tax  : ");
			   if(criteria.list()!=null&&criteria.list().size()!=0)
			   {
				   seller=(Seller)criteria.list().get(0);
				   if(seller.getTaxDetails()!=null&&seller.getTaxDetails().size()!=0)
				   {
				   existingObj=seller.getTaxDetails().get(0);
				   
					  if(existingObj.getUploadDate().getMonth()!=todaysDate.getMonth())
					   {
						   taxDetail.setStatus("Due");
						   taxDetail.setDescription("Tax Amount for "+formatter.format(todaysDate));
						   taxDetail.setTaxortds("Tax");
						   taxDetail.setTaxortdsCycle(formatter.format(todaysDate));
						   taxDetail.setUploadDate(todaysDate);
						   seller.getTaxDetails().add(taxDetail);
						   session.saveOrUpdate(seller);
					   }
					   else if(existingObj.getUploadDate().getMonth()==todaysDate.getMonth())
					   {
						   existingObj.setBalanceRemaining(existingObj.getBalanceRemaining()+amount);
						   existingObj.setUploadDate(todaysDate);
						   session.saveOrUpdate(existingObj);
					   }
				   }
			   }
			   else
			   {
				   seller=(Seller)session.get(Seller.class, sellerId);
				   System.out.println(" saving new tax object ");
				   taxDetail.setStatus("Due");
				   taxDetail.setDescription("Tax Amount for "+formatter.format(todaysDate));
				   taxDetail.setTaxortds("Tax");
				   taxDetail.setTaxortdsCycle(formatter.format(todaysDate));
				   taxDetail.setUploadDate(todaysDate);
				   seller.getTaxDetails().add(taxDetail);
				   session.saveOrUpdate(seller);
			   }
			 
			    
		    session.getTransaction().commit();
		  // session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception in add monthly taxdetails  "+e.getLocalizedMessage());
			  /* if( session.getTransaction().isActive())
				   session.getTransaction().rollback(); */
			   e.printStackTrace();
			   throw e;
		}
		  return taxDetail;
		
	 
 }
 
 @Override
 public TaxDetail addMonthlyTDSDetail(Session session,TaxDetail taxDetail , int sellerId)
 {
	System.out.println(" Add Monthly TDS : cat : "+taxDetail.getParticular()+" Amoun :"+taxDetail.getBalanceRemaining());
	// sellerId=4;
		Seller seller=null;
		TaxDetail existingObj=null;
		Date todaysDate=new Date();
		double amount=taxDetail.getBalanceRemaining();
		  try
		   {
			  // Session session=sessionFactory.openSession();
			   session.beginTransaction();
			  
				   DetachedCriteria maxQuery = DetachedCriteria.forClass( Seller.class );
				   maxQuery.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
				    .add(Restrictions.eq("taxDetail.particular", "TDS"))
				    .add(Restrictions.eq("taxDetail.taxortds", "TDS"))
				   .setProjection(Projections.max("taxDetail.uploadDate"))
				   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);   
				  

			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   criteria.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
			   .add(Restrictions.eq("taxDetail.particular","TDS"))
			    .add(Restrictions.eq("taxDetail.taxortds", "TDS"))
			   .add(Property.forName( "taxDetail.uploadDate" ).eq( maxQuery ))
			   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			   if(criteria.list()!=null&&criteria.list().size()!=0)
			   {
				   seller=(Seller)criteria.list().get(0);
				   if(seller.getTaxDetails()!=null&&seller.getTaxDetails().size()!=0)
				   {
				   existingObj=seller.getTaxDetails().get(0);
				   
					  if(existingObj.getUploadDate().getMonth()!=todaysDate.getMonth())
					   {
						   taxDetail.setStatus("Due");
						   taxDetail.setDescription("TDS for "+formatter.format(todaysDate));
						   taxDetail.setTaxortds("TDS");
						   taxDetail.setTaxortdsCycle(formatter.format(todaysDate));
						   taxDetail.setParticular("TDS");
						   taxDetail.setUploadDate(todaysDate);
						   seller.getTaxDetails().add(taxDetail);
						   session.saveOrUpdate(seller);
					   }
					   else if(existingObj.getUploadDate().getMonth()==todaysDate.getMonth())
					   {
						   existingObj.setBalanceRemaining(existingObj.getBalanceRemaining()+amount);
						   existingObj.setUploadDate(todaysDate);
						   session.saveOrUpdate(existingObj);
					   }
				   }
			   }
			   else
			   {
				   seller=(Seller)session.get(Seller.class, sellerId);
				   System.out.println(" saving new TDS object ");
					  
				   taxDetail.setStatus("Due");
				   taxDetail.setDescription("TDS for "+formatter.format(todaysDate));
				   taxDetail.setTaxortds("TDS");
				   taxDetail.setParticular("TDS");
				   taxDetail.setTaxortdsCycle(formatter.format(todaysDate));
				   taxDetail.setUploadDate(todaysDate);
				   seller.getTaxDetails().add(taxDetail);
				   session.saveOrUpdate(seller);
			   }
			 
			    
		    session.getTransaction().commit();
		  // session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
			   e.printStackTrace();
			   throw e;
		}
		  return taxDetail;
		
	 
 }
 
 @Override
 public void addPaymentTaxDetail(TaxDetail taxDetail , int sellerId)
 {
	
	// sellerId=4;
		//Seller seller=null;
		TaxDetail existingObj=null;
		Date todaysDate=new Date();
		double amount=taxDetail.getPaidAmount();
		int taxDetailid=taxDetail.getTaxId();
		  try
		   {
			  Session session=sessionFactory.openSession();
			   session.beginTransaction();
			  
			   existingObj=(TaxDetail)session.get(TaxDetail.class, taxDetailid);
			   existingObj.setStatus(taxDetail.getStatus());
			   existingObj.setPaidAmount(amount);
			   existingObj.setDateOfPayment(todaysDate);
			   session.saveOrUpdate(existingObj);
		    session.getTransaction().commit();
		  	session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
			   e.printStackTrace();
		}
		
		
	 
 }
 
 @Override
 public void addStatusTDSDetail(TaxDetail taxDetail , int sellerId)
 {
	
	// sellerId=4;
		//Seller seller=null;
		TaxDetail existingObj=null;
		Date todaysDate=new Date();
		String status=taxDetail.getStatus();
		int taxDetailid=taxDetail.getTaxId();
		  try
		   {
			  Session session=sessionFactory.openSession();
			   session.beginTransaction();
			  
			   existingObj=(TaxDetail)session.get(TaxDetail.class, taxDetailid);
			   existingObj.setStatus(status);
			   existingObj.setDateOfPayment(todaysDate);
			   session.saveOrUpdate(existingObj);
		    session.getTransaction().commit();
		  	session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
			   e.printStackTrace();
		}
		
		
	 
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
 
 public List<TaxDetail> listTaxDetails(int sellerId,String taxortds)
 {
	 List<TaxDetail> returnlist=null;
	 Seller seller=null;
		try
		{
			Session session=sessionFactory.openSession();
			   session.beginTransaction();
		
			 Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   criteria.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
			   .add(Restrictions.eq("taxDetail.taxortds",taxortds))
			   .addOrder(org.hibernate.criterion.Order.desc("taxDetail.uploadDate"))
			   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			   if(criteria.list()!=null&&criteria.list().size()!=0)
			   {
				   seller=(Seller)criteria.list().get(0);
				   returnlist=seller.getTaxDetails();
			
			
			   }
			   session.getTransaction().commit();
			   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting TaxDetail list :"+e.getLocalizedMessage());
			e.printStackTrace();
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