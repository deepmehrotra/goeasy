package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.ManualCharges;

/**
 * @author Deep Mehrotra
 *
 */
public interface ManualChargesDao {
 
 public void addManualCharges(ManualCharges manualCharges , int sellerId);
 
 public List<ManualCharges> listManualCharges(int sellerId);

 public ManualCharges getManualCharges(int mcId);
 
 public void deleteManualCharges(ManualCharges manualCharges,int sellerId);

public Double getMCforPaymentID(String paymentId, int sellerId);
 
}