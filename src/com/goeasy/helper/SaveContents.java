package com.goeasy.helper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.goeasy.bean.CustomerBean;
import com.goeasy.bean.DebitNoteBean;
import com.goeasy.bean.ExpenseBean;
import com.goeasy.bean.OrderBean;
import com.goeasy.bean.OrderTaxBean;
import com.goeasy.bean.PoPaymentBean;
import com.goeasy.bean.ProductBean;
import com.goeasy.model.Category;
import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.Partner;
import com.goeasy.model.PaymentUpload;
import com.goeasy.model.Product;
import com.goeasy.model.TaxCategory;
import com.goeasy.service.CategoryService;
import com.goeasy.service.ExpenseService;
import com.goeasy.service.OrderService;
import com.goeasy.service.PartnerService;
import com.goeasy.service.PaymentUploadService;
import com.goeasy.service.ProductService;
import com.goeasy.service.TaxDetailService;



@Service("saveContents")
@Transactional
public class SaveContents {
    private static final Logger logger = LoggerFactory.getLogger(SaveContents.class);
     /*@Resource(name="sessionFactory")
     private SessionFactory sessionFactory;*/
     
     
     @Autowired
     private OrderService orderService;
     @Autowired
     private ProductService productService;
     @Autowired
     private PaymentUploadService paymentUploadService;
     @Autowired
     private CategoryService categoryService;
     @Autowired
     private ExpenseService expenseService;
     @Autowired
     private PartnerService partnerService;
     @Autowired
     private TaxDetailService taxDetailService;
   
     private final SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
 	
    public Map<String ,OrderBean> saveOrderContents(MultipartFile file,int sellerId)throws IOException{
    	

    	HSSFRow entry;
    	Integer noOfEntries=1;
    	boolean validaterow=true;
    	Map<String ,OrderBean> returnOrderMap=new HashMap<>();
    	OrderBean order=null;
    	StringBuffer errorMessage=null;
    	CustomerBean customerBean=null;
    	OrderTaxBean otb=null;
    	try{
    		System.out.println("Inside save content -->");

    		HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
    		HSSFSheet worksheet = offices.getSheetAt(0);
    		while(worksheet.getRow(noOfEntries)!=null){
    			noOfEntries++;
    		}
    		logger.info(noOfEntries.toString());
    		System.out.println("After getting no of rows"+noOfEntries);
    		for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){

    			entry=worksheet.getRow(rowIndex);
    			/*if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
    			{
    				validaterow=true;
    			}*/
    			errorMessage=new StringBuffer("Row : "+(rowIndex-2));
    			System.out.println(entry.getCell(0).toString());
    			System.out.println(entry.getCell(1).getDateCellValue());
    			System.out.println(entry.getCell(2).toString());
    			System.out.println(entry.getCell(3).toString());
    			System.out.println(entry.getCell(4).toString());
    			order=new OrderBean();
				customerBean=new CustomerBean();
				otb=new OrderTaxBean();
    			if(entry.getCell(0)!=null&&entry.getCell(0).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				List<Order> onj=orderService.findOrders("channelOrderID", entry.getCell(0).toString(), sellerId);
    				if(onj==null||onj.size()==0)
    				{
    					order.setChannelOrderID(entry.getCell(0).toString());
    				}
    				else
    				{
    					order.setChannelOrderID(entry.getCell(0).toString());
    					errorMessage.append(" Channel OrderId is already present;");
    					validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Channel OrderId is null ");
					validaterow=false;
    			}
    			if(entry.getCell(1)!=null&&entry.getCell(1).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				if (HSSFDateUtil.isCellDateFormatted(entry.getCell(1))) 
    				{
    				order.setOrderDate(entry.getCell(1).getDateCellValue());
    				}
    				else
    				{
    					errorMessage.append(" Order Recieved Date formate is wrong ,enter mm/dd/yyyy,");
    					validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Order Recieved Date is null;");
    				validaterow=false;
    			}
    			if(entry.getCell(2)!=null&&entry.getCell(2).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				Product product=productService.getProduct(entry.getCell(2).toString(), sellerId);
    				if(product==null)
    				{
    					order.setProductSkuCode(entry.getCell(2).toString());
    					errorMessage.append(" Product SKU does not exist;");
    					validaterow=false;
    				}
    				else {
    					order.setProductSkuCode(entry.getCell(2).toString());
					}
    				
    			}else
    			{
    				errorMessage.append(" Product SKU is null;");
					validaterow=false;
    			}
    			if(entry.getCell(3)!=null&&entry.getCell(3).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				Partner partner=partnerService.getPartner(entry.getCell(3).toString(), sellerId);
    				if(partner!=null)
    				order.setPcName(entry.getCell(3).toString());
    				else
    				{
    					order.setPcName(entry.getCell(3).toString());
    					errorMessage.append(" Partner does not exist;");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Partner Name is null;");
					validaterow=false;
    			}
    			if(entry.getCell(4)!=null&&entry.getCell(4).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				customerBean.setCustomerName(entry.getCell(4).toString());
    			}
    			if(entry.getCell(5)!=null&&entry.getCell(5).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				if(entry.getCell(5).toString().equalsIgnoreCase("COD")||entry.getCell(5).toString().equalsIgnoreCase("Prepaid"))
    				order.setPaymentType(entry.getCell(5).toString());
    				else
    				{
    					order.setPaymentType(entry.getCell(5).toString());
    					errorMessage.append(" Payment type should be COD or Prepaid;");
        				validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Payment type is null;");
    				validaterow=false;
    			}
    			if(entry.getCell(6)!=null&&entry.getCell(6).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				order.setAwbNum(entry.getCell(6).toString());
    			}else
    			{
    				errorMessage.append(" AWBNUM is null;");
    				validaterow=false;
    			}
    			if(entry.getCell(7)!=null&&entry.getCell(7).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				order.setInvoiceID(entry.getCell(7).toString());
    			}else
    			{
    				errorMessage.append(" Invoice ID is null;");
    				validaterow=false;
    			}
    			if(entry.getCell(8)!=null&&entry.getCell(8).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				order.setSubOrderID(entry.getCell(8).toString());
    			}
    			if(entry.getCell(9)!=null&&entry.getCell(9).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				order.setPIreferenceNo(entry.getCell(9).toString());
    			}
    			if(entry.getCell(10)!=null&&entry.getCell(10).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				order.setLogisticPartner(entry.getCell(10).toString());
    			}
    			if(entry.getCell(11)!=null&&entry.getCell(11).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				try
    				{
    				order.setOrderMRP(Double.parseDouble(entry.getCell(11).toString()));
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Order MRP should be a number ");
        				validaterow=false;	
    				}
    			}else
    			{
    				errorMessage.append(" Order MRP is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(12)!=null&&entry.getCell(12).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				try
    				{
    					order.setOrderSP(Double.parseDouble(entry.getCell(12).toString()));
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Order SP should be a number ");
        				validaterow=false;	
    				}
    				
    			}else
    			{
    				errorMessage.append(" Order SP is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(13)!=null&&entry.getCell(13).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				try
    				{
    				order.setShippingCharges(Double.parseDouble(entry.getCell(13).toString()));
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Shipping Charges should be number ");
        				validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Shipping Charges is null ");
    				validaterow=false;
    			}
    			
    			if(entry.getCell(14)!=null&&entry.getCell(14).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				if (HSSFDateUtil.isCellDateFormatted(entry.getCell(14))) 
    				{
    					order.setShippedDate(entry.getCell(14).getDateCellValue());
    				}
    				else
    				{
    					errorMessage.append(" Shipped Date formate is wrong ,enter mm/dd/yyyy,");
    					validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Shipping Date is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(15)!=null&&entry.getCell(15).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				if (HSSFDateUtil.isCellDateFormatted(entry.getCell(15))) 
    				{
    					order.setDeliveryDate(entry.getCell(15).getDateCellValue());
    				}
    				else
    				{
    					errorMessage.append(" Delivery Date formate is wrong ,enter mm/dd/yyyy,");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Delivery Date is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(16)!=null&&entry.getCell(16).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				try
					{
    				if((int)Float.parseFloat(entry.getCell(16).toString())!=0)
    				{
    					
    						order.setQuantity((int)Float.parseFloat(entry.getCell(16).toString()));
    					}
    				
    				else
    				{
    					errorMessage.append(" Quantity can not be 0 ");
    					validaterow=false;
    				}
    				}
	    			catch(NumberFormatException e)
	    			{
	    				errorMessage.append(" Quantity should be a number ");
    					validaterow=false;
	    			}
    		}else
    			{
    				errorMessage.append(" Quantity can not be null ");
					validaterow=false;
    			}
    			if(entry.getCell(17)!=null&&entry.getCell(17).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				try
    				{
    					order.setGrossNetRate(Double.parseDouble(entry.getCell(17).toString()));
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Net Rate should be number ");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Net Rate is null ");
					validaterow=false;
    			}
    			if(entry.getCell(18)!=null&&entry.getCell(18).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				customerBean.setCustomerEmail(entry.getCell(18).toString());
    			}
    			if(entry.getCell(19)!=null&&entry.getCell(19).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				customerBean.setCustomerPhnNo(entry.getCell(19).toString());
    			}
    			if(entry.getCell(20)!=null&&entry.getCell(20).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				customerBean.setCustomerCity(entry.getCell(20).toString());
    			}
    			if(entry.getCell(21)!=null&&entry.getCell(21).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				customerBean.setCustomerAddress(entry.getCell(21).toString());
    			}
    			if(entry.getCell(22)!=null&&entry.getCell(22).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				TaxCategory taxcat=taxDetailService.getTaxCategory(entry.getCell(22).toString(), sellerId);
    				if(taxcat!=null)
    				otb.setTaxCategtory(entry.getCell(22).toString());
    				else
    				{
    					otb.setTaxCategtory(entry.getCell(22).toString());
    					errorMessage.append("Tax Category does not exist ");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append("Tax Category is null ");
					validaterow=false;
    			}
    			if(entry.getCell(23)!=null&&StringUtils.isNotBlank(entry.getCell(23).toString()))
    			{
    				order.setSellerNote(entry.getCell(23).toString());;
    			}
    				System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
    				//Pre save to generate id for use in hierarchy
    				if(validaterow)
    				{
    					order.setCustomer(customerBean);
    					order.setOrderTax(otb);
    				orderService.addOrder(ConverterClass.prepareModel(order),sellerId);
    				}
    				else
    				{
    					order.setCustomer(customerBean);
    					order.setOrderTax(otb);
    					System.out.println(" Error while saving order : "+order.getChannelOrderID()+" errorMessage : "+errorMessage);
    					returnOrderMap.put(errorMessage.toString(), order);
    				}

    			
    		}

    	}catch(Exception e){
    		System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
    		e.printStackTrace();
    		throw new MultipartException("Constraints Violated");
    	}
    	return returnOrderMap;
    }
    
 public Map<String ,OrderBean> saveOrderPOContents(MultipartFile file,int sellerId)throws IOException{
    	

    	HSSFRow entry;
    	Integer noOfEntries=1;
    	boolean validaterow=true;
    	Map<String ,OrderBean> returnOrderMap=new HashMap<>();
    	OrderBean order=null;
    	StringBuffer errorMessage=null;
    	double poprice=0;
    	double poMRP=0;
    	double poSP=0;
    	double poNR=0;
    	int totalquantity=0;
    	//StringBuffer totalSkucode=new StringBuffer("");
    	//OrderBean poOrder=null;
    	
    	try{
    		System.out.println("Inside save content -->");

    		HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
    		HSSFSheet worksheet = offices.getSheetAt(0);
    		while(worksheet.getRow(noOfEntries)!=null){
    			noOfEntries++;
    		}
    		logger.info(noOfEntries.toString());
    		System.out.println("After getting no of rows"+noOfEntries);
    		for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){

    			entry=worksheet.getRow(rowIndex);
    			/*if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
    			{
    				validaterow=true;
    			}*/
    			errorMessage=new StringBuffer("");
    			System.out.println(entry.getCell(1).toString());
    			System.out.println(entry.getCell(2).toString());
    			System.out.println(entry.getCell(3).toString());
    			//System.out.println(entry.getCell(4).toString());
    			order=new OrderBean();
    			if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
    			{
    				List<Order> onj=orderService.findOrders("channelOrderID", entry.getCell(0).toString(), sellerId);
    				if(onj==null||onj.size()==0)
    				{
    					order.setChannelOrderID(entry.getCell(0).toString());
    				}
    				else
    				{
    					errorMessage.append(" Channel OrderId is already present ");
    					validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Channel OrderId is null ");
					validaterow=false;
    			}
    			if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
    			{
    				try
    				{
    				Date dateobj=formatter.parse(entry.getCell(1).toString());
    				order.setOrderDate(dateobj);
    				}
    				catch(ParseException e)
    				{
    					errorMessage.append(" Order Recieved Date formate is wrong ,enter mm/dd/yyyy ");
    					validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Order Recieved Date is null");
    				validaterow=false;
    			}
    			if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
    			{
    				Product product=productService.getProduct(entry.getCell(2).toString(), sellerId);
    				if(product==null)
    				{
    					errorMessage.append(" Product SKU does not exist ");
    					validaterow=false;
    				}
    				else
    				{
    				order.setProductSkuCode(entry.getCell(2).toString());
    				}
    			}else
    			{
    				errorMessage.append(" Product SKU is null ");
					validaterow=false;
    			}
    			if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
    			{
    				Partner partner=partnerService.getPartner(entry.getCell(3).toString(), sellerId);
    				if(partner!=null)
    				order.setPcName(entry.getCell(3).toString());
    				else
    				{
    					errorMessage.append(" Partner does not exist ");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Partner Name is null ");
					validaterow=false;
    			}
    			if(entry.getCell(4)!=null&&StringUtils.isNotBlank(entry.getCell(4).toString()))
    			{
    				order.setSealNo(entry.getCell(4).toString());
    			}
    			if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
    			{
    				order.setInvoiceID(entry.getCell(5).toString());
    			}else
        			{
        				errorMessage.append(" Invoice ID is null ");
        				validaterow=false;
        			}
    			if(entry.getCell(6)!=null&&StringUtils.isNotBlank(entry.getCell(6).toString()))
    			{
    				
    				try
    				{
    					order.setOrderMRP(Float.parseFloat(entry.getCell(6).toString()));
        				poMRP=poMRP+Float.parseFloat(entry.getCell(6).toString());
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Order MRP should be a number ");
        				validaterow=false;	
    				}
    			}else
    			{
    				errorMessage.append(" Order MRP is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(7)!=null&&StringUtils.isNotBlank(entry.getCell(7).toString()))
    			{
    				
    				try
    				{
    					order.setOrderSP(Float.parseFloat(entry.getCell(7).toString()));
        				poSP=poSP+Float.parseFloat(entry.getCell(7).toString());
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Order SP should be a number ");
        				validaterow=false;	
    				}
    			}else
    			{
    				errorMessage.append(" Order SP should be a number ");
    				validaterow=false;	
    			}
    			if(entry.getCell(8)!=null&&StringUtils.isNotBlank(entry.getCell(8).toString()))
    			{
    				try
    				{
    				Date dateobj=formatter.parse(entry.getCell(8).toString());
    				if(dateobj.getDate()>31||dateobj.getMonth()>12)
    				{
    					throw new ParseException("Date is not in mm/dd/yyyy formate",0);
    				}
    				else
    				{
    					order.setShippedDate(dateobj);
    				}
    				}
    				catch(ParseException e)
    				{
    					errorMessage.append(" Shipped Date format is wrong ,enter mm/dd/yyyy ");
        				validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Shipped Date format is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(9)!=null&&StringUtils.isNotBlank(entry.getCell(9).toString()))
    			{
    				
    				try
    				{
    				Date dateobj=formatter.parse(entry.getCell(9).toString());
    				if(dateobj.getDate()>31||dateobj.getMonth()>12)
    				{
    					throw new ParseException("Date is not in mm/dd/yyyy formate",0);
    				}
    				else
    				{
    					order.setDeliveryDate(dateobj);
    				}
    				}
    				catch(ParseException e)
    				{
    					errorMessage.append(" Delivery Date format is wrong ,enter mm/dd/yyyy ");
        				validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Delivery Date is null ");
    				validaterow=false;
    			}
    			if(entry.getCell(10)!=null&&StringUtils.isNotBlank(entry.getCell(10).toString()))
    			{
    				
    				try
					{
    				if((int)Float.parseFloat(entry.getCell(10).toString())!=0)
    				{
    					
    					order.setQuantity((int)Float.parseFloat(entry.getCell(10).toString()));
        				totalquantity=totalquantity+(int)Float.parseFloat(entry.getCell(10).toString());
    					}
    				
    				else
    				{
    					errorMessage.append(" Quantity can not be 0 ");
    					validaterow=false;
    				}
    				}
	    			catch(NumberFormatException e)
	    			{
	    				errorMessage.append(" Quantity should be a number ");
    					validaterow=false;
	    			}
    			}else
    			{
    				errorMessage.append(" Quantity is null ");
					validaterow=false;
    			}
    			if(entry.getCell(11)!=null&&StringUtils.isNotBlank(entry.getCell(11).toString()))
    			{
    				
    				try
    				{
    					order.setGrossNetRate(Double.parseDouble(entry.getCell(11).toString()));
        				poNR=poNR+Double.parseDouble(entry.getCell(11).toString());
    					}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Net Rate should be number ");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" Net Rate is null ");
					validaterow=false;
    			}
    			if(entry.getCell(12)!=null&&StringUtils.isNotBlank(entry.getCell(12).toString()))
    			{
    				
    				try
    				{
    					order.setPoPrice(Double.parseDouble(entry.getCell(12).toString()));
        				poprice=poprice+Double.parseDouble(entry.getCell(12).toString());
    					}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" PO price should be number ");
    					validaterow=false;
    				}
    			}else
    			{
    				errorMessage.append(" PO price is null ");
					validaterow=false;
    			}
    			if(entry.getCell(13)!=null&&StringUtils.isNotBlank(entry.getCell(13).toString()))
    			{
    				order.setSellerNote(entry.getCell(13).toString());
    			}
    			
    			System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
    				//Pre save to generate id for use in hierarchy
    				if(validaterow)
    				{
    					orderService.addOrder(ConverterClass.prepareModel(order),sellerId);
    				}
    				else
    				{
    					returnOrderMap.put(errorMessage.toString(), order);
    				}

    			
    		}
    		/*poOrder=new OrderBean();
    		entry=worksheet.getRow(3);
    		poOrder.setChannelOrderID(entry.getCell(0).toString());
    		poOrder.setOrderDate(new Date(entry.getCell(1).toString()));
    		poOrder.setProductSkuCode("Multiple SKU");
    		poOrder.setPcName(entry.getCell(3).toString());
    		poOrder.setSealNo(entry.getCell(4).toString());
    		poOrder.setInvoiceID(entry.getCell(5).toString());
    		poOrder.setOrderMRP(poMRP);
    		poOrder.setOrderSP(poSP);
    		poOrder.setQuantity(totalquantity);
    		poOrder.setGrossNetRate(poNR);
    		poOrder.setPoPrice(poprice);
    		poOrder.setSellerNote(totalSkucode.toString());
    		poOrder.setShippedDate(new Date(entry.getCell(8).toString()));
    		poOrder.setDeliveryDate(new Date(entry.getCell(9).toString()));
    		
    		orderService.addOrder(ConverterClass.prepareModel(poOrder),sellerId);*/
    		
    		

    	}catch(Exception e){
    		System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
    		e.printStackTrace();
    		throw new MultipartException("Constraints Violated");
    	}
    	return returnOrderMap;
    }

    public Map<String ,ProductBean> saveProductContents(MultipartFile file,int sellerId)throws IOException{
    	
    	boolean validaterow=true;
    	Map<String ,ProductBean> returnProductMap=new HashMap<>();
    	StringBuffer errorMessage=null;
    	
        try{
        	System.out.println("Inside save content -->");
        	
        	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
            //HSSFSheet worksheet = offices.getSheet("OrderReport");
            HSSFSheet worksheet = offices.getSheetAt(0);
            
            HSSFRow entry;
            Integer noOfEntries=1;
            //sellerId=4;
            //getLastRowNum and getPhysicalNumberOfRows showing false values sometimes.
            while(worksheet.getRow(noOfEntries)!=null){
                noOfEntries++;
            }
            logger.info(noOfEntries.toString());
            System.out.println("After getting no of rows"+noOfEntries);
            for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
                entry=worksheet.getRow(rowIndex);
                errorMessage=new StringBuffer("Row :"+(rowIndex-2));
                System.out.println("Product 1"+entry.getCell(1).toString());
                System.out.println("Product  2"+entry.getCell(2).toString());
                System.out.println(entry.getCell(3).toString());
                System.out.println(entry.getCell(4).toString());
                Product product=new Product();
            	if(entry.getCell(0)!=null&&entry.getCell(0).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
            		  product.setProductName(entry.getCell(0).toString());
            		  
    			}else
    			{
    				errorMessage.append(" Product Name is null ");
    				validaterow=false;
    			}
            	if(entry.getCell(1)!=null&&entry.getCell(1).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
            		Product obj=productService.getProduct(entry.getCell(1).toString(), sellerId);
            		if(obj==null)
            		{
            		 product.setProductSkuCode(entry.getCell(1).toString());
            		}
            		else
            		{
            			 product.setProductSkuCode(entry.getCell(1).toString());
            			errorMessage.append(" Product SKU does not exist ");
    					validaterow=false;
            		}
    			}else
    			{
    				errorMessage.append(" Product SKU is null ");
					validaterow=false;
    			}
            	if(entry.getCell(2)!=null&&entry.getCell(2).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
            		Category cat=categoryService.getCategory(entry.getCell(2).toString(), sellerId);
            		if(cat!=null)
            		{
            		product.setCategoryName(entry.getCell(2).toString());
            		}
            		else
            		{
            			product.setCategoryName(entry.getCell(2).toString());
            			errorMessage.append(" Category does not exist ");
    					validaterow=false;
            		}
    			}else
    			{
    				errorMessage.append(" Category is null ");
    				validaterow=false;
    			}
            	if(entry.getCell(3)!=null&&entry.getCell(3).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
            		
            		try
    				{
            			product.setProductPrice(Float.valueOf(entry.getCell(3).toString()));
    				}
    				catch(NumberFormatException e)
    				{
    					errorMessage.append(" Product price should be a number ");
        				validaterow=false;	
    				}
    			}else
    			{
    				errorMessage.append(" Product price is null ");
    				validaterow=false;
    			}
            	if(entry.getCell(4)!=null&&entry.getCell(4).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
            		try
					{
    				if((int)Float.parseFloat(entry.getCell(4).toString())!=0)
    				{
    					
    					product.setQuantity(Float.valueOf(entry.getCell(4).toString()).longValue());
    					}
    				
    				else
    				{
    					errorMessage.append(" Quantity can not be 0 ");
    					validaterow=false;
    				}
    				}
	    			catch(NumberFormatException e)
	    			{
	    				errorMessage.append(" Quantity should be a number ");
    					validaterow=false;
	    			}
            		
            		
    			}else
    			{
    				errorMessage.append(" Quantity is null");
					validaterow=false;
    			}
            	if(entry.getCell(5)!=null&&entry.getCell(5).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
            		 
            			try
        				{
            				product.setThreholdLimit(Float.valueOf(entry.getCell(5).toString()).longValue());
                			
        				}
        				catch(NumberFormatException e)
        				{
        					errorMessage.append(" Product price should be a number ");
            				validaterow=false;	
        				}
    			}else
    			{
    				errorMessage.append(" Product price is null ");
    				validaterow=false;
    			}
                product.setProductDate(new Date());
                if(entry.getCell(6)!=null&&entry.getCell(6).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
                product.setChannelSKU(entry.getCell(6).toString());
          
            	if(validaterow)
				{
            		productService.addProduct(product, sellerId);
                 }
				else
				{
					returnProductMap.put(errorMessage.toString(), ConverterClass.prepareProductBean(product));
				}
                
               System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
                //Pre save to generate id for use in hierarchy
             
            }
           
        }catch(Exception e){
           System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
           e.printStackTrace();
            throw new MultipartException("Constraints Violated");
        }
        
        return returnProductMap;
    }
    
    public Map<String ,OrderBean> savePaymentContents(MultipartFile file ,int sellerId)throws IOException{
    	
    	 PaymentUpload paymentUpload=new PaymentUpload();
         double totalpositive=0;
         double totalnegative=0;
         String channelOrderId=null;
         String skucode=null;
         Order order=null;
         HSSFRow entry;
         Integer noOfEntries=1;
         Date todaydat=new Date();
         Map<String ,OrderBean> returnPaymentMap=new HashMap<>();
         StringBuffer errorMessage=null;
         boolean validaterow=true;
       //sellerId=4;
        try{
        	System.out.println("Inside save content -->");
        	
        	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
            //HSSFSheet worksheet = offices.getSheet("OrderReport");
            HSSFSheet worksheet = offices.getSheetAt(0);
           
            //getLastRowNum and getPhysicalNumberOfRows showing false values sometimes.
            while(worksheet.getRow(noOfEntries)!=null){
                noOfEntries++;
            }
            logger.info(noOfEntries.toString());
            System.out.println("After getting no of rows"+noOfEntries);
            
            for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
                entry=worksheet.getRow(rowIndex);
                System.out.println("Payment 1"+entry.getCell(1).toString());
                System.out.println("Payment  2"+entry.getCell(2).toString());
               /* System.out.println(entry.getCell(3).toString());
                System.out.println(entry.getCell(4).toString());*/
                //Product product=new Product();
                OrderPayment payment=new OrderPayment();
                errorMessage=new StringBuffer("Row :"+(rowIndex-2));
               
                
                System.out.println(" channelOrderId "+channelOrderId);
                if(entry.getCell(0)!=null&&entry.getCell(0).getCellType()!=HSSFCell.CELL_TYPE_BLANK)
    			{
    				List<Order> onj=orderService.findOrders("channelOrderID", entry.getCell(0).toString(), sellerId);
    				if(onj!=null&&onj.size()!=0)
    				{
    					 channelOrderId=entry.getCell(0).toString();
    				}
    				else
    				{
    					 channelOrderId=entry.getCell(0).toString();
    					errorMessage.append(" Channel OrderId is already present ");
    					validaterow=false;
    				}
    				
    			}else
    			{
    				errorMessage.append(" Channel OrderId is null ");
					validaterow=false;
    			}
                if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
    			{
    				Product product=productService.getProduct(entry.getCell(2).toString(), sellerId);
    				if(product==null)
    				{
    					errorMessage.append(" Product SKU does not exist ");
    					validaterow=false;
    				}
    				else
    				{
    					skucode=entry.getCell(2).toString();
    				}
    			}else
    			{
    				errorMessage.append(" Product SKU is null ");
					validaterow=false;
    			}
               if(entry.getCell(4)!=null&&StringUtils.isNotBlank(entry.getCell(4).toString())&&
            		   (int)Float.parseFloat(entry.getCell(4).toString())!=0)
	               {
	            	   try
	            	   {
	                        payment.setNegativeAmount(Double.parseDouble(entry.getCell(4).toString()));
	                        totalnegative=totalnegative+Double.parseDouble(entry.getCell(4).toString());
	                        System.out.println(" ******toatal totalnegative :"+totalnegative);
	            	   }
	            	   catch(NumberFormatException e)
	            	   {
	            		   errorMessage.append(" Negative value should be number ");
	    					validaterow=false;
	            	   }
	            	   
	                }
                	else if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString())&&
                			(int)Float.parseFloat(entry.getCell(3).toString())!=0)
                	{
                		try
 	            	   {
                		payment.setPositiveAmount(Double.parseDouble(entry.getCell(3).toString()));
                		totalpositive=totalpositive+Double.parseDouble(entry.getCell(3).toString());
                		System.out.println(" ******toatal psitive :"+totalpositive);
 	            	  }
 	            	   catch(NumberFormatException e)
 	            	   {
 	            		   errorMessage.append(" Recieved amount should be number ");
 	    					validaterow=false;
 	            	   }
                	}
                	else
                	{
                		errorMessage.append(" Amount should be given ");
	    					validaterow=false;
                	}
                
                if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
                {
                payment.setDateofPayment(new Date(entry.getCell(5).toString()));
                try
				{
				Date dateobj=formatter.parse(entry.getCell(5).toString());
				payment.setDateofPayment(dateobj);
	               
				}
				catch(ParseException e)
				{
					errorMessage.append(" Payment Date format is wrong ,enter mm/dd/yyyy ");
					validaterow=false;
				}
                }
                else
                {
                	errorMessage.append(" Payment Date is null ");
					validaterow=false;
                }
             System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
              order=orderService.addOrderPayment(skucode,channelOrderId, payment,sellerId);
              if(validaterow)
				{
            	  orderService.addOrderPayment(skucode,channelOrderId, payment,sellerId);
               }
				else
				{
					returnPaymentMap.put(errorMessage.toString(), ConverterClass.prepareOrderBean(order));
				}
              
               
               if(order!=null)
               {
            	   System.out.println(order);
            	   paymentUpload.getOrders().add(order);
               }
            }
            paymentUpload.setTotalpositivevalue(totalpositive);
            paymentUpload.setTotalnegativevalue(totalnegative);
            paymentUpload.setNetRecievedAmount(totalpositive-totalnegative);
            paymentUpload.setUploadDesc("PAYU"+sellerId+""+todaydat.getTime());
            paymentUpload.setUploadStatus("Success");
            paymentUploadService.addPaymentUpload(paymentUpload, sellerId);
        }catch(Exception e){
           System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
           e.printStackTrace();
            throw new MultipartException("Constraints Violated");
        }
        
        return returnPaymentMap;
    }
    

    public  Map<String ,String> saveInventoryDetails(MultipartFile file,int sellerId)throws IOException{
    	
    	Map<String ,String> returnInventoryMap=new HashMap<>();
         StringBuffer errorMessage=null;
         boolean validaterow=true;
         String SkUCode=null;
         int currentInventory=0;
         int quantoAdd=0;
         int quantoSub=0;
        try{
        	System.out.println("Inside save inventory data -->");
        	
        	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
        	 HSSFSheet worksheet = offices.getSheetAt(0);
             HSSFRow entry;
            Integer noOfEntries=1;
           // sellerId=4;
            
            
             while(worksheet.getRow(noOfEntries)!=null){
                noOfEntries++;
            }
            logger.info(noOfEntries.toString());
            System.out.println("After getting no of rows"+noOfEntries);
            for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
            	errorMessage=new StringBuffer("");
                entry=worksheet.getRow(rowIndex);
                System.out.println("Product 1"+entry.getCell(1).toString());
                System.out.println("Product  2"+entry.getCell(2).toString());
               
               
          if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
          {
        	  Product product=productService.getProduct(entry.getCell(0).toString(), sellerId);
				if(product==null)
				{
					errorMessage.append(" Product SKU does not exist ");
					validaterow=false;
				}
				else
				{
					SkUCode=entry.getCell(0).toString();
				}
				
				if((entry.getCell(1)==null||StringUtils.isNotBlank(entry.getCell(1).toString())||(int)entry.getCell(1).getNumericCellValue()==0)
						&&(entry.getCell(2)==null||StringUtils.isNotBlank(entry.getCell(2).toString())||(int)entry.getCell(2).getNumericCellValue()==0)
						&&(entry.getCell(3)==null||StringUtils.isNotBlank(entry.getCell(3).toString())||(int)entry.getCell(3).getNumericCellValue()==0))
				{
					errorMessage.append(" All the inventory values can not be blank ");
					validaterow=false;
				}
				else
				{
					 currentInventory=entry.getCell(2)!=null?(int)entry.getCell(2).getNumericCellValue():0;
		              quantoAdd=entry.getCell(3)!=null?(int)entry.getCell(3).getNumericCellValue():0;
		               quantoSub= entry.getCell(4)!=null?(int)entry.getCell(4).getNumericCellValue():0;
				}
             
               System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
                //Pre save to generate id for use in hierarchy
              
          }
          else
          {
        	  errorMessage.append(" SKU can not be blank ");
				validaterow=false;
          }
          if(validaterow)
          {
          productService.updateInventory(SkUCode, currentInventory, quantoAdd, quantoSub,true, sellerId);
          }
          else
          {
        	  returnInventoryMap.put(errorMessage.toString(),SkUCode);
          }
               
            }
           
        }catch(Exception e){
           System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
           e.printStackTrace();
            throw new MultipartException("Constraints Violated");
        }
        return returnInventoryMap;
    }
    
    public Map<String ,Order> saveOrderReturnDetails(MultipartFile file,int sellerId)throws IOException{


    	 HSSFRow entry;
         Integer noOfEntries=1;
         //sellerId=4;
         Order order=new Order();
         String errorMessage=null;
         boolean validaterow=true;
         int returnId =0;
         Map<String ,Order> returnlist=new HashMap<>();
         try{
        	 System.out.println("Inside save order retrun data data -->");

        	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
        	 HSSFSheet worksheet = offices.getSheetAt(0);


        	 while(worksheet.getRow(noOfEntries)!=null){
        		 noOfEntries++;
        	 }
        	 logger.info(noOfEntries.toString());
        	 System.out.println("After getting no of rows"+noOfEntries);
        	 for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
        		 entry=worksheet.getRow(rowIndex);
        		 
        		 System.out.println(entry.getCell(1).toString());
        		 System.out.println(entry.getCell(2).toString());
        		 System.out.println(entry.getCell(3).toString());
        		 System.out.println(entry.getCell(4).toString());

        		 if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
        		 {
        			 List<Order> orderlist=orderService.findOrders("channelOrderID", entry.getCell(0).toString(), sellerId);
        			 if(orderlist!=null&&orderlist.size()!=0)
        				 returnId=orderlist.get(0).getOrderReturnOrRTO().getReturnId();
        			 order.getOrderReturnOrRTO().setReturnId(returnId);
        			 order.setOrderId(orderlist.get(0).getOrderId());

        			 order.getOrderReturnOrRTO().setReturnOrRTOId(entry.getCell(4).toString());
        			 if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
        			 {
        				 order.getOrderReturnOrRTO().setReturnOrRTOreason(entry.getCell(5).toString());
        			 }else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Return reason is null";
        				 validaterow=false;
        			 }
        			 if(entry.getCell(6)!=null&&StringUtils.isNotBlank(entry.getCell(6).toString()))
        			 {
        				 order.getOrderReturnOrRTO().setReturnDate(new Date(entry.getCell(6).toString()));
        			 }
        			 else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Return Date is null";
        				 validaterow=false;
        			 }
        			 if(entry.getCell(7)!=null&&StringUtils.isNotBlank(entry.getCell(7).toString()))
        			 {
        				 order.setStatus(entry.getCell(7).toString());
        			 }else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Status is null";
        				 validaterow=false;
        			 }
        			 if(entry.getCell(8)!=null&&StringUtils.isNotBlank(entry.getCell(8).toString()))
        			 {
        				 order.getOrderReturnOrRTO().setReturnorrtoQty(Integer.parseInt(entry.getCell(8).toString()));
        			 }else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Quantity is null";
        				 validaterow=false;
        			 }

        			 if(entry.getCell(9)!=null&&StringUtils.isNotBlank(entry.getCell(9).toString()))
        			 {
        				 order.getOrderReturnOrRTO().setReturnOrRTOCharges(Double.parseDouble(entry.getCell(9).toString()));

        			 }else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Quantity is null";
        				 validaterow=false;
        			 }


        			 if(!validaterow)       	 	  
        				 orderService.addReturnOrder( entry.getCell(0).toString(),order.getOrderReturnOrRTO(),sellerId);
        			 else
        			 {
        				 returnlist.put(errorMessage, order) ;
        			 }
        		 }

        	 }

         }catch(Exception e){
        	 System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
        	 e.printStackTrace();
        	 throw new MultipartException("Constraints Violated");
         }
         return returnlist;
    }
    
    public Map<String ,DebitNoteBean> saveDebitNoteDetails(MultipartFile file,int sellerId)throws IOException{


   	 HSSFRow entry;
        Integer noOfEntries=1;
        //sellerId=4;
        String errorMessage="Default error";
        boolean validaterow=true;
        //int returnId =0;
        Map<String ,DebitNoteBean> returnlist=new HashMap<>();
        try{
       	 System.out.println("Inside save debit note data -->");

       	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
       	 HSSFSheet worksheet = offices.getSheetAt(0);


       	 while(worksheet.getRow(noOfEntries)!=null){
       		 noOfEntries++;
       	 }
       	 logger.info(noOfEntries.toString());
       	 System.out.println("After getting no of rows"+noOfEntries);
       	 DebitNoteBean dnBean=null;
       	 for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
       		 entry=worksheet.getRow(rowIndex);
       		 System.out.println(entry.getCell(1).toString());
       		 System.out.println(entry.getCell(2).toString());
       		 System.out.println(entry.getCell(3).toString());
       		 System.out.println(entry.getCell(4).toString());

       		 if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
       		 {
       			dnBean=new DebitNoteBean();
       			 
       			dnBean.setPOOrderId(entry.getCell(0).toString());
       			 if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
       			 {
       				dnBean.setGatePassId(entry.getCell(1).toString());
       			 }else
       			 {
       				
       				 errorMessage="Error at row "+rowIndex+" , Gate Pass Id is null";
       				 System.out.println(" *** +"+errorMessage);
       				 validaterow=false;
       			 }
       			 if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
       			 {
       				dnBean.setSKU(entry.getCell(2).toString());
       			 }
       			 else
       			 {
       				 errorMessage="Error at row "+rowIndex+" , SKU is null";
       				System.out.println(" *** +"+errorMessage);
       				 validaterow=false;
       			 }
       			 if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
       			 {
       				dnBean.setPcName(entry.getCell(3).toString());
       			 }else
       			 {
       				 errorMessage="Error at row "+rowIndex+" , Partner is null";
       				System.out.println(" *** +"+errorMessage);
       				 validaterow=false;
       			 }
       			 if(entry.getCell(4)!=null&&StringUtils.isNotBlank(entry.getCell(4).toString()))
       			 {
       				dnBean.setInvoiceId(entry.getCell(4).toString());
       			 }else
       			 {
       				 errorMessage="Error at row "+rowIndex+" , Invoice is null";
       				System.out.println(" *** +"+errorMessage);
       				 validaterow=false;
       			 }

       			 if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
       			 {
       				dnBean.setAmount(Double.parseDouble(entry.getCell(5).toString()));
       			 }else
       			 {
       				 errorMessage="Error at row "+rowIndex+" , Amount is null";
       				 validaterow=false;
       			 }
       			 if(entry.getCell(6)!=null&&StringUtils.isNotBlank(entry.getCell(6).toString()))
       			 {
       				dnBean.setQuantity((int)Float.parseFloat(entry.getCell(6).toString()));
       			 }else
       			 {
       				 errorMessage="Error at row "+rowIndex+" , Quantity is null";
       				System.out.println(" *** +"+errorMessage);
       				 validaterow=false;
       			 }
       			 if(entry.getCell(7)!=null&&StringUtils.isNotBlank(entry.getCell(7).toString()))
       			 {
       				dnBean.setReturnDate(new Date(entry.getCell(7).toString()));
       			 }else
       			 {
       				 errorMessage="Error at row "+rowIndex+" , Return date is null";
       				System.out.println(" *** +"+errorMessage);
       				 validaterow=false;
       			 }
       			 if(entry.getCell(8)!=null&&StringUtils.isNotBlank(entry.getCell(8).toString()))
       			 {
       				dnBean.setReturnReason(entry.getCell(8).toString());
       			 }


       			 System.out.println(" validaterow : "+validaterow+" errorMessage : "+errorMessage);
       			 if(validaterow)   
       			 {
       				 System.out.println(" Saving dnbBean : "+dnBean.getInvoiceId());
       				 orderService.addDebitNote(dnBean, sellerId);
       			 }
       			 else
       			 {
       				 System.out.println("false validate row :"+errorMessage);
       				 returnlist.put(errorMessage, dnBean) ;
       			 }
       		 }

       	 }

        }catch(Exception e){
       	 System.out.println("Inside save debit note exception :"+e.getLocalizedMessage());
       	 e.printStackTrace();
       	 throw new MultipartException("Constraints Violated");
        }
        return returnlist;
   }
    
    public Map<String ,PoPaymentBean> savePoPaymetnDetails(MultipartFile file,int sellerId)throws IOException{


      	 HSSFRow entry;
           Integer noOfEntries=1;
           //sellerId=4;
           String errorMessage=null;
           boolean validaterow=true;
           PoPaymentBean popabean=null;
           Map<String ,PoPaymentBean> returnlist=new HashMap<>();
           Map<String ,PoPaymentBean> acceptPOlist=new HashMap<>();
           try{
          	 System.out.println("Inside save popayment note data -->");

          	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
          	 HSSFSheet worksheet = offices.getSheetAt(0);


          	 while(worksheet.getRow(noOfEntries)!=null){
          		 noOfEntries++;
          	 }
          	 logger.info(noOfEntries.toString());
          	 System.out.println("After getting no of rows"+noOfEntries);
          	
          	 for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
          		 entry=worksheet.getRow(rowIndex);
          		 System.out.println(entry.getCell(1).toString());
          		 System.out.println(entry.getCell(2).toString());
          		
          		 if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
          		 {
          			popabean=new PoPaymentBean();
          			 
          			popabean.setPoOrderId(entry.getCell(0).toString());
          			 if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
          			 {
          				popabean.setInvoiceID(entry.getCell(1).toString());
          			 }else
          			 {
          				 errorMessage="Error at row "+rowIndex+" , invoice Id is null";
          				 validaterow=false;
          			 }
          			 if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
          			 {
          				popabean.setPcName(entry.getCell(2).toString());
          			 }
          			 else
          			 {
          				 errorMessage="Error at row "+rowIndex+" , Partner is null";
          				 validaterow=false;
          			 }
          			 if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
          			 {
          				popabean.setGatePassId(entry.getCell(3).toString());
          			 }
          			 if(entry.getCell(4)!=null&&StringUtils.isNotBlank(entry.getCell(4).toString()))
          			 {
          				popabean.setPositiveAmount(Double.parseDouble(entry.getCell(4).toString()));
          			 }

          			 if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
          			 {
          				popabean.setNegativeAmount(Double.parseDouble(entry.getCell(5).toString()));
          			 }
          			 if(entry.getCell(6)!=null&&StringUtils.isNotBlank(entry.getCell(6).toString()))
          			 {
          				popabean.setPaymentDate(new Date(entry.getCell(6).toString()));
          			 }else
          			 {
          				 errorMessage="Error at row "+rowIndex+" , date is null";
          				 validaterow=false;
          			 }
          			 if(entry.getCell(7)!=null&&StringUtils.isNotBlank(entry.getCell(7).toString()))
          			 {
          				popabean.setQuantity((int)Float.parseFloat(entry.getCell(7).toString()));
          			 }else
          			 {
          				 errorMessage="Error at row "+rowIndex+" , Return date is null";
          				 validaterow=false;
          			 }
          			
          		 }
          		 
          		 if(acceptPOlist!=null&&acceptPOlist.containsKey(popabean.getInvoiceID()))
          		 {
          			 if((int)popabean.getPositiveAmount()!=0){
          				acceptPOlist.get(popabean.getInvoiceID()).setPositiveAmount((acceptPOlist.get(popabean.getInvoiceID()).getPositiveAmount()+popabean.getPositiveAmount()));
          			 }
          			 else
          			 {
          				acceptPOlist.get(popabean.getInvoiceID()).setNegativeAmount((acceptPOlist.get(popabean.getInvoiceID()).getNegativeAmount()+popabean.getNegativeAmount()));
              			 
          			 }
          		 }
          		 else
          		 {
          			 acceptPOlist.put(popabean.getInvoiceID(), popabean);
          		 }

          	 }
          	 
          	for (Map.Entry<String, PoPaymentBean> mapentry : acceptPOlist.entrySet()) {
          		 orderService.addPOPayment(mapentry.getValue(), sellerId);
          		
          	    System.out.println("Key = " + mapentry.getKey() + ", Value = " + mapentry.getValue());
          	}

           }catch(Exception e){
          	 System.out.println("Inside save debit note exception :"+e.getLocalizedMessage());
          	 e.printStackTrace();
          	 throw new MultipartException("Constraints Violated");
           }
           return returnlist;
      }
   
    public Map<String ,ExpenseBean> saveExpenseDetails(MultipartFile file,int sellerId)throws IOException{


    	 HSSFRow entry;
         Integer noOfEntries=1;
         //sellerId=4;
         StringBuffer errorMessage=null;
         boolean validaterow=true;
         ExpenseBean expensebean=null;
         Map<String ,ExpenseBean> returnlist=new HashMap<>();
         
         try{
        	 System.out.println("Inside save expense datails note data -->");

        	 HSSFWorkbook offices= new HSSFWorkbook(file.getInputStream());
        	 HSSFSheet worksheet = offices.getSheetAt(0);


        	 while(worksheet.getRow(noOfEntries)!=null){
        		 noOfEntries++;
        	 }
        	 logger.info(noOfEntries.toString());
        	 System.out.println("After getting no of rows"+noOfEntries);
        	
        	 for(int rowIndex=3;rowIndex<noOfEntries;rowIndex++){
        		 entry=worksheet.getRow(rowIndex);
        		 System.out.println(entry.getCell(1).toString());
        		 System.out.println(entry.getCell(2).toString());
        		 errorMessage=new StringBuffer("");
        		 if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
        		 {
        			expensebean=new ExpenseBean();
        			 
        			expensebean.setExpenseName(entry.getCell(0).toString());
        			 if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
        			 {
        				expensebean.setExpenseDescription(entry.getCell(1).toString());
        			 }
        			 if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
        			 {
        				 List<ExpenseCategory> expCateList=expenseService.listExpenseCategories(sellerId);
        				 boolean temp=false;
        				 for(ExpenseCategory cat:expCateList)
        				 {
        					 if(cat.getExpcatName().equalsIgnoreCase(entry.getCell(2).toString()))
        					 {
        						 temp=true;
        						 break;
        					 }
        				 }
        				 if(temp)
        				 {
        				expensebean.setExpenseCatName(entry.getCell(2).toString());
        				 }
        				 else
        				 {
        					 errorMessage.append(" Expense Category does not exist ");
            				 validaterow=false; 
        				 }
        			 }
        			 else
        			 {
        				 errorMessage.append(" Expense Category is null ");
        				 validaterow=false;
        			 }
        			 if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
        			 {
        				 try
        				 {
        				expensebean.setAmount(Double.parseDouble(entry.getCell(3).toString()));
        				 }
        				 catch(NumberFormatException e)
        				 {
        					 errorMessage.append(" Amount should be numeric ");
            				 validaterow=false; 
        				 }
        				 
        			 }
        			 else
        			 {
        				 errorMessage.append(" Amount is null ");
        				 validaterow=false;
        			 }
        			 if(entry.getCell(4)!=null&&StringUtils.isNotBlank(entry.getCell(4).toString()))
        			 {
        				expensebean.setExpenditureByperson(entry.getCell(4).toString());
        			 }

        			 if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
        			 {
        				expensebean.setPaidTo(entry.getCell(5).toString());
        			 }
        			
        			
        		 }
        		 System.out.println("Validaterow : "+validaterow+"  error message: "+errorMessage);
        		 
        		 if(validaterow)       	 	  
        			expenseService.addExpense(ConverterClass.prepareExpenseModel(expensebean), sellerId);
   			 else
   			 {
   				 returnlist.put(errorMessage.toString(), expensebean) ;
   			 }

        	 }
        	 
         }catch(Exception e){
        	 System.out.println("Inside save expense exception :"+e.getLocalizedMessage());
        	 e.printStackTrace();
        	 throw new MultipartException("Constraints Violated");
         }
         return returnlist;
    }
    public boolean validateRowForNull(HSSFRow entry , int cellcount)
    {
    	for(int i=0;i<cellcount;i++)
    	{
    		
    	if(entry.getCell(i)==null||StringUtils.isBlank(entry.getCell(i).toString()))
    	{
    		return false;
    	}
    	    	}
    	return true;
    }
} 