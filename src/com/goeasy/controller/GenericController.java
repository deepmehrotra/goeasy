package com.goeasy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goeasy.model.Seller;
import com.goeasy.service.SellerService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class GenericController {
 

 @Autowired
 private SellerService sellerService;

@RequestMapping(value = "/seller/orderindex", method = RequestMethod.GET)
public String welcome(HttpServletRequest request) {
	System.out.println(" Inside order index ");
	Seller seller=sellerService.getSeller(request.getUserPrincipal().getName());
	request.getSession().setAttribute( "sellerId", seller.getId() );

	System.out.println(" Afterlogin"+request.getUserPrincipal().getName());
  return "landing";
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