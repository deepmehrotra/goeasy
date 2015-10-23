package com.goeasy.helper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.PaymentUpload;
import com.goeasy.model.Product;
import com.goeasy.service.ExpenseService;
import com.goeasy.service.OrderService;
import com.goeasy.service.PaymentUploadService;
import com.goeasy.service.ProductService;



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
     private ExpenseService expenseService;
   
    public Map<String ,OrderBean> saveOrderContents(MultipartFile file,int sellerId)throws IOException{
    	

    	HSSFRow entry;
    	Integer noOfEntries=1;
    	boolean validaterow=true;
    	Map<String ,OrderBean> returnOrderMap=new HashMap<>();
    	OrderBean order=null;
    	String errorMessage=null;
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

    			System.out.println(entry.getCell(1).toString());
    			System.out.println(entry.getCell(2).toString());
    			System.out.println(entry.getCell(3).toString());
    			System.out.println(entry.getCell(4).toString());
    			order=new OrderBean();
				customerBean=new CustomerBean();
				otb=new OrderTaxBean();
    			if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
    			{
    				order.setChannelOrderID(entry.getCell(0).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Channel OrderId is null";
    				validaterow=false;
    			}
    			if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
    			{
    				order.setOrderDate(new Date(entry.getCell(1).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Order Recieved Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
    			{
    				order.setProductSkuCode(entry.getCell(2).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Product SKU is null";
    				validaterow=false;
    			}
    			if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
    			{
    				order.setPcName(entry.getCell(3).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Partner Name is null";
    				validaterow=false;
    			}
    			if(entry.getCell(4)!=null&&StringUtils.isNotBlank(entry.getCell(4).toString()))
    			{
    				customerBean.setCustomerName(entry.getCell(4).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Customer Name is null";
    				validaterow=false;
    			}
    			if(entry.getCell(5)!=null&&StringUtils.isNotBlank(entry.getCell(5).toString()))
    			{
    				order.setPaymentType(entry.getCell(5).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Customer Name is null";
    				validaterow=false;
    			}
    			if(entry.getCell(6)!=null&&StringUtils.isNotBlank(entry.getCell(6).toString()))
    			{
    				order.setAwbNum(entry.getCell(6).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , AWBNUM is null";
    				validaterow=false;
    			}
    			if(entry.getCell(7)!=null&&StringUtils.isNotBlank(entry.getCell(7).toString()))
    			{
    				order.setInvoiceID(entry.getCell(7).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Invoice ID is null";
    				validaterow=false;
    			}
    			if(entry.getCell(8)!=null&&StringUtils.isNotBlank(entry.getCell(8).toString()))
    			{
    				order.setSubOrderID(entry.getCell(8).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , SubOrderId is null";
    				validaterow=false;
    			}
    			if(entry.getCell(9)!=null&&StringUtils.isNotBlank(entry.getCell(9).toString()))
    			{
    				order.setPIreferenceNo(entry.getCell(9).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , PI reference number is null";
    				validaterow=false;
    			}
    			if(entry.getCell(10)!=null&&StringUtils.isNotBlank(entry.getCell(10).toString()))
    			{
    				order.setLogisticPartner(entry.getCell(10).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Logistics Partner is null";
    				validaterow=false;
    			}
    			if(entry.getCell(11)!=null&&StringUtils.isNotBlank(entry.getCell(11).toString()))
    			{
    				order.setOrderMRP(Float.parseFloat(entry.getCell(11).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Order MRP is null";
    				validaterow=false;
    			}
    			if(entry.getCell(12)!=null&&StringUtils.isNotBlank(entry.getCell(12).toString()))
    			{
    				order.setOrderSP(Float.parseFloat(entry.getCell(12).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Order SP is null";
    				validaterow=false;
    			}
    			if(entry.getCell(13)!=null&&StringUtils.isNotBlank(entry.getCell(13).toString()))
    			{
    				order.setShippingCharges(Float.parseFloat(entry.getCell(13).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Shipping Charges is null";
    				validaterow=false;
    			}
    			
    			if(entry.getCell(14)!=null&&StringUtils.isNotBlank(entry.getCell(14).toString()))
    			{
    				order.setShippedDate(new Date(entry.getCell(14).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Shipping Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(15)!=null&&StringUtils.isNotBlank(entry.getCell(15).toString()))
    			{
    				order.setDeliveryDate(new Date(entry.getCell(15).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Delivery Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(16)!=null&&StringUtils.isNotBlank(entry.getCell(16).toString()))
    			{
    				order.setQuantity((int)Float.parseFloat(entry.getCell(16).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Shipping Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(17)!=null&&StringUtils.isNotBlank(entry.getCell(17).toString()))
    			{
    				order.setGrossNetRate(Double.parseDouble(entry.getCell(17).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Shipping Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(18)!=null&&StringUtils.isNotBlank(entry.getCell(18).toString()))
    			{
    				customerBean.setCustomerEmail(entry.getCell(18).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Shipping Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(19)!=null&&StringUtils.isNotBlank(entry.getCell(19).toString()))
    			{
    				customerBean.setCustomerPhnNo(entry.getCell(19).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Customer Phone Number is null";
    				validaterow=false;
    			}
    			if(entry.getCell(20)!=null&&StringUtils.isNotBlank(entry.getCell(20).toString()))
    			{
    				customerBean.setCustomerCity(entry.getCell(20).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Customer City is null";
    				validaterow=false;
    			}
    			if(entry.getCell(21)!=null&&StringUtils.isNotBlank(entry.getCell(21).toString()))
    			{
    				customerBean.setCustomerAddress(entry.getCell(21).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Customer Address is null";
    				validaterow=false;
    			}
    			if(entry.getCell(22)!=null&&StringUtils.isNotBlank(entry.getCell(22).toString()))
    			{
    				otb.setTaxCategtory(entry.getCell(22).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Tax Category is null";
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
    					returnOrderMap.put(errorMessage, order);
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
    	String errorMessage=null;
    	double poprice=0;
    	double poMRP=0;
    	double poSP=0;
    	double poNR=0;
    	int totalquantity=0;
    	StringBuffer totalSkucode=new StringBuffer("");
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

    			System.out.println(entry.getCell(1).toString());
    			System.out.println(entry.getCell(2).toString());
    			System.out.println(entry.getCell(3).toString());
    			//System.out.println(entry.getCell(4).toString());
    			order=new OrderBean();
    			if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
    			{
    				order.setChannelOrderID(entry.getCell(0).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Channel OrderId is null";
    				validaterow=false;
    			}
    			if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
    			{
    				order.setOrderDate(new Date(entry.getCell(1).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Order Recieved Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
    			{
    				order.setProductSkuCode(entry.getCell(2).toString());
    				totalSkucode.append(entry.getCell(2).toString()+",");
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Product SKU is null";
    				validaterow=false;
    			}
    			if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
    			{
    				order.setPcName(entry.getCell(3).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" , Partner Name is null";
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
    				errorMessage="Error at row "+rowIndex+" , Invoice ID is null";
    				validaterow=false;
    			}
    			if(entry.getCell(6)!=null&&StringUtils.isNotBlank(entry.getCell(6).toString()))
    			{
    				order.setOrderMRP(Float.parseFloat(entry.getCell(6).toString()));
    				poMRP=poMRP+Float.parseFloat(entry.getCell(6).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Order MRP is null";
    				validaterow=false;
    			}
    			if(entry.getCell(7)!=null&&StringUtils.isNotBlank(entry.getCell(7).toString()))
    			{
    				order.setOrderSP(Float.parseFloat(entry.getCell(7).toString()));
    				poSP=poSP+Float.parseFloat(entry.getCell(7).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Order SP is null";
    				validaterow=false;
    			}
    			if(entry.getCell(8)!=null&&StringUtils.isNotBlank(entry.getCell(8).toString()))
    			{
    				order.setShippedDate(new Date(entry.getCell(8).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Shipping Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(9)!=null&&StringUtils.isNotBlank(entry.getCell(9).toString()))
    			{
    				order.setDeliveryDate(new Date(entry.getCell(9).toString()));
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Delivery Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(10)!=null&&StringUtils.isNotBlank(entry.getCell(10).toString()))
    			{
    				order.setQuantity((int)Float.parseFloat(entry.getCell(10).toString()));
    				totalquantity=totalquantity+(int)Float.parseFloat(entry.getCell(10).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Quantity Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(11)!=null&&StringUtils.isNotBlank(entry.getCell(11).toString()))
    			{
    				order.setGrossNetRate(Double.parseDouble(entry.getCell(11).toString()));
    				poNR=poNR+Double.parseDouble(entry.getCell(11).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,Shipping Date is null";
    				validaterow=false;
    			}
    			if(entry.getCell(12)!=null&&StringUtils.isNotBlank(entry.getCell(12).toString()))
    			{
    				order.setPoPrice(Double.parseDouble(entry.getCell(12).toString()));
    				poprice=poprice+Double.parseDouble(entry.getCell(12).toString());
    			}else
    			{
    				errorMessage="Error at row "+rowIndex+" ,PO price  is null";
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
    					returnOrderMap.put(errorMessage, order);
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

    public void saveProductContents(MultipartFile file,int sellerId)throws IOException{
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
                System.out.println("Product 1"+entry.getCell(1).toString());
                System.out.println("Product  2"+entry.getCell(2).toString());
                System.out.println(entry.getCell(3).toString());
                System.out.println(entry.getCell(4).toString());
                Product product=new Product();
                product.setProductName(entry.getCell(0).toString());
                product.setProductDate(new Date());
                product.setProductSkuCode(entry.getCell(1).toString());
                product.setCategoryName(entry.getCell(2).toString());
                product.setProductPrice(Float.valueOf(entry.getCell(3).toString()));
                product.setQuantity(Float.valueOf(entry.getCell(4).toString()).longValue());
                product.setThreholdLimit(Float.valueOf(entry.getCell(5).toString()).longValue());
                if(entry.getCell(6)!=null)
                product.setChannelSKU(entry.getCell(6).toString());
          
              
                
               System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
                //Pre save to generate id for use in hierarchy
               productService.addProduct(product, sellerId);
               
            }
           
        }catch(Exception e){
           System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
           e.printStackTrace();
            throw new MultipartException("Constraints Violated");
        }
    }
    
    public void savePaymentContents(MultipartFile file ,int sellerId)throws IOException{
    	
    	 PaymentUpload paymentUpload=new PaymentUpload();
         double totalpositive=0;
         double totalnegative=0;
         String channelOrderId=null;
         String skucode=null;
         Order order=null;
         HSSFRow entry;
         Integer noOfEntries=1;
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
                System.out.println(entry.getCell(3).toString());
                System.out.println(entry.getCell(4).toString());
                //Product product=new Product();
                OrderPayment payment=new OrderPayment();
                
                channelOrderId=entry.getCell(0).toString();
                skucode=entry.getCell(3).toString();
                System.out.println(" channelOrderId "+channelOrderId);
                if(entry.getCell(6)!=null)
                {
                	if(entry.getCell(6).toString().equalsIgnoreCase("Return"))
                	{
                        payment.setNegativeAmount(Double.parseDouble(entry.getCell(5).toString()));
                        totalnegative=totalnegative+Double.parseDouble(entry.getCell(5).toString());
                        System.out.println(" ******toatal totalnegative :"+totalnegative);
                	}
                	else
                	{
                		payment.setPositiveAmount(Double.parseDouble(entry.getCell(4).toString()));
                		totalpositive=totalpositive+Double.parseDouble(entry.getCell(4).toString());
                		System.out.println(" ******toatal psitive :"+totalpositive);
                	}
                }
                if(entry.getCell(7)!=null)
                {
                payment.setDateofPayment(new Date(entry.getCell(7).toString()));
                }
             System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
              order=orderService.addOrderPayment(skucode,channelOrderId, payment,sellerId);
              
               
               if(order!=null)
               {
            	   System.out.println(order);
            	   paymentUpload.getOrders().add(order);
               }
            }
            paymentUpload.setTotalpositivevalue(totalpositive);
            paymentUpload.setTotalnegativevalue(totalnegative);
            paymentUpload.setNetRecievedAmount(totalpositive-totalnegative);
            paymentUpload.setUploadStatus("Success");
            paymentUploadService.addPaymentUpload(paymentUpload, sellerId);
        }catch(Exception e){
           System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
           e.printStackTrace();
            throw new MultipartException("Constraints Violated");
        }
    }
    

    public void saveInventoryDetails(MultipartFile file,int sellerId)throws IOException{
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
                entry=worksheet.getRow(rowIndex);
                System.out.println("Product 1"+entry.getCell(1).toString());
                System.out.println("Product  2"+entry.getCell(2).toString());
                System.out.println(entry.getCell(3).toString());
                System.out.println(entry.getCell(4).toString());
               
          if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
          {
              String SkUCode=entry.getCell(1).toString();
              int currentInventory=entry.getCell(2)!=null?(int)entry.getCell(2).getNumericCellValue():0;
              int quantoAdd=entry.getCell(3)!=null?(int)entry.getCell(3).getNumericCellValue():0;
               int quantoSub= entry.getCell(4)!=null?(int)entry.getCell(4).getNumericCellValue():0;
               System.out.println("Sheet values :1 :"+entry.getCell(1)+" 2 :"+entry.getCell(2)+" 3 :"+entry.getCell(3));
                //Pre save to generate id for use in hierarchy
               productService.updateInventory(SkUCode, currentInventory, quantoAdd, quantoSub, sellerId);
          }
               
            }
           
        }catch(Exception e){
           System.out.println("Inside save contents exception :"+e.getLocalizedMessage());
           e.printStackTrace();
            throw new MultipartException("Constraints Violated");
        }
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
        int returnId =0;
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
         String errorMessage=null;
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
        		
        		 if(entry.getCell(0)!=null&&StringUtils.isNotBlank(entry.getCell(0).toString()))
        		 {
        			expensebean=new ExpenseBean();
        			 
        			expensebean.setExpenseName(entry.getCell(0).toString());
        			 if(entry.getCell(1)!=null&&StringUtils.isNotBlank(entry.getCell(1).toString()))
        			 {
        				expensebean.setExpenseDescription(entry.getCell(1).toString());
        			 }else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , desc Id is null";
        				 validaterow=false;
        			 }
        			 if(entry.getCell(2)!=null&&StringUtils.isNotBlank(entry.getCell(2).toString()))
        			 {
        				expensebean.setExpenseCatName(entry.getCell(2).toString());
        			 }
        			 else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Category is null";
        				 validaterow=false;
        			 }
        			 if(entry.getCell(3)!=null&&StringUtils.isNotBlank(entry.getCell(3).toString()))
        			 {
        				expensebean.setAmount(Double.parseDouble(entry.getCell(3).toString()));
        			 }
        			 else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Amount is null";
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
        			else
        			 {
        				 errorMessage="Error at row "+rowIndex+" , Paid to is null";
        				 validaterow=false;
        			 }
        			
        		 }
        		 System.out.println("Validaterow : "+validaterow+"  error message: "+errorMessage);
        		 
        		 if(validaterow)       	 	  
        			expenseService.addExpense(ConverterClass.prepareExpenseModel(expensebean), sellerId);
   			 else
   			 {
   				 returnlist.put(errorMessage, expensebean) ;
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