package com.goeasy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.ExpenseBean;
import com.goeasy.bean.ExpenseCategoryBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.helper.HelperClass;
import com.goeasy.model.ExpenseCategory;
import com.goeasy.model.Expenses;
import com.goeasy.service.ExpenseService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class ExpenseController {
 
 @Autowired
 private ExpenseService expenseService;

 
@RequestMapping(value = "/seller/saveExpenseCategory", method = RequestMethod.POST)
public ModelAndView saveExpenseCategory(HttpServletRequest request,@ModelAttribute("command")ExpenseCategoryBean categoryBean, 
   BindingResult result) {
	System.out.println("Inside expense category Ssave");
	
	categoryBean.setCreatedOn(new Date());;
	ExpenseCategory category = ConverterClass.prepareExpenseCategoryModel(categoryBean);
	expenseService.addExpenseCategory(category, HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/expenseCategories.html");
 }

@RequestMapping(value = "/seller/saveExpense", method = RequestMethod.POST)
public ModelAndView saveExpense(HttpServletRequest request,@ModelAttribute("command")ExpenseBean expenseBean, 
   BindingResult result) {
	System.out.println("Inside expense category Ssave");
	
	Expenses expense = ConverterClass.prepareExpenseModel(expenseBean);
	expenseService.addExpense(expense, HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/expenselist.html");
 }

 @RequestMapping(value="/seller/expenseCategories", method = RequestMethod.GET)
 public ModelAndView listExpenseCategory(HttpServletRequest request) {
	 System.out.println(" Inside expense category ies");
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("expenseCategories",  ConverterClass.prepareListofExpenseCategoryBean(expenseService.listExpenseCategories(HelperClass.getSellerIdfromSession(request))));
  /*return new ModelAndView("expenseCategoryList", model);*/
  return new ModelAndView("initialsetup/expenseCategoryList", model);
 }
 
 @RequestMapping(value="/seller/expenselist", method = RequestMethod.GET)
 public ModelAndView listExpenses(HttpServletRequest request) {
	 System.out.println(" Inside expses");
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("expenses",  ConverterClass.prepareListofExpenseBean(expenseService.listExpenses(HelperClass.getSellerIdfromSession(request))));
  System.out.println(" Inside expense list got expenses");
  return new ModelAndView("initialsetup/viewExpenseGroup", model);
 }

 @RequestMapping(value = "/seller/addExpenseCategory", method = RequestMethod.GET)
 public String addExpenseCategory(HttpServletRequest request,@ModelAttribute("command")ExpenseCategoryBean categoryBean,
   BindingResult result) {
 return "initialsetup/addExpenseCategory";
 }
 
 @RequestMapping(value = "/seller/viewExpenseGroup", method = RequestMethod.GET)
 public ModelAndView viewExpenseCategory(HttpServletRequest request,@ModelAttribute("command")ExpenseCategoryBean categoryBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  ExpenseCategoryBean expcatbean= ConverterClass.prepareExpenseCategoryBean(expenseService.getExpenseCategory(categoryBean.getExpcategoryId()));
  model.put("expenseCategory",expcatbean );
 model.put("expenses", expcatbean.getExpenses());
 // return new ModelAndView("addExpenseCategory", model);
  return new ModelAndView("initialsetup/viewExpenseGroup", model);
 }
 
 @RequestMapping(value = "/seller/addExpense", method = RequestMethod.GET)
 public ModelAndView addExpense(HttpServletRequest request,@ModelAttribute("command")ExpenseBean expense,
   BindingResult result) {
	 Map<String, Object> model = new HashMap<String, Object>();
	 System.out.println("Inside Add Expense");
	 Map<String,String> catmap=new HashMap<String, String>();
	 List<ExpenseCategory> categorylist =expenseService.listExpenseCategories(HelperClass.getSellerIdfromSession(request));
	 for(ExpenseCategory expcat:categorylist)
	 {
		 catmap.put(expcat.getExpcatName(), expcat.getExpcatName());
	 }
	 model.put("catmap", catmap);
	 
	 // return new ModelAndView("addExpenseCategory", model);
	  return new ModelAndView("initialsetup/addExpense", model);
  
 }


@RequestMapping(value = "/seller/deleteExpenseCategory", method = RequestMethod.GET)
public ModelAndView deleteExpenseCategory(HttpServletRequest request,@ModelAttribute("command")ExpenseCategoryBean categoryBean,
   BindingResult result) {
	System.out.println(" Exp cat bean id todelete :"+categoryBean.getExpcategoryId());
	expenseService.deleteExpenseCategory(ConverterClass.prepareExpenseCategoryModel(categoryBean),HelperClass.getSellerIdfromSession(request));
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("expensecategory", null);
  model.put("expensecategories",  ConverterClass.prepareListofExpenseCategoryBean(expenseService.listExpenseCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addExpenseCategory", model);
 }

@RequestMapping(value = "/seller/deleteExpense", method = RequestMethod.GET)
public ModelAndView deleteCategory(HttpServletRequest request,@ModelAttribute("command")ExpenseBean expenseBean,
   BindingResult result) {
	System.out.println(" Expense id todelete :"+expenseBean.getExpenseId());
	expenseService.deleteExpense(ConverterClass.prepareExpenseModel(expenseBean),HelperClass.getSellerIdfromSession(request));
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("expense", null);
  model.put("expenses",  ConverterClass.prepareListofExpenseBean(expenseService.listExpenses(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addExpense", model);
 }


 
@RequestMapping(value = "/seller/editExpenseCategory", method = RequestMethod.GET)
public ModelAndView editExpenseCategory(HttpServletRequest request,@ModelAttribute("command")ExpenseCategoryBean categoryBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("expenseCategory", ConverterClass.prepareExpenseCategoryBean(expenseService.getExpenseCategory(categoryBean.getExpcategoryId())));
  model.put("expenseCategories",  ConverterClass.prepareListofExpenseCategoryBean(expenseService.listExpenseCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addExpenseCategory", model);
 }

@RequestMapping(value = "/seller/editExpense", method = RequestMethod.GET)
public ModelAndView editExpense(HttpServletRequest request,@ModelAttribute("command")ExpenseBean expenseBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  Map<String,String> catmap=new HashMap<String, String>();
  List<ExpenseCategory> categorylist =expenseService.listExpenseCategories(HelperClass.getSellerIdfromSession(request));
	 for(ExpenseCategory expcat:categorylist)
	 {
		 catmap.put(expcat.getExpcatName(), expcat.getExpcatName());
	 }
	 model.put("catmap", catmap);
	 System.out.println("Expense edit id :"+expenseBean.getExpenseId());
  model.put("expense", ConverterClass.prepareExpenseBean(expenseService.getExpense(expenseBean.getExpenseId())));
 
  return new ModelAndView("initialsetup/addExpense", model);
 }

}