<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Partners</title>
</head>
<body>
<h1>List Partner</h1>
<h3><a href="addPartner.html">Add More Partner</a></h3>

<c:if test="${!empty partners}">
 <table align="left" border="1">
  <tr>
   <th>Partner ID</th>
   <th>Partner Name</th>
   <th>Partner Desc</th>
   <th>Partner Logo URL</th>
   <th>Partner ismonthlycycle</th>
   <th>Partner cyclestartdate</th>
   <th>Partner cycleenddate</th>
   <th>Partner cyclepaymentdate</th>
   <th>Partner isshippeddatecalc</th>
   <th>Partner noofdaysfromshippeddate</th>
  </tr>

  <c:forEach items="${partners}" var="partner">
   <tr>
    <td><c:out value="${partner.pcId}"/></td>
    <td><c:out value="${partner.pcName}"/></td>
    <td><c:out value="${partner.pcDesc}"/></td>
    <td><c:out value="${partner.pcLogoUrl}"/></td>
    <td><c:out value="${partner.ismonthlycycle}"/></td>
    <td><c:out value="${partner.cyclestartdate}"/></td>
    <td><c:out value="${partner.cycleenddate}"/></td>
    <td><c:out value="${partner.cyclepaymentdate}"/></td>
    <td><c:out value="${partner.isshippeddatecalc}"/></td>
    <td><c:out value="${partner.noofdaysfromshippeddate}"/></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</body>
</html>