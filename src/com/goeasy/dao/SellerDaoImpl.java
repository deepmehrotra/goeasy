package com.goeasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.Seller;

/**
 * @author Dinesh Rajput
 *
 */
@Repository("sellerDao")
public class SellerDaoImpl implements SellerDao {

 @Autowired
 private SessionFactory sessionFactory;
 
 public void addSeller(Seller seller) {
	 
   //sessionFactory.getCurrentSession().saveOrUpdate(seller);
	 try
	 {
   Session session=sessionFactory.openSession();
   session.beginTransaction();
   session.saveOrUpdate(seller);
   session.getTransaction().commit();
   session.close();
	 }
	 catch (Exception e) {
		System.out.println(" Seller DAO IMPL :"+e.getLocalizedMessage());
	}
 }

 @SuppressWarnings("unchecked")
 public List<Seller> listSeller() {
	
  return (List<Seller>) sessionFactory.getCurrentSession().createCriteria(Seller.class).list();
	
 }

 public Seller getSeller(int sellerid) {
  return (Seller) sessionFactory.getCurrentSession().get(Seller.class, sellerid);
 }
 
 public Seller getSeller(String email) {
	  //return (Seller) sessionFactory.getCurrentSession().get(Seller.class, sellerid);
	 Seller seller=null;
	 try
	 {
   Session session=sessionFactory.openSession();
   session.beginTransaction();
   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("email", email));
   seller=(Seller)criteria.list().get(0);
   session.getTransaction().commit();
   session.close();
	 }
	 catch (Exception e) {
		System.out.println(" Seller  DAO IMPL :"+e.getLocalizedMessage());
		e.printStackTrace();
	}
	 return seller;
	 }

 public void deleteSeller(Seller seller) {
 // sessionFactory.getCurrentSession().createQuery("DELETE FROM Seller WHERE id = "+seller.getId()).executeUpdate();
	 
	 System.out.println(" In seller  delete sellerid "+seller.getId());
	 Seller seller1=null;
	 try
	 {
  Session session=sessionFactory.openSession();
  session.beginTransaction();
  Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", seller.getId()));
  
  seller1=(Seller)criteria.list().get(0);
 seller1.getPartners().clear();
 seller1.getOrders().clear();
  System.out.println(" List size of partner after deletion :"+seller1.getPartners().size());
  System.out.println(" List size of order after deletion :"+seller1.getOrders().size());
  session.delete(seller1);
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