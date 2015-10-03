package com.goeasy.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.bean.TotalShippedOrder;
import com.goeasy.model.Order;


 

/**
 * @author Deep Mehrotra
 *
 */

@Repository("reportGeneratorDao")
public class ReportsGeneratorDaoImpl implements ReportsGeneratorDao{


 @Autowired
 private SessionFactory sessionFactory;
 
 @SuppressWarnings("unchecked")
@Override
 public TotalShippedOrder getPartnerTSOdetails(String pcName,Date startDate ,Date endDate, int sellerId)
 {
	 //http://howtodoinjava.com/2014/10/28/hibernate-criteria-queries-tutorial-and-examples/#unique_result
	 System.out.println(" getPartnerTSOdetails   : pcName :"+pcName+" >>startDate"+startDate+">>endDate :"+endDate);
	// List<Order> orderlist=null;
	 TotalShippedOrder ttso=null;
	  try
	   {
		   Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Order.class);
	 	   criteria.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN)
	 	   .add(Restrictions.eq("seller.id", sellerId))
	 	    .add(Restrictions.eq("pcName", pcName))
	 	   .add(Restrictions.between("orderDate",startDate, endDate));
	 	  criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 	   ProjectionList projList = Projections.projectionList();
	 	  projList.add(Projections.sum("quantity"));
	 	 projList.add(Projections.sum("netRate"));
	 	criteria.setProjection(projList);
	 	List<Object[]> results = criteria.list();
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
	  return ttso;
 }
 
 
 @Override
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
		 .add(Restrictions.between("orderDate",startDate, endDate));
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
				 System.out.println("city "+ recordsRow[0]);
				 cityTotalQuantity=cityTotalQuantity+Integer.parseInt(recordsRow[1].toString());
				 }

				 
			 }
		 }

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
		 /*
		  * 
		  * Calcuting total values for NR ,QA ,
		  * 
		  */
/*
		 Criteria criteriaTotal=session.createCriteria(Order.class);
		 criteriaTotal.createAlias("seller", "seller", CriteriaSpecification.LEFT_JOIN);
		 criteriaTotal.createAlias("customer", "customer", CriteriaSpecification.LEFT_JOIN);
		 criteriaTotal.createAlias("orderPayment", "orderPayment", CriteriaSpecification.LEFT_JOIN);
		 criteriaTotal.createAlias("orderReturnOrRTO", "orderReturnOrRTO", CriteriaSpecification.LEFT_JOIN)
		 .add(Restrictions.eq("seller.id", sellerId))
		 .add(Restrictions.between("orderDate",startDate, endDate));
		 criteriaTotal.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		 ProjectionList projListTotal = Projections.projectionList();
		 projListTotal.add(Projections.sum("quantity"));
		 projListTotal.add(Projections.sum("netRate"));
		 projListTotal.add(Projections.sum("orderReturnOrRTO.returnorrtoQty"));
		 projListTotal.add(Projections.sum("orderPayment.negativeAmount"));
		 projListTotal.add(Projections.sum("orderSP"));
		 projListTotal.add(Projections.sum("orderPayment.positiveAmount"));
		 criteriaTotal.setProjection(projListTotal);
		 List<Object[]> resultsTotal = criteriaTotal.list();
		 Iterator iteratorTotal = resultsTotal.iterator();
		 if(resultsTotal != null){
			 while(iteratorTotal.hasNext()){
				 System.out.println("\n");
				 Object[] recordsRow = (Object[])iteratorTotal.next();
				 System.out.println(" record length:"+recordsRow.length);
				 for(int i = 0; i < recordsRow.length;i++){
					 System.out.print("\t"+recordsRow[i]);

				 }
			 }
		 }*/

	      
	 	/* for (Object object : results) {
	            Map<Object, Object> map = (Map<Object, Object>) object;
	            System.out.println(map);
	        }*/
	 	  /* if(criteria.list()!=null)
	 	  orderlist=(List<Order>)criteria.list();
	 	  
		   
		   if(orderlist!=null&&orderlist.size()!=0)
			   System.out.println(" GOt orders "+orderlist.size()+"  "+orderlist.get(0).getChannelOrderID());
			else
			   System.out.println(" Null list orderlist");*/
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
