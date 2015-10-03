package com.goeasy.service;

import java.util.List;

import com.goeasy.model.Employee;

/**
 * @author Dinesh Rajput
 *
 */
public interface EmployeeService {
 
 public void addEmployee(Employee employee);

 public List<Employee> listEmployeess();
 
 public List<Employee> listEmployeess(int pageno);
 
 public Employee getEmployee(int empid);
 
 public void deleteEmployee(Employee employee);
}