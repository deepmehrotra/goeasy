package com.goeasy.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.bean.DebitNoteBean;
import com.goeasy.bean.PoPaymentBean;
import com.goeasy.model.Customer;
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.OrderRTOorReturn;
import com.goeasy.model.OrderTimeline;
import com.goeasy.model.Partner;
import com.goeasy.model.Product;
import com.goeasy.model.Seller;
import com.goeasy.model.TaxDetail;
import com.goeasy.service.ProductService;
import com.goeasy.service.TaxDetailService;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

 @Autowired
 private SessionFactory sessionFactory;
 @Autowired
 private ProductService productService;
 @Autowired
 private TaxDetailService taxDetailService;
 
 private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
 
 
 @SuppressWarnings("deprecation")
@Override
 public void addOrder(Order order,int sellerId) {
 	System.out.println(" Order id "+order.getOrderId());
 	System.out.println(" Order Partner "+order.getPcName());
 	System.out.println("Order delivery date "+order.getDeliveryDate());
 	//sellerId=4;
 	Seller seller=null;
 	Date reconciledate=null;
 	Customer customer=null;
 	Date tempDate=null;
 	Session session=null;
 	TaxDetail taxDetails=null;
 	
 	Product product = productService.getProduct(order.getProductSkuCode(), sellerId);
 	if(product!=null)
 	{
 	   try
 	   {
 	   session=sessionFactory.openSession();
 	   session.beginTransaction();
 	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
 	   criteria.createAlias("partners", "partner", CriteriaSpecification.LEFT_JOIN)
 	   .add(Restrictions.eq("partner.pcName", order.getPcName()))
 	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
 	   seller=(Seller)criteria.list().get(0);
 	   seller.getPartners().get(0);
 	   float taxpercent=taxDetailService.getTaxCategory(order.getOrderTax().getTaxCategtory(), sellerId).getTaxPercent();
 	   if(seller.getPartners()!=null&&seller.getPartners().size()!=0)
 	   {
 	   reconciledate=getreconciledate(order ,seller.getPartners().get(0), order.getOrderDate());
 	   if(reconciledate!=null)
 	   order.setPaymentDueDate(reconciledate);
 	   System.out.println(" after settinf rec date delivery date :"+order.getDeliveryDate());
 	   }
 		 /* populating derived values of order*/
 	   order.setStatus("Shipped");
 	   order.setLastActivityOnOrder(new Date());
 	   System.out.println(" Shipping charges :"+order.getShippingCharges()+" >> Gross net rate "+order.getGrossNetRate()+" delivery date :"+order.getDeliveryDate());
 	   order.setNetRate(order.getGrossNetRate()+order.getShippingCharges());
 	  
 	   if((int)order.getPoPrice()!=0&&order.getPcName().equals("Myntra"))
 	   {
 		   double taxvalue=order.getPoPrice()-(order.getPoPrice()*(100/(100+taxpercent)));
 		 order.setDiscount((Math.abs(order.getPoPrice()-order.getNetRate())));
 		 order.getOrderTax().setTax(taxvalue);
 	   }
 	   else{
 	   order.setDiscount((Math.abs(order.getOrderMRP()-order.getOrderSP())));
 	   System.out.println(" Tax cal SP:"+order.getOrderSP()+" >>TAxReate="+taxpercent+"  Tax>>"+
 	   (order.getOrderSP()-(order.getOrderSP()*(100/(100+seller.getPartners().get(0).getTaxrate())))));
 	  order.getOrderTax().setTax(order.getOrderSP()-(order.getOrderSP()*(100/(100+taxpercent))));
 	 taxDetails=new TaxDetail();
 	  taxDetails.setBalanceRemaining( order.getOrderTax().getTax());
 	  taxDetails.setParticular(order.getOrderTax().getTaxCategtory());
 	  taxDetailService.addMonthlyTaxDetail(session, taxDetails, sellerId);
 	  
 	   }
 	   order.setPartnerCommission(order.getOrderSP()-order.getGrossNetRate());
 	   order.setTotalAmountRecieved(order.getNetRate());
 	   order.setFinalStatus("In Process");
 	   //Set Order Timeline
 	   OrderTimeline timeline=new OrderTimeline();
 	   
 	   
 	   //populating tax related values of order
 	System.out.println(" Tax before pr:"+order.getOrderTax().getTax());
 	   order.setPr(order.getNetRate()-order.getOrderTax().getTax());
 	   if(seller.getPartners().get(0).isTdsApplicable())
 	   {
 		   System.out.println(" PC "+order.getPartnerCommission());
 		   order.getOrderTax().setTdsToDeduct(order.getPartnerCommission()*(.1));
 		  taxDetails=new TaxDetail();
 		  taxDetails.setBalanceRemaining(order.getPartnerCommission()*(.1));
 		 taxDetails.setParticular("TDS");
 		taxDetailService.addMonthlyTDSDetail(session, taxDetails, sellerId);
 	   }
 	   //Reducing Product Inventory For Order
 	  productService.updateInventory(order.getProductSkuCode(), 0, 0, order.getQuantity(),false, sellerId);
 	 /* product=productService.getProduct(order.getProductSkuCode(), sellerId);
 	  product.setQuantity(product.getQuantity()- order.getQuantity());
 	  session.saveOrUpdate(product);*/
 	   
 	   /* checking if customer is available*/
 	   System.out.println(" Inside add order before checking customer");
 	   if(order.getCustomer()!=null&&order.getCustomer().getCustomerEmail()!=null&&seller.getPartners().get(0).isTdsApplicable())
 	   {
 		   System.out.println(" Customer Email id in add order :"+order.getCustomer().getCustomerEmail());
 	   order.getCustomer().setSellerId(sellerId);
 	   System.out.println(" After setting seller id in customer");
 	   CustomerDao customerdao=new CustomerDaoImpl();
 	   customer=customerdao.getCustomer(order.getCustomer().getCustomerEmail(), sellerId,session);
 	  if(customer!=null)
	   {
		   order.setCustomer(customer);
		   customer.getOrders().add(order);
	   }
 	   
 	   }
 	 
 	   //Adding order to the Partner
 	   Partner partner=seller.getPartners().get(0);
 	   if(partner.getOrders()!=null&&order.getOrderId()==0)
 	   {partner.getOrders().add(order);
 	   }
 	   
 	   //Setting return and rto limits
 	  tempDate=(Date)order.getDeliveryDate().clone();
 	   tempDate.setDate(tempDate.getDate()+partner.getMaxReturnAcceptance());
 	   order.setReturnLimitCrossed(tempDate);
 	  tempDate=(Date)order.getDeliveryDate().clone();
 	  tempDate.setDate(tempDate.getDate()+partner.getMaxRTOAcceptance());
 	   order.setrTOLimitCrossed(tempDate);
 	  if(order.getOrderId()!=0)
 	   {
 		System.out.println(" Saving edited order");
 		//Code for order timeline
 		timeline.setEventDate(new Date());
 		timeline.setEvent(" Order Edited");
 		order.getOrderTimeline().add(timeline);
 		   session.merge(order);
 	   }
 	   else
 	   {
 		   System.out.println(" ****************Saving new  order delivery date :"+order.getDeliveryDate());
 		   
 		   //Code for order timeline
 		   timeline.setEvent("Order Created");
 		   timeline.setEventDate(new Date());
 		  order.getOrderTimeline().add(timeline);
 		  order.setSeller(seller);
 	   seller.getOrders().add(order);
 	   session.saveOrUpdate(partner);
 	   session.saveOrUpdate(seller);
 	   }
 	 session.getTransaction().commit();
 	   /*session.getTransaction().commit();
 	   session.close();*/
 	   }
 	   catch (Exception e) {
 		//   if(session.getTransaction()!=null&&session.getTransaction().isActive())
 		  // session.getTransaction().rollback();
 		   System.out.println("Inside exception in add order "+e.getLocalizedMessage()+" message: "+e.getMessage());
 		   e.printStackTrace();
 		
 	}
 	
 	  
 		  finally
 			{
 				
 				   session.close();
 			}
 	   
 	}
 	
 }

/*@Override
public void addOrder(Order order,int sellerId) {
	System.out.println(" Order id "+order.getOrderId());
	System.out.println(" Order Partner "+order.getPcName());
	System.out.println("Order Status "+order.getStatus());
	sellerId=4;
	Seller seller=null;
	Date reconciledate=null;
	Customer customer=null;
	
	   try
	   {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   criteria.createAlias("partners", "partner", CriteriaSpecification.LEFT_JOIN)
	   .add(Restrictions.eq("partner.pcName", order.getPcName()))
	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	   seller=(Seller)criteria.list().get(0);
	   seller.getPartners().get(0);
	   if(seller.getPartners()!=null&&seller.getPartners().size()!=0)
	   {
	   reconciledate=getreconciledate(seller.getPartners().get(0), order.getOrderDate());
	   if(reconciledate!=null)
	   order.setPaymentDueDate(reconciledate);
	   
	   }
		  populating derived values of order
	   order.setLastActivityOnOrder(new Date());
	   order.setDiscount((Math.abs(order.getOrderMRP()-order.getOrderSP()))*order.getQuantity());
	   order.setNetRate(order.getGrossNetRate()+order.getShippingCharges());
	   order.setPartnerCommission(order.getOrderSP()-order.getGrossNetRate());
	   
	   
	   //populating tax related values of order
	   order.getOrderTax().setTax(order.getNetRate()-(order.getNetRate()*(100/(100+seller.getPartners().get(0).getTaxrate()))));
	   order.setPr(order.getNetRate()-order.getOrderTax().getTax());
	   if(seller.getPartners().get(0).isTdsApplicable())
	   {
		   order.getOrderTax().setTdsToDeduct(order.getPartnerCommission()*(.1));
	   }
	    checking if customer is available
	   System.out.println(" Inside add order before checking customer");
	   if(order.getCustomer()!=null)
	   {
		   System.out.println(" Customer Email id in add order :"+order.getCustomer().getCustomerEmail());
	   order.getCustomer().setSellerId(sellerId);
	   System.out.println(" After setting seller id in customer");
	   CustomerDao customerdao=new CustomerDaoImpl();
	   customer=customerdao.getCustomer(order.getCustomer().getCustomerEmail(), sellerId);
	   
	   }
	   if(customer!=null)
	   {
		   order.setCustomer(customer);
		   customer.getOrders().add(order);
	   }
	   //Adding order to the Partner
	   Partner partner=seller.getPartners().get(0);
	   if(partner.getOrders()!=null&&order.getOrderId()==0)
	   {
		   System.out.println(" Setting order in partner in the ");
	   partner.getOrders().add(order);
	   }
	  
	   System.out.println(" reconciledate  ******** :"+reconciledate);
	   if(order.getOrderId()!=0)
	   {
		System.out.println(" Saving edited order");
		   session.merge(order);
	   }
	   else
	   {
		   System.out.println(" ****************Saving new  order");
	   seller.getOrders().add(order);
	   session.saveOrUpdate(partner);
	   session.saveOrUpdate(seller);
	   }
	   session.getTransaction().commit();
	   session.close();
	   }
	   catch (Exception e) {
		   System.out.println("Inside exception in add order "+e.getLocalizedMessage());
		   e.printStackTrace();
		
	}
	
	
}*/

@Override
public List<Order> listOrders(int sellerId) {
	//sellerId=4;
	List<Order> returnlist=null;
	try
	{
	Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Seller seller=(Seller)session.get(Seller.class, sellerId);
	   if(seller.getOrders()!=null&&seller.getOrders().size()!=0)
		   returnlist=seller.getOrders();
	   returnlist=seller.getOrders();
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
public Order getOrder(int orderId) {
	Order returnorder=null;
	try
	{
	Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   returnorder=(Order)session.get(Order.class, orderId);
	   Hibernate.initialize(returnorder.getOrderReturnOrRTO());
	   Hibernate.initialize(returnorder.getOrderTax());
	   Hibernate.initialize(returnorder.getOrderPayment());
	   Hibernate.initialize(returnorder.getOrderTimeline());
	   Hibernate.initialize(returnorder.getCustomer());
	   session.getTransaction().commit();
	   session.close();
	}
	catch(Exception e)
	{
		System.out.println(" Exception in getting order list :"+e.getLocalizedMessage());
	}
	return returnorder;
	//return (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
}

@Override
public void deleteOrder(Order order,int sellerId) {
	 System.out.println(" In Order delete oid "+order.getOrderId());
	 try
	 {
		 Session session=sessionFactory.openSession();
		  session.beginTransaction();
		  Query deleteQuery = session.createSQLQuery(
				    "delete from Seller_Order_Table where seller_Id=? and orders_orderId=?");
		  		deleteQuery.setInteger(0,sellerId);
				deleteQuery.setInteger(1, order.getOrderId());
				
				int updated = deleteQuery.executeUpdate();
				int orderdelete=session.createQuery("DELETE FROM Order WHERE orderId = "+order.getOrderId()).executeUpdate();
		System.out.println(" Deletion process successful :updated"+updated +"orderdelete "+orderdelete);
		  session.getTransaction().commit();
		  session.close();

	 }
	 catch(Exception e)
	 {
		 System.out.println(" Inside delleting order"+e.getLocalizedMessage());
		 e.printStackTrace();
	 }
	
}



public void addReturnOrder(String channelOrderId ,OrderRTOorReturn orderReturn,int sellerId)
{
	System.out.println(" saving rder id :"+channelOrderId);
	Seller seller = null;
	Order order=null;
	
	
	//To add change order status
		   try
		   {
			   Session session=sessionFactory.openSession();
			   session.beginTransaction();
			   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
				criteria.createAlias("orders", "order", CriteriaSpecification.LEFT_JOIN)
				.add(Restrictions.eq("order.channelOrderID", channelOrderId))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
				seller=(Seller)criteria.list().get(0);
				if(seller.getOrders()!=null&&seller.getOrders().size()!=0)
				{
					order=seller.getOrders().get(0);
				 if((int)order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()==0)
				 {
					   order.getOrderReturnOrRTO().setReturnUploadDate(orderReturn.getReturnUploadDate());
				 }
			   if(order.getReturnLimitCrossed().compareTo(orderReturn.getReturnDate())<0)
				{
				   order.getOrderReturnOrRTO().setReturnOrRTOChargestoBeDeducted(0);
				   order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getNetRate());
				   order.setStatus("Return Limit Crossed");
				   OrderTimeline timeline=new OrderTimeline();
				   timeline.setEvent("Return Recieved");
				   timeline.setEventDate(new Date());
				   order.getOrderTimeline().add(timeline);
				   OrderTimeline timeline1=new OrderTimeline();
				   timeline1.setEvent("Return Limit Crossed");
				   timeline1.setEventDate(new Date());
				   order.getOrderTimeline().add(timeline1);
				}
			   else
			   {
				   order.getOrderReturnOrRTO().setReturnOrRTOChargestoBeDeducted(orderReturn.getReturnOrRTOChargestoBeDeducted());
				   order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getPaymentDifference()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
				   order.setStatus("Return Recieved");
				   OrderTimeline timeline=new OrderTimeline();
				   timeline.setEvent("Return Recieved");
				   timeline.setEventDate(new Date());
				   order.getOrderTimeline().add(timeline);
				   
			   }
			   if( (int)order.getOrderPayment().getNegativeAmount()!=0)
               {
                               order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
                                           
               }
			   if(order.getOrderPayment().getNetPaymentResult()>0)
			   {
				   order.getOrderPayment().setPaymentDifference(-(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
			   }
			  // order.setStatus("Return Recieved");
			   order.setFinalStatus("Actionable");
			   order.setNetSaleQuantity(order.getQuantity()-order.getOrderReturnOrRTO().getReturnorrtoQty());
			   orderReturn.setReturnUploadDate(new Date());
			   productService.updateInventory(order.getProductSkuCode(), 0, order.getOrderReturnOrRTO().getReturnorrtoQty(), 0,false, sellerId);
			   
			   order.getOrderReturnOrRTO().setReturnDate(orderReturn.getReturnDate());
			   order.getOrderReturnOrRTO().setReturnOrRTOId(orderReturn.getReturnOrRTOId());
			   order.getOrderReturnOrRTO().setReturnorrtoQty(orderReturn.getReturnorrtoQty());
			   order.getOrderReturnOrRTO().setReturnOrRTOreason(orderReturn.getReturnOrRTOreason());
			   order.getOrderReturnOrRTO().setReturnOrRTOstatus("Return");
			  
			   order.setOrderReturnOrRTO(orderReturn);
			
		  // session.saveOrUpdate(order);
		  
		   session.getTransaction().commit();
		   session.close();
				}
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
			   e.printStackTrace();
		}
}

@Override
public List<Order> findOrders(String column , String value ,int sellerId)
{
	String searchString="order."+column;
	System.out.println(" Inside Find order dao method searchString :"+searchString+" value :"+value+"   sellerId :"+sellerId);
	
	Seller seller=null;
	List<Order> orderlist=null;
	
	 try
	   {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   criteria.createAlias("orders", "order", CriteriaSpecification.LEFT_JOIN)
	   .add(Restrictions.eq(searchString, value).ignoreCase())
	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	   if(criteria.list().size()!=0)
	   {
	   seller=(Seller)criteria.list().get(0);
	   if(seller==null)
		   System.out.println("Null seller");
	   orderlist=seller.getOrders();
	   if(orderlist!=null&&orderlist.size()!=0)
	   {
		   for(Order order:orderlist)
		   {
			   Hibernate.initialize(order.getOrderTimeline());
		   }
	   }
	   }
	   session.getTransaction().commit();
	   session.close();
	   
	   }
	 catch(Exception e)
	 {System.out.println(" Exception in find order "+e.getLocalizedMessage());
		 e.printStackTrace();
	 }
	return orderlist;
}

@Override
public List<Order> findOrdersbyDate(String column ,Date startDate , Date endDate ,int sellerId)
{
	String searchString=null;
	searchString="order."+column;
	Seller seller=null;
	List<Order> orderlist=null;
	
	 try
	   {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   criteria.createAlias("orders", "order", CriteriaSpecification.LEFT_JOIN)
	   .add(Restrictions.between(searchString, startDate, endDate))
	   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	   seller=(Seller)criteria.list().get(0);
	   orderlist=seller.getOrders();
	   if(orderlist!=null&&orderlist.size()!=0)
	   {
		   for(Order order:orderlist)
		   {
			   Hibernate.initialize(order.getOrderTimeline());
		   }
	   }
	   if(orderlist==null||orderlist.size()==0)
		   System.out.println(" orderlist is null");
	  session.getTransaction().commit();
	   session.close();
	   }
	 catch(Exception e)
	 {
		 System.out.println(" Inside findorderbydate  exception :"+e.getMessage());
		 e.printStackTrace();
	 }
	return orderlist;
}

@Override
public List<Order> findOrdersbyReturnDate(String column ,Date startDate , Date endDate ,int sellerId)
{
	
	Seller seller=null;
	List<Order> orderlist=new ArrayList<Order>();
	try
	   {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   seller=(Seller)criteria.list().get(0);
	   if(seller.getOrders()!=null)
	   {
	   for(Order order:seller.getOrders())
	   {
		   if(order.getOrderReturnOrRTO().getReturnDate().compareTo(startDate)>0&&
				   order.getOrderReturnOrRTO().getReturnDate().compareTo(endDate)<0)
			   orderlist.add(order);
	   }
	   if(orderlist!=null&&orderlist.size()!=0)
	   {
		   for(Order order:orderlist)
		   {
			   Hibernate.initialize(order.getOrderTimeline());
		   }
	   }
	   }
	  session.getTransaction().commit();
	   session.close();
	   }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	
	return orderlist;
}
@Override
public void deleteReturnInfo(String orderId)
{
	//To be implemented
}

@Override
public List<Order> findOrdersbyCustomerDetails(String column , String value ,int sellerId)
{
	Seller seller=null;
	List<Order> orderlist=new ArrayList<Order>();
	try
	   {
	   Session session=sessionFactory.openSession();
	   session.beginTransaction();
	   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
	   seller=(Seller)criteria.list().get(0);
	   
	   for(Order order:seller.getOrders())
	   {
		   if(order.getCustomer().getCustomerCity().equalsIgnoreCase(value)||
				   order.getCustomer().getCustomerName().equalsIgnoreCase(value)||
				   order.getCustomer().getCustomerEmail().equalsIgnoreCase(value)||
				   order.getCustomer().getCustomerPhnNo().equalsIgnoreCase(value))
			   orderlist.add(order);
	   }
	  session.getTransaction().commit();
	   session.close();
	   }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	
	return orderlist;
}

@Override
public Order addOrderPayment(int orderid, OrderPayment orderPayment,int sellerId)
{
	Order order=null;
	System.out.println("Inside add ordr payment iwtih order id "+orderid);
	try
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(Order.class).add(Restrictions.eq("orderId", orderid));
		if(criteria.list()!=null&&criteria.list().size()!=0)
			order=(Order)criteria.list().get(0);
	
			if(order.getOrderReturnOrRTO().getReturnDate()!=null&&order.getOrderReturnOrRTO().getReturnorrtoQty()!=0)
			{
				if(((int)orderPayment.getPositiveAmount())!=0)
				{
					
					order.getOrderPayment().setPositiveAmount(orderPayment.getPositiveAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()+orderPayment.getPositiveAmount());
					if(order.getOrderPayment().getNetPaymentResult()>0)
					{
						  if(order.getReturnLimitCrossed().compareTo(order.getOrderReturnOrRTO().getReturnDate())<0)
							{
							  order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getTotalAmountRecieved());
							}
						  else
						  {
							  order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-(order.getTotalAmountRecieved()-order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
						  }
					}
					else
					{
						order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
						
					}
					
					order.setStatus("Payment Recieved");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getPositiveAmount()+" Recieved");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					
				}
				else
				{
					 order.setStatus("Payment Deducted");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getNegativeAmount()+" Deducted");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					order.getOrderPayment().setNegativeAmount(orderPayment.getNegativeAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()-orderPayment.getNegativeAmount());
					 if(order.getReturnLimitCrossed().compareTo(order.getOrderReturnOrRTO().getReturnDate())<0)
						{
						  order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getTotalAmountRecieved());
						  order.setStatus("Return Limit Crossed");
							 OrderTimeline timeline1=new OrderTimeline();
							   timeline1.setEvent("Return Limit Crossed");
							   timeline1.setEventDate(new Date());
							   order.getOrderTimeline().add(timeline1);
						}
					  else
					  {
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
					  }
					
				}
			}
			else
			{
				
				if(((int)orderPayment.getPositiveAmount())!=0)
				{
					
					order.getOrderPayment().setPositiveAmount(orderPayment.getPositiveAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()+orderPayment.getPositiveAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-(order.getTotalAmountRecieved()-order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
					System.out.println("payment difference :"+order.getOrderPayment().getPaymentDifference());
					
					order.setStatus("Payment Recieved");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getPositiveAmount()+" Recieved");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					
					
				}
				else
				{
					order.setStatus("Return Not Recieved");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getNegativeAmount()+" Deducted");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					if(order.getReturnLimitCrossed().compareTo(new Date())<0)
					{
						order.setStatus("Return Limit Crossed");
						order.getOrderReturnOrRTO().setReturnOrRTOChargestoBeDeducted(0);
						 OrderTimeline timeline1=new OrderTimeline();
						   timeline1.setEvent("Return Limit Crossed");
						   timeline1.setEventDate(new Date());
						   order.getOrderTimeline().add(timeline1);
					}
					order.getOrderPayment().setNegativeAmount(orderPayment.getNegativeAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()-orderPayment.getNegativeAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getNetRate()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
					
					
				}
			}
			order.getOrderPayment().setDateofPayment(orderPayment.getDateofPayment());
			order.getOrderPayment().setPaymentCycle(orderPayment.getPaymentCycle());
			order.getOrderPayment().setUploadDate(new Date());
			if(order.getOrderPayment().getPaymentDifference()>0||(int)order.getOrderPayment().getPaymentDifference()==0)
			{
				//order.setStatus("OK Payment");
				order.setFinalStatus("Settled");
			}
			else
			{
				//order.setStatus("Payment Difference");
				order.setFinalStatus("Actionable");
			}
			System.out.println("order in add payment :   **"+order);
			//To Do - implement netactualrate 2
			session.saveOrUpdate(order);
			session.getTransaction().commit();
			session.close();
			

		}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	return order;
}
@Override
public Order addOrderPayment(String skucode ,String channelOrderId , OrderPayment orderPayment, int sellerId)
{
	Order order=null;
	System.out.println(" SKUCODE: "+skucode+"  channedorderid "+channelOrderId+"sellerId :"+sellerId);
	try
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		//Criteria criteria=session.createCriteria(Order.class).add(Restrictions.eq("channelOrderID", channelOrderId));
		 Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	   .add(Restrictions.eq("channelOrderID", channelOrderId))
		.add(Restrictions.eq("productSkuCode", skucode));
		
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		if(criteria.list()!=null&&criteria.list().size()!=0)
		{	System.out.println("get order ");
		order=(Order)criteria.list().get(0);
		}
		else
		{
			System.out.println(" Null order");
		}
		if(order!=null)
		{
			//order=seller.getOrders().get(0);
			
			/*if(order.getOrderReturnOrRTO().getReturnDate()!=null&&order.getOrderReturnOrRTO().getReturnorrtoQty()!=0)
			{
				if(((int)orderPayment.getPositiveAmount())!=0)
				{
					
					order.getOrderPayment().setPositiveAmount(orderPayment.getPositiveAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()+orderPayment.getPositiveAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-(order.getTotalAmountRecieved()-order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
					
					
				}
				else
				{
					order.getOrderPayment().setNegativeAmount(orderPayment.getNegativeAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()-orderPayment.getPositiveAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
					
				}
			}
			else
			{
				if(((int)orderPayment.getPositiveAmount())!=0)
				{
					System.out.println("Positive payment");
					order.getOrderPayment().setPositiveAmount(orderPayment.getPositiveAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()+orderPayment.getPositiveAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-(order.getTotalAmountRecieved()-order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
					System.out.println("payment difference :"+order.getOrderPayment().getPaymentDifference());
					
				}
				else
				{
					if(order.getReturnLimitCrossed().compareTo(new Date())<0)
					{
						order.setStatus("Return Limit Crossed");
						order.getOrderReturnOrRTO().setReturnOrRTOChargestoBeDeducted(0);
					}
					order.getOrderPayment().setNegativeAmount(orderPayment.getNegativeAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()-orderPayment.getPositiveAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getNetRate()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
					System.out.println(" Iside negative payment wothout return");
					
				}
			}
			order.getOrderPayment().setDateofPayment(orderPayment.getDateofPayment());
			order.getOrderPayment().setPaymentCycle(orderPayment.getPaymentCycle());
			order.getOrderPayment().setUploadDate(new Date());
			if(order.getOrderPayment().getPaymentDifference()>0||(int)order.getOrderPayment().getPaymentDifference()==0)
			{
				order.setStatus("OK Payment");
				order.setFinalStatus("Settled");
			}
			else
			{
				order.setStatus("Payment Difference");
				order.setFinalStatus("Actionable");
			}
			System.out.println("order in add payment :   **"+order);
			//To Do - implement netactualrate 2
			session.saveOrUpdate(order);
			session.getTransaction().commit();
			session.close();*/
			

			if(order.getOrderReturnOrRTO().getReturnDate()!=null&&order.getOrderReturnOrRTO().getReturnorrtoQty()!=0)
			{
				if(((int)orderPayment.getPositiveAmount())!=0)
				{
					
					order.getOrderPayment().setPositiveAmount(orderPayment.getPositiveAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()+orderPayment.getPositiveAmount());
					if(order.getOrderPayment().getNetPaymentResult()>0)
					{
						  if(order.getReturnLimitCrossed().compareTo(order.getOrderReturnOrRTO().getReturnDate())<0)
							{
							  order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getTotalAmountRecieved());
							}
						  else
						  {
							  order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-(order.getTotalAmountRecieved()-order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
						  }
					}
					else
					{
						order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
						
					}
					
					order.setStatus("Payment Recieved");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getPositiveAmount()+" Recieved");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					
				}
				else
				{
					 order.setStatus("Payment Deducted");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getNegativeAmount()+" Deducted");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					order.getOrderPayment().setNegativeAmount(orderPayment.getNegativeAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()-orderPayment.getNegativeAmount());
					 if(order.getReturnLimitCrossed().compareTo(order.getOrderReturnOrRTO().getReturnDate())<0)
						{
						  order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getTotalAmountRecieved());
						  order.setStatus("Return Limit Crossed");
							 OrderTimeline timeline1=new OrderTimeline();
							   timeline1.setEvent("Return Limit Crossed");
							   timeline1.setEventDate(new Date());
							   order.getOrderTimeline().add(timeline1);
						}
					  else
					  {
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
					  }
					
				}
			}
			else
			{
				
				if(((int)orderPayment.getPositiveAmount())!=0)
				{
					
					order.getOrderPayment().setPositiveAmount(orderPayment.getPositiveAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()+orderPayment.getPositiveAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-(order.getTotalAmountRecieved()-order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted()));
					System.out.println("payment difference :"+order.getOrderPayment().getPaymentDifference());
					
					order.setStatus("Payment Recieved");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getPositiveAmount()+" Recieved");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					
					
				}
				else
				{
					order.setStatus("Return Not Recieved");
					 OrderTimeline timeline=new OrderTimeline();
					   timeline.setEvent("Rs "+orderPayment.getNegativeAmount()+" Deducted");
					   timeline.setEventDate(new Date());
					   order.getOrderTimeline().add(timeline);
					if(order.getReturnLimitCrossed().compareTo(new Date())<0)
					{
						order.setStatus("Return Limit Crossed");
						order.getOrderReturnOrRTO().setReturnOrRTOChargestoBeDeducted(0);
						 OrderTimeline timeline1=new OrderTimeline();
						   timeline1.setEvent("Return Limit Crossed");
						   timeline1.setEventDate(new Date());
						   order.getOrderTimeline().add(timeline1);
					}
					order.getOrderPayment().setNegativeAmount(orderPayment.getNegativeAmount());
					order.getOrderPayment().setNetPaymentResult(order.getOrderPayment().getNetPaymentResult()-orderPayment.getNegativeAmount());
					order.getOrderPayment().setPaymentDifference(order.getOrderPayment().getNetPaymentResult()-order.getNetRate()+order.getOrderReturnOrRTO().getReturnOrRTOChargestoBeDeducted());
					
					
				}
			}
			order.getOrderPayment().setDateofPayment(orderPayment.getDateofPayment());
			order.getOrderPayment().setPaymentCycle(orderPayment.getPaymentCycle());
			order.getOrderPayment().setUploadDate(new Date());
			if(order.getOrderPayment().getPaymentDifference()>0||(int)order.getOrderPayment().getPaymentDifference()==0)
			{
				//order.setStatus("OK Payment");
				order.setFinalStatus("Settled");
			}
			else
			{
				//order.setStatus("Payment Difference");
				order.setFinalStatus("Actionable");
			}
			System.out.println("order in add payment :   **"+order);
			//To Do - implement netactualrate 2
			session.saveOrUpdate(order);
			session.getTransaction().commit();
			session.close();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	return order;
}


//MEthod to add debit notes for PO companies

public void addDebitNote(DebitNoteBean dnBean,int sellerId)
{
	
	System.out.println("######## debit note for invoice  id :"+dnBean.getInvoiceId()+" gp id : "+dnBean.getGatePassId()+" po id+ "+ dnBean.getPOOrderId());
	Order order=null;
	
	
	//To add change order status
		   try
		   {
			   Session session=sessionFactory.openSession();
				session.beginTransaction();
				//Criteria criteria=session.createCriteria(Order.class).add(Restrictions.eq("channelOrderID", channelOrderId));
				 Criteria criteria=session.createCriteria(Order.class);
			 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
			 	   .add(Restrictions.eq("seller.id", sellerId))
			 	   .add(Restrictions.eq("channelOrderID", dnBean.getPOOrderId()))
			 	   .add(Restrictions.eq("invoiceID", dnBean.getInvoiceId()))
			 	   .add(Restrictions.eq("pcName", dnBean.getPcName()))
				.add(Restrictions.eq("productSkuCode", dnBean.getSKU()));
				
				criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
				if(criteria.list()!=null&&criteria.list().size()!=0)
				{	System.out.println("get order ");
				order=(Order)criteria.list().get(0);
				}
				else
				{
					System.out.println(" Null order");
				}
				System.out.println(" PO price : "+(order.getPoPrice()/order.getQuantity()) + "  --- amount  : "+(dnBean.getAmount()/dnBean.getQuantity()));
				if((int)(order.getPoPrice()/order.getQuantity())!=(int)(dnBean.getAmount()/dnBean.getQuantity()))
				{
					order.getOrderPayment().setPaymentDifference((order.getPoPrice()/order.getQuantity())-(dnBean.getAmount()/dnBean.getQuantity()));
				}
					
					
					order.getOrderReturnOrRTO().setReturnDate(dnBean.getReturnDate());
					order.getOrderReturnOrRTO().setReturnOrRTOreason(dnBean.getReturnReason());
					order.getOrderReturnOrRTO().setReturnorrtoQty(dnBean.getQuantity());
					order.getOrderReturnOrRTO().setReturnOrRTOChargestoBeDeducted(dnBean.getAmount());
					order.setSealNo(dnBean.getGatePassId());
					order.setStatus("Return Recived");
					
					if((int)order.getOrderPayment().getPaymentDifference()!=0)
					{
						order.setFinalStatus("Actionable");
					}
					else
					{
						order.setFinalStatus("Settled");
					}
					
					session.saveOrUpdate(order);
					session.getTransaction().commit();
					session.close();
		   }
		   catch(Exception e)
		   {
			   System.out.println(" Getting exception inadd debit note : "+e.getLocalizedMessage());
			   e.printStackTrace();
		   }
	
	
}

// Method to add PO payment

@SuppressWarnings("unchecked")
public void addPOPayment(PoPaymentBean popaBean,int sellerId)
{
	System.out.println(" PoPaymentBean note for invoice  id :"+popaBean.getInvoiceID()+" gp id : "+popaBean.getGatePassId()+" po id+ "+ popaBean.getPoOrderId());
	//Seller seller = null;
	List<Order> orderlist=null;
	double positiveamount=0;
	double negativeamount=0;
	double returnamount=0;
	boolean positiveOK=false;
	boolean returnOK=false;

	//To add change order status
	try
	{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		//Criteria criteria=session.createCriteria(Order.class).add(Restrictions.eq("channelOrderID", channelOrderId));
		Criteria criteria=session.createCriteria(Order.class);
		criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
		.add(Restrictions.eq("seller.id", sellerId))
		.add(Restrictions.eq("channelOrderID", popaBean.getPoOrderId()))
		.add(Restrictions.eq("invoiceID", popaBean.getInvoiceID()))
		.add(Restrictions.eq("pcName", popaBean.getPcName()));

		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		orderlist=criteria.list();


		Criteria amountCriteria=session.createCriteria(Order.class);
		amountCriteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		amountCriteria.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN);
		amountCriteria.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
		.add(Restrictions.eq("seller.id", sellerId))
		.add(Restrictions.eq("channelOrderID", popaBean.getPoOrderId()))
		.add(Restrictions.eq("invoiceID", popaBean.getInvoiceID()))
		.add(Restrictions.eq("pcName", popaBean.getPcName()));
		amountCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.sum("orderPayment.negativeAmount"));
		projList.add(Projections.sum("poPrice"));
		projList.add(Projections.sum("orderReturnOrRTO.returnOrRTOChargestoBeDeducted"));
		amountCriteria.setProjection(projList);
		List<Object[]> results = amountCriteria.list();
		Iterator iterator1 = results.iterator();
		if(results != null){
			while(iterator1.hasNext()){

				Object[] recordsRow = (Object[])iterator1.next();
				negativeamount=(Double)recordsRow[0];
				positiveamount=(Double)recordsRow[1];
				returnamount=(Double)recordsRow[2];

			}
		}


		if((int)positiveamount==(int)popaBean.getPositiveAmount())
		{
			positiveOK=true;
		}
		if((int)returnamount==(int)popaBean.getNegativeAmount())
		{
			returnOK=true;
		}
		if(orderlist!=null&&orderlist.size()!=0)
		{	System.out.println("get order ");


		for(Order order:orderlist)
		{
			if(positiveOK&&returnOK)
			{
				order.setFinalStatus("Settled");
				order.setStatus("PO payment");
				order.getOrderPayment().setDateofPayment(popaBean.getPaymentDate());
				
			}
			else
			{
				order.setFinalStatus("Actionable");
				order.setStatus("Inappropriate Payment Recieved");
			}

			order.getOrderPayment().setDateofPayment(popaBean.getPaymentDate());
			session.saveOrUpdate(order);
		}
		}
		else
		{
			System.out.println(" Null order");
		}
				
				
				//session.saveOrUpdate(order);
				session.getTransaction().commit();
				session.close();
		   }
		   catch(Exception e)
		   {
			   System.out.println(" Getting exception inadd debit note : "+e.getLocalizedMessage());
			   e.printStackTrace();
		   }
}

@SuppressWarnings("deprecation")
public Date getreconciledate(Order order,Partner partner,Date orderdate)
{
	System.out.println(" Inside reconciled date method");
	System.out.println(" Delivery date :"+order.getDeliveryDate());
	Date reconciledate=new Date();
	int startdate=partner.getStartcycleday();
	//int cycleduration=partner.getPaycycleduration();
	int paydate=partner.getPaydaysfromstartday();
	int currentdate=reconciledate.getDate();
	boolean payfromshippingdate=partner.isPaycyclefromshipordel();
	String paymentType=partner.getPaymentType();
	boolean isIsshippeddatecalc=partner.isIsshippeddatecalc();
	int monthlypaydate=partner.getMonthlypaydate();
	int noofdaysfromshippeddate=partner.getNoofdaysfromshippeddate();
	Date deliverydate=new Date(dateFormat.format(order.getDeliveryDate()));
	Date shippeddate=new Date(dateFormat.format(order.getShippedDate()));
	int orderdeliverydate=deliverydate.getDate();
	int ordershippeddate=shippeddate.getDate();
	int fsd=0;
	int loop=0;
	int tempsd=0;
	int temped=0;
	int enddate=partner.getPaycycleduration();
	Map<String,Date> returndates=new HashMap<>();
			
	System.out.println(" ORder delivery date in rec 2 : "+order.getDeliveryDate() );
	if(paymentType.equals("paymentcycle"))
	{
		if(payfromshippingdate)
			currentdate=ordershippeddate;
		else
			currentdate=orderdeliverydate;
			
		
	while(true)
	{
		loop++;
		System.out.println("Loop :"+loop+" currentdate :"+currentdate+ ">>startdate : "+startdate+">>enddate :"+enddate);
		if(currentdate>startdate||currentdate==startdate)
		{
			if(currentdate<enddate||currentdate==enddate||currentdate==startdate)
				{
				fsd=paydate;
				System.out.println(" Cycle Start Day :" +startdate);
				System.out.println(" Cycle End Day :" +enddate);
				break;
				}
			else
			{
				tempsd=startdate;
				temped=enddate;
				startdate=enddate+1;
				enddate=startdate+(enddate-tempsd);
				paydate=enddate+(paydate-temped);
				continue;
			}
		}
		else
		{
			System.out.println(" Payent date in previous cycle : ");
			
				temped=enddate;
				tempsd=startdate;
				enddate=startdate-1;
				startdate=enddate-(temped-tempsd);
				paydate=enddate+(paydate-temped);
				continue;
			
			
		}
	}
	if(!payfromshippingdate)
	reconciledate=(Date)deliverydate.clone();
	else
		reconciledate=(Date)shippeddate.clone();
	reconciledate.setDate(paydate);
	System.out.println("reconciledate in paycycle :"+reconciledate);
	}
	else if(paymentType.equals("datewisepay"))
	{
		System.out.println(" Datewise payment isIsshippeddatecalc : "+isIsshippeddatecalc+"  noofdaysfromshippeddate : "+noofdaysfromshippeddate);
		System.out.println(" Delivery date : "+deliverydate);
		if(isIsshippeddatecalc)
		{
			reconciledate=shippeddate;
			reconciledate.setDate(reconciledate.getDate()+noofdaysfromshippeddate);
		}
		else
		{
			reconciledate=deliverydate;
			reconciledate.setDate(reconciledate.getDate()+noofdaysfromshippeddate);
		}
		System.out.println(" Reconcile date after datewisepayment : "+reconciledate);
	}
	else
	{
		reconciledate=deliverydate;
		reconciledate.setMonth(reconciledate.getMonth()+1);
		reconciledate.setDate(monthlypaydate);
		
		
	}
System.out.println(" ORder delivery date in rec : "+order.getDeliveryDate() );
return reconciledate;
}
}