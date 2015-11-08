package com.goeasy.helper;

import java.util.ArrayList;

public class GlobalConstant {
	
	public final ArrayList<String> orderList = new ArrayList<String>(){
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			{
			   add("ChannelOrderID");
			   add("OrderRecievedDate");
			   add("SkUCode");
			   add("Partner");
			   add("Customer Name");
			   add("Payment Type");
			   add("AWB No.");
			   add("InvoiceID");
			   add("subOrderID");
			   add("PIreferenceNo");
			   add("Logistic Partner");
			   add("Order MRP");
			   add("Order SP");
			   add("Shipping Charges");
			   add("Shipped Date");
			   add("Delivery Date");
			   add("Quantity");
			   add("Net Rate");
			   add("Customer Email");
			   add("Customer Phone No");
			   add("Customer City");
			   add("Customer Address");
			   add("Tax Category");
			   add("Seller Notes");
			  
			   }};



public final ArrayList<String> productHeaderList = new ArrayList<String>(){
	/**
 * 
 */
private static final long serialVersionUID = 1L;

	{
	   add("ProductName");
	   add("SkUCode");
	   add("Category");
	   add("ProductPrice");
	   add("Quantity");
	   add("Threshold Limit");
	   add("ChanelSKU(Separated by ;)");
	  
	   }
	};
	
	
public final ArrayList<String> paymentHeaderList = new ArrayList<String>(){
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		{
		   add("ChannelOrderId");
		   add("InvoiceId");
		   add("SkUCode");
		   add("Recieved Amount");
		   add("Negative Charges");
		   add("Payment Date");
		   		  
		   }
		};
		

public final ArrayList<String> inventoryHeaderList = new ArrayList<String>(){
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			{
			   add("ProductName");
			   add("SkUCode");
			   add("CurrentQuantity");
			   add("Quantity to Add");
			   add("Quantity to Substract");
			   		  
			   }
			};
			
public final ArrayList<String> expenseHeaderList = new ArrayList<String>(){
				/**
			 * 
			 */
	private static final long serialVersionUID = 1L;

		{
		   add("Name");
		   add("Description");
		   add("Expense Category");
		   add("Expense Amount");
		   add("Expenditure By");
		   add("Paid To");
		   		  
		   }
		};

}


