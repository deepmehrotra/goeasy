package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.TaxDetailsDao;
import com.goeasy.model.TaxCategory;
import com.goeasy.model.TaxDetail;

/**
 * @author Deep Mehortra
 *
 */
@Service("taxDetailService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaxDetailServiceImpl implements TaxDetailService {

 @Autowired
 private TaxDetailsDao taxDetailDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addTaxDetail(TaxDetail taxDetail , int sellerId) {
	taxDetailDao.addTaxDetail(taxDetail, sellerId);
	
}

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public List<TaxDetail> listTaxDetails(int sellerId) {
	return taxDetailDao.listTaxDetails(sellerId);
	
}

@Override
public TaxDetail getTaxDetail(int taxDetailId){
	return taxDetailDao.getTaxDetail(taxDetailId);
}

@Override
public void deleteTaxDetail(TaxDetail taxDetail,int sellerId)
{
	taxDetailDao.deleteTaxDetail(taxDetail, sellerId);
}
@Override
public TaxCategory addTaxCategory(TaxCategory taxCategory, int sellerId)
{
	return taxDetailDao.addTaxCategory(taxCategory, sellerId);
}

@Override
public TaxCategory getTaxCategory(int tcId)
{
	return taxDetailDao.getTaxCategory(tcId);
}

@Override
public List<TaxCategory> listTaxCategories(int sellerId)
{
	return taxDetailDao.listTaxCategories(sellerId);
}

@Override
public void deleteTaxCategory(TaxCategory taxCategory, int sellerId)
{
	taxDetailDao.deleteTaxCategory(taxCategory,sellerId);
}

@Override
public TaxCategory getTaxCategory(String catName, int sellerId)
{
	return taxDetailDao.getTaxCategory(catName, sellerId);
}

}