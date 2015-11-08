package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.ManualChargesDao;
import com.goeasy.model.ManualCharges;

/**
 * @author Deep Mehortra
 *
 */
@Service("manualChargesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ManualChargesServiceImpl implements ManualChargesService {

 @Autowired
 private ManualChargesDao manualChargesDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addManualCharges(ManualCharges manualCharges , int sellerId) {
	manualChargesDao.addManualCharges(manualCharges, sellerId);
	
}

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public List<ManualCharges> listManualCharges(int sellerId) {
	return manualChargesDao.listManualCharges(sellerId);
	
}

@Override
public ManualCharges getManualCharges(int mcId)
{
	return manualChargesDao.getManualCharges(mcId);
}
@Override
public void deleteManualCharges(ManualCharges manualCharges,int sellerId) {
	manualChargesDao.deleteManualCharges(manualCharges, sellerId);
}

@Override
public Double getMCforPaymentID(String paymentId, int sellerId)
{
	return manualChargesDao.getMCforPaymentID(paymentId, sellerId);
}

}