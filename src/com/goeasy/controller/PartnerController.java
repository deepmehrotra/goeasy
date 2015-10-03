package com.goeasy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.PartnerBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.helper.HelperClass;
import com.goeasy.model.Partner;
import com.goeasy.service.PartnerService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class PartnerController {
 
 @Autowired
 private PartnerService partnerService;
 
 @Autowired
 ServletContext context;

 
 ArrayList<String> partnerList = new ArrayList<String>(){/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

{
	   add("Amazon");
	   add("Flipkart");
	   add("Snapdeal");
	   add("Shopclues");
	   }};
 
@RequestMapping(value = "/seller/savePartner", method = RequestMethod.POST)
public ModelAndView savePartner(HttpServletRequest request,@ModelAttribute("command")PartnerBean partnerBean, 
   BindingResult result,@RequestParam(value = "image", required = false) MultipartFile image) {
	System.out.println("Inside partner Ssave ");
	System.out.println(" partnerBean : **** "+partnerBean);
	System.out.println("inside controller noofdaysfromshipped date : "+partnerBean.getNoofdaysfromshippeddate());
	System.out.println(" Seller id :"+partnerBean.getPcId());
	if(!partnerBean.isIsshippeddatecalc())
	{
		partnerBean.setNoofdaysfromshippeddate(partnerBean.getNoofdaysfromdeliverydate());
	}
	//int sellerId=HelperClass.getSellerIdfromSession(request);
	if(image==null)
		System.out.println(" Not getting any image");
	 if (!image.isEmpty()) {
		  try {
		  validateImage(image);
		   
		  } catch (RuntimeException re) {
			  result.reject(re.getMessage());
		  }
	 }
	 try {
		 partnerBean.setPcLogoUrl(HelperClass.getSellerIdfromSession(request) +partnerBean.getPcName()+".jpg");
		 saveImage(HelperClass.getSellerIdfromSession(request) +partnerBean.getPcName()+".jpg", image);
		 } catch (IOException e) {
		 result.reject(e.getMessage());
		 return new ModelAndView("redirect:/seller/partners.html");
		 }
	Partner partner=ConverterClass.preparePartnerModel(partnerBean);
	partnerService.addPartner(partner,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/partners.html");
 }

@RequestMapping(value="/seller/listPartners", method = RequestMethod.GET)
public ModelAndView listAllPartners(HttpServletRequest request) {
 Map<String, Object> model = new HashMap<String, Object>();
 List<PartnerBean> addedlist=ConverterClass.prepareListofPartnerBean(partnerService.listPartners(HelperClass.getSellerIdfromSession(request)));
   model.put("partners",addedlist);
  return new ModelAndView("initialsetup/partnerDetails", model);
}

@RequestMapping(value = "/seller/savePartnertest", method = RequestMethod.POST)
public ModelAndView savePartnertest(HttpServletRequest request,@ModelAttribute("command")PartnerBean partnerBean, 
   BindingResult result) {
	System.out.println("Inside partner Ssave");
	System.out.println(" Seller id :"+partnerBean.getPcId());
	Partner partner=ConverterClass.preparePartnerModel(partnerBean);
	partnerService.addPartner(partner,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/addPartnertest.html");
 }



 @RequestMapping(value="/seller/partners", method = RequestMethod.GET)
 public ModelAndView listPartners(HttpServletRequest request) {
  Map<String, Object> model = new HashMap<String, Object>();
  List<PartnerBean> toAddPartner=new ArrayList<PartnerBean>();
  List<PartnerBean> addedlist=ConverterClass.prepareListofPartnerBean(partnerService.listPartners(HelperClass.getSellerIdfromSession(request)));
  for(String partner:partnerList)
  {
	  if(partnerService.getPartner(partner,HelperClass.getSellerIdfromSession(request))==null)
	  {
		  toAddPartner.add(new PartnerBean(partner,partner,partner));
	  }
  }
  
  model.put("partners",addedlist);
  model.put("partnertoadd",toAddPartner);
 System.out.println(" toaddpartner size  :"+toAddPartner.size());
  return new ModelAndView("initialsetup/partnerInfo", model);
 }
 

 @RequestMapping(value = "/seller/addPartner", method = RequestMethod.GET)
 public ModelAndView addOrder(HttpServletRequest request,@ModelAttribute("command")PartnerBean partnerBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  Map<String, Object> datemap = new HashMap<String, Object>();
  datemap.put("default", "Select payment from");
  datemap.put("true", "Shipping Date");
  datemap.put("false", "Delivery Date");
  PartnerBean partner=new PartnerBean();
 
  String id=request.getParameter("pid");
  System.out.println(" Inside partner controller :"+id);
  if(id!=null)
  {
	
	  if(id.equals("1"))
		  partner.setPcName("Amazon");
  }
  model.put("partner", partner);
  model.put("datemap", datemap);
  model.put("partners", ConverterClass.prepareListofPartnerBean(partnerService.listPartners(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("initialsetup/addPartner", model);
 }
 
 @RequestMapping(value = "/seller/addPartnertest", method = RequestMethod.GET)
 public ModelAndView addPartnertest(HttpServletRequest request,@ModelAttribute("command")PartnerBean partnerBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  PartnerBean partner=new PartnerBean();
  String id=request.getParameter("pid");
  System.out.println(" Inside partner controller :"+id);
  if(id!=null)
  {
	
	  if(id.equals("1"))
		  partner.setPcName("Amazon");
  }
  model.put("partner", partner);
  model.put("partners", ConverterClass.prepareListofPartnerBean(partnerService.listPartners(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addPartner", model);
 }
 
@RequestMapping(value = "/seller/partnerindex", method = RequestMethod.GET)
public ModelAndView welcome() {
  return new ModelAndView("index");
 }

@RequestMapping(value = "/seller/deletePartner", method = RequestMethod.GET)
public ModelAndView deletePartner(HttpServletRequest request,@ModelAttribute("command")PartnerBean partnerBean,
   BindingResult result) {
  System.out.println(" pcid in controller "+partnerBean.getPcId());
  System.out.println(" pcname in controller "+partnerBean.getPcName());
	partnerService.deletePartner(ConverterClass.preparePartnerModel(partnerBean),HelperClass.getSellerIdfromSession(request));
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("partner", null);
  model.put("partners", ConverterClass.prepareListofPartnerBean(partnerService.listPartners(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addPartner", model);
 }
 
@RequestMapping(value = "/seller/editPartner", method = RequestMethod.GET)
public ModelAndView editPartner(HttpServletRequest request,@ModelAttribute("command")PartnerBean partnerBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  
  model.put("partner", ConverterClass.preparePartnerBean(partnerService.getPartner(partnerBean.getPcId())));
  model.put("partners",  ConverterClass.prepareListofPartnerBean(partnerService.listPartners(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("initialsetup/addPartner", model);
 }

private void validateImage(MultipartFile image) {
	if (!image.getContentType().equals("image/jpeg")) {
		throw new RuntimeException("Only JPG images are accepted");
	}
}
private void saveImage(String filename, MultipartFile image)
		throws RuntimeException, IOException {
	try {
		File file = new File(context.getRealPath("/") + "/"
				+ filename);

		FileUtils.writeByteArrayToFile(file, image.getBytes());
		System.out.println("Go to the location:  " + file.toString()
				+ " on your computer and verify that the image has been stored.");
	} catch (IOException e) {
		throw e;
	}
}
}