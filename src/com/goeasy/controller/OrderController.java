package com.goeasy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.OrderBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.helper.FileUploadForm;
import com.goeasy.helper.HelperClass;
import com.goeasy.helper.SaveContents;
import com.goeasy.helper.ValidateUpload;
import com.goeasy.model.Order;
import com.goeasy.model.Partner;
import com.goeasy.service.DownloadService;
import com.goeasy.service.OrderService;
import com.goeasy.service.PartnerService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class OrderController {
 
 @Autowired
 private OrderService orderService;
 @Resource(name="downloadService")
	private DownloadService downloadService;
 @Resource(name="saveContents")
 private SaveContents saveContents;
 @Autowired
 private PartnerService partnerService;
 
 private static final String UPLOAD_DIR="upload";
 
/*@RequestMapping(value = "/seller/saveOrder", method = RequestMethod.POST)
public ModelAndView saveOrder(@ModelAttribute("command")OrderBean orderBean, 
   BindingResult result) {
	System.out.println("Inside order Ssave");
	System.out.println(" Order id :"+orderBean.getOrderId());
	if(orderBean.getOrderTax()!=null)
	System.out.println(" Order tax id = "+orderBean.getOrderTax().getTaxId());
  Order order = ConverterClass.prepareModel(orderBean);
  System.out.println(" Order tax id = "+order.getOrderTax().getTaxId());
  orderService.addOrder(order,4);
  return new ModelAndView("redirect:/seller/addOrder.html");
 }*/

/**
 * Downloads the report as an Excel format. 
 * <p>
 * Make sure this method doesn't return any model. Otherwise, you'll get 
 * an "IllegalStateException: getOutputStream() has already been called for this response"
 */
@RequestMapping(value = "/seller/download/xls", method = RequestMethod.GET)
public void getXLS(HttpServletResponse response,@RequestParam(value="sheetvalue")String sheetvalue) throws ClassNotFoundException {
	
	// Delegate to downloadService. Make sure to pass an instance of HttpServletResponse 
	if(sheetvalue!=null)
	{ 
		if(sheetvalue.equals("ordersummary"))
		{
			downloadService.downloadXLS(response);
		}
		else if(sheetvalue.equals("orderPoSummary"))
		{
			downloadService.downloadOrderPOXLS(response);
		}
		else if(sheetvalue.equals("paymentSummary"))
		{
			downloadService.downloadPaymentXLS(response);
		}
		else if(sheetvalue.equals("returnSummary"))
		{
			downloadService.downloadReturnXLS(response);
		}
		else if(sheetvalue.equals("productSummary"))
		{
			downloadService.downloadProductXLS(response);
		}
		else if(sheetvalue.equals("inventorySummary"))
		{
			downloadService.downloadInventoryXLS(response);
		}
		else if(sheetvalue.equals("debitNoteSummary"))
		{
			downloadService.downloadDebitNoteXLS(response);
		}
		else if(sheetvalue.equals("poPaymentSummary"))
		{
			downloadService.downloadPOPaymentXLS(response);
		}
	}
}

/**
 * Redirect to upload download page. 
 * <p>
 
 */
/*@RequestMapping(value = "/seller/ordersheet", method = RequestMethod.GET)
public String displayForm() {
	
		return "file_upload_form";
	}*/

@RequestMapping(value = "/seller/downloadOrderDA", method = RequestMethod.GET)
public ModelAndView displayDownloadForm(@RequestParam("value") String value) {
	System.out.println("Inside Payment orders  viewpayments uploadId"+value);
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("downloadValue",value);
	
  return new ModelAndView("dailyactivities/order_upload_form",model);
 }

@RequestMapping(value = "/seller/uploadOrderDA", method = RequestMethod.GET)
public ModelAndView displayUploadForm(@RequestParam("value") String value) {
	System.out.println("Inside Payment orders  viewpayments uploadId"+value);
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("uploadValue",value);
	
  return new ModelAndView("dailyactivities/order_upload_form",model);
 }


@RequestMapping(value="/user-login", method=RequestMethod.GET)
public ModelAndView loginForm() {
	return new ModelAndView("login-form");
}

@RequestMapping(value="/error-login", method=RequestMethod.GET)
public ModelAndView invalidLogin() {
	ModelAndView modelAndView = new ModelAndView("login-form");
	modelAndView.addObject("error", true);
	return modelAndView;
}
 
@RequestMapping(value = "/seller/saveSheet", method = RequestMethod.POST)
public ModelAndView save(HttpServletRequest request,@ModelAttribute("uploadForm") FileUploadForm uploadForm,
				Model map) {
	System.out.println("Inside save method");
	List<MultipartFile> files = uploadForm.getFiles();
	 InputStream inputStream = null;  
	  OutputStream outputStream = null; 

	List<String> fileNames = new ArrayList<String>();
	MultipartFile fileinput=files.get(0);
	int sellerId=HelperClass.getSellerIdfromSession(request);
	System.out.println(" got file");
	if(null != files && files.size() > 0) {
		fileNames.add(files.get(0).getOriginalFilename());
		try{
			System.out.println(" Filename : "+files.get(0).getOriginalFilename());
			System.out.println(" uploadForm.getSheetValue() : "+uploadForm.getSheetValue());
			
		 ValidateUpload.validateOfficeData(files.get(0));
		 System.out.println(" fileinput "+fileinput.getName());
		 switch(uploadForm.getSheetValue())
		 {
		 	case "ordersummary" :
		 		saveContents.saveOrderContents(files.get(0),sellerId);
		 		break;
		 	case "orderPoSummary" :
		 		saveContents.saveOrderPOContents(files.get(0),sellerId);
		 		break;
		 	case "paymentSummary" :
		 		saveContents.savePaymentContents(files.get(0),sellerId);
		 		break;
		 	case "returnSummary" :
		 		saveContents.saveOrderReturnDetails(files.get(0),sellerId);
		 		break;
		 	case "productSummary" :
		 		saveContents.saveProductContents(files.get(0),sellerId);
		 		break;
		 	case "inventorySummary" :
		 		saveContents.saveInventoryDetails(files.get(0),sellerId);
		 		break;
			case "debitNoteSummary" :
		 		saveContents.saveDebitNoteDetails(files.get(0),sellerId);
		 		break;
			case "poPaymentSummary" :
		 		saveContents.savePoPaymetnDetails(files.get(0),sellerId);
		 		break;
		 		
		 }
		 inputStream = files.get(0).getInputStream();
		
		 // gets absolute path of the web application
	        String applicationPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
	          System.out.println("***** Application path  : "+applicationPath+"  file xontent type : "+files.get(0).getContentType());
	          System.out.println("***** uploadFilePath path  : "+uploadFilePath);
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(uploadFilePath);
	        if (!fileSaveDir.exists()) {
	        	System.out.println(" Directory doesnnt exist");
	            fileSaveDir.mkdirs();
	        }
	        outputStream = new FileOutputStream(fileSaveDir + File.separator+files.get(0).getOriginalFilename());  
	        int read = 0;  
	        byte[] bytes = new byte[1024];  
	       
	        while ((read = inputStream.read(bytes)) != -1) {  
	         outputStream.write(bytes, 0, read);  
	        }  
	       
	        System.out.println("### Saved files succesfully");
	        outputStream.close();
	        inputStream.close();
	        FileUtils.cleanDirectory(fileSaveDir);
	        
		// saveContents.saveOrderContents(files.get(0),sellerId);
		}
		catch (Exception e) {
			System.out.println("Inside exception , filetype not accepted "+e.getLocalizedMessage());
			e.printStackTrace();
			
		}
          
	}
	
	 Map<String, Object> model = new HashMap<String, Object>();
	  model.put("orders",  ConverterClass.prepareListofBean(orderService.listOrders(4)));
	  return new ModelAndView("redirect:/seller/orderList.html");
	
}
 
//Methods for Ajax implementtation

@RequestMapping(value = "/seller/orderList", method = RequestMethod.GET)
public ModelAndView orderListDailyAct(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
  BindingResult result) {
 Map<String, Object> model = new HashMap<String, Object>();
 model.put("orders",  ConverterClass.prepareListofBean(orderService.listOrders(HelperClass.getSellerIdfromSession(request))));
 return new ModelAndView("dailyactivities/orderList", model);
}

@RequestMapping(value = "/seller/viewOrderDA", method = RequestMethod.GET)
public ModelAndView viewOrderDailyAct(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
  BindingResult result) {
 Map<String, Object> model = new HashMap<String, Object>();
 Order order=orderService.getOrder(orderBean.getOrderId());
 System.out.println(" Payment difference :"+order.getOrderPayment().getPaymentDifference());
 model.put("order",  ConverterClass.prepareOrderBean(order));
 return new ModelAndView("dailyactivities/viewOrder", model);
}

@RequestMapping(value = "/seller/editOrderDA", method = RequestMethod.GET)
public ModelAndView editOrderDA(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("order", ConverterClass.prepareOrderBean(orderService.getOrder(orderBean.getOrderId())));
  model.put("orders",  ConverterClass.prepareListofBean(orderService.listOrders(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("dailyactivities/editOrder", model);
 }

@RequestMapping(value = "/seller/deleteOrderDA", method = RequestMethod.GET)
public ModelAndView deleteOrderDA(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
   BindingResult result) {
	System.out.println(" Order bean id todelete :"+orderBean.getOrderId());
  orderService.deleteOrder(ConverterClass.prepareModel(orderBean),HelperClass.getSellerIdfromSession(request));
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("order", null);
  model.put("orders",  ConverterClass.prepareListofBean(orderService.listOrders(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("dailyactivities/addOrder", model);
 }

@RequestMapping(value = "/seller/saveOrderDA", method = RequestMethod.POST)
public ModelAndView saveOrderDA(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean, 
   BindingResult result) {
	System.out.println("Inside order Ssave");
	System.out.println(" Order id :"+orderBean.getOrderId());
  Order order = ConverterClass.prepareModel(orderBean);
  orderService.addOrder(order,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/orderList.html");
 }

@RequestMapping(value = "/seller/addOrderDA", method = RequestMethod.GET)
public ModelAndView addOrderDA(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
  BindingResult result) {
	System.out.println(" Inside add order da");
 Map<String, Object> model = new HashMap<String, Object>();
 List<Partner> partnerlist=partnerService.listPartners(HelperClass.getSellerIdfromSession(request));
 Map<String,String> partnermap=new HashMap<String, String>();
 for(Partner bean:partnerlist)
 {
	 partnermap.put(bean.getPcName(), bean.getPcName());
	  
 }
 model.put("partnermap", partnermap);
 //model.put("orders",  ConverterClass.prepareListofBean(orderService.listOrders(4)));
 return new ModelAndView("dailyactivities/addOrder", model);
}
}