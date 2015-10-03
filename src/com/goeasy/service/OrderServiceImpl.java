package com.goeasy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.bean.DebitNoteBean;
import com.goeasy.bean.PoPaymentBean;
import com.goeasy.dao.OrderDao;
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.OrderRTOorReturn;

/**
 * @author Deep Mehortra
 *
 */
@Service("orderService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderServiceImpl implements OrderService {

 @Autowired
 private OrderDao orderDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addOrder(Order order ,int sellerId) {
	System.out.println("Inside add order OrderServiceImpl awb :"+order.getAwbNum());
	orderDao.addOrder(order,sellerId);
	
}

@Override
public List<Order> listOrders(int sellerId) {
	return orderDao.listOrders(sellerId);
}

@Override
public Order getOrder(int orderId) {
	return orderDao.getOrder(orderId);
}

@Override
public void deleteOrder(Order order,int sellerId) {
	
	orderDao.deleteOrder(order,sellerId);
}

public void addReturnOrder(String channelOrderId ,OrderRTOorReturn orderReturn,int sellerId)
{
	orderDao.addReturnOrder(channelOrderId,orderReturn,sellerId);
}

public List<Order> findOrders(String column , String value ,int sellerId)
{
	return orderDao.findOrders(column,value,sellerId);
}
public List<Order> findOrdersbyDate(String column ,Date startDate , Date endDate ,int sellerId)
{
	return orderDao.findOrdersbyDate(column ,startDate,endDate,sellerId);
}
public List<Order> findOrdersbyReturnDate(String column ,Date startDate , Date endDate ,int sellerId)
{
	return orderDao.findOrdersbyReturnDate(column ,startDate,endDate,sellerId);
}
public List<Order> findOrdersbyCustomerDetails(String column , String value ,int sellerId)
{
	return orderDao.findOrdersbyCustomerDetails(column,value,sellerId);
}
public void deleteReturnInfo(String orderId)
{
	orderDao.deleteReturnInfo(orderId);
}

public Order addOrderPayment(String skucode ,String channelOrderId , OrderPayment orderPayment,int sellerId)
{
	return orderDao.addOrderPayment(skucode,channelOrderId ,orderPayment,sellerId);
}
public Order addOrderPayment(int orderid, OrderPayment orderPayment,int sellerId)
{
	return orderDao.addOrderPayment(orderid,orderPayment,sellerId);
}

public void addDebitNote(DebitNoteBean dnBean,int sellerId)
{
	orderDao.addDebitNote(dnBean,sellerId);
}

public void addPOPayment(PoPaymentBean popaBean,int sellerId)
{
	orderDao.addPOPayment(popaBean,sellerId);
}
}