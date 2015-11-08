package com.goeasy.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.goeasy.bean.ProductBean;
import com.goeasy.helper.ConverterClass;
import com.goeasy.helper.FileUploadForm;
import com.goeasy.helper.HelperClass;
import com.goeasy.helper.SaveContents;
import com.goeasy.helper.ValidateUpload;
import com.goeasy.model.Product;
import com.goeasy.service.DownloadService;
import com.goeasy.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class ProductController {
 
 @Autowired
 private ProductService productService;
 @Resource(name="downloadService")
	private DownloadService downloadService;
 @Resource(name="saveContents")
 private SaveContents saveContents;
 
 @RequestMapping(value = "/seller/saveProduct", method = RequestMethod.POST)
 public ModelAndView saveProduct(HttpServletRequest request,@ModelAttribute("command")ProductBean productBean, 
    BindingResult result) {
 	System.out.println("Inside Product Ssave");
 	System.out.println(" Product SKU :"+productBean.getProductSkuCode());
 	Product product = ConverterClass.prepareProductModel(productBean);
 	productService.addProduct(product,HelperClass.getSellerIdfromSession(request));
   return new ModelAndView("redirect:/seller/addProduct.html");
  }
 
 @RequestMapping(value = "/seller/saveProductJson", method = RequestMethod.POST)
 public @ResponseBody String saveProductJson(HttpServletRequest request) {
 	System.out.println("Inside Product Ssave");
 	Map<String, Object> model = new HashMap<String, Object>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	Product product =new Product();
 	System.out.println("Product id "+request.getParameter("productId"));
 	 int productId=0;
 	if(request.getParameter("productId")!=null&&request.getParameter("productId").toString().length()!=0)
 	{
 		productId=Integer.parseInt(request.getParameter("productId"));
 	}
 	String productSkuCode=request.getParameter("productSkuCode")!=null?request.getParameter("productSkuCode"):""; 
	   String productName=request.getParameter("productName")!=null?request.getParameter("productName"):"";
	   String categoryName=request.getParameter("categoryName")!=null?request.getParameter("categoryName"):"";
	 float productPrice=request.getParameter("productPrice")!=null?Float.valueOf(request.getParameter("productPrice")):0;
	   long quantity=request.getParameter("quantity")!=null?Long.parseLong(request.getParameter("quantity")):0;
	   long threholdLimit=request.getParameter("threholdLimit")!=null?Long.parseLong(request.getParameter("threholdLimit")):0;
	   String channelSKU=request.getParameter("channelSKU")!=null?request.getParameter("channelSKU"):"";
	   
	   if(productId!=0)
	   {
		   product.setProductId(productId);
		   
	   }
	   else
	   {
		   product.setProductDate(new Date());
		   product.setCategoryName(categoryName);
	   }
	   
	   product.setProductSkuCode(productSkuCode);
	   product.setProductName(productName);
	   product.setProductPrice(productPrice);
	   product.setQuantity(quantity);
	   product.setThreholdLimit(threholdLimit);
	   product.setChannelSKU(channelSKU);
	   productService.addProduct(product, HelperClass.getSellerIdfromSession(request)); 
	
	   model.put("Result", "OK");
		  model.put("Record",ConverterClass.prepareProductBean(product));
		  String jsonArray = gson.toJson(model);
	 	   return jsonArray;
  }

 /**
  * Downloads the report as an Excel format. 
  * <p>
  * Make sure this method doesn't return any model. Otherwise, you'll get 
  * an "IllegalStateException: getOutputStream() has already been called for this response"
  */
 @RequestMapping(value = "/seller/download/Product/xls", method = RequestMethod.GET)
 public void getXLS(HttpServletResponse response) throws ClassNotFoundException {
 	
 	// Delegate to downloadService. Make sure to pass an instance of HttpServletResponse 
 	downloadService.downloadProductXLS(response);
 }
 
 @RequestMapping(value = "/seller/download/Inventoryxls", method = RequestMethod.GET)
 public void getInventoryXLS(HttpServletResponse response) throws ClassNotFoundException {
 	
 	// Delegate to downloadService. Make sure to pass an instance of HttpServletResponse 
 	downloadService.downloadInventoryXLS(response);
 }

 /**
  * Redirect to upload download page. 
  * <p>
  
  */
 @RequestMapping(value = "/seller/Productsheet", method = RequestMethod.GET)
 public String displayForm() {
 	
 		return "product_upload_form";
 	}
 
 @RequestMapping(value = "/seller/Inventorysheet", method = RequestMethod.GET)
 public String InventoryForm() {
 	
 		return "dailyactivities/inventory_upload_form";
 	}


 @RequestMapping(value = "/seller/inventoryList", method = RequestMethod.GET)
 public String redirectIventoryPage() {
 	
 		return "dailyactivities/productInventories";
 	}


 /* @RequestMapping(value="/seller/products", method = RequestMethod.GET)
  public ModelAndView listProducts(HttpServletRequest request) {
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
   return new ModelAndView("productList", model);
  }*/
  
  @RequestMapping(value="/seller/listProductJson", method = RequestMethod.POST)
  public @ResponseBody String listProductsJSON(HttpServletRequest request) {
	  System.out.println("Inside product list json");
   Map<String, Object> model = new HashMap<String, Object>();
   Gson gson = new GsonBuilder().setPrettyPrinting().create();
   model.put("Records",ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
   model.put("Result", "OK");
   String jsonArray = gson.toJson(model);
	   return jsonArray;
  }

  @RequestMapping(value="/seller/showInventory", method = RequestMethod.POST)
  public  @ResponseBody String inventoryList(HttpServletRequest request) {
   Map<String, Object> model = new HashMap<String, Object>();
   Gson gson = new GsonBuilder().setPrettyPrinting().create();
   String action=request.getParameter("action");
   int sellerId=HelperClass.getSellerIdfromSession(request);
   if(action!=null&&action.equals("list"))
	  {
	   model.put("Records",ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
		
	  }
   else
   {
	   System.out.println(" Getting inventory update subtract value : : "+request.getParameter("quantityToSubstract"));
	   int productId=request.getParameter("productId")!=null?Integer.parseInt(request.getParameter("productId")):0;
	   String productSkuCode=request.getParameter("productSkuCode")!=null?request.getParameter("productSkuCode"):"";
	   int currentInventory=request.getParameter("currentInventory")!=null?Integer.parseInt(request.getParameter("currentInventory")):0;
	   int quantityToAdd=(request.getParameter("quantityToAdd")!=null&&request.getParameter("quantityToAdd").toString().length()!=0)?Integer.parseInt(request.getParameter("quantityToAdd")):0;
	   int quantityToSubstract=(request.getParameter("quantityToSubstract")!=null&&request.getParameter("quantityToSubstract").toString().length()!=0)?Integer.parseInt(request.getParameter("quantityToSubstract")):0;
	   
	 
	   productService.updateInventory(productSkuCode, currentInventory, quantityToAdd, quantityToSubstract,true,sellerId); 
	   model.put("Record",ConverterClass.prepareProductBean(productService.getProduct(productId)));
   }
   model.put("Result", "OK");
	 
	  String jsonArray = gson.toJson(model);
 	   return jsonArray;
  }
  /* @RequestMapping(value = "/seller/addProduct", method = RequestMethod.GET)
  public ModelAndView addOrder(HttpServletRequest request,@ModelAttribute("command")ProductBean productBean,
    BindingResult result) {
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
   return new ModelAndView("addProduct", model);
  }
  

 @RequestMapping(value = "/seller/deleteProduct", method = RequestMethod.GET)
 public ModelAndView deleteOrder(HttpServletRequest request,@ModelAttribute("command")ProductBean productBean,
    BindingResult result) {
 	System.out.println(" Product bean id todelete :"+productBean.getProductId());
 	productService.deleteProduct(ConverterClass.prepareProductModel(productBean),HelperClass.getSellerIdfromSession(request));
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("product", null);
   model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
   return new ModelAndView("addProduct", model);
  }
  
 @RequestMapping(value = "/seller/editProduct", method = RequestMethod.GET)
 public ModelAndView editOrder(HttpServletRequest request,@ModelAttribute("command")ProductBean productBean,
    BindingResult result) {
   Map<String, Object> model = new HashMap<String, Object>();
   model.put("product", ConverterClass.prepareProductBean(productService.getProduct(productBean.getProductId())));
   model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
   return new ModelAndView("addProduct", model);
  }*/

 @RequestMapping(value = "/seller/saveProductSheet", method = RequestMethod.POST)
 public ModelAndView save(HttpServletRequest request,
 		@ModelAttribute("uploadForm") FileUploadForm uploadForm,
 				Model map) {
 	System.out.println("Inside save method");
 	List<MultipartFile> files = uploadForm.getFiles();

 	List<String> fileNames = new ArrayList<String>();
 	MultipartFile fileinput=files.get(0);
 	System.out.println(" got file");
 	int sellerId=HelperClass.getSellerIdfromSession(request);
 	if(null != files && files.size() > 0) {
 		fileNames.add(files.get(0).getOriginalFilename());
 		try{
 			System.out.println(" Filename : "+files.get(0).getOriginalFilename());
 			System.out.println(" Filename : "+files.get(0).getName());
 		 ValidateUpload.validateOfficeData(files.get(0));
 		 System.out.println(" fileinput "+fileinput.getName());
 		 saveContents.saveProductContents(files.get(0),sellerId);
 		}
 		catch (Exception e) {
 			System.out.println("Inside exception , filetype not accepted "+e.getLocalizedMessage());
 			
 		}
           
 	}
 	
 	 Map<String, Object> model = new HashMap<String, Object>();
 	  model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
 	  return new ModelAndView("productList", model);
 	
 }
  
 @RequestMapping(value = "/seller/saveInventorySheet", method = RequestMethod.POST)
 public ModelAndView saveInventories(HttpServletRequest request,
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
 		 saveContents.saveInventoryDetails(files.get(0),sellerId);
 		}
 		catch (Exception e) {
 			System.out.println("Inside exception , filetype not accepted "+e.getLocalizedMessage());
 			
 		}
           
 	}
 	
 	 Map<String, Object> model = new HashMap<String, Object>();
 	  model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
 	  return new ModelAndView("productList", model);
 	
 }
 @RequestMapping(value="/seller/Product", method = RequestMethod.GET)
 public String Product() {
 /* Map<String, Object> model = new HashMap<String, Object>();
  model.put("products",  ConverterClass.prepareListofProductBean(productService.listProducts(HelperClass.getSellerIdfromSession(request))));
  return new ModelAndView("initialsetup/Product", model);
  */
	 return "initialsetup/Product";
 }
  
  
 
}