package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.Employee;

/**
 * @author Deep Mehrotra
 *
 */
public interface EmployeeDao {
 
 public void addEmployee(Employee employee);

 public List<Employee> listEmployeess();
 
 public List<Employee> listEmployeess(int pageno);
 
 public Employee getEmployee(int empid);
 
 public void deleteEmployee(Employee employee);
}