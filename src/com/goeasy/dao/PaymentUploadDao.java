package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.PaymentUpload;

/**
 * @author Deep Mehrotra
 *
 */
public interface PaymentUploadDao {
 
 public void addPaymentUpload(PaymentUpload upload , int sellerId);

 public List<PaymentUpload> listPaymentUploads(int sellerId);
 
 public PaymentUpload getPaymentUpload(int paymentUploadId);
 
 public void deletePaymentUpload(PaymentUpload payupload,int sellerId);
 
 public PaymentUpload getManualPayment(int sellerId);
}