package com.goeasy.service;

import java.util.List;

import org.hibernate.Session;

import com.goeasy.model.TaxCategory;
import com.goeasy.model.TaxDetail;

/**
 * @author Deep Mehrotra
 *
 */
public interface TaxDetailService {
 
	 public void addTaxDetail(TaxDetail taxDetail , int sellerId);
	 
	 public List<TaxDetail> listTaxDetails(int sellerId);
	 
	 public List<TaxDetail> listTaxDetails(int sellerId,String taxOrTds);
	 
	 public TaxDetail getTaxDetail(int taxDetailId);
	 
	 public void deleteTaxDetail(TaxDetail taxDetail,int sellerId);
	 
	 public TaxCategory addTaxCategory(TaxCategory taxCategory, int sellerId);

	 public TaxCategory getTaxCategory(int tcId);

	 public List<TaxCategory> listTaxCategories(int sellerId);

	 public void deleteTaxCategory(TaxCategory taxCategory, int sellerId);
	 
	 public TaxCategory getTaxCategory(String catName, int sellerId);
	 
	 public void addPaymentTaxDetail(TaxDetail taxDetail, int sellerId);
	 
	 public TaxDetail addMonthlyTaxDetail(Session session, TaxDetail taxDetail, int sellerId);
	 
	 public TaxDetail addMonthlyTDSDetail(Session session, TaxDetail taxDetail, int sellerId);

	 public void addStatusTDSDetail(TaxDetail taxDetail, int sellerId);
}