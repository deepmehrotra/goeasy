package com.goeasy.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goeasy.model.OrderTimeline;

public class OrderBean {

	private int orderId;
	private String geOrderId;
	private Date orderDate;
	private Date paymentDueDate;
	private String productSkuCode;
	private String pcName;
	private String awbNum;
	private String status;
	private String channelOrderID;
	private String invoiceID;
	private String subOrderID;
	private String PIreferenceNo;
	private String logisticPartner;
	private double orderMRP;
	private double orderSP;
	private double shippingCharges;
	private Date shippedDate;
	private Date deliveryDate;
	private Date lastActivityOnOrder;
	private int quantity;
	private int netSaleQuantity;
	private double netRate;
	private double grossNetRate;
	private double discount;
    private double partnerCommission;	
	private double pr;
	private double totalAmountRecieved;	
	private String uniqueItemId;
	private String sellerNote;
	private String paymentType;
	private String finalStatus;
	private Date returnLimitCrossed;
	private Date rTOLimitCrossed;
	private double poPrice;
	private String sealNo;
	private List<OrderTimeline> orderTimeline=new ArrayList<>();
	private CustomerBean customer=new CustomerBean();
	private OrderPaymentBean orderPayment=new OrderPaymentBean();
	private OrderRTOorReturnBean orderReturnOrRTO = new OrderRTOorReturnBean();
	private OrderTaxBean orderTax = new OrderTaxBean();
	

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getGeOrderId() {
		return geOrderId;
	}

	public void setGeOrderId(String geOrderId) {
		this.geOrderId = geOrderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductSkuCode() {
		return productSkuCode;
	}

	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getAwbNum() {
		return awbNum;
	}

	public void setAwbNum(String awbNum) {
		this.awbNum = awbNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getChannelOrderID() {
		return channelOrderID;
	}

	public void setChannelOrderID(String channelOrderID) {
		this.channelOrderID = channelOrderID;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getSubOrderID() {
		return subOrderID;
	}

	public void setSubOrderID(String subOrderID) {
		this.subOrderID = subOrderID;
	}

	public String getPIreferenceNo() {
		return PIreferenceNo;
	}

	public void setPIreferenceNo(String pIreferenceNo) {
		PIreferenceNo = pIreferenceNo;
	}

	public String getLogisticPartner() {
		return logisticPartner;
	}

	public void setLogisticPartner(String logisticPartner) {
		this.logisticPartner = logisticPartner;
	}

	public double getOrderMRP() {
		return orderMRP;
	}

	public void setOrderMRP(double orderMRP) {
		this.orderMRP = orderMRP;
	}

	public double getOrderSP() {
		return orderSP;
	}

	public void setOrderSP(double orderSP) {
		this.orderSP = orderSP;
	}

	public double getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(double shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getLastActivityOnOrder() {
		return lastActivityOnOrder;
	}

	public void setLastActivityOnOrder(Date lastActivityOnOrder) {
		this.lastActivityOnOrder = lastActivityOnOrder;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getNetRate() {
		return netRate;
	}

	public void setNetRate(double netRate) {
		this.netRate = netRate;
	}

	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getPr() {
		return pr;
	}

	public void setPr(double pr) {
		this.pr = pr;
	}

	public double getTotalAmountRecieved() {
		return totalAmountRecieved;
	}

	public void setTotalAmountRecieved(double totalAmountRecieved) {
		this.totalAmountRecieved = totalAmountRecieved;
	}

	public OrderPaymentBean getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(OrderPaymentBean orderPayment) {
		this.orderPayment = orderPayment;
	}

	public OrderRTOorReturnBean getOrderReturnOrRTO() {
		return orderReturnOrRTO;
	}

	public void setOrderReturnOrRTO(OrderRTOorReturnBean orderReturnOrRTO) {
		this.orderReturnOrRTO = orderReturnOrRTO;
	}

	public OrderTaxBean getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(OrderTaxBean orderTax) {
		this.orderTax = orderTax;
	}

	public double getGrossNetRate() {
		return grossNetRate;
	}

	public void setGrossNetRate(double grossNetRate) {
		this.grossNetRate = grossNetRate;
	}

	public int getNetSaleQuantity() {
		return netSaleQuantity;
	}

	public void setNetSaleQuantity(int netSaleQuantity) {
		this.netSaleQuantity = netSaleQuantity;
	}

	public double getPartnerCommission() {
		return partnerCommission;
	}

	public void setPartnerCommission(double partnerCommission) {
		this.partnerCommission = partnerCommission;
	}

	
	public String getUniqueItemId() {
		return uniqueItemId;
	}

	public void setUniqueItemId(String uniqueItemId) {
		this.uniqueItemId = uniqueItemId;
	}

	public String getSellerNote() {
		return sellerNote;
	}

	public void setSellerNote(String sellerNote) {
		this.sellerNote = sellerNote;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public List<OrderTimeline> getOrderTimeline() {
		return orderTimeline;
	}

	public void setOrderTimeline(List<OrderTimeline> orderTimeline) {
		this.orderTimeline = orderTimeline;
	}

	public Date getReturnLimitCrossed() {
		return returnLimitCrossed;
	}

	public void setReturnLimitCrossed(Date returnLimitCrossed) {
		this.returnLimitCrossed = returnLimitCrossed;
	}

	public Date getrTOLimitCrossed() {
		return rTOLimitCrossed;
	}

	public void setrTOLimitCrossed(Date rTOLimitCrossed) {
		this.rTOLimitCrossed = rTOLimitCrossed;
	}

	public double getPoPrice() {
		return poPrice;
	}

	public void setPoPrice(double poPrice) {
		this.poPrice = poPrice;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	
	
}
