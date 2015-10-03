package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.PartnerDao;
import com.goeasy.model.Partner;

/**
 * @author Deep Mehrotra
 *
 */
@Service("partnerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PartnerServiceImpl implements PartnerService {

 @Autowired
 private PartnerDao partnerDao;
 
 @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
 public void addPartner(Partner partner,int sellerId) {
	 partnerDao.addPartner(partner,sellerId);
 }
 
 public List<Partner> listPartners(int sellerId) {
  return partnerDao.listPartner(sellerId);
 }

 public Partner getPartner(int partnerid) {
  return partnerDao.getPartner(partnerid);
 }
 
 public void deletePartner(Partner partner,int sellerId) {
	 partnerDao.deletePartner(partner,sellerId);
 }
 public Partner getPartner(String name ,int sellerId)
 {
	 return partnerDao.getPartner(name,sellerId);
 }
}