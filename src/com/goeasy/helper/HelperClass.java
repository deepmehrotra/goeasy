package com.goeasy.helper;

import javax.servlet.http.HttpServletRequest;

import com.goeasy.dao.SellerDao;
import com.goeasy.dao.SellerDaoImpl;
import com.goeasy.model.Seller;


public class HelperClass {
	
	
	public static int getSellerIdfromSession(HttpServletRequest request)
	{
		int sellerId;
		System.out.println(" Inside seller id from session :"+request.getUserPrincipal().getName());
		if(request.getSession().getAttribute("sellerId")!=null)
		{
			sellerId=Integer.parseInt(request.getSession().getAttribute("sellerId").toString());
		}
		else
		{
		SellerDao sellerdao=new SellerDaoImpl();
		Seller seller=sellerdao.getSeller(request.getUserPrincipal().getName());
		request.getSession().setAttribute("sellerId", seller.getId());
		sellerId=seller.getId();
		}
		return sellerId;
	}

}
