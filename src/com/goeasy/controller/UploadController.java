package com.goeasy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.OrderBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.helper.FileUploadForm;
import com.goeasy.helper.HelperClass;
import com.goeasy.helper.SaveContents;
import com.goeasy.helper.ValidateUpload;
import com.goeasy.model.Order;
import com.goeasy.model.OrderPayment;
import com.goeasy.model.Partner;
import com.goeasy.model.PaymentUpload;
import com.goeasy.service.DownloadService;
import com.goeasy.service.OrderService;
import com.goeasy.service.PartnerService;
import com.goeasy.service.PaymentUploadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class UploadController {
 
 @Autowired
 private PaymentUploadService paymentUploadService;
 @Autowired
 private OrderService orderService;
 @Resource(name="downloadService")
	private DownloadService downloadService;
@Resource(name="saveContents")
private SaveContents saveContents;
@Autowired
private PartnerService partnerService;

 @RequestMapping(value = "/seller/paymentUploadList", method = RequestMethod.GET)
 public ModelAndView paymentUploadList(HttpServletRequest request) {
		  Map<String, Object> model = new HashMap<String, Object>();
		  System.out.println(" Inside Upload payment list");
		  model.put("payments", ConverterClass.prepareListofPaymentUploadBean(paymentUploadService.listPaymentUploads(HelperClass.getSellerIdfromSession(request))));
		  return new ModelAndView("dailyactivities/paymentUploadList", model);
		 }

 
 @RequestMapping(value = "/seller/viewPayments", method = RequestMethod.GET)
 public ModelAndView viewPayments(HttpServletRequest request) {
	 String uploadId=request.getParameter("uploadId");
	 String manualPay=request.getParameter("manualPay");
 	System.out.println("Inside Payment orders  viewpayments uploadId"+uploadId);
 	System.out.println("Inside Payment orders  viewpayments manualPay"+manualPay);
 	Map<String, Object> model = new HashMap<String, Object>();
 	String manualpayid=null;
 	int sellerId=HelperClass.getSellerIdfromSession(request);
 	if(manualPay!=null&&manualPay.equals("true"))
 	{
 		if(paymentUploadService.getManualPayment(sellerId)!=null)
 			manualpayid=String.valueOf(paymentUploadService.getManualPayment(sellerId).getUploadId());
 		model.put("uploadId",manualpayid);
 	}
 	else
 	{
 	model.put("uploadId",uploadId);
 	}
   return new ModelAndView("dailyactivities/orderPaymentDetails",model);
  }
 
 @RequestMapping(value = "/seller/paymentDetails", method = RequestMethod.POST)
 public @ResponseBody String viewPaymentDetails(HttpServletRequest request) {
 	System.out.println("****Inside payment details Ssave");
 	 Map<String, Object> model = new HashMap<String, Object>();
 	  Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	  PaymentUpload payment=null;
 	 List<OrderBean> orderlist=null;
 	 String action=request.getParameter("action");
 	String uploadId=request.getParameter("uploadId");
 	 
 	if(action!=null&&action.equals("list")&&uploadId!=null&&uploadId.length()!=0)
 	  {
 		payment=paymentUploadService.getPaymentUpload(Integer.parseInt(uploadId));
 		orderlist=ConverterClass.prepareListofBean(payment.getOrders());
 	  
 	  }
 	  System.out.println(" Inside find order method controller");
 	  
 	 model.put("Result", "OK");
 	  model.put("Records",orderlist);

 	 	// Convert Java Object to Json
 	 String jsonArray = gson.toJson(model);
 	  //model.put("employees", jsonArray);
 	   return jsonArray;
  }


/**
 * Downloads the report as an Excel format. 
 * <p>
 * Make sure this method doesn't return any model. Otherwise, you'll get 
 * an "IllegalStateException: getOutputStream() has already been called for this response"
 */
@RequestMapping(value = "/seller/download/paymentXls", method = RequestMethod.GET)
public void getXLS(HttpServletResponse response) throws ClassNotFoundException {
	
	// Delegate to downloadService. Make sure to pass an instance of HttpServletResponse 
	downloadService.downloadPaymentXLS(response);
}
/**
 * Redirect to upload download page. 
 * <p>
 
 */
@RequestMapping(value = "/seller/orderPaymentSheet", method = RequestMethod.GET)
public String displayForm() {
	
		return "dailyactivities/payment_upload_form";
	}

@RequestMapping(value = "/seller/addManualPayment", method = RequestMethod.GET)
public ModelAndView addManualPayment(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
  BindingResult result) {
	System.out.println(" Inside add order payment");
 Map<String, Object> model = new HashMap<String, Object>();
 Map<Integer, String> orderIdmap = new HashMap<>();
 Map<String, String> partnermap = new HashMap<>();
 int sellerId=HelperClass.getSellerIdfromSession(request);
 List<Order> orderlist=orderService.listOrders(sellerId);
 for(Order order:orderlist)
 {
	 orderIdmap.put(order.getOrderId(), order.getChannelOrderID());
 }
 List<Partner> partnerList=partnerService.listPartners(sellerId);
 for(Partner partner:partnerList)
 {
	 partnermap.put(partner.getPcName(), partner.getPcName());
 }
 model.put("orderIdmap",orderIdmap);
 model.put("partnermap",partnermap);
 return new ModelAndView("dailyactivities/addManualPayment", model);
}

@RequestMapping(value = "/seller/saveManualPayment", method = RequestMethod.POST)
public ModelAndView saveManualPayment(HttpServletRequest request,@ModelAttribute("command")OrderBean orderBean,
  BindingResult result) {
 int sellerId=HelperClass.getSellerIdfromSession(request);
 Order order=null;
 PaymentUpload paymentUpload=null;
 OrderPayment payment=new OrderPayment();
 
 System.out.println(" channelOrderId "+orderBean.getChannelOrderID());
 if((int)orderBean.getOrderPayment().getNegativeAmount()!=0)
 {
 	
         payment.setNegativeAmount(orderBean.getOrderPayment().getNegativeAmount());
 }
 	
else
{
	payment.setPositiveAmount(orderBean.getOrderPayment().getPositiveamout());
	
}
payment.setDateofPayment(orderBean.getOrderPayment().getDateofPayment());
System.out.println("order id in payment controller : "+orderBean.getOrderId());
order=orderService.addOrderPayment(orderBean.getOrderId(), payment,sellerId);

paymentUpload=paymentUploadService.getManualPayment(sellerId);
if(paymentUpload==null)
{
	paymentUpload=new PaymentUpload();
	paymentUpload.setTotalpositivevalue(payment.getPositiveAmount());
	paymentUpload.setTotalnegativevalue(payment.getNegativeAmount());
	paymentUpload.setNetRecievedAmount(payment.getPositiveAmount()-payment.getNegativeAmount());
	paymentUpload.setUploadDesc("Manual Upload");
	paymentUpload.setUploadStatus("Success");

}
else
{
	paymentUpload.setTotalpositivevalue(paymentUpload.getTotalpositivevalue()+payment.getPositiveAmount());
	paymentUpload.setTotalnegativevalue(paymentUpload.getTotalnegativevalue()+payment.getNegativeAmount());
	paymentUpload.setNetRecievedAmount(paymentUpload.getTotalpositivevalue()-paymentUpload.getTotalnegativevalue());
	paymentUpload.setUploadStatus("Success");
}
if(order!=null)
{
	   System.out.println(order);
	   paymentUpload.getOrders().add(order);
}

paymentUploadService.addPaymentUpload(paymentUpload, sellerId);
 
 
 return new ModelAndView("redirect:/seller/paymentUploadList.html");
}


@RequestMapping(value = "/seller/savePaymentUpload", method = RequestMethod.POST)
public ModelAndView save(HttpServletRequest request,
		@ModelAttribute("uploadForm") FileUploadForm uploadForm,
				Model map) {
	System.out.println("Inside save method");
	List<MultipartFile> files = uploadForm.getFiles();

	List<String> fileNames = new ArrayList<String>();
	MultipartFile fileinput=files.get(0);
	int sellerId=HelperClass.getSellerIdfromSession(request);
	System.out.println(" got file");
	if(null != files && files.size() > 0) {
		fileNames.add(files.get(0).getOriginalFilename());
		try{
			System.out.println(" Filename : "+files.get(0).getOriginalFilename());
			System.out.println(" Filename : "+files.get(0).getName());
		 ValidateUpload.validateOfficeData(files.get(0));
		 System.out.println(" fileinput "+fileinput.getName());
		 saveContents.savePaymentContents(files.get(0),sellerId);
		}
		catch (Exception e) {
			System.out.println("Inside exception , filetype not accepted "+e.getLocalizedMessage());
			
		}
          
	}
	
	
	  return new ModelAndView("dailyactivities/dailyactivities");
	
}
 

}