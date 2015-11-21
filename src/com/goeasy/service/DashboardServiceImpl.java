package com.goeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.bean.DashboardBean;
import com.goeasy.dao.DashboardDao;

/**
 * @author Deep Mehrotra
 *
 */
@Service("dashboardService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DashboardServiceImpl implements DashboardService {

 @Autowired
 private DashboardDao dashboardDao;
 
 public DashboardBean getDashboardDetails(int sellerId)
 {
	 return dashboardDao.getDashboardDetails(sellerId);
 }

}