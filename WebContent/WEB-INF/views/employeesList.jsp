<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Employees</title>
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#EmployeeTableContainer').jtable({
			title : 'Employee List',
			actions : {
				listAction : 'controller.html?action=list',
				 createAction : '/seller/controller.html?action=create',
				updateAction : '/seller/controller.html?action=update',
				deleteAction : '/seller/controller.html?action=delete' 
			},
			fields : {
				id : {
					title : 'Employee Id',
					width : '10%',
					key : true,
					list : true,
					edit : false,
					create : true
				},
				name : {
					title : 'Name',
					width : '20%',
					edit : true
				},
				age : {
					title : 'Age',
					width : '10%',
					edit : true
				},
				salary : {
					title : 'Salary',
					width : '20%',
					edit : true
				},
				address : {
					title : 'Address',
					width : '20%',
					edit : true
				}
			}
		});
		$('#EmployeeTableContainer').jtable('load');
	});
</script>
</head>
<body>
<h1>List Employees</h1>
<h3><a href="add.html?page=1">Add More Employee</a></h3>

${employees} 
<div id="EmployeeTableContainer"></div>
<%-- <c:if test="${!empty employees}">
 <table align="left" border="1">
  <tr>
   <th>Employee ID</th>
   <th>Employee Name</th>
   <th>Employee Age</th>
   <th>Employee Salary</th>
   <th>Employee Address</th>
  </tr>

  <c:forEach items="${employees}" var="employee">
   <tr>
    <td><c:out value="${employee.id}"/></td>
    <td><c:out value="${employee.name}"/></td>
    <td><c:out value="${employee.age}"/></td>
    <td><c:out value="${employee.salary}"/></td>
    <td><c:out value="${employee.address}"/></td>
   </tr>
  </c:forEach>
 </table>
</c:if> --%>
</body>
</html>