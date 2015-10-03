package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.TaxCategory;
import com.goeasy.model.TaxDetail;

/**
 * @author Deep Mehrotra
 *
 */
public interface TaxDetailsDao {
 
 public TaxDetail addTaxDetail(TaxDetail taxDetail , int sellerId);
 
 public List<TaxDetail> listTaxDetails(int sellerId);
 
 public TaxDetail getTaxDetail(int taxDetailId);
 
 public void deleteTaxDetail(TaxDetail taxDetail,int sellerId);

public TaxCategory addTaxCategory(TaxCategory taxCategory, int sellerId);

public TaxCategory getTaxCategory(int tcId);

List<TaxCategory> listTaxCategories(int sellerId);

public void deleteTaxCategory(TaxCategory taxCategory, int sellerId);

public TaxCategory getTaxCategory(String catName, int sellerId);
 
}