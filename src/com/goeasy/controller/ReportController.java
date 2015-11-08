package com.goeasy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goeasy.bean.TotalShippedOrder;
import com.goeasy.helper.HelperClass;
import com.goeasy.model.Order;
import com.goeasy.model.Partner;
import com.goeasy.service.OrderService;
import com.goeasy.service.PartnerService;
import com.goeasy.service.ReportDownloadService;
import com.goeasy.service.ReportGeneratorService;


/**
 * @author Deep Mehrotra
 *
 */
@Controller
public class ReportController {
 

@Resource(name="reportGeneratorService")
private ReportGeneratorService reportGeneratorService;

@Resource(name="reportDownloadService")
private ReportDownloadService reportDownloadService;

@Resource(name="partnerService")
private PartnerService partnerService;

@Autowired
private OrderService orderService;

private static final SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");


@RequestMapping(value = "/seller/getAllReports", method = RequestMethod.GET)
public String displayForm() {
	
		return "reports/allreports";
	}

@RequestMapping(value = "/seller/getReportsFilter", method = RequestMethod.GET)
public ModelAndView addManualPayment(HttpServletRequest request) {
	System.out.println(" Inside getReportsFilter");
 Map<String, Object> model = new HashMap<String, Object>();
 List<String> partnerlist=new ArrayList<String>();
 List<Partner> partners=partnerService.listPartners(HelperClass.getSellerIdfromSession(request));
 for(Partner partner:partners)
	 partnerlist.add(partner.getPcName());
 String reportName=request.getParameter("reportName");
 model.put("reportName",reportName);
 model.put("partnerlist", partnerlist);
 return new ModelAndView("reports/filterReports", model);
}


/*@RequestMapping(value = "/seller/getReport", method = RequestMethod.POST)
public ModelAndView getReport(HttpServletRequest request) {
	System.out.println(" Inside getReportsFilter");
 Map<String, Object> model = new HashMap<String, Object>();
 List<TotalShippedOrder> ttso=new ArrayList<>();
 String reportName=request.getParameter("reportName");
 Date startDate=new Date(request.getParameter("startdate"));
 Date endDate=new Date(request.getParameter("enddate"));
 String partner=request.getParameter("toggler");
 String selectedPartner=request.getParameter("selectedPartner");
 System.out.println(" Cat :"+partner);
 if(partner!=null&&partner.equals("allPartners"))
 {
	 ttso= reportGeneratorService.getAllPartnerTSOdetails(startDate, endDate,  HelperClass.getSellerIdfromSession(request));
	 System.out.println(" Got response ttso size in controller: "+ttso.size());
 }
 else
 {
	 reportGeneratorService.getPartnerTSOdetails(selectedPartner, startDate, endDate, HelperClass.getSellerIdfromSession(request));
 }
 model.put("ttsolist",ttso);
 
 model.put("reportName",reportName);
 model.put("partnerlist",partnerlist);
 return new ModelAndView("reports/viewGraphReport", model);
}
*/

@RequestMapping(value = "/seller/getReport", method = RequestMethod.POST)
public ModelAndView getReport(HttpServletRequest request) {
	System.out.println(" Inside getReportsFilter");
 Map<String, Object> model = new HashMap<String, Object>();
 List<TotalShippedOrder> ttso=new ArrayList<>();
 String reportName=request.getParameter("reportName");
 Date startDate=new Date(request.getParameter("startdate"));
 Date endDate=new Date(request.getParameter("enddate"));
 String partner=request.getParameter("toggler");
 String selectedPartner=request.getParameter("selectedPartner");
 System.out.println(" Cat :"+partner);
 
 if(partner!=null&&partner.equals("allPartners"))
 {
	 ttso= reportGeneratorService.getAllPartnerTSOdetails(startDate, endDate,  HelperClass.getSellerIdfromSession(request));
	 if(ttso!=null)
	 System.out.println(" ****Inside controller after gettitng ttso objkect : "+ttso.size());
	 else
		 System.out.println(" TTSO object is geting null");
	 
	 model.put("ttsolist",ttso);
	 if(ttso.size()>0)
	 {System.out.println(" Citi quantity size : "+ttso.get(0).getCityQuantity());
	 System.out.println(" Citi percent size : "+ttso.get(0).getCityPercentage());
		 model.put("citicount", ttso.get(0).getCityQuantity());
		 model.put("citipercent", ttso.get(0).getCityPercentage());
	 }
	 Collections.sort(ttso, new TotalShippedOrder.OrderByNR());
	 model.put("NRsortedttso", getSortedList(ttso));
	 Collections.sort(ttso, new TotalShippedOrder.OrderByReturnamount());
	 model.put("returnAmountsortedttso", getSortedList(ttso));
	 Collections.sort(ttso, new TotalShippedOrder.OrderByReturnQuantity());
	 model.put("returnQsortedttso", getSortedList(ttso));
	 Collections.sort(ttso, new TotalShippedOrder.OrderBySaleQuantity());
	 model.put("saleQsortedttso", getSortedList(ttso));
	 Collections.sort(ttso, new TotalShippedOrder.OrderBySaleamount());
	 model.put("saleAmounrsortedttso", getSortedList(ttso));
 }
 else
 {
	 reportGeneratorService.getPartnerTSOdetails(selectedPartner, startDate, endDate, HelperClass.getSellerIdfromSession(request));
 }
model.put("period", dateFormat.format(startDate)+" to "+dateFormat.format(endDate));
/* 
 model.put("reportName",reportName);
 model.put("partnerlist",partnerlist);*/
 return new ModelAndView("reports/viewGraphReport", model);
}




@RequestMapping(value = "/seller/downloadreport", method = RequestMethod.POST)
public void downloadreport(HttpServletRequest request ,HttpServletResponse response) {
	System.out.println(" Inside downloadreport");
 Map<String, Object> model = new HashMap<String, Object>();
 List<TotalShippedOrder> ttso=new ArrayList<>();
 List<Order> orderlist=new ArrayList<>();
 String reportName=request.getParameter("reportName");
 Date startDate=new Date(request.getParameter("startdate"));
 Date endDate=new Date(request.getParameter("enddate"));
 String partner=request.getParameter("toggler");
 String selectedPartner=request.getParameter("selectedPartner");
 String[] reportheaders=request.getParameterValues("headers");
 System.out.println(" Cat :"+partner);
 if(partner!=null&&partner.equals("allPartners"))
 {
	 orderlist=orderService.findOrdersbyDate("orderDate", startDate, endDate, HelperClass.getSellerIdfromSession(request));
	 try {
		reportDownloadService.downloadReport(response, orderlist, reportheaders,reportName, HelperClass.getSellerIdfromSession(request));
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println(" Inside report controller exception in calling report download service : "+e.getLocalizedMessage());
		e.printStackTrace();
	}
	 System.out.println(" Got response ttso size in controller: "+ttso.size());
 }
 else
 {
	 reportGeneratorService.getPartnerTSOdetails(selectedPartner, startDate, endDate, HelperClass.getSellerIdfromSession(request));
 }
 /* 
 model.put("reportName",reportName);
 model.put("partnerlist",partnerlist);*/
 
}

public List<TotalShippedOrder> getSortedList(List<TotalShippedOrder> ttso)
{
	List<TotalShippedOrder> returnlist=new ArrayList<TotalShippedOrder>();
	TotalShippedOrder fifthreco=null;
	if(ttso!=null&&ttso.size()>4)
	{
		
	returnlist.add(ttso.get(0));
	returnlist.add(ttso.get(1));
	returnlist.add(ttso.get(2));
	returnlist.add(ttso.get(3));
	int i=0;
	fifthreco=new TotalShippedOrder();
	for(TotalShippedOrder newttso:ttso)
	{
		
		if(i>3)
		{
		fifthreco.setSaleQuantity(fifthreco.getSaleQuantity()+newttso.getSaleQuantity());
		fifthreco.setSaleQuantityPercent(fifthreco.getSaleQuantityPercent()+newttso.getSaleQuantityPercent());
		fifthreco.setReturnQuantity(fifthreco.getReturnQuantity()+newttso.getReturnQuantity());
		fifthreco.setReturnQuantityPercent(fifthreco.getReturnQuantityPercent()+newttso.getReturnQuantityPercent());
		fifthreco.setNr(fifthreco.getNr()+newttso.getNr());
		fifthreco.setNrPercent(fifthreco.getNrPercent()+newttso.getNrPercent());
		fifthreco.setReturnAmount(fifthreco.getReturnAmount()+newttso.getReturnAmount());
		fifthreco.setReturnAmountPercent(fifthreco.getReturnAmountPercent()+newttso.getReturnAmountPercent());
		fifthreco.setNetSaleAmount(fifthreco.getNetSaleAmount()+newttso.getNetSaleAmount());
		fifthreco.setNetSaleAmountPercent(fifthreco.getNetSaleAmountPercent()+newttso.getNetSaleAmountPercent());
		fifthreco.setNoOfDeliveredOrder(fifthreco.getNoOfDeliveredOrder()+newttso.getNoOfDeliveredOrder());
		fifthreco.setDeliveredOrderPercent(fifthreco.getDeliveredOrderPercent()+newttso.getDeliveredOrderPercent());
		fifthreco.setNoOfReturnOrder(fifthreco.getNoOfReturnOrder()+newttso.getNoOfReturnOrder());
		fifthreco.setReturnOrderPercent(fifthreco.getNetSaleAmount()+newttso.getNetSaleAmount());
		fifthreco.setNoOfRTOOrder(fifthreco.getNoOfRTOOrder()+newttso.getNoOfRTOOrder());
		fifthreco.setRTOOrderPercent(fifthreco.getRTOOrderPercent()+newttso.getRTOOrderPercent());
		fifthreco.setNoOfRTOLimitCrossed(fifthreco.getNoOfRTOLimitCrossed()+newttso.getNoOfRTOLimitCrossed());
		fifthreco.setRTOLimitCrossedPercent(fifthreco.getRTOLimitCrossedPercent()+newttso.getRTOLimitCrossedPercent());
		fifthreco.setNoOfReturnLimitCrossed(fifthreco.getNoOfReturnLimitCrossed()+newttso.getNoOfReturnLimitCrossed());
		fifthreco.setReturnLimitCrossedPercent(fifthreco.getReturnLimitCrossedPercent()+newttso.getReturnLimitCrossedPercent());
		fifthreco.setNoOfActionableOrders(fifthreco.getNoOfActionableOrders()+newttso.getNoOfActionableOrders());
		fifthreco.setActionableOrdersPercent(fifthreco.getActionableOrdersPercent()+newttso.getActionableOrdersPercent());
		fifthreco.setNoOfSettledOrders(fifthreco.getNoOfSettledOrders()+newttso.getNoOfSettledOrders());
		fifthreco.setSettledOrdersPercent(fifthreco.getSettledOrdersPercent()+newttso.getSettledOrdersPercent());
		}
		i++;
	}
	
	fifthreco.setPcName("Others");
	returnlist.add(fifthreco);
	
	
	}
	else if(ttso!=null&&ttso.size()<5&&ttso.size()>0)
	{
		for(int i=0;i<ttso.size();i++)
		{
			returnlist.add(ttso.get(i));
		}
	}
	return returnlist;
	
	
	
	
}

}