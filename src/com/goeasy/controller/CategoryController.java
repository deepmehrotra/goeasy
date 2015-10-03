package com.goeasy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.CategoryBean;
import com.goeasy.bean.ExpenseCategoryBean;
import com.goeasy.bean.TaxCategoryBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.helper.HelperClass;
import com.goeasy.helper.SaveContents;
import com.goeasy.model.Category;
import com.goeasy.model.TaxCategory;
import com.goeasy.service.CategoryService;
import com.goeasy.service.DownloadService;
import com.goeasy.service.TaxDetailService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class CategoryController {
 
 @Autowired
 private CategoryService categoryService;

 @Autowired
 private TaxDetailService taxDetailService;
 
 @Resource(name="downloadService")
	private DownloadService downloadService;
 @Resource(name="saveContents")
 private SaveContents saveContents;
 
@RequestMapping(value = "/seller/saveCategory", method = RequestMethod.POST)
public ModelAndView saveCategory(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean, 
   BindingResult result) {
	System.out.println("Inside order Ssave");
	System.out.println(" category id :"+categoryBean.getCatName());
	categoryBean.setSubCategory(false);
	Category category = ConverterClass.prepareCategoryModel(categoryBean);
	categoryService.addCategory(category,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/addCategory.html");
 }

@RequestMapping(value = "/seller/saveSubCategory", method = RequestMethod.POST)
public ModelAndView saveSubCategory(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean, 
   BindingResult result) {
	System.out.println("Inside order Ssave");
	System.out.println(" category id :"+categoryBean.getParentCatName());
	categoryBean.setSubCategory(true);
	Category category = ConverterClass.prepareCategoryModel(categoryBean);
	categoryService.addCategory(category,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/addCategory.html");
 }

@RequestMapping(value = "/seller/saveTaxCategory", method = RequestMethod.POST)
public ModelAndView saveTaxCategory(HttpServletRequest request,@ModelAttribute("command")TaxCategoryBean taxCategoryBean, 
   BindingResult result) {
	System.out.println("Inside tax category Ssave");
	System.out.println(" category id :"+taxCategoryBean.getTaxCatName());
	TaxCategory category = ConverterClass.prepareTaxCategoryModel(taxCategoryBean);
	taxDetailService.addTaxCategory(category, HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/listTaxCategories.html");
 }

@RequestMapping(value = "/seller/listTaxCategories", method = RequestMethod.GET)
public ModelAndView listTaxCategory(HttpServletRequest request,@ModelAttribute("command")TaxCategoryBean taxCategoryBean, 
   BindingResult result) {
	System.out.println("Inside list tax category");
	//System.out.println(" category id :"+taxCategoryBean.getTaxCatName());
	 Map<String, Object> model = new HashMap<String, Object>();
	  model.put("taxCategories",  ConverterClass.prepareListofTaxCategoryBean(taxDetailService.listTaxCategories(HelperClass.getSellerIdfromSession(request))));
	  return new ModelAndView("initialsetup/taxCategoryList", model);
 }
@RequestMapping(value = "/seller/addTaxCategory", method = RequestMethod.GET)
public String addTaxCategory(HttpServletRequest request,@ModelAttribute("command")TaxCategoryBean categoryBean,
  BindingResult result) {
	System.out.println(" Inside add tac category");
return "initialsetup/addTaxCategory";
}

 @RequestMapping(value="/seller/categories", method = RequestMethod.GET)
 public ModelAndView listCategory(HttpServletRequest request) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("categoryList", model);
 }

 @RequestMapping(value = "/seller/addCategory", method = RequestMethod.GET)
 public ModelAndView addCategory(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addCategory", model);
 }
 
 @RequestMapping(value = "/seller/changeInventorygroup", method = RequestMethod.GET)
 public ModelAndView changeInventorygroup(HttpServletRequest request,@RequestParam("catId") String catId,@ModelAttribute("command")CategoryBean categoryBean,
   BindingResult result) {
 	System.out.println("*********Inside change intentory category id "+catId);
 	Map<String, Object> model = new HashMap<String, Object>();
 	List<CategoryBean> categorylist=ConverterClass.prepareListofCategoryBean(categoryService.listParentCategories(HelperClass.getSellerIdfromSession(request)));
 	 Map<String,String> catageorymap=new HashMap<String, String>();
 	 Category category=categoryService.getCategory(Integer.parseInt(catId));
 	 if(category.getSubCategory()!=null)
 		 System.out.println("No of subcat :"+category.getSubCategory().size());
 	for(CategoryBean bean:categorylist)
 	 {
 		  catageorymap.put(String.valueOf(bean.getId()), bean.getCatName());
 		  
 	 }
 	 model.put("categorymap", catageorymap);
 	  model.put("category", ConverterClass.prepareCategoryBean(category));
 	  model.put("subcategory", ConverterClass.prepareListofCategoryBean(category.getSubCategory()));
 	  return new ModelAndView("initialsetup/viewInventorygroup", model);
 }
 
 @RequestMapping(value = "/seller/addSubCategory", method = RequestMethod.GET)
 public ModelAndView addSubCategory(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  List<CategoryBean> categorylist=ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request)));
  Map<String,String> catageorymap=new HashMap<String, String>();
  for(CategoryBean bean:categorylist)
  {
	  catageorymap.put(String.valueOf(bean.getId()), bean.getCatName());
	  
  }
  model.put("categorymap", catageorymap);
  model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listParentCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addSubCategory", model);
 }


@RequestMapping(value = "/seller/deleteCategory", method = RequestMethod.GET)
public ModelAndView deleteCategory(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
   BindingResult result) {
	System.out.println(" Order bean id todelete :"+categoryBean.getId());
	categoryService.deleteCategory(ConverterClass.prepareCategoryModel(categoryBean),HelperClass.getSellerIdfromSession(request));
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("category", null);
  model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addCategory", model);
 }


 
@RequestMapping(value = "/seller/editCategory", method = RequestMethod.GET)
public ModelAndView editCategory(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
   BindingResult result) {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("category", ConverterClass.prepareCategoryBean(categoryService.getCategory(categoryBean.getId())));
  model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("addCategory", model);
 }

@RequestMapping(value = "/seller/viewInventorygroup", method = RequestMethod.GET)
public ModelAndView viewsingleInventorygroup(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
  BindingResult result) {
	System.out.println("*********Inside view intentory category id "+categoryBean.getId());
	Map<String, Object> model = new HashMap<String, Object>();
	List<CategoryBean> categorylist=ConverterClass.prepareListofCategoryBean(categoryService.listParentCategories(HelperClass.getSellerIdfromSession(request)));
	 Map<String,String> catageorymap=new HashMap<String, String>();
	 Category category=categoryService.getCategory(categoryBean.getId());
	 if(category.getSubCategory()!=null)
		 System.out.println("No of subcat :"+category.getSubCategory().size());
	for(CategoryBean bean:categorylist)
	 {
		  catageorymap.put(String.valueOf(bean.getId()), bean.getCatName());
		  
	 }
	 model.put("categorymap", catageorymap);
	  model.put("category", ConverterClass.prepareCategoryBean(category));
	  model.put("subcategory", ConverterClass.prepareListofCategoryBean(category.getSubCategory()));
	  return new ModelAndView("initialsetup/viewInventorygroup", model);
}
@RequestMapping(value = "/seller/inventoryGroups", method = RequestMethod.GET)
public ModelAndView inventorygroups(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
  BindingResult result) {
	System.out.println(" Inside inventory controller");
	 Map<String, Object> model = new HashMap<String, Object>();
	  model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listParentCategories(HelperClass.getSellerIdfromSession(request))));
	  return new ModelAndView("initialsetup/inventorygroup", model);
}

@RequestMapping(value = "/seller/addInventoryGroup", method = RequestMethod.GET)
public ModelAndView addInventoryGroup(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
  BindingResult result) {
 Map<String, Object> model = new HashMap<String, Object>();
 model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request))));
 return new ModelAndView("initialsetup/addInventoryGroup", model);
}
@RequestMapping(value = "/seller/saveInventoryGroup", method = RequestMethod.POST)
public ModelAndView saveInventoryGroup(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean, 
   BindingResult result) {
	System.out.println("Inside category Ssave");
	System.out.println(" category id :"+categoryBean.getCatName());
	categoryBean.setSubCategory(false);
	Category category = ConverterClass.prepareCategoryModel(categoryBean);
	categoryService.addCategory(category,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/inventoryGroups.html");
 }

@RequestMapping(value = "/seller/addCatInventorygroup", method = RequestMethod.GET)
public ModelAndView addCatInventorygroup(HttpServletRequest request,@ModelAttribute("command")CategoryBean categoryBean,
  BindingResult result) {
 Map<String, Object> model = new HashMap<String, Object>();
 List<CategoryBean> categorylist=ConverterClass.prepareListofCategoryBean(categoryService.listParentCategories(HelperClass.getSellerIdfromSession(request)));
 Map<String,String> catageorymap=new HashMap<String, String>();
 for(CategoryBean bean:categorylist)
 {
	  catageorymap.put(String.valueOf(bean.getId()), bean.getCatName());
	  
 }
 model.put("categorymap", catageorymap);
 model.put("categories",  ConverterClass.prepareListofCategoryBean(categoryService.listCategories(HelperClass.getSellerIdfromSession(request))));
 return new ModelAndView("initialsetup/addCatInventorygroup", model);
}

@RequestMapping(value = "/seller/saveCatInventory", method = RequestMethod.POST)
public ModelAndView saveCatInventory(@ModelAttribute("command")CategoryBean categoryBean, 
   BindingResult result,HttpServletRequest request) {
	System.out.println("Inside category  Ssave");
	System.out.println("********* parent category name :"+categoryBean.getParentCatName());
	String parentcatid=request.getParameter("parentid");
	System.out.println(" Parent id in sav vhild : "+parentcatid);
	categoryBean.setSubCategory(true);
	Category category = ConverterClass.prepareCategoryModel(categoryBean);
	categoryService.addCategory(category,HelperClass.getSellerIdfromSession(request));
  return new ModelAndView("redirect:/seller/viewInventorygroup.html?id="+parentcatid);
 }

}