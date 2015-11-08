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

import com.goeasy.model.ManualCharges;
import com.goeasy.model.Seller;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("manualChargesDao")
public class ManualChargesDaoImpl implements ManualChargesDao {

 @Autowired
 private SessionFactory sessionFactory;
 

 @Override
 public void addManualCharges(ManualCharges manualCharges , int sellerId)
 {
	
	// sellerId=4;
		Seller seller=null;
		  try
		   {
			   Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   if(manualCharges.getMcId()==0)
			   {
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   seller=(Seller)criteria.list().get(0);
			   manualCharges.setUploadDate(new Date());
			   System.out.println(" Inside manual charges  add");
			  seller.getManualCharges().add(manualCharges);
			  session.saveOrUpdate(seller);
			   }
			   else
			   {
				   manualCharges.setUploadDate(new Date());
				   session.saveOrUpdate(manualCharges);
			   }
		    session.getTransaction().commit();
		   session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
		}
		
	 
 }
 
 
 public List<ManualCharges> listManualCharges(int sellerId)
 {
	 //sellerId=4;
		List<ManualCharges> returnlist=null;
		try
		{
			Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   Seller seller=(Seller)session.get(Seller.class, sellerId);
			   if(seller.getManualCharges()!=null&&seller.getManualCharges().size()!=0)
				   returnlist=seller.getManualCharges();
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
 public Double getMCforPaymentID(String paymentId ,int sellerId)
 {
	 //sellerId=4;
		Double returnValue=0.0;
		Seller seller=null;
		ManualCharges mc=null;
		try
		{
			Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
			   criteria.createAlias("manualCharges", "manualCharges", CriteriaSpecification.LEFT_JOIN)
			   .add(Restrictions.eq("manualCharges.chargesDesc", paymentId))
			   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			   
			   if(criteria.list()!=null&&criteria.list().size()!=0)
			   {
			   seller=(Seller)criteria.list().get(0);
			   mc=seller.getManualCharges().get(0);
			   }
			   if(mc!=null)
			   {
				   returnValue=mc.getPaidAmount();
			   }
			   session.getTransaction().commit();
			   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting Manual Charges list :"+e.getLocalizedMessage());
		}
		return returnValue;
 }
 
 public ManualCharges getManualCharges(int mcId)
 {
	 return (ManualCharges) sessionFactory.getCurrentSession().get(ManualCharges.class, mcId);
 }
 
 
 public void deleteManualCharges(ManualCharges manualCharges,int sellerId)
 {
	 System.out.println(" In Category delete cid "+manualCharges.getMcId());
	// sellerId=4;
	 try
	 {
		
		 
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_ManualCharges where seller_Id=? and ManualCharges_mcId=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1, manualCharges.getMcId());
				
				int updated = deleteQuery.executeUpdate();
				int catdelete=session.createQuery("DELETE FROM ManualCharges WHERE mcId = "+manualCharges.getMcId()).executeUpdate();
		
				System.out.println("  Deleteing manual charges updated:"+updated+" catdelete :"+catdelete);
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