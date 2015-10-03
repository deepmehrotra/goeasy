package com.goeasy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Order_Table")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int orderId;
	@Column
	private String geOrderId;
	@Column
	private Date orderDate;
	@Column
	private Date paymentDueDate;
	@Column
	private String productSkuCode;
	@Column
	private String pcName;
	@Column
	private String awbNum;
	@Column
	private String status;
	@Column
	private String channelOrderID;
	@Column
	private String invoiceID;
	@Column
	private String subOrderID;
	@Column
	private String PIreferenceNo;
	@Column
	private String logisticPartner;
	@Column
	private double orderMRP;
	@Column
	private double orderSP;
	@Column
	private double shippingCharges;
	@Column
	private Date shippedDate;
	@Column
	private Date deliveryDate;
	@Column
	private Date lastActivityOnOrder;
	@Column
	private int quantity;
	@Column
	private int netSaleQuantity;
	@Column
	private double netRate;
	@Column
	private double grossNetRate;
	@Column
	private double discount;	
	@Column
	private double partnerCommission;	
	@Column
	private double pr;
	@Column
	private String uniqueItemId;
	@Column
	private String sellerNote;
	@Column
	private double totalAmountRecieved;	
	@Column
	private String paymentType;
	@Column
	private String finalStatus;
	@Column
	private Date returnLimitCrossed;
	@Column
	private Date rTOLimitCrossed;
	@Column
	private double poPrice;
	@Column
	private String sealNo;	
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrderTimeline> orderTimeline=new ArrayList<>();
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer customer;
	@OneToOne(cascade=CascadeType.ALL) 
	private OrderPayment orderPayment;
	@OneToOne(cascade=CascadeType.ALL)
	private OrderRTOorReturn orderReturnOrRTO;
	@OneToOne(cascade=CascadeType.ALL)
	private OrderTax orderTax;
	@ManyToOne(cascade=CascadeType.ALL)
	private PaymentUpload paymentUpload;
	@ManyToOne(fetch = FetchType.LAZY)
	private Seller seller;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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

	public OrderPayment getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(OrderPayment orderPayment) {
		this.orderPayment = orderPayment;
	}

	public OrderRTOorReturn getOrderReturnOrRTO() {
		return orderReturnOrRTO;
	}

	public void setOrderReturnOrRTO(OrderRTOorReturn orderReturnOrRTO) {
		this.orderReturnOrRTO = orderReturnOrRTO;
	}

	public OrderTax getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(OrderTax orderTax) {
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

	public PaymentUpload getPaymentUpload() {
		return paymentUpload;
	}

	public void setPaymentUpload(PaymentUpload paymentUpload) {
		this.paymentUpload = paymentUpload;
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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", geOrderId=" + geOrderId
				+ ", orderDate=" + orderDate + ", paymentDueDate="
				+ paymentDueDate + ", productSkuCode=" + productSkuCode
				+ ", pcName=" + pcName + ", awbNum=" + awbNum + ", status="
				+ status + ", channelOrderID=" + channelOrderID
				+ ", invoiceID=" + invoiceID + ", subOrderID=" + subOrderID
				+ ", PIreferenceNo=" + PIreferenceNo + ", logisticPartner="
				+ logisticPartner + ", orderMRP=" + orderMRP + ", orderSP="
				+ orderSP + ", shippingCharges=" + shippingCharges
				+ ", shippedDate=" + shippedDate + ", deliveryDate="
				+ deliveryDate + ", lastActivityOnOrder=" + lastActivityOnOrder
				+ ", quantity=" + quantity + ", netSaleQuantity="
				+ netSaleQuantity + ", netRate=" + netRate + ", grossNetRate="
				+ grossNetRate + ", discount=" + discount
				+ ", partnerCommission=" + partnerCommission + ", pr=" + pr
				+ ", uniqueItemId=" + uniqueItemId + ", sellerNote="
				+ sellerNote + ", totalAmountRecieved=" + totalAmountRecieved
				+ ", paymentType=" + paymentType + ", finalStatus="
				+ finalStatus + ", returnLimitCrossed=" + returnLimitCrossed
				+ ", rTOLimitCrossed=" + rTOLimitCrossed + ", orderTimeline="
				+ orderTimeline + ", customer=" + customer + ", orderPayment="
				+ orderPayment + ", orderReturnOrRTO=" + orderReturnOrRTO
				+ ", orderTax=" + orderTax + ", paymentUpload=" + paymentUpload
				+ "]";
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

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}


}
