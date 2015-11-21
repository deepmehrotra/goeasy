package com.goeasy.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.bean.DashboardBean;
import com.goeasy.bean.PendingPaymentsBean;
import com.goeasy.bean.TotalShippedOrder;
import com.goeasy.model.Customer;
import com.goeasy.model.Expenses;
import com.goeasy.model.Order;
import com.goeasy.model.Seller;
import com.goeasy.model.TaxDetail;


 

/**
 * @author Deep Mehrotra
 *
 */

@Repository("dashboardDao")
public class DashboardDaoImpl implements DashboardDao{


 @Autowired
 private SessionFactory sessionFactory;
 
 @SuppressWarnings("unchecked")
@Override
public DashboardBean getDashboardDetails(int sellerId)
{
		 DashboardBean dashboardBean=null;
		 Date endDate=new Date();
		 endDate.setDate(endDate.getDate()+1);
		 endDate.setMonth(endDate.getMonth()+1);
		 Date startDate=new Date();
		 startDate.setDate(1);
		 startDate.setMonth(startDate.getMonth()-1);
	 
	 
	 try
	   {
		   	Session session=sessionFactory.openSession();
		   	session.beginTransaction();
		   	System.out.println(" Calling list ofupcoming payment");
		   	ListOfUpcomingPayment(session, startDate, endDate, sellerId);
		   //	expenditureSixMonths(session,sellerId);
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 
	 return dashboardBean;
}

public List<Object[]> orderResultforTime(Session session, Date startDate,Date endDate,int sellerId)
{
	List<Object[]> results=null;
	 try
	   {
		  // session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.between("orderDate",startDate, endDate));
	 	    criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	    ProjectionList projList = Projections.projectionList();
	 	    projList.add(Projections.sum("quantity"));
	 	    projList.add(Projections.sum("netRate"));
	 	   projList.add(Projections.sum("orderReturnOrRTO.returnorrtoQty"));
	 	    projList.add(Projections.rowCount());
	 	    criteria.setProjection(projList);
	 	    results = criteria.list();
	 	    Iterator iterator1 = results.iterator();
	 	    if(results != null){
	 	    	while(iterator1.hasNext()){
	 	    		System.out.println("\n");
	 	    		Object[] recordsRow = (Object[])iterator1.next();
	 	    		System.out.println(" record length:"+recordsRow.length);
	 	    		for(int i = 0; i < recordsRow.length;i++){
	 	    			System.out.print("\t"+recordsRow[i]);
                      
	 	    		}
	 	    	}
	 	    }
	 	
	 	    session.getTransaction().commit();
	  // session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return results;
}

public List<Object[]> netProfitForTime(Session session, Date startDate,Date endDate,int sellerId)
{
	//NP=NSR+Valuation of closing stock -Openign stock value -expense
	List<Object[]> results=null;
	 try
	   {
		  // session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.between("orderDate",startDate, endDate));
	 	    criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	    ProjectionList projList = Projections.projectionList();
	 	    projList.add(Projections.sum("quantity"));
	 	    projList.add(Projections.sum("netRate"));
	 	    projList.add(Projections.rowCount());
	 	    criteria.setProjection(projList);
	 	    results = criteria.list();
	 	    Iterator iterator1 = results.iterator();
	 	    if(results != null){
	 	    	while(iterator1.hasNext()){
	 	    		System.out.println("\n");
	 	    		Object[] recordsRow = (Object[])iterator1.next();
	 	    		System.out.println(" record length:"+recordsRow.length);
	 	    		for(int i = 0; i < recordsRow.length;i++){
	 	    			System.out.print("\t"+recordsRow[i]);
                      
	 	    		}
	 	    	}
	 	    }
	 	
	 	 //   session.getTransaction().commit();
	  // session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return results;
}

public List<Object[]> paymentCountforTime(Session session, Date startDate,Date endDate,int sellerId)
{
	List<Object[]> results=null;
	 try
	   {
		 	session=sessionFactory.openSession();
		   	session.beginTransaction();
		   	Criteria criteria=session.createCriteria(Order.class);
		   	criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.between("orderPayment.dateofPayment",startDate, endDate));
	 	    criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	    ProjectionList projList = Projections.projectionList();
	 	/*  projList.add(Projections.sum("quantity"));
	 	 projList.add(Projections.sum("netRate"));
	 	 */projList.add(Projections.rowCount());
	 	projList.add(Projections.groupProperty("orderPayment.dateofPayment"));
	 		criteria.setProjection(projList);
	 		results = criteria.list();
	 		Iterator iterator1 = results.iterator();
	 		if(results != null){
	 			while(iterator1.hasNext()){
	 				System.out.println("\n");
	 				Object[] recordsRow = (Object[])iterator1.next();
	 				System.out.println(" record length:"+recordsRow.length);
	 				for(int i = 0; i < recordsRow.length;i++){
	 					System.out.print("\t"+recordsRow[i]);
                      
         }
         }
	     }
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return results;
}

/*
public Object[] quarterlyTaxAlert(Session session, Date startDate,Date endDate,int sellerId)
{/*
	List<Object[]> results=null;
	Object[] resultstoreturn=null;
	 try
	   {
		  // session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	   criteria.createAlias("orderTax", "orderTax", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.between("orderDate",startDate, endDate));
	 	  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	  projList.add(Projections.sum("quantity"));
	 	 projList.add(Projections.sum("netRate"));
	 	projList.add(Projections.sum("orderTax.tax"));
	 	projList.add(Projections.sum("orderTax.tdsToDeduct"));
	 	criteria.setProjection(projList);
	 	results = criteria.list();
	 	 Iterator iterator1 = results.iterator();
	     if(results != null){
         while(iterator1.hasNext()){
          System.out.println("\n");
          Object[] recordsRow = (Object[])iterator1.next();
          resultstoreturn=recordsRow;
          System.out.println(" record length:"+recordsRow.length);
          for(int i = 0; i < recordsRow.length;i++){
          System.out.print("\t"+recordsRow[i]);
                      
         }
         }
	     }
	 	
	    session.getTransaction().commit();
	    
	    
	    
	    Criteria criteriaforTax=session.createCriteria(Seller.class);
	 	   criteria.createAlias("taxDetails", "taxDetails", CriteriaSpecification.LEFT_JOIN)
	 	  .add(Restrictions.eq("id", sellerId))
	 	   .add(Restrictions.between("orderDate",startDate, endDate));
	 	  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	  projList.add(Projections.sum("quantity"));
	 	 projList.add(Projections.sum("netRate"));
	 	projList.add(Projections.sum("orderTax.tax"));
	 	projList.add(Projections.sum("orderTax.tdsToDeduct"));
	 	criteria.setProjection(projList);
	 	results = criteria.list();
	 	 Iterator iterator1 = results.iterator();
	     if(results != null){
      while(iterator1.hasNext()){
       System.out.println("\n");
       Object[] recordsRow = (Object[])iterator1.next();
       resultstoreturn=recordsRow;
       System.out.println(" record length:"+recordsRow.length);
       for(int i = 0; i < recordsRow.length;i++){
       System.out.print("\t"+recordsRow[i]);
                   
      }
      }
	     }
	 	
	  
	  // session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return resultstoreturn;
}
*/
public List<PendingPaymentsBean> ListOfUpcomingPayment(Session session, Date startDate,Date endDate,int sellerId)
{
	List<PendingPaymentsBean> results=null;
	System.out.println(" Inside upcoming payments -startDate- "+startDate+" endDate : "+endDate);
	 try
	   {
		   //session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN)
		// criteria.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.between("paymentDueDate",startDate, endDate));
	 	   //.add(Restrictions.lt("orderPayment.paymentDifference",0.0));
	 	  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	/*  projList.add(Projections.sum("quantity"));
	 	 projList.add(Projections.sum("netRate"));
	 	*/projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("orderPayment.paymentDifference"));
	 	projList.add(Projections.sum("netRate"));
	 	/*projList.add(Projections.sqlGroupProjection("month({alias}.paymentDueDate) as month, year({alias}.paymentDueDate) as year", 
	 		    "month({alias}.paymentDueDate), year({alias}.paymentDueDate)", 
	 		    new String[]{"month","year"}, 
	 		    new Type[] {Hibernate.DOUBLE}));*/
	 	/*projList.add(Projections.sqlGroupProjection("date(paymentDueDate) as paymentDueDate",
	 			"paymentDueDate", new String[] { "paymentDueDate" }, new Type[] { Hibernate.DATE }));*/
	 	projList.add(Projections.groupProperty("paymentDueDate"));
	 	criteria.setProjection(projList);
	 	// criteria.addOrder(org.hibernate.criterion.Order.desc("paymentDueDate"));
	 	results = criteria.list();
	 	 Iterator iterator1 = results.iterator();
	     if(results != null){
         while(iterator1.hasNext()){
          System.out.println(" row\n");
          Object[] recordsRow = (Object[])iterator1.next();
          System.out.println(" record length:"+recordsRow.length);
          for(int i = 0; i < recordsRow.length;i++){
          System.out.print("\t"+recordsRow[i]);
                      
         }
         }
	     }
	 	
	  //  session.getTransaction().commit();
	  //.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside upcomingpayment exception  "+e.getLocalizedMessage());
		   e.printStackTrace();
	}
	 return results;
}

public List<PendingPaymentsBean> ListOfOutstandingPayment(Session session,int sellerId)
{
	List<PendingPaymentsBean> results=null;
	System.out.println(" Inside ListOfOutstandingPayment payments -startDate- ");
	 try
	   {
		   //session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN)
		// criteria.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.lt("orderPayment.paymentDifference",0.0));
	 	  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	/*  projList.add(Projections.sum("quantity"));
	 	 projList.add(Projections.sum("netRate"));
	 	*/projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("orderPayment.paymentDifference"));
	 	projList.add(Projections.sum("netRate"));
	 	/*projList.add(Projections.sqlGroupProjection("month({alias}.paymentDueDate) as month, year({alias}.paymentDueDate) as year", 
	 		    "month({alias}.paymentDueDate), year({alias}.paymentDueDate)", 
	 		    new String[]{"month","year"}, 
	 		    new Type[] {Hibernate.DOUBLE}));*/
	 	/*projList.add(Projections.sqlGroupProjection("date(paymentDueDate) as paymentDueDate",
	 			"paymentDueDate", new String[] { "paymentDueDate" }, new Type[] { Hibernate.DATE }));*/
	 	projList.add(Projections.groupProperty("pcName"));
	 	criteria.setProjection(projList);
	 	// criteria.addOrder(org.hibernate.criterion.Order.desc("paymentDueDate"));
	 	results = criteria.list();
	 	 Iterator iterator1 = results.iterator();
	     if(results != null){
         while(iterator1.hasNext()){
          System.out.println(" row\n");
          Object[] recordsRow = (Object[])iterator1.next();
          System.out.println(" record length:"+recordsRow.length);
          for(int i = 0; i < recordsRow.length;i++){
          System.out.print("\t"+recordsRow[i]);
                      
         }
         }
	     }
	 	
	  //  session.getTransaction().commit();
	  //.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside upcomingpayment exception  "+e.getLocalizedMessage());
		   e.printStackTrace();
	}
	 return results;
}
public Object[] getTotalCustomer(Session session,int sellerId)
{
	Object[] recordsRow=null;
	 try
	   {
		   session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Customer.class)
				   .add(Restrictions.eq("sellerId", sellerId));
		   			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	   projList.add(Projections.rowCount());
	 	   criteria.setProjection(projList);
	 	   List<Object[]> results = criteria.list();
	 	   Iterator iterator1 = results.iterator();
	 	   if(results != null){
	 		   while(iterator1.hasNext()){
	 			   System.out.println("\n");
	 			   recordsRow = (Object[])iterator1.next();
         System.out.println(recordsRow[0].toString());
        }
	     }
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return recordsRow;
}


public Object[] getTotalSKUCount(Session session,int sellerId)
{
	Object[] recordsRow=null;
	 try
	   {
		   session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		   criteria.createAlias("products", "product", CriteriaSpecification.LEFT_JOIN);
	 	   criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("product.quantity"));
	 	//projList.add(Projections.sqlProjection("quantity*productPrice", columnAliases, types));
	 	criteria.setProjection(projList);
	 	List<Object[]> results = criteria.list();
	 	 Iterator iterator1 = results.iterator();
	     if(results != null){
        while(iterator1.hasNext()){
         System.out.println("\n");
          recordsRow = (Object[])iterator1.next();
         System.out.println(" record length:"+recordsRow.length);
        /* for(int i = 0; i < recordsRow.length;i++){
         System.out.print("\t"+recordsRow[i]);*/
                     
        //}
         System.out.println(recordsRow[0].toString());
        }
	     }
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return recordsRow;
}

public List<TaxDetail> getTaxAlert(Session session,Date taxDate,int sellerId)
{
	Seller seller=null;
	List<TaxDetail> taxList=null;
	 try
	   {
		   session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		   criteria.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
		   .add(Restrictions.ge("taxDetail.uploadDate", taxDate))
		   .add(Restrictions.eq("taxDetail.taxortds", "Tax"));
	 	   criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	  /* ProjectionList projList = Projections.projectionList();
	 	projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("product.quantity"));
	 	//projList.add(Projections.sqlProjection("quantity*productPrice", columnAliases, types));
	 	criteria.setProjection(projList);*/
	 	   if(criteria.list()!=null)
	 	seller = (Seller)criteria.list().get(0);
	 	 taxList=seller.getTaxDetails();
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return taxList;
} 

public List<TaxDetail> getTDSAlert(Session session,Date taxDate,int sellerId)
{
	Seller seller=null;
	List<TaxDetail> taxList=null;
	 try
	   {
		   session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		   criteria.createAlias("taxDetails", "taxDetail", CriteriaSpecification.LEFT_JOIN)
		   .add(Restrictions.ge("taxDetail.uploadDate", taxDate))
		   .add(Restrictions.eq("taxDetail.taxortds", "TDS"));
	 	   criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	  /* ProjectionList projList = Projections.projectionList();
	 	projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("product.quantity"));
	 	//projList.add(Projections.sqlProjection("quantity*productPrice", columnAliases, types));
	 	criteria.setProjection(projList);*/
	 	   if(criteria.list()!=null)
	 	seller = (Seller)criteria.list().get(0);
	 	 taxList=seller.getTaxDetails();
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return taxList;
} 

public List<Object[]> expenditureSixMonths(Session session,int sellerId)
{
	System.out.println(" Inside for six months ");
	//Seller seller=null;
	List<Object[]> results=null;
	Date befor6=new Date();
	befor6.setMonth(befor6.getMonth()-6);
	befor6.setDate(1);
	 try
	   {
		   session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Expenses.class)
				   .add(Restrictions.eq("sellerId", sellerId))
		  .add(Restrictions.ge("createdOn", befor6));
		   criteria.add(Restrictions.sqlRestriction("YEAR(createdOn)="
		           + befor6.getYear()));
		  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		  ProjectionList projList = Projections.projectionList();
		/*  projList.add(Projections.sqlGroupProjection(
		          "MONTHNAME(createdOn) as orderMonthName",
		         "MONTHNAME(createdOn)", new String[] { "orderMonthName" },
		            new Type[] { Hibernate.STRING }));
		  projList.add(Projections.sqlGroupProjection(
		          "MONTH(createdOn) as orderMonthNo", "MONTH(createdOn)",
		         new String[] { "orderMonthNo" },
		            new Type[] { Hibernate.INTEGER }));*/
		  projList.add(Projections.sum("amount"));
		   criteria.setProjection(projList);
		   criteria.addOrder(org.hibernate.criterion.Order.desc("createdOn"));
	 	  /* ProjectionList projList = Projections.projectionList();
	 	projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("product.quantity"));
	 	//projList.add(Projections.sqlProjection("quantity*productPrice", columnAliases, types));
	 	criteria.setProjection(projList);*/
		  results = criteria.list();
		 	 Iterator iterator1 = results.iterator();
		     if(results != null){
	         while(iterator1.hasNext()){
	          System.out.println(" row\n");
	          Object[] recordsRow = (Object[])iterator1.next();
	          System.out.println(" record length:"+recordsRow.length);
	          for(int i = 0; i < recordsRow.length;i++){
	          System.out.print("\t"+recordsRow[i]);
	                      
	         }
	         }
		     }
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
		   e.printStackTrace();
	}
	 return results;
} 

public List<Object[]> topSellingRegion(Session session, Date startDate,Date endDate,int sellerId)
{
	//NP=NSR+Valuation of closing stock -Openign stock value -expense
	List<Object[]> results=null;
	 try
	   {
		  // session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
	 	    criteria.createAlias("customer", "customer", CriteriaSpecification.LEFT_JOIN)
	 	    .add(Restrictions.eq("seller.id", sellerId))
	 	    .add(Restrictions.isNotNull("customer.customerCity"))
		 .add(Restrictions.ne("customer.customerCity",""))
	 	   .add(Restrictions.between("orderDate",startDate, endDate));
	 	    criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	    ProjectionList projList = Projections.projectionList();
	 	   projList.add(Projections.groupProperty("customer.customerCity"));
	 	    projList.add(Projections.rowCount());
	 	    criteria.setProjection(projList);
	 	    results = criteria.list();
	 	    Iterator iterator1 = results.iterator();
	 	    if(results != null){
	 	    	while(iterator1.hasNext()){
	 	    		System.out.println("\n");
	 	    		Object[] recordsRow = (Object[])iterator1.next();
	 	    		System.out.println(" record length:"+recordsRow.length);
	 	    		for(int i = 0; i < recordsRow.length;i++){
	 	    			System.out.print("\t"+recordsRow[i]);
                      
	 	    		}
	 	    	}
	 	    }
	 	
	 	 //   session.getTransaction().commit();
	  // session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return results;
}

public List<Object[]> expenditureForTime(Session session,Date startDate ,Date endDate ,int sellerId)
{
	Seller seller=null;
	List<Object[]> results=null;
	 try
	   {
		   session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Expenses.class)
				   .add(Restrictions.eq("sellerId", sellerId))
		  .add(Restrictions.between("createdOn", startDate,endDate));
		  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		  ProjectionList projList = Projections.projectionList();
		  projList.add(Projections.sum("amount"));
		  projList.add(Projections.groupProperty("expenseCatName"));
		  criteria.setProjection(projList);
	 	  /* ProjectionList projList = Projections.projectionList();
	 	projList.add(Projections.rowCount());
	 	projList.add(Projections.sum("product.quantity"));
	 	//projList.add(Projections.sqlProjection("quantity*productPrice", columnAliases, types));
	 	criteria.setProjection(projList);*/
		  results = criteria.list();
		 	 Iterator iterator1 = results.iterator();
		     if(results != null){
	         while(iterator1.hasNext()){
	          System.out.println(" row\n");
	          Object[] recordsRow = (Object[])iterator1.next();
	          System.out.println(" record length:"+recordsRow.length);
	          for(int i = 0; i < recordsRow.length;i++){
	          System.out.print("\t"+recordsRow[i]);
	                      
	         }
	         }
		     }
	 	
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
	}
	 return results;
} 

 public List<TotalShippedOrder> getAllPartnerTSOdetails(Date startDate ,Date endDate, int sellerId)
 {
	 //http://howtodoinjava.com/2014/10/28/hibernate-criteria-queries-tutorial-and-examples/#unique_result
	 System.out.println(" getAllPartnerTSOdetails   :  >>startDate"+startDate+">>endDate :"+endDate);
	 TotalShippedOrder[] ttso=null;
	 
	 int  totalQuantity=0;
	 int  returnQuantity=0;
	 double  totalNR=0;
	 double returnAmount=0;
	 double netSaleAmoount=0;
	 int cityTotalQuantity=0;
	 int totalNoofDO=0;
	 int totalNoofRO=0;
	 int totalNoofSO=0;
	 int totalNoofAO=0;
	 int totalNoofRTOCross=0;
	 int totalNoofreturnCross=0;
	 TotalShippedOrder ttsoTemp=null;
	 Map<String,Double> cityMap=new HashMap<>();
	 Map<String,Double> cityPercentMap=new HashMap<>();
	 try
	 {
		 Session session=sessionFactory.openSession();
		 session.beginTransaction();
		 Criteria criteria=session.createCriteria(Order.class);
		 criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		 criteria.createAlias("customer", "customer", CriteriaSpecification.LEFT_JOIN);
		 criteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN);
		 criteria.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate));
		 criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList projList = Projections.projectionList();
		 projList.add(Projections.groupProperty("pcName"));
		 projList.add(Projections.sum("quantity"));
		 projList.add(Projections.sum("netRate"));
		 projList.add(Projections.sum("orderReturnOrRTO.returnorrtoQty"));
		 projList.add(Projections.sum("orderPayment.negativeAmount"));
		 projList.add(Projections.sum("orderSP"));
		 projList.add(Projections.sum("orderPayment.positiveAmount"));
		 criteria.setProjection(projList);
		 criteria.addOrder(org.hibernate.criterion.Order.asc("pcName"));
		 List<Object[]> results = criteria.list();
		 System.out.println("results.size()  "+results.size());
		 ttso=new TotalShippedOrder[results.size()];
		 System.out.println(" Array size :"+ttso.length);
		 System.out.println("ttso  Object "+ttso[0]);
		 Iterator iterator1 = results.iterator();
		 int iteratorCount=0;
		 if(results != null){
			 while(iterator1.hasNext()){
				 System.out.println("\n");
				 ttsoTemp=new TotalShippedOrder();
				 Object[] recordsRow = (Object[])iterator1.next();
				 System.out.println("recordsRow.length  "+recordsRow.length);
				 System.out.println(" Quantity  NR ReturnQty  Return Amount SaleAmount  PositiveAmount");
				 for(int i = 0; i < recordsRow.length;i++){
					 if(i==0)
					 {System.out.println(" Setting pc name :"+recordsRow[i].toString());
					 //ttso[iteratorCount].setPcName(recordsRow[i].toString());
					 ttsoTemp.setPcName(recordsRow[i].toString());
					 System.out.println(" After setting pc name = "+ttsoTemp.getPcName());
					 }
					 if(i==1)
					 {
						 ttsoTemp.setSaleQuantity(Integer.parseInt(recordsRow[i].toString()));
						 totalQuantity=totalQuantity+Integer.parseInt(recordsRow[i].toString());
					 }
					 else if(i==2)
					 {
						 ttsoTemp.setNr(Double.parseDouble(recordsRow[i].toString()));
						 totalNR=totalNR+Double.parseDouble(recordsRow[i].toString());
					 }
					 else if(i==3)
					 {
						 ttsoTemp.setReturnQuantity(Integer.parseInt(recordsRow[i].toString()));
						 returnQuantity=returnQuantity+Integer.parseInt(recordsRow[i].toString());
					 }
					 else if(i==4)
					 {
						 ttsoTemp.setReturnAmount(Double.parseDouble(recordsRow[i].toString()));
						 returnAmount=returnAmount+Double.parseDouble(recordsRow[i].toString());
					 }
					 else if(i==5)
					 {
						 ttsoTemp.setNetSaleAmount(Double.parseDouble(recordsRow[i].toString()));
						 netSaleAmoount=netSaleAmoount+Double.parseDouble(recordsRow[i].toString());
					 }
					
					 System.out.print("\t"+recordsRow[i]);

				 }
				 ttso[iteratorCount]=ttsoTemp;
				 iteratorCount++;
			 }
		 }


		 /*
		  * Code for caluclating no of deliverred orders
		  */
		 Criteria criteriaforDeliveredOrder=session.createCriteria(Order.class);
		 criteriaforDeliveredOrder.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate))
		 .add(Restrictions.le("deliveryDate", new Date()));
		 criteriaforDeliveredOrder.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList DOprojList = Projections.projectionList();
		 DOprojList.add(Projections.groupProperty("pcName"));
		 DOprojList.add(Projections.rowCount());
		 criteriaforDeliveredOrder.setProjection(DOprojList);
		// Object deliveredOrderCount = criteriaforDeliveredOrder.list().get(0);
		 List<Object[]> deliveredOrderCount = criteriaforDeliveredOrder.list();
		 Iterator DOiterator = deliveredOrderCount.iterator();
		 if(deliveredOrderCount != null){
			 iteratorCount=0;
			 while(DOiterator.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])DOiterator.next();
				 System.out.println(" Delivered order partner :"+recordsRow[0].toString()+" coumt :"+recordsRow[1].toString());
				 totalNoofDO=totalNoofDO+Integer.parseInt(recordsRow[1].toString());
				 ttso[iteratorCount++].setNoOfDeliveredOrder(Integer.parseInt(recordsRow[1].toString()));
			 }
		 }

		 /*
		  * Code for caluclating no of return orders
		  */
		 Criteria criteriaforReturnOrder=session.createCriteria(Order.class);
		 criteriaforReturnOrder.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		 criteriaforReturnOrder.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate))
		 .add(Restrictions.isNotNull("orderReturnOrRTO.returnOrRTOId"));
		 criteriaforReturnOrder.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList ROprojList = Projections.projectionList();
		 ROprojList.add(Projections.groupProperty("pcName"));
		 ROprojList.add(Projections.rowCount());
		 criteriaforReturnOrder.setProjection(ROprojList);
		 
		 List<Object[]> returnOrderCount = criteriaforReturnOrder.list();
		 Iterator ROiterator = returnOrderCount.iterator();
		 if(returnOrderCount != null){
			 iteratorCount=0;
			 while(ROiterator.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])ROiterator.next();
				 System.out.println(" Return order partner :"+recordsRow[0].toString()+" coumt :"+recordsRow[1].toString());
					
				 totalNoofRO=totalNoofRO+Integer.parseInt(recordsRow[1].toString());
				 ttso[iteratorCount++].setNoOfReturnOrder(Integer.parseInt(recordsRow[1].toString()));
					
			 }
		 }

		 /*
		  * Code for caluclating no of actionalble orders
		  */
		 Criteria criteriaforActionalbleOrder=session.createCriteria(Order.class);
		 criteriaforActionalbleOrder.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate))
		 .add(Restrictions.eq("finalStatus", "Actionable"));
		 criteriaforActionalbleOrder.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList AOprojList = Projections.projectionList();
		 AOprojList.add(Projections.groupProperty("pcName"));
		 AOprojList.add(Projections.rowCount());
		 criteriaforActionalbleOrder.setProjection(AOprojList);
		
		 List<Object[]> actionableOrderCount = criteriaforActionalbleOrder.list();
		 Iterator AOiterator = actionableOrderCount.iterator();
		 if(actionableOrderCount != null){
			 iteratorCount=0;
			 while(AOiterator.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])AOiterator.next();
				 System.out.println(" Actionable order partner :"+recordsRow[0].toString()+" coumt :"+recordsRow[1].toString());
					
				 totalNoofAO=totalNoofAO+Integer.parseInt(recordsRow[1].toString());
				 ttso[iteratorCount++].setNoOfActionableOrders(Integer.parseInt(recordsRow[1].toString()));
					
			 }
		 }

		 /*
		  * Code for caluclating no of settled orders
		  */
		 Criteria criteriaforSettledOrder=session.createCriteria(Order.class);
		 criteriaforSettledOrder.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate))
		 .add(Restrictions.eq("finalStatus", "Settled"));
		 criteriaforSettledOrder.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList SOprojList = Projections.projectionList();
		 SOprojList.add(Projections.groupProperty("pcName"));
		 SOprojList.add(Projections.rowCount());
		 criteriaforSettledOrder.setProjection(SOprojList);
		
		 List<Object[]> settledOrderCount = criteriaforSettledOrder.list();
		 Iterator SOiterator = settledOrderCount.iterator();
		 if(settledOrderCount != null){
			 iteratorCount=0;
			 while(SOiterator.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])SOiterator.next();
				 System.out.println(" Settled order partner :"+recordsRow[0].toString()+" coumt :"+recordsRow[1].toString());
				 totalNoofSO=totalNoofSO+Integer.parseInt(recordsRow[1].toString());
				 ttso[iteratorCount++].setNoOfSettledOrders(Integer.parseInt(recordsRow[1].toString()));
					
			 }
		 }

		 /*
		  * Code for caluclating no of RTOlimit crossed orders
		  */
		 Criteria criteriaRTOlimitCross=session.createCriteria(Order.class);
		 criteriaRTOlimitCross.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		 criteriaRTOlimitCross.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.geProperty("orderReturnOrRTO.returnDate", "rTOLimitCrossed"));
		 criteriaRTOlimitCross.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList RTOprojList = Projections.projectionList();
		 RTOprojList.add(Projections.groupProperty("pcName"));
		 RTOprojList.add(Projections.rowCount());
		 criteriaRTOlimitCross.setProjection(RTOprojList);
		
		 List<Object[]> RTOlimitCrossOrderCount = criteriaRTOlimitCross.list();
		 Iterator rtoOiterator = RTOlimitCrossOrderCount.iterator();
		 if(RTOlimitCrossOrderCount != null){
			 iteratorCount=0;
			 while(rtoOiterator.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])rtoOiterator.next();
				 System.out.println(" Rto limit partner :"+recordsRow[0].toString()+" coumt :"+recordsRow[1].toString());
					
				 totalNoofRTOCross=totalNoofRTOCross+Integer.parseInt(recordsRow[1].toString());
				 ttso[iteratorCount++].setNoOfRTOLimitCrossed(Integer.parseInt(recordsRow[1].toString()));
					
			 }
		 }
		

		 /*
		  * Code for caluclating no of retunrLimit crossed orders
		  */
		 Criteria criteriaReturnlimitCross=session.createCriteria(Order.class);
		 criteriaReturnlimitCross.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		 criteriaReturnlimitCross.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.geProperty("orderReturnOrRTO.returnDate", "returnLimitCrossed"));
		 criteriaReturnlimitCross.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList returnprojList = Projections.projectionList();
		 returnprojList.add(Projections.groupProperty("pcName"));
		 returnprojList.add(Projections.rowCount());
		 criteriaReturnlimitCross.setProjection(returnprojList);
		 List<Object[]> returnlimitCrossOrderCount = criteriaReturnlimitCross.list();
		 Iterator returniterator = returnlimitCrossOrderCount.iterator();
		 if(returnlimitCrossOrderCount != null){
			 while(returniterator.hasNext()){
				 iteratorCount=0;
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])returniterator.next();
				 System.out.println(" Return limit partner :"+recordsRow[0].toString()+" coumt :"+recordsRow[1].toString());
					
				 totalNoofreturnCross=totalNoofreturnCross+Integer.parseInt(recordsRow[1].toString());
				 ttso[iteratorCount++].setNoOfReturnLimitCrossed(Integer.parseInt(recordsRow[1].toString()));
					
			 }
		 }
		
		 /*
		  * 
		  */
		  /*
		   * Code for caluclating cities wise distribution of orders
		   */
		 Criteria criteriaforCitiesOrder=session.createCriteria(Order.class);
		 criteriaforCitiesOrder.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		 criteriaforCitiesOrder.createAlias("customer", "customer", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate))
		 .add(Restrictions.isNotNull("customer.customerCity"))
		 .add(Restrictions.ne("customer.customerCity",""));
		 criteriaforCitiesOrder.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList ccprojList = Projections.projectionList();
		 ccprojList.add(Projections.groupProperty("customer.customerCity"));
		 ccprojList.add(Projections.rowCount());
		 criteriaforCitiesOrder.setProjection(ccprojList);
		 List<Object[]> cityresults = criteriaforCitiesOrder.list();
		 Iterator cityIterator = cityresults.iterator();
		 if(cityresults != null){
			 while(cityIterator.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])cityIterator.next();
				 
				 System.out.println(" Cities row length "+recordsRow.length);
				 if(recordsRow.length>0)
				 {
					 if(!cityMap.containsKey(recordsRow[0].toString()))
					 {
						 cityMap.put(recordsRow[0].toString(), Double.parseDouble(recordsRow[1].toString()));
					 }
				 System.out.println("city "+ recordsRow[0]+" count : "+recordsRow[1]);
				 cityTotalQuantity=cityTotalQuantity+Integer.parseInt(recordsRow[1].toString());
				 }

				 
			 }
		 }
		 System.out.println(" ***cityTotalQuantity "+cityTotalQuantity);
		 ttso[0].setCityQuantity(cityMap);
		 for (Map.Entry<String, Double> entry : cityMap.entrySet()) {
			 System.out.println("for city : "+entry.getKey()+" count is :"+entry.getValue()+" percent is :"+ (entry.getValue())/cityTotalQuantity*100);
			 cityPercentMap.put(entry.getKey(), (entry.getValue())/cityTotalQuantity*100);
		}

		 ttso[0].setCityQuantity(cityMap);
		 ttso[0].setCityPercentage(cityPercentMap);
		
		 
		 for(int i=0;i<ttso.length;i++)
		 {
			 ttso[i].setSaleQuantityPercent( ttso[i].getSaleQuantity()*100/totalQuantity);
			 if((int)returnQuantity!=0)
			 ttso[i].setReturnQuantityPercent( ttso[i].getReturnQuantity()*100/returnQuantity);
			 ttso[i].setNrPercent(ttso[i].getNr()*100/totalNR);
			 if((int)returnAmount!=0)
			 ttso[i].setReturnAmountPercent(ttso[i].getReturnAmount()*100/returnAmount);
			 if((int)netSaleAmoount!=0)
			 ttso[i].setNetSaleAmountPercent(ttso[i].getNetSaleAmount()*100/netSaleAmoount);
			 if((int)totalNoofDO!=0)
			 ttso[i].setDeliveredOrderPercent(ttso[i].getNoOfDeliveredOrder()*100/totalNoofDO);
			 if((int)totalNoofAO!=0)
			 ttso[i].setActionableOrdersPercent(ttso[i].getNoOfActionableOrders()*100/totalNoofAO);
			 if((int)totalNoofRO!=0)
			 ttso[i].setReturnOrderPercent(ttso[i].getNoOfReturnOrder()*100/totalNoofRO);
			 ttso[i].setRTOOrderPercent(ttso[i].getNoOfRTOOrder());

			 if((int)totalNoofRTOCross!=0)
			 ttso[i].setRTOLimitCrossedPercent(ttso[i].getNoOfRTOLimitCrossed()*100/totalNoofRTOCross);
			 if((int)totalNoofreturnCross!=0)
			 ttso[i].setReturnLimitCrossedPercent(ttso[i].getNoOfReturnLimitCrossed()*100/totalNoofreturnCross);
			 if((int)totalNoofSO!=0)
			 ttso[i].setSettledOrdersPercent(ttso[i].getNoOfSettledOrders()*100/totalNoofSO);
			 
			 
		 }
		 
	    session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception  "+e.getLocalizedMessage());
		   e.printStackTrace();
	}
	 return Arrays.asList(ttso);
 }
 
 

}