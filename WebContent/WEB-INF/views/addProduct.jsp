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
 <h2>1. <a href="Productsheet.html">Upload Products</a></h2>
 <h2>2. <a href="download/Product/xls.html">Download Product Excel</a></h2>
  <h2>Add Product Data</h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveProduct.html">
      <table>
       <tr>
           <td><form:label path="productId">Product ID:</form:label></td>
           <td><form:input path="productId" value="${product.productId}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="productName">Product Name:</form:label></td>
           <td><form:input path="productName" value="${product.productName}"/></td>
       </tr>
       <tr>
           <td><form:label path="productDate">Product Date:</form:label></td>
           <td><form:input path="productDate" value="${product.productDate}"/></td>
       </tr>
       <tr>
           <td><form:label path="productSkuCode">Product SKU code:</form:label></td>
                    <td><form:input path="productSkuCode" value="${product.productSkuCode}"/></td>
       </tr>
       
       <tr>
           <td><form:label path="productPrice">Product price:</form:label></td>
                    <td><form:input path="productPrice" value="${product.productPrice}"/></td>
       </tr>
       <tr>
           <td><form:label path="quantity">Product quantity:</form:label></td>
                    <td><form:input path="quantity" value="${product.quantity}"/></td>
       </tr>
       <tr>
           <td><form:label path="categoryName">Product category:</form:label></td>
                    <td><form:input path="categoryName" value="${product.categoryName}"/></td>
       </tr>
      
      <tr>
         <td colspan="2"><input type="submit" value="Submit"/></td>
       </tr>
   </table> 
  </form:form>
  
  <c:if test="${!empty products}">
  <h2>List Products</h2>
 <table align="left" border="1">
  <tr>
   <th>Product ID</th>
   <th>Product Name</th>
   <th>Product Date</th>
   <th>Product SKU code</th>
   <th>Product price</th>
   <th>Product category</th>
           <th>Actions on Row</th>
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
    <td align="center"><a href="editProduct.html?productId=${product.productId}">Edit</a> | <a href="deleteProduct.html?productId=${product.productId}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </body>
</html>