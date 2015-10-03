package com.goeasy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.SellerBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.model.Seller;
import com.goeasy.service.SellerService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class SellerController {
 
 @Autowired
 private SellerService sellerService;
 
 
@RequestMapping(value = "/saveSeller", method = RequestMethod.POST)
public ModelAndView saveOrder(@ModelAttribute("command")SellerBean sellerBean, 
   BindingResult result) {
	System.out.println("Inside seller Ssave");
	System.out.println(" Seller id :"+sellerBean.getId());
  Seller seller=ConverterClass.prepareSellerModel(sellerBean);
  //seller.getRole().setRole("moderator");
  sellerService.addSeller(seller);
  return new ModelAndView("redirect:/login-form.html");
 }





 @RequestMapping(value="/sellers", method = RequestMethod.GET)
 public ModelAndView listSellers() {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("sellers",  ConverterClass.prepareListofSellerBean(sellerService.listSellers()));
 
  return new ModelAndView("sellerList", model);
 }

 @RequestMapping(value = "/addSeller", method = RequestMethod.GET)
 public ModelAndView addOrder(@ModelAttribute("command")SellerBean sellerBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("sellers", ConverterClass.prepareListofSellerBean(sellerService.listSellers()));
  return new ModelAndView("addSeller", model);
 }
 
 @RequestMapping(value = "/register", method = RequestMethod.GET)
 public ModelAndView register(@ModelAttribute("command")SellerBean sellerBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  return new ModelAndView("register", model);
 }
 
@RequestMapping(value = "/sellerindex", method = RequestMethod.GET)
public ModelAndView welcome() {
  return new ModelAndView("index");
 }

@RequestMapping(value = "/deleteSeller", method = RequestMethod.GET)
public ModelAndView deleteOrder(@ModelAttribute("command")SellerBean sellerBean,
   BindingResult result) {
  
  sellerService.deleteSeller(ConverterClass.prepareSellerModel(sellerBean));
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("seller", null);
  model.put("sellers", ConverterClass.prepareListofSellerBean(sellerService.listSellers()));
  return new ModelAndView("addSeller", model);
 }
 
@RequestMapping(value = "/editSeller", method = RequestMethod.GET)
public ModelAndView editOrder(@ModelAttribute("command")SellerBean sellerBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("seller", ConverterClass.prepareSellerBean(sellerService.getSeller(sellerBean.getId())));
  model.put("sellers", ConverterClass.prepareListofSellerBean(sellerService.listSellers()));
  return new ModelAndView("addSeller", model);
 }



 
 
}