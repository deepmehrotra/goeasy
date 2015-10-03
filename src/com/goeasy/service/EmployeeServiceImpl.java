package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.EmployeeDao;
import com.goeasy.model.Employee;

/**
 * @author Dinesh Rajput
 *
 */
@Service("employeeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

 @Autowired
 private EmployeeDao employeeDao;
 
 @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
 public void addEmployee(Employee employee) {
  employeeDao.addEmployee(employee);
 }
 
 public List<Employee> listEmployeess() {
  return employeeDao.listEmployeess();
 }
 
 public List<Employee> listEmployeess(int pageno){
	 return employeeDao.listEmployeess(pageno);
 }

 public Employee getEmployee(int empid) {
  return employeeDao.getEmployee(empid);
 }
 
 public void deleteEmployee(Employee employee) {
  employeeDao.deleteEmployee(employee);
 }

}