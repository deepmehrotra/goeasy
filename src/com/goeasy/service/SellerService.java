package com.goeasy.service;

import java.util.List;

import com.goeasy.model.Seller;

/**
 * @author Deep Mehrotra
 *
 */
public interface SellerService {
 
 public void addSeller(Seller seller);

 public List<Seller> listSellers();
 
 public Seller getSeller(int sellerid);
 
 public void deleteSeller(Seller seller);
 
 public Seller getSeller(String email);
}