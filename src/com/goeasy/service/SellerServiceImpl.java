package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.SellerDao;
import com.goeasy.model.Seller;

/**
 * @author Deep Mehrotra
 *
 */
@Service("sellerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SellerServiceImpl implements SellerService {

 @Autowired
 private SellerDao sellerDao;
 
 @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
 public void addSeller(Seller seller) {
	 sellerDao.addSeller(seller);
 }
 
 public List<Seller> listSellers() {
  return sellerDao.listSeller();
 }

 public Seller getSeller(int sellerid) {
  return sellerDao.getSeller(sellerid);
 }
 
 public void deleteSeller(Seller seller) {
	 sellerDao.deleteSeller(seller);
 }
 
 public Seller getSeller(String email)
 {
	 return sellerDao.getSeller(email); 
 }

}