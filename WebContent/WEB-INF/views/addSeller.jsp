<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Spring MVC Form Handling</title>
 </head>
 <body>
  <h2>Add Seller Data</h2>
  <form:form method="POST" action="saveSeller.html">
      <table>
       <tr>
           <td><form:label path="id">Seller ID:</form:label></td>
           <td><form:input path="id" value="${seller.id}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="name">Seller Name:</form:label></td>
           <td><form:input path="name" value="${seller.name}"/></td>
       </tr>
       <tr>
           <td><form:label path="email">Seller Email:</form:label></td>
           <td><form:input path="email" value="${seller.email}"/></td>
       </tr>
        <tr>
           <td><form:label path="password">Password:</form:label></td>
           <td><form:input path="password" value="${seller.password}"/></td>
       </tr>
       <tr>
           <td><form:label path="contactNo">Seller contactNo:</form:label></td>
           <td><form:input path="contactNo" value="${seller.contactNo}"/></td>
       </tr>
       <tr>
           <td><form:label path="companyName">Seller CompanyName:</form:label></td>
           <td><form:input path="companyName" value="${seller.companyName}"/></td>
       </tr>
       
       <tr>
           <td><form:label path="address">Seller Address:</form:label></td>
                    <td><form:input path="address" value="${seller.address}"/></td>
       </tr>
          <tr>
         <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
   </table> 
  </form:form>
  
  <c:if test="${!empty sellers}">
  <h2>List Sellers</h2>
 <table align="left" border="1">
  <tr>
   <th>Seller ID</th>
   <th>Seller Name</th>
   <th>Seller Email</th>
   <th>Seller contactNo</th>
   <th>Seller Company Name</th>
   <th>Seller Address</th>
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${sellers}" var="seller">
   <tr>
    <td><c:out value="${seller.id}"/></td>
    <td><c:out value="${seller.name}"/></td>
    <td><c:out value="${seller.email}"/></td>
    <td><c:out value="${seller.contactNo}"/></td>
    <td><c:out value="${seller.companyName}"/></td>
    <td><c:out value="${seller.address}"/></td>
    <td align="center"><a href="editSeller.html?id=${seller.id}">Edit</a> | <a href="deleteSeller.html?id=${seller.id}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </body>
</html>