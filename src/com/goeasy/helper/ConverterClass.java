package com.goeasy.helper;

import java.util.ArrayList;
import java.util.List;

import com.goeasy.bean.CategoryBean;
import com.goeasy.bean.CustomerBean;
import com.goeasy.bean.ExpenseBean;
import com.goeasy.bean.ExpenseCategoryBean;
import com.goeasy.bean.ManualChargesBean;
import com.goeasy.bean.OrderBean;
import com.goeasy.bean.OrderPaymentBean;
import com.goeasy.bean.OrderRTOorReturnBean;
import com.goeasy.bean.OrderTaxBean;
import com.goeasy.bean.PartnerBean;
import com.goeasy.bean.PaymentUploadBean;
import com.goeasy.bean.ProductBean;
import com.goeasy.bean.SellerBean;
import com.goeasy.bean.TaxCategoryBean;
import com.goeasy.bean.TaxDetailBean;
import com.goeasy.model.Category;
import com.goeasy.model.Customer;
import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Expenses;
import com.goeasy.model.ManualCharges;
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.OrderRTOorReturn;
import com.goeasy.model.OrderTax;
import com.goeasy.model.Partner;
import com.goeasy.model.PaymentUpload;
import com.goeasy.model.Product;
import com.goeasy.model.Seller;
import com.goeasy.model.TaxCategory;
import com.goeasy.model.TaxDetail;

public class ConverterClass {
	
	public static Order prepareModel(OrderBean orderBean){
		  Order order = new Order();
		  order.setAwbNum(orderBean.getAwbNum());
		  order.setGeOrderId(orderBean.getGeOrderId());
		  order.setOrderDate(orderBean.getOrderDate());
		  order.setPcName(orderBean.getPcName());
		  order.setStatus(orderBean.getStatus());
		  order.setOrderId(orderBean.getOrderId());
		  order.setPaymentDueDate(orderBean.getPaymentDueDate());
		  order.setProductSkuCode(orderBean.getProductSkuCode());
		  order.setChannelOrderID(orderBean.getChannelOrderID());
		  order.setInvoiceID(orderBean.getInvoiceID());
		  order.setSubOrderID(orderBean.getSubOrderID());
		  order.setPIreferenceNo(orderBean.getPIreferenceNo());
		  order.setLogisticPartner(orderBean.getLogisticPartner());
		  order.setOrderMRP(orderBean.getOrderMRP());
		  order.setOrderSP(orderBean.getOrderSP());
		  order.setShippingCharges(orderBean.getShippingCharges());
		  order.setShippedDate(orderBean.getShippedDate());
		  order.setDeliveryDate(orderBean.getDeliveryDate());
		  order.setLastActivityOnOrder(orderBean.getLastActivityOnOrder());
		  order.setNetRate(orderBean.getNetRate());
		  order.setCustomer(prepareCustomerModel((orderBean.getCustomer()!=null)?orderBean.getCustomer():(new CustomerBean())));
		  order.setDiscount(orderBean.getDiscount());
		  order.setOrderPayment(prepareOrderPaymentModel(orderBean.getOrderPayment()));
		  order.setOrderReturnOrRTO(prepareOrderRTOorReturnModel(orderBean.getOrderReturnOrRTO()));
		  order.setOrderTax(prepareOrderTaxModel(orderBean.getOrderTax()));
		  order.setTotalAmountRecieved(orderBean.getTotalAmountRecieved());
		  order.setQuantity(orderBean.getQuantity());
		  order.setPr(orderBean.getPr());
		  order.setNetSaleQuantity(orderBean.getNetSaleQuantity());
		 order.setUniqueItemId(orderBean.getUniqueItemId());
		  order.setPartnerCommission(orderBean.getPartnerCommission());
		  order.setSellerNote(orderBean.getSellerNote());
		  order.setPaymentType(orderBean.getPaymentType());
		  order.setFinalStatus(orderBean.getFinalStatus());
		  order.setOrderTimeline(orderBean.getOrderTimeline());
		  order.setReturnLimitCrossed(orderBean.getReturnLimitCrossed());
		  order.setrTOLimitCrossed(orderBean.getrTOLimitCrossed());
		  order.setSealNo(orderBean.getSealNo());
		  order.setPoPrice(orderBean.getPoPrice());
		  order.setGrossNetRate(orderBean.getGrossNetRate());
		  
		  return order;
		 }
	
	public static List<Order> prepareListofOrdersFromBean(List<OrderBean> orderBeans){
		  List<Order> orders = null;
		  if(orderBeans != null && !orderBeans.isEmpty()){
			  orders = new ArrayList<Order>();
		   Order order = null;
		   for(OrderBean orderBean : orderBeans){
			   order = new Order();
			   order.setAwbNum(orderBean.getAwbNum());
				 order.setGeOrderId(orderBean.getGeOrderId());
				  order.setOrderDate(orderBean.getOrderDate());
				  order.setPcName(orderBean.getPcName());
				  order.setStatus(orderBean.getStatus());
				  order.setOrderId(orderBean.getOrderId());
				  order.setPaymentDueDate(orderBean.getPaymentDueDate());
				  order.setProductSkuCode(orderBean.getProductSkuCode());
				  order.setChannelOrderID(orderBean.getChannelOrderID());
				  order.setInvoiceID(orderBean.getInvoiceID());
				  order.setSubOrderID(orderBean.getSubOrderID());
				  order.setPIreferenceNo(orderBean.getPIreferenceNo());
				  order.setLogisticPartner(orderBean.getLogisticPartner());
				  order.setOrderMRP(orderBean.getOrderMRP());
				  order.setOrderSP(orderBean.getOrderSP());
				  order.setShippingCharges(orderBean.getShippingCharges());
				  order.setShippedDate(orderBean.getShippedDate());
				  order.setDeliveryDate(orderBean.getDeliveryDate());
				  order.setLastActivityOnOrder(orderBean.getLastActivityOnOrder());
				  order.setNetRate(orderBean.getNetRate());
				  order.setCustomer(prepareCustomerModel((orderBean.getCustomer()!=null)?orderBean.getCustomer():(new CustomerBean())));
				  order.setDiscount(orderBean.getDiscount());
				  order.setOrderPayment(prepareOrderPaymentModel(orderBean.getOrderPayment()));
				  order.setOrderReturnOrRTO(prepareOrderRTOorReturnModel(orderBean.getOrderReturnOrRTO()));
				  order.setOrderTax(prepareOrderTaxModel(orderBean.getOrderTax()));
				  order.setTotalAmountRecieved(orderBean.getTotalAmountRecieved());
				  order.setQuantity(orderBean.getQuantity());
				  order.setPr(orderBean.getPr());
				  order.setGrossNetRate(orderBean.getGrossNetRate());
				  order.setNetSaleQuantity(orderBean.getNetSaleQuantity());
				  order.setUniqueItemId(orderBean.getUniqueItemId());
				  order.setPartnerCommission(orderBean.getPartnerCommission());
				  order.setSellerNote(orderBean.getSellerNote());
				  order.setPaymentType(orderBean.getPaymentType());
				  order.setFinalStatus(orderBean.getFinalStatus());
				  order.setOrderTimeline(orderBean.getOrderTimeline());
				  order.setReturnLimitCrossed(orderBean.getReturnLimitCrossed());
				  order.setrTOLimitCrossed(orderBean.getrTOLimitCrossed());
				  order.setSealNo(orderBean.getSealNo());
				  order.setPoPrice(orderBean.getPoPrice());
		    
			   orders.add(order);
		   }
		  }
		  return orders;
		 }
	
	public static List<OrderBean> prepareListofBean(List<Order> orders){
		  List<OrderBean> beans = null;
		  if(orders != null && !orders.isEmpty()){
		   beans = new ArrayList<OrderBean>();
		   OrderBean bean = null;
		   for(Order order : orders){
		    bean = new OrderBean();
		    bean.setAwbNum(order.getAwbNum());
		   bean.setGeOrderId(order.getGeOrderId());
		    bean.setOrderDate(order.getOrderDate());
		    bean.setPcName(order.getPcName());
		    bean.setStatus(order.getStatus());
		    bean.setOrderId(order.getOrderId());
		    bean.setPaymentDueDate(order.getPaymentDueDate());
		    bean.setProductSkuCode(order.getProductSkuCode());
		    bean.setChannelOrderID(order.getChannelOrderID());
		    bean.setInvoiceID(order.getInvoiceID());
		    bean.setSubOrderID(order.getSubOrderID());
		    bean.setPIreferenceNo(order.getPIreferenceNo());
		    bean.setLogisticPartner(order.getLogisticPartner());
		    bean.setOrderMRP(order.getOrderMRP());
		    bean.setOrderSP(order.getOrderSP());
		    bean.setShippingCharges(order.getShippingCharges());
		    bean.setShippedDate(order.getShippedDate());
		    bean.setDeliveryDate(order.getDeliveryDate());
		    bean.setLastActivityOnOrder(order.getLastActivityOnOrder());
		    bean.setNetRate(order.getNetRate());
		    bean.setCustomer(prepareCustomerBean((order.getCustomer()!=null)?order.getCustomer():(new Customer())));
		    bean.setDiscount(order.getDiscount());
		    bean.setOrderPayment(prepareOrderPaymentBean(order.getOrderPayment()));
		    bean.setOrderReturnOrRTO(prepareOrderRTOorReturnBean(order.getOrderReturnOrRTO()));
		    bean.setOrderTax(prepareOrderTaxBean(order.getOrderTax()));
		    bean.setTotalAmountRecieved(order.getTotalAmountRecieved());
		    bean.setQuantity(order.getQuantity());
		    bean.setPr(order.getPr());
		    bean.setGrossNetRate(order.getGrossNetRate()); 
		    bean.setNetSaleQuantity(order.getNetSaleQuantity());
		    bean.setUniqueItemId(order.getUniqueItemId());
		    bean.setPartnerCommission(order.getPartnerCommission());
		    bean.setSellerNote(order.getSellerNote());
		    bean.setPaymentType(order.getPaymentType());
		    bean.setFinalStatus(order.getFinalStatus());
		    //bean.setOrderTimeline(order.getOrderTimeline());
		    bean.setReturnLimitCrossed(order.getReturnLimitCrossed());
		    bean.setrTOLimitCrossed(order.getrTOLimitCrossed());
		    bean.setSealNo(order.getSealNo());
		    bean.setPoPrice(order.getPoPrice());
			  beans.add(bean);
		   }
		  }
		  return beans;
		 }
	
	 public static OrderBean prepareOrderBean(Order order){
		  OrderBean bean = new OrderBean();
		  bean.setAwbNum(order.getAwbNum());
		   bean.setGeOrderId(order.getGeOrderId());
		    bean.setOrderDate(order.getOrderDate());
		    bean.setPcName(order.getPcName());
		    bean.setStatus(order.getStatus());
		    bean.setOrderId(order.getOrderId());
		    bean.setPaymentDueDate(order.getPaymentDueDate());
		    bean.setProductSkuCode(order.getProductSkuCode());
		    bean.setChannelOrderID(order.getChannelOrderID());
		    bean.setInvoiceID(order.getInvoiceID());
		    bean.setSubOrderID(order.getSubOrderID());
		    bean.setPIreferenceNo(order.getPIreferenceNo());
		    bean.setLogisticPartner(order.getLogisticPartner());
		    bean.setOrderMRP(order.getOrderMRP());
		    bean.setOrderSP(order.getOrderSP());
		    bean.setShippingCharges(order.getShippingCharges());
		    bean.setShippedDate(order.getShippedDate());
		    bean.setDeliveryDate(order.getDeliveryDate());
		    bean.setLastActivityOnOrder(order.getLastActivityOnOrder());
		    bean.setNetRate(order.getNetRate());
		    bean.setCustomer(prepareCustomerBean((order.getCustomer()!=null)?order.getCustomer():(new Customer())));
		    bean.setDiscount(order.getDiscount());
		    bean.setOrderPayment(prepareOrderPaymentBean(order.getOrderPayment()));
		    bean.setOrderReturnOrRTO(prepareOrderRTOorReturnBean(order.getOrderReturnOrRTO()));
		    bean.setOrderTax(prepareOrderTaxBean(order.getOrderTax()));
		    bean.setTotalAmountRecieved(order.getTotalAmountRecieved());
		    bean.setQuantity(order.getQuantity());
		    bean.setPr(order.getPr());
		    bean.setGrossNetRate(order.getGrossNetRate());
		    bean.setNetSaleQuantity(order.getNetSaleQuantity());
		    bean.setUniqueItemId(order.getUniqueItemId());
		    bean.setPartnerCommission(order.getPartnerCommission());
		    bean.setSellerNote(order.getSellerNote());
		    bean.setPaymentType(order.getPaymentType());
		    bean.setFinalStatus(order.getFinalStatus());
		    bean.setOrderTimeline(order.getOrderTimeline());
		    bean.setReturnLimitCrossed(order.getReturnLimitCrossed());
		    bean.setrTOLimitCrossed(order.getrTOLimitCrossed());
		    bean.setSealNo(order.getSealNo());
		    bean.setPoPrice(order.getPoPrice());
		  return bean;
		 }
	
	public static Seller prepareSellerModel(SellerBean sellerBean){
		  Seller seller = new Seller();
		  
		  seller.setAddress(sellerBean.getAddress());
		  seller.setCompanyName(sellerBean.getCompanyName());
		  seller.setEmail(sellerBean.getEmail());
		  seller.setName(sellerBean.getName());
		 // seller.setOrders(prepareListofOrdersFromBean(sellerBean.getOrders()));
		  seller.setId(sellerBean.getId());
		  seller.setPassword(sellerBean.getPassword());
		  seller.setContactNo(sellerBean.getContactNo());
		  seller.setBrandName(sellerBean.getBrandName());
		  seller.setLogoUrl(sellerBean.getLogoUrl());
		  seller.setTinNumber(sellerBean.getTinNumber());
		  seller.setTanNumber(sellerBean.getTanNumber());
		 
		  return seller;
		 }
	
	
	public static List<SellerBean> prepareListofSellerBean(List<Seller> sellers){
		  List<SellerBean> beans = null;
		  if(sellers != null && !sellers.isEmpty()){
		   beans = new ArrayList<SellerBean>();
		   SellerBean bean = null;
		   for(Seller seller : sellers){
		    bean = new SellerBean();
		    bean.setAddress(seller.getAddress());
		    bean.setCompanyName(seller.getCompanyName());
		    bean.setEmail(seller.getEmail());
		    bean.setId(seller.getId());
		    //bean.setOrders(prepareListofBean(seller.getOrders()));
		    bean.setName(seller.getName());
		    bean.setPassword(seller.getPassword());
		    bean.setContactNo(seller.getContactNo());
		    bean.setBrandName(seller.getBrandName());
		    bean.setLogoUrl(seller.getLogoUrl());
		    bean.setTinNumber(seller.getTinNumber());
		    bean.setTanNumber(seller.getTanNumber());
		    beans.add(bean);
		    
		   }
		  }
		  return beans;
		 }
	
	 
	 public static SellerBean prepareSellerBean(Seller seller){
		  SellerBean bean = new SellerBean();
		  
		  bean.setAddress(seller.getAddress());
		    bean.setCompanyName(seller.getCompanyName());
		    bean.setEmail(seller.getEmail());
		   // bean.setOrders(prepareListofBean(seller.getOrders()));
		    bean.setName(seller.getName());
		    bean.setId(seller.getId());
		    bean.setPassword(seller.getPassword());
		    bean.setContactNo(seller.getContactNo());
		    bean.setBrandName(seller.getBrandName());
		    bean.setLogoUrl(seller.getLogoUrl());
		    bean.setTinNumber(seller.getTinNumber());
		    bean.setTanNumber(seller.getTanNumber());
		  return bean;
		 }
	 
	
	public static Partner preparePartnerModel(PartnerBean partnerBean){
		  
		  Partner partner=new Partner();
		  partner.setPcId(partnerBean.getPcId());
		  partner.setPcDesc(partnerBean.getPcDesc());
		  partner.setPcLogoUrl(partnerBean.getPcLogoUrl());
		  partner.setPcName(partnerBean.getPcName());
		  partner.setPaymentType(partnerBean.getPaymentType());
		  partner.setIsshippeddatecalc(partnerBean.isIsshippeddatecalc());
		  partner.setNoofdaysfromshippeddate(partnerBean.getNoofdaysfromshippeddate());
		  partner.setStartcycleday(partnerBean.getStartcycleday());
		  partner.setPaycycleduration(partnerBean.getPaycycleduration());
		  partner.setPaydaysfromstartday(partnerBean.getPaydaysfromstartday());
		  partner.setMaxReturnAcceptance(partnerBean.getMaxReturnAcceptance());
		  partner.setMaxRTOAcceptance(partnerBean.getMaxRTOAcceptance());
		  partner.setTaxcategory(partnerBean.getTaxcategory());
		  partner.setTaxrate(partnerBean.getTaxrate());
		  partner.setTdsApplicable(partnerBean.isTdsApplicable());
		  partner.setPaycyclefromshipordel(partnerBean.isPaycyclefromshipordel());
		  partner.setMonthlypaydate(partnerBean.getMonthlypaydate());
		  
		  return partner;
		 }
	
	
	
	
	
	public static List<PartnerBean> prepareListofPartnerBean(List<Partner> partners){
		  List<PartnerBean> beans = null;
		  if(partners != null && !partners.isEmpty()){
		   beans = new ArrayList<PartnerBean>();
		   PartnerBean bean = null;
		   for(Partner partner : partners){
		    bean = new PartnerBean();
		    bean.setPcId(partner.getPcId());
		    bean.setPcDesc(partner.getPcDesc());
		    bean.setPcLogoUrl(partner.getPcLogoUrl());
		    bean.setPcName(partner.getPcName());
		    bean.setPaymentType(partner.getPaymentType());
		    bean.setIsshippeddatecalc(partner.isIsshippeddatecalc());
		    bean.setNoofdaysfromshippeddate(partner.getNoofdaysfromshippeddate());
		    bean.setStartcycleday(partner.getStartcycleday());
		    bean.setPaycycleduration(partner.getPaycycleduration());
		    bean.setPaydaysfromstartday(partner.getPaydaysfromstartday());
		    bean.setMaxReturnAcceptance(partner.getMaxReturnAcceptance());
		    bean.setMaxRTOAcceptance(partner.getMaxRTOAcceptance());
		    bean.setTaxcategory(partner.getTaxcategory());
		    bean.setTaxrate(partner.getTaxrate());
		    bean.setTdsApplicable(partner.isTdsApplicable());
		    bean.setPaycyclefromshipordel(partner.isPaycyclefromshipordel());
		    bean.setMonthlypaydate(partner.getMonthlypaydate());
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }

	
	 
	 public static PartnerBean preparePartnerBean(Partner partner){
		 PartnerBean bean = new PartnerBean();
		 bean.setPcId(partner.getPcId());
		 bean.setPcDesc(partner.getPcDesc());
		 bean.setPcLogoUrl(partner.getPcLogoUrl());
		 bean.setPcName(partner.getPcName());
		 bean.setPaymentType(partner.getPaymentType());
		 bean.setIsshippeddatecalc(partner.isIsshippeddatecalc());
		 bean.setNoofdaysfromshippeddate(partner.getNoofdaysfromshippeddate());
		 bean.setStartcycleday(partner.getStartcycleday());
		 bean.setPaycycleduration(partner.getPaycycleduration());
		 bean.setPaydaysfromstartday(partner.getPaydaysfromstartday());
		 bean.setMaxReturnAcceptance(partner.getMaxReturnAcceptance());
	    bean.setMaxRTOAcceptance(partner.getMaxRTOAcceptance());
	    bean.setTaxcategory(partner.getTaxcategory());
	    bean.setTaxrate(partner.getTaxrate());
	    bean.setTdsApplicable(partner.isTdsApplicable());
	    bean.setPaycyclefromshipordel(partner.isPaycyclefromshipordel());
	    bean.setMonthlypaydate(partner.getMonthlypaydate());
		  return bean;
		 }
	
	 public static CategoryBean prepareCategoryBean(Category category){
		 CategoryBean bean = new CategoryBean();
		  
		 bean.setId(category.getCategoryId());
		    bean.setCatDescription(category.getCatDescription());
		    bean.setCatName(category.getCatName());
		    bean.setSubCategory(category.isSubCategory());
		    bean.setProductCount(category.getProductCount());
		    bean.setParentCatName(category.getParentCatName());
		    bean.setSubCategory(prepareListofCategoryBean(category.getSubCategory()));
		    bean.setCreatedOn(category.getCreatedOn());
		    bean.setOpeningStock(category.getOpeningStock());
		    bean.setSkuCount(category.getSkuCount());
		    bean.setOsUpdate(category.getOsUpdate());
		  return bean;
		 }
	 
	 public static Category prepareCategoryModel(CategoryBean categoryBean){
		 Category category = new Category();
		  category.setCategoryId(categoryBean.getId());
		  category.setCatDescription(categoryBean.getCatDescription());
		  category.setCatName(categoryBean.getCatName());
		  category.setSubCategory(categoryBean.isSubCategory());
		  category.setProductCount(categoryBean.getProductCount());
		  category.setParentCatName(categoryBean.getParentCatName());
		  category.setCreatedOn(categoryBean.getCreatedOn());
		  category.setOpeningStock(categoryBean.getOpeningStock());
		  category.setSkuCount(categoryBean.getSkuCount());
		  category.setOsUpdate(categoryBean.getOsUpdate());

		  return category;
		 }
	 
	 public static List<CategoryBean> prepareListofCategoryBean(List<Category> categories){
		  List<CategoryBean> beans = null;
		  if(categories != null && !categories.isEmpty()){
		   beans = new ArrayList<CategoryBean>();
		   CategoryBean bean = null;
		   for(Category category : categories){
		    bean = new CategoryBean();
		    bean.setId(category.getCategoryId());
		    bean.setCatDescription(category.getCatDescription());
		    bean.setCatName(category.getCatName());
		    bean.setSubCategory(category.isSubCategory());
		    bean.setProductCount(category.getProductCount());
		    bean.setParentCatName(category.getParentCatName());
		    bean.setCreatedOn(category.getCreatedOn());
		    bean.setOpeningStock(category.getOpeningStock());
		    bean.setSkuCount(category.getSkuCount());
		    bean.setOsUpdate(category.getOsUpdate());

		    
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 public static ProductBean prepareProductBean(Product product){
		 ProductBean bean = new ProductBean();
		  
		 bean.setCategoryName(product.getCategoryName());
		bean.setProductDate(product.getProductDate());
		bean.setProductId(product.getProductId());
		bean.setProductName(product.getProductName());
		bean.setProductPrice(product.getProductPrice());
		bean.setProductSkuCode(product.getProductSkuCode());
		bean.setQuantity(product.getQuantity());
		bean.setThreholdLimit(product.getThreholdLimit());
		bean.setChannelSKU(product.getChannelSKU());
		  return bean;
		 }
	 
	 public static Product prepareProductModel(ProductBean product){
		 Product bean = new Product();
			bean.setProductDate(product.getProductDate());
			bean.setProductId(product.getProductId());
			bean.setProductName(product.getProductName());
			bean.setProductPrice(product.getProductPrice());
			bean.setProductSkuCode(product.getProductSkuCode());
			bean.setQuantity(product.getQuantity());
			bean.setCategoryName(product.getCategoryName());
			bean.setThreholdLimit(product.getThreholdLimit());
			bean.setChannelSKU(product.getChannelSKU());
		  
		  return bean;
		 }
	 
	 public static List<ProductBean> prepareListofProductBean(List<Product> products){
		  List<ProductBean> beans = null;
		  if(products != null && !products.isEmpty()){
		   beans = new ArrayList<ProductBean>();
		   ProductBean bean = null;
		   for(Product product : products){
		    bean = new ProductBean();
		    bean.setCategoryName(product.getCategoryName());
			bean.setProductDate(product.getProductDate());
			bean.setProductId(product.getProductId());
			bean.setProductName(product.getProductName());
			bean.setProductPrice(product.getProductPrice());
			bean.setProductSkuCode(product.getProductSkuCode());
			bean.setQuantity(product.getQuantity());
			bean.setThreholdLimit(product.getThreholdLimit());
			bean.setChannelSKU(product.getChannelSKU());
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 
	 public static Customer prepareCustomerModel(CustomerBean customerBean){
		  
		 Customer customer=new Customer();
		 if(customerBean!=null)
		 {
		  customer.setCustomerId(customerBean.getCustomerId());
		  customer.setCustomerAddress(customerBean.getCustomerAddress());
		  customer.setCustomerCity(customerBean.getCustomerCity());
		  customer.setCustomerEmail(customerBean.getCustomerEmail());
		  customer.setCustomerName(customerBean.getCustomerName());
		  customer.setCustomerPhnNo(customerBean.getCustomerPhnNo());
		  
		 }
		  
		  return customer;
		 }
	 
	 public static CustomerBean prepareCustomerBean(Customer customer){
		  
		 CustomerBean customerBean=new CustomerBean();
		 if(customer!=null)
		 {
		 customerBean.setCustomerId(customer.getCustomerId());
		 customerBean.setCustomerAddress(customer.getCustomerAddress());
		 customerBean.setCustomerCity(customer.getCustomerCity());
		 customerBean.setCustomerEmail(customer.getCustomerEmail());
		 customerBean.setCustomerName(customer.getCustomerName());
		 customerBean.setCustomerPhnNo(customer.getCustomerPhnNo());
		 }
		  
		  return customerBean;
		 }
	 
	 public static ExpenseCategoryBean prepareExpenseCategoryBean(ExpenseCategory category){
		 ExpenseCategoryBean bean = new ExpenseCategoryBean();
		  
		 bean.setExpcategoryId(category.getExpcategoryId());
		 bean.setCreatedOn(category.getCreatedOn());
		 bean.setExpcatDescription(category.getExpcatDescription());
		 bean.setExpcatName(category.getExpcatName());
		 bean.setExpenses(prepareListofExpenseBean(category.getExpenses()));
		   
		  return bean;
		 }
	 
	 public static ExpenseCategory prepareExpenseCategoryModel(ExpenseCategoryBean categoryBean){
		 ExpenseCategory category = new ExpenseCategory();
		 category.setExpcategoryId(categoryBean.getExpcategoryId());
		 category.setCreatedOn(categoryBean.getCreatedOn());
		 category.setExpcatDescription(categoryBean.getExpcatDescription());
		 category.setExpcatName(categoryBean.getExpcatName());
		 //category.setExpenses(categoryBean.getExpenses());
		  return category;
		 }
	 
	 public static List<ExpenseCategoryBean> prepareListofExpenseCategoryBean(List<ExpenseCategory> categories){
		  List<ExpenseCategoryBean> beans = null;
		  if(categories != null && !categories.isEmpty()){
		   beans = new ArrayList<ExpenseCategoryBean>();
		   ExpenseCategoryBean bean = null;
		   for(ExpenseCategory category : categories){
		    bean = new ExpenseCategoryBean();
		    bean.setExpcategoryId(category.getExpcategoryId());
			 bean.setCreatedOn(category.getCreatedOn());
			 bean.setExpcatDescription(category.getExpcatDescription());
			 bean.setExpcatName(category.getExpcatName());
			bean.setExpenses(prepareListofExpenseBean(category.getExpenses()));
		    
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 
	 public static ExpenseBean prepareExpenseBean(Expenses expense){
		 ExpenseBean bean = new ExpenseBean();
		bean.setAmount(expense.getAmount());
		bean.setCreatedOn(expense.getCreatedOn());
		//bean.setExpenseCategory(prepareExpenseCategoryBean(expense.getExpenseCategory()));
		bean.setExpenseCatName(expense.getExpenseCatName());
		bean.setExpenseDescription(expense.getExpenseDescription());
		bean.setExpenseId(expense.getExpenseId());
		bean.setExpenseName(expense.getExpenseName());
		bean.setSellerId(expense.getSellerId());
		bean.setExpenditureByperson(expense.getExpenditureByperson());
		bean.setPaidTo(expense.getPaidTo());
		
		  return bean;
		 }
	 
	 public static Expenses prepareExpenseModel(ExpenseBean bean){
		 Expenses expense = new Expenses();
		 expense.setAmount(bean.getAmount());
		 expense.setCreatedOn(bean.getCreatedOn());
		 //expense.setExpenseCategory(prepareExpenseCategoryModel(bean.getExpenseCategory()));
		 expense.setExpenseCatName(bean.getExpenseCatName());
		 expense.setExpenseDescription(bean.getExpenseDescription());
		 expense.setExpenseId(bean.getExpenseId());
		 expense.setExpenseName(bean.getExpenseName());
		 expense.setSellerId(bean.getSellerId());
		 expense.setExpenditureByperson(bean.getExpenditureByperson());
		 expense.setPaidTo(bean.getPaidTo());
		  return expense;
		 }
	 
	 public static List<ExpenseBean> prepareListofExpenseBean(List<Expenses> expenses){
		  List<ExpenseBean> beans = null;
		  if(expenses != null && !expenses.isEmpty()){
		   beans = new ArrayList<ExpenseBean>();
		   ExpenseBean bean = null;
		   for(Expenses expense : expenses){
			   bean=new ExpenseBean();
			   bean.setAmount(expense.getAmount());
				bean.setCreatedOn(expense.getCreatedOn());
				//bean.setExpenseCategory(prepareExpenseCategoryBean(expense.getExpenseCategory()));
				bean.setExpenseCatName(expense.getExpenseCatName());
				bean.setExpenseDescription(expense.getExpenseDescription());
				bean.setExpenseId(expense.getExpenseId());
				bean.setExpenseName(expense.getExpenseName());
				bean.setSellerId(expense.getSellerId());
				bean.setExpenditureByperson(expense.getExpenditureByperson());
				bean.setPaidTo(expense.getPaidTo());
		    
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 
	 public static OrderTax prepareOrderTaxModel(OrderTaxBean bean){
		 OrderTax orderTax =new OrderTax();
		 if(bean!=null)
		 {
		 orderTax.setNetTax(bean.getNetTax());
		 orderTax.setNetTds(bean.getNetTds());
		 orderTax.setTax(bean.getTax());
		 orderTax.setTaxCategtory(bean.getTaxCategtory());
		 orderTax.setTaxdepositdate(bean.getTaxdepositdate());
		 orderTax.setTaxId(bean.getTaxId());
		 orderTax.setTaxperiod(bean.getTaxperiod());
		 orderTax.setTaxRate(bean.getTaxRate());
		 orderTax.setTdsdepositedate(bean.getTdsdepositedate());
		 orderTax.setTdsperiod(bean.getTdsperiod());
		 orderTax.setTdsToDeduct(bean.getTdsToDeduct());
		 orderTax.setTdsToReturn(bean.getTdsToReturn());
		 orderTax.setTotalpositivevalue(bean.getTotalpositivevalue());
		 orderTax.setToxToReturn(bean.getToxToReturn());
		 }
		 return orderTax;
		 }
	 
	 public static OrderTaxBean prepareOrderTaxBean(OrderTax bean){
		 OrderTaxBean orderTax =new OrderTaxBean();
		 if(bean!=null)
		 {
		 orderTax.setNetTax(bean.getNetTax());
		 orderTax.setNetTds(bean.getNetTds());
		 orderTax.setTax(bean.getTax());
		 orderTax.setTaxCategtory(bean.getTaxCategtory());
		 orderTax.setTaxdepositdate(bean.getTaxdepositdate());
		 orderTax.setTaxId(bean.getTaxId());
		 orderTax.setTaxperiod(bean.getTaxperiod());
		 orderTax.setTaxRate(bean.getTaxRate());
		 orderTax.setTdsdepositedate(bean.getTdsdepositedate());
		 orderTax.setTdsperiod(bean.getTdsperiod());
		 orderTax.setTdsToDeduct(bean.getTdsToDeduct());
		 orderTax.setTdsToReturn(bean.getTdsToReturn());
		 orderTax.setTotalpositivevalue(bean.getTotalpositivevalue());
		 orderTax.setToxToReturn(bean.getToxToReturn());
		 }
		 return orderTax;
		 }

	 public static OrderPayment prepareOrderPaymentModel(OrderPaymentBean bean){
		 OrderPayment orderPayment =new OrderPayment();
		 if(bean!=null)
		 {
		 orderPayment.setDateofPayment(bean.getDateofPayment());
		 orderPayment.setNegativeAmount(bean.getNegativeAmount());
		 orderPayment.setPaymentId(bean.getOrderpayId());
		 orderPayment.setPaymentdesc(bean.getPaymentdesc());
		 orderPayment.setPaymentUploadId(bean.getPaymentUploadId());
		 orderPayment.setPositiveAmount(bean.getPositiveamout());
		 orderPayment.setUploadDate(bean.getUploadDate());
		 orderPayment.setActualrecived2(bean.getActualrecived2());
		 orderPayment.setNetPaymentResult(bean.getNetPaymentResult());
		 orderPayment.setPaymentCycle(bean.getPaymentCycle());
		 orderPayment.setPaymentDifference(bean.getPaymentDifference());
		 orderPayment.setPaymentCycleStart(bean.getPaymentCycleStart());
		 orderPayment.setPaymentCycleEnd(bean.getPaymentCycleEnd());
		
		}
		 return orderPayment;
		 }
	 
	 public static OrderPaymentBean prepareOrderPaymentBean(OrderPayment bean){
		 OrderPaymentBean orderPayment =new OrderPaymentBean();
		 if(bean!=null)
		 {
			 orderPayment.setDateofPayment(bean.getDateofPayment());
			 orderPayment.setNegativeAmount(bean.getNegativeAmount());
			 orderPayment.setOrderpayId(bean.getPaymentId());
			 orderPayment.setPaymentdesc(bean.getPaymentdesc());
			 orderPayment.setPaymentUploadId(bean.getPaymentUploadId());
			 orderPayment.setPositiveamout(bean.getPositiveAmount());
			 orderPayment.setUploadDate(bean.getUploadDate());
			 orderPayment.setActualrecived2(bean.getActualrecived2());
			 orderPayment.setNetPaymentResult(bean.getNetPaymentResult());
			 orderPayment.setPaymentCycle(bean.getPaymentCycle());
			 orderPayment.setPaymentDifference(bean.getPaymentDifference());
			 orderPayment.setPaymentCycleStart(bean.getPaymentCycleStart());
			 orderPayment.setPaymentCycleEnd(bean.getPaymentCycleEnd());
		 }
		 return orderPayment;
		 }
	 
	 public static OrderRTOorReturn prepareOrderRTOorReturnModel(OrderRTOorReturnBean bean){
		 OrderRTOorReturn orderReturn =new OrderRTOorReturn();
		 if(bean!=null)
		 {
		 orderReturn.setReturnDate(bean.getReturnDate());
		 orderReturn.setReturnId(bean.getReturnId());
		 orderReturn.setReturnOrRTOCharges(bean.getReturnOrRTOCharges());
		 orderReturn.setReturnOrRTOId(bean.getReturnOrRTOId());
		 orderReturn.setReturnorrtoQty(bean.getReturnorrtoQty());
		 orderReturn.setReturnOrRTOreason(bean.getReturnOrRTOreason());
		 orderReturn.setReturnUploadDate(bean.getReturnUploadDate());
		 orderReturn.setReturnOrRTOChargestoBeDeducted(bean.getReturnOrRTOChargestoBeDeducted());
		 orderReturn.setReturnOrRTOstatus(bean.getReturnOrRTOstatus());
		 }
		 return orderReturn;
		 }
	 
	 public static OrderRTOorReturnBean prepareOrderRTOorReturnBean(OrderRTOorReturn bean){
		 OrderRTOorReturnBean orderReturn =new OrderRTOorReturnBean();
		 if(bean!=null)
		 {
		 orderReturn.setReturnDate(bean.getReturnDate());
		 orderReturn.setReturnId(bean.getReturnId());
		 orderReturn.setReturnOrRTOCharges(bean.getReturnOrRTOCharges());
		 orderReturn.setReturnOrRTOId(bean.getReturnOrRTOId());
		 orderReturn.setReturnorrtoQty(bean.getReturnorrtoQty());
		 orderReturn.setReturnOrRTOreason(bean.getReturnOrRTOreason());
		 orderReturn.setReturnUploadDate(bean.getReturnUploadDate());
		 orderReturn.setReturnOrRTOChargestoBeDeducted(bean.getReturnOrRTOChargestoBeDeducted());
		 orderReturn.setReturnOrRTOstatus(bean.getReturnOrRTOstatus());
		 }
		 return orderReturn;
		 }
	 
	 public static PaymentUploadBean preparePaymentUploadBean(PaymentUpload payUpload){
		 PaymentUploadBean bean = new PaymentUploadBean();
		bean.setUploadStatus(payUpload.getUploadStatus());
		bean.setNetRecievedAmount(payUpload.getNetRecievedAmount());
		bean.setTotalpositivevalue(payUpload.getTotalpositivevalue());
		bean.setUploadDate(payUpload.getUploadDate());
		bean.setUploadId(payUpload.getUploadId());
		bean.setUploadDesc(payUpload.getUploadDesc());
		bean.setOrders(ConverterClass.prepareListofBean(payUpload.getOrders()));
		
		  return bean;
		 }
	 
	 public static PaymentUpload preparePaymentUploadModel(PaymentUploadBean bean){
		 PaymentUpload payment = new PaymentUpload();
		 payment.setUploadStatus(bean.getUploadStatus());
		 payment.setNetRecievedAmount(bean.getNetRecievedAmount());
		 payment.setTotalpositivevalue(bean.getTotalpositivevalue());
		 payment.setUploadDate(bean.getUploadDate());
		 payment.setUploadId(bean.getUploadId());
		 payment.setUploadDesc(bean.getUploadDesc());
		 payment.setOrders(ConverterClass.prepareListofOrdersFromBean(bean.getOrders()));
			
		  return payment;
		 }
	 
	 public static List<PaymentUploadBean> prepareListofPaymentUploadBean(List<PaymentUpload> payment){
		  List<PaymentUploadBean> beans = null;
		  if(payment != null && !payment.isEmpty()){
		   beans = new ArrayList<PaymentUploadBean>();
		   PaymentUploadBean bean = null;
		   for(PaymentUpload payUpload : payment){
			   bean=new PaymentUploadBean();
			   bean.setUploadStatus(payUpload.getUploadStatus());
				bean.setNetRecievedAmount(payUpload.getNetRecievedAmount());
				bean.setTotalpositivevalue(payUpload.getTotalpositivevalue());
				bean.setUploadDate(payUpload.getUploadDate());
				bean.setUploadId(payUpload.getUploadId());
				bean.setUploadDesc(payUpload.getUploadDesc());
				//bean.setOrders(ConverterClass.prepareListofBean(payUpload.getOrders()));
		    
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 
	 public static TaxDetailBean prepareTaxDetailBean(TaxDetail taxDetail){
		 TaxDetailBean bean = new TaxDetailBean();
		 bean.setBalanceRemaining(taxDetail.getBalanceRemaining());
		 bean.setDateOfPayment(taxDetail.getDateOfPayment());
		 bean.setDescription(taxDetail.getDescription());
		 bean.setPaidAmount(taxDetail.getPaidAmount());
		 bean.setParticular(taxDetail.getParticular());
		 bean.setStatus(taxDetail.getStatus());
		 bean.setTaxId(taxDetail.getTaxId());
		 bean.setTaxortds(taxDetail.getTaxortds());
		 bean.setTaxortdsCycle(taxDetail.getTaxortdsCycle());
		 bean.setUploadDate(taxDetail.getUploadDate());
		
		  return bean;
		 }
	 
	 public static TaxDetail prepareTaxDetailModel(TaxDetailBean taxDetail){
		 TaxDetail bean = new TaxDetail();
		 bean.setBalanceRemaining(taxDetail.getBalanceRemaining());
		 bean.setDateOfPayment(taxDetail.getDateOfPayment());
		 bean.setDescription(taxDetail.getDescription());
		 bean.setPaidAmount(taxDetail.getPaidAmount());
		 bean.setParticular(taxDetail.getParticular());
		 bean.setStatus(taxDetail.getStatus());
		 bean.setTaxId(taxDetail.getTaxId());
		 bean.setTaxortds(taxDetail.getTaxortds());
		 bean.setTaxortdsCycle(taxDetail.getTaxortdsCycle());
		 bean.setUploadDate(taxDetail.getUploadDate());
			
		  return bean;
		 }
	 
	 public static List<TaxDetailBean> prepareListofTaxDetailBean(List<TaxDetail> taxDetaillist){
		  List<TaxDetailBean> beans = null;
		  if(taxDetaillist != null && !taxDetaillist.isEmpty()){
		   beans = new ArrayList<TaxDetailBean>();
		   TaxDetailBean bean = null;
		   for(TaxDetail taxDetail : taxDetaillist){
			   bean=new TaxDetailBean();
			   bean.setBalanceRemaining(taxDetail.getBalanceRemaining());
				 bean.setDateOfPayment(taxDetail.getDateOfPayment());
				 bean.setDescription(taxDetail.getDescription());
				 bean.setPaidAmount(taxDetail.getPaidAmount());
				 bean.setParticular(taxDetail.getParticular());
				 bean.setStatus(taxDetail.getStatus());
				 bean.setTaxId(taxDetail.getTaxId());
				 bean.setTaxortds(taxDetail.getTaxortds());
				 bean.setTaxortdsCycle(taxDetail.getTaxortdsCycle());
				 bean.setUploadDate(taxDetail.getUploadDate());
					
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 
	 public static ManualChargesBean prepareManualChargesBean(ManualCharges manualCharges){
		 ManualChargesBean bean = new ManualChargesBean();
		 bean.setChargesCategory(manualCharges.getChargesCategory());
		 bean.setChargesDesc(manualCharges.getChargesDesc());
		 bean.setDateOfPayment(manualCharges.getDateOfPayment());
		 bean.setMcId(manualCharges.getMcId());
		 bean.setPaidAmount(manualCharges.getPaidAmount());
		 bean.setParticular(manualCharges.getParticular());
		 bean.setPartner(manualCharges.getPartner());
		 bean.setPaymentCycle(manualCharges.getPaymentCycle());
		 bean.setUploadDate(manualCharges.getUploadDate());
		
		  return bean;
		 }
	 
	 public static ManualCharges prepareManualChargesModel(ManualChargesBean manualCharges){
		 ManualCharges bean = new ManualCharges();
		 bean.setChargesCategory(manualCharges.getChargesCategory());
		 bean.setChargesDesc(manualCharges.getChargesDesc());
		 bean.setDateOfPayment(manualCharges.getDateOfPayment());
		 bean.setMcId(manualCharges.getMcId());
		 bean.setPaidAmount(manualCharges.getPaidAmount());
		 bean.setParticular(manualCharges.getParticular());
		 bean.setPartner(manualCharges.getPartner());
		 bean.setPaymentCycle(manualCharges.getPaymentCycle());
		 bean.setUploadDate(manualCharges.getUploadDate());
			
		  return bean;
		 }
	 
	 public static List<ManualChargesBean> prepareListofManualChargesBean(List<ManualCharges> manualChargeslist){
		  List<ManualChargesBean> beans = null;
		  if(manualChargeslist != null && !manualChargeslist.isEmpty()){
		   beans = new ArrayList<ManualChargesBean>();
		   ManualChargesBean bean = null;
		   for(ManualCharges manualCharges : manualChargeslist){
			   bean=new ManualChargesBean();
			   bean.setChargesCategory(manualCharges.getChargesCategory());
				 bean.setChargesDesc(manualCharges.getChargesDesc());
				 bean.setDateOfPayment(manualCharges.getDateOfPayment());
				 bean.setMcId(manualCharges.getMcId());
				 bean.setPaidAmount(manualCharges.getPaidAmount());
				 bean.setParticular(manualCharges.getParticular());
				 bean.setPartner(manualCharges.getPartner());
				 bean.setPaymentCycle(manualCharges.getPaymentCycle());
				 bean.setUploadDate(manualCharges.getUploadDate());
					
		    beans.add(bean);
		   }
		  }
		  return beans;
		 }
	 
	 public static TaxCategoryBean prepareTaxCategoryBean(TaxCategory category){
		 TaxCategoryBean bean = new TaxCategoryBean();
		  
		bean.setPartner(category.getPartner());
		bean.setTaxCatDescription(category.getTaxCatDescription());
		bean.setTaxCatId(category.getTaxCatId());
		bean.setTaxCatName(category.getTaxCatName());
		bean.setUploadDate(category.getUploadDate());
		bean.setTaxPercent(category.getTaxPercent());
		  return bean;
		 }
	 
	 public static TaxCategory prepareTaxCategoryModel(TaxCategoryBean bean){
		 TaxCategory category = new TaxCategory();
			
		 category.setPartner(bean.getPartner());
		 category.setTaxCatDescription(bean.getTaxCatDescription());
		 category.setTaxCatId(bean.getTaxCatId());
		 category.setTaxCatName(bean.getTaxCatName());
		 category.setUploadDate(bean.getUploadDate());
		 category.setTaxPercent(bean.getTaxPercent());
			  return category;
		
		 }
	 
	 public static List<TaxCategoryBean> prepareListofTaxCategoryBean(List<TaxCategory> categories){
		  List<TaxCategoryBean> beans = null;
		  if(categories != null && !categories.isEmpty()){
		   beans = new ArrayList<TaxCategoryBean>();
		   TaxCategoryBean bean = null;
		   for(TaxCategory category : categories){
		    bean = new TaxCategoryBean();
			bean.setPartner(category.getPartner());
			bean.setTaxCatDescription(category.getTaxCatDescription());
			bean.setTaxCatId(category.getTaxCatId());
			bean.setTaxCatName(category.getTaxCatName());
			bean.setUploadDate(category.getUploadDate());
			bean.setTaxPercent(category.getTaxPercent());
		    
		    beans.add(bean);
		   }
		  }
		  return beans;
		 } 
	 
}
