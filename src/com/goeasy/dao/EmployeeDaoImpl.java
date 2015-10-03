package com.goeasy.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.Employee;

/**
 * @author Deep Mehrotra
 *
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

 @Autowired
 private SessionFactory sessionFactory;
 private static final int LIMITITEMSPERPAGE = 10;
 
 public void addEmployee(Employee employee) {
	 System.out.println(" Employee age "+employee.getEmpAge());
	 System.out.println("Employee name :"+employee.getEmpName());
	 try
	 {
   sessionFactory.getCurrentSession().saveOrUpdate(employee);
	 }
	 catch(Exception e)
	 {
		 System.out.println(" Employee exception :"+e.getLocalizedMessage());
	 }
 }

 @SuppressWarnings("unchecked")
 public List<Employee> listEmployeess(int pageno) {
	 List<Employee> returnlist=(List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class).setMaxResults(LIMITITEMSPERPAGE).setFirstResult(LIMITITEMSPERPAGE*(pageno-1)).list();
	 System.out.println(" Getting employee records"+returnlist.size());

  return returnlist; }

 @SuppressWarnings("unchecked")
 public List<Employee> listEmployeess() {
  return (List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
 }

 
 public Employee getEmployee(int empid) {
  return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);
 }

 public void deleteEmployee(Employee employee) {
  sessionFactory.getCurrentSession().createQuery("DELETE FROM Employee WHERE empid = "+employee.getEmpId()).executeUpdate();
 }
}