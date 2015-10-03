package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.PaymentUploadDao;
import com.goeasy.model.PaymentUpload;


/**
 * @author Deep Mehrotra
 *
 */
@Service("paymentUploadService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PaymentUploadServiceImpl implements PaymentUploadService{

@Autowired
private PaymentUploadDao paymentUploadDao;
 

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
 public void addPaymentUpload(PaymentUpload upload , int sellerId)
 {
	 paymentUploadDao.addPaymentUpload(upload, sellerId);
 }
 
 
 @Override
public List<PaymentUpload> listPaymentUploads(int sellerId)
 {
	 
		return  paymentUploadDao.listPaymentUploads(sellerId);
 }
 
 @Override
 public PaymentUpload getPaymentUpload(int paymentUploadId)
 {
	 return paymentUploadDao.getPaymentUpload(paymentUploadId);
 }
 
 @Override
 public void deletePaymentUpload(PaymentUpload payupload,int sellerId)
 {
	 paymentUploadDao.deletePaymentUpload(payupload, sellerId);
 }
 
 
 @Override
 public PaymentUpload getManualPayment(int sellerId)
 {
	 return  paymentUploadDao.getManualPayment(sellerId);
 }
 
 

}