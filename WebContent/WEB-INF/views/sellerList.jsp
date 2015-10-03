<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Employees</title>
</head>
<body>
<h1>List Sellers</h1>
<h3><a href="addSeller.html">Add More Sellers</a></h3>

<c:if test="${!empty sellers}">
 <table align="left" border="1">
  <tr>
   <th>Seller ID</th>
   <th>Seller Name</th>
   <th>Seller Email</th>
   <th>Seller Contact No</th>
   <th>Seller Company Name</th>
   <th>Seller Address</th>
  </tr>

  <c:forEach items="${sellers}" var="seller">
   <tr>
    <td><c:out value="${seller.id}"/></td>
    <td><c:out value="${seller.name}"/></td>
    <td><c:out value="${seller.email}"/></td>
    <td><c:out value="${seller.contactNo}"/></td>
    <td><c:out value="${seller.companyName}"/></td>
    <td><c:out value="${seller.address}"/></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</body>
</html>