package com.goeasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.Partner;
import com.goeasy.model.Seller;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("partnerDao")
public class PartnerDaoImpl implements PartnerDao {

 @Autowired
 private SessionFactory sessionFactory;
 
 public void addPartner(Partner partner,int sellerId) {
	// sellerId=4;
   //sessionFactory.getCurrentSession().saveOrUpdate(partner);
	 System.out.println(" Inside PartnerDaoIMpl partner id :"+partner.getPcId());
	 try
	 {
   Session session=sessionFactory.openSession();
   session.beginTransaction();
   if(partner.getPcId()!=0)
   {
	   session.saveOrUpdate(partner);
   }
   else
   {
   Seller seller=(Seller)session.get(Seller.class, sellerId);
   seller.getPartners().add(partner);
   session.saveOrUpdate(seller);
   }
   session.getTransaction().commit();
   session.close();
	 }
	 catch (Exception e) {
		System.out.println(" pcId DAO IMPL :"+e.getLocalizedMessage());
	}
 }

 
 public List<Partner> listPartner(int sellerId) {
	
	// sellerId=4;
		List<Partner> returnlist=null;
		System.out.println(" Inside list partner");
		try
		{
		Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Seller seller=(Seller)session.get(Seller.class, sellerId);
		   System.out.println(" getting seller "+seller);
		   if(seller.getPartners()!=null&&seller.getPartners().size()!=0)
		   returnlist=seller.getPartners();
		  /* System.out.println(" Getting list of partners size"+seller.getPartners().size());
		   System.out.println(" Getting list of partners size"+returnlist);*/
		   session.getTransaction().commit();
		   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting partner list :"+e.getLocalizedMessage());
		}
		return returnlist;	
 }

 public Partner getPartner(int partnerid) {
  return (Partner) sessionFactory.getCurrentSession().get(Partner.class, partnerid);
 }
 
 public Partner getPartner(String partnername,int sellerId) {
	// sellerId=4;
	 Partner returnpartner=null;
	 Seller seller=null;
	  
	 System.out.println(" ***Insid get partner ***"+partnername);
	
		 try
		 {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   criteria.createAlias("partners", "partner", CriteriaSpecification.LEFT_JOIN)
	   .add(Restrictions.eq("partner.pcName", partnername))
	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	   
	   if(criteria.list()!=null&&criteria.list().size()!=0)
	   {
	   seller=(Seller)criteria.list().get(0);
	   returnpartner=seller.getPartners().get(0);
	   }
	   session.getTransaction().commit();
	   session.close();
		 }
		 catch (Exception e) {
			System.out.println(" Partner  DAO IMPL :"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		 
		 return returnpartner;
	 
	 }

 public void deletePartner(Partner partner,int sellerId) {
	 System.out.println(" In partner delete pid "+partner.getPcId()+"   pcname "+partner.getPcName());
	// sellerId=4;
	 try
	 {
  Session session=sessionFactory.openSession();
  session.beginTransaction();
  Query deleteQuery = session.createSQLQuery(
		    "delete from Seller_Partner where partners_pcId=? and Seller_id=?");
	
		deleteQuery.setInteger(0, partner.getPcId());
		deleteQuery.setInteger(1,sellerId);
		int updated = deleteQuery.executeUpdate();
		int sellerdelete=session.createQuery("DELETE FROM Partner WHERE pcId = "+partner.getPcId()).executeUpdate();
  /*Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
  criteria.createAlias("partners", "partner", CriteriaSpecification.LEFT_JOIN)
  .add(Restrictions.eq("partner.pcId", partner.getPcId()))
  .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
  seller=(Seller)criteria.list().get(0);
  Partner partneris=seller.getPartners().get(0);
  seller.getPartners().remove(partneris);
  session.delete(partneris);
  System.out.println(" List size after deletion :"+seller.getPartners().size());
  session.saveOrUpdate(seller);*/
		System.out.println(" update : "+updated+  " sellerdelete "+sellerdelete);
  session.getTransaction().commit();
  session.close();

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting partner"+e.getLocalizedMessage());
		 e.printStackTrace();
	 }

 }
 
}