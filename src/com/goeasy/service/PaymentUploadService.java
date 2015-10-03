package com.goeasy.service;

import java.util.List;

import com.goeasy.model.PaymentUpload;

/**
 * @author Deep Mehrotra
 *
 */
public interface PaymentUploadService {
 
 public void addPaymentUpload(PaymentUpload expense , int sellerId);

 public List<PaymentUpload> listPaymentUploads(int sellerId);
 
 public PaymentUpload getPaymentUpload(int paymentUploadId);
 
 public void deletePaymentUpload(PaymentUpload payupload,int sellerId);
 
 public PaymentUpload getManualPayment(int sellerId);
}