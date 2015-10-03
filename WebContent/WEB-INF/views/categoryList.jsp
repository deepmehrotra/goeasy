<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Categories</title>
</head>
<body>
<h1>List Categories</h1>
<h3><a href="addCategory.html?page=1">Add More categories</a></h3>

<c:if test="${!empty categories}">
 <table align="left" border="1">
  <tr>
   <th>Category ID</th>
   <th>Category Name</th>
   <th>Category Description</th>
  </tr>

  <c:forEach items="${categories}" var="category">
   <tr>
   <td><c:out value="${category.id}"/></td>
    <td><c:out value="${category.catName}"/></td>
    <td><c:out value="${category.catDescription}"/></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</body>
</html>