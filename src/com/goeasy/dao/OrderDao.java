package com.goeasy.dao;

import java.util.Date;
import java.util.List;













import com.goeasy.bean.DebitNoteBean;
import com.goeasy.bean.PoPaymentBean;
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.OrderRTOorReturn;

/**
 * @author Dinesh Rajput
 *
 */
public interface OrderDao {
 
	 public void addOrder(Order order,int sellerId);

	 public List<Order> listOrders(int sellerId);
	 
	 public Order getOrder(int orderId);
	 
	 public void deleteOrder(Order order,int sellerId);
	 
	 public void addReturnOrder(String channelOrderId ,OrderRTOorReturn orderReturn,int sellerId);
	 
	 public void deleteReturnInfo(String orderId);
	 
	 public List<Order> findOrders(String column , String value ,int sellerId);
	 
	 public List<Order> findOrdersbyDate(String column ,Date startDate , Date endDate ,int sellerId);
	 
	 public List<Order> findOrdersbyReturnDate(String column ,Date startDate , Date endDate ,int sellerId);
	 
	 public List<Order> findOrdersbyCustomerDetails(String column , String value ,int sellerId);
	 
	 public Order addOrderPayment(String skucode ,String channelOrderId , OrderPayment orderPayment,int sellerId);
	 
	 public Order addOrderPayment(int orderid, OrderPayment orderPayment,int sellerId);
	 
	 public void addDebitNote(DebitNoteBean dnBean,int sellerId);
	 
	 public void addPOPayment(PoPaymentBean popaBean,int sellerId);
}