package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.Partner;

/**
 * @author Deep Mehrotra
 *
 */
public interface PartnerDao {
 
 public void addPartner(Partner partner,int sellerId);

 public List<Partner> listPartner(int sellerId);
 
 public Partner getPartner(int partnerid);
 
 public void deletePartner(Partner partner,int sellerId);
 
 public Partner getPartner(String name ,int sellerId);
}