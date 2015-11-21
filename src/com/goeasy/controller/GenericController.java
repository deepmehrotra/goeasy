package com.goeasy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.DashboardBean;
import com.goeasy.helper.HelperClass;
import com.goeasy.model.Seller;
import com.goeasy.service.DashboardService;
import com.goeasy.service.SellerService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class GenericController {
 

 @Autowired
 private SellerService sellerService;
 
 @Autowired
 private DashboardService dashboardService;
 
 
@RequestMapping(value = "/seller/orderindex", method = RequestMethod.GET)
public ModelAndView welcome(HttpServletRequest request) {
	System.out.println(" Inside order index ");
	Seller seller=sellerService.getSeller(request.getUserPrincipal().getName());
	if(seller!=null)
	{
	request.getSession().setAttribute( "sellerId", seller.getId() );

	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());
  //return "landing";
	
	return new ModelAndView("redirect:/seller/dashboard.html");
	}
	else
	{
		return new ModelAndView("redirect:/login-form.html");
	}
 }

@RequestMapping(value = "/seller/dashboard", method = RequestMethod.GET)
public ModelAndView displayUploadForm(HttpServletRequest request,@ModelAttribute("command")DashboardBean dashboardBean,
		  BindingResult result) {
	System.out.println("Inside dashboard");
	Map<String, Object> model = new HashMap<String, Object>();
	DashboardBean dbean=new DashboardBean();
	model.put("uploadValue",dbean);
	dashboardService.getDashboardDetails(HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("landing",model);
 }

@RequestMapping(value = "/seller/initialsetup", method = RequestMethod.GET)
public String initialsetup(HttpServletRequest request) {
	System.out.println(" Inside initialsetup ");
/*	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());*/
  return "initialsetup/initialsetup";
 }

@RequestMapping(value = "/seller/productInfo", method = RequestMethod.GET)
public String productInfo(HttpServletRequest request) {
	System.out.println(" Inside productInfo ");
/*	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());*/
  return "initialsetup/productInfo";
 }


@RequestMapping(value = "/seller/partnerInfo", method = RequestMethod.GET)
public String partnerInfo(HttpServletRequest request) {
	System.out.println(" Inside opartner info ");
/*	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());*/
  return "initialsetup/partnerInfo";
 }

@RequestMapping(value = "/seller/dailyactivities", method = RequestMethod.GET)
public String dailyactivities(HttpServletRequest request) {
	System.out.println(" Inside initialsetup ");
/*	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());*/
  return "dailyactivities/dailyactivities";
 }

@RequestMapping(value = "/login-form", method = RequestMethod.GET)
public String redirectlogin() {
	System.out.println(" Inside login-form ");
/*	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());*/
  return "login-form";
 }
}