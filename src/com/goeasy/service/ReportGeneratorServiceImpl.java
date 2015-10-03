package com.goeasy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.bean.TotalShippedOrder;
import com.goeasy.dao.ReportsGeneratorDao;

/**
 * @author Deep Mehortra
 *
 */
@Service("reportGeneratorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportGeneratorServiceImpl implements ReportGeneratorService {

 @Autowired
 private ReportsGeneratorDao reportGeneratorDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public TotalShippedOrder getPartnerTSOdetails(String pcName,Date startDate ,Date endDate, int sellerId)
{
	return reportGeneratorDao.getPartnerTSOdetails(pcName, startDate, endDate, sellerId);
}


@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public List<TotalShippedOrder> getAllPartnerTSOdetails(Date startDate ,Date endDate, int sellerId)
{
	return reportGeneratorDao.getAllPartnerTSOdetails(startDate, endDate, sellerId);
}




}