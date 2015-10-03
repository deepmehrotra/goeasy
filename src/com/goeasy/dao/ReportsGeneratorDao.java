package com.goeasy.dao;

import java.util.Date;
import java.util.List;

import com.goeasy.bean.TotalShippedOrder;

/**
 * @author Deep Mehrotra
 *
 */
public interface ReportsGeneratorDao {
 
	public TotalShippedOrder getPartnerTSOdetails(String pcName,Date startDate ,Date endDate, int sellerId);

	 public List<TotalShippedOrder> getAllPartnerTSOdetails(Date startDate ,Date endDate, int sellerId);
	 
 
}