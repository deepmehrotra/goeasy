package com.goeasy.service;

import java.util.List;

import com.goeasy.model.Partner;

/**
 * @author Deep Mehrotra
 *
 */
public interface PartnerService {
 
 public void addPartner(Partner partner,int sellerId);

 public List<Partner> listPartners(int sellerId);
 
 public Partner getPartner(int partnerid);
 
 public Partner getPartner(String name ,int sellerId);
 
 public void deletePartner(Partner partner,int sellerId);
}