<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Products</title>
</head>
<body>
<h1>List Product</h1>
<h3><a href="addProduct.html">Add More Product</a></h3>

<c:if test="${!empty products}">
 <table align="left" border="1">
  <tr>
  <th>Product ID</th>
   <th>Product Name</th>
   <th>Product Date</th>
   <th>Product SKU code</th>
   <th>Product price</th>
   <th>Product category</th>
  </tr>

  <c:forEach items="${products}" var="product">
   <tr>
   <td><c:out value="${product.productId}"/></td>
    <td><c:out value="${product.productName}"/></td>
    <td><c:out value="${product.productDate}"/></td>
    <td><c:out value="${product.productSkuCode}"/></td>
    <td><c:out value="${product.productPrice}"/></td>
    <td><c:out value="${product.quantity}"/></td>
    <td><c:out value="${product.categoryName}"/></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</body>
</html>