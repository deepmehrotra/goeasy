<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Expense Category</title>
 </head>
 <body>
  <h2>Add Category Data</h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveExpenseCategory.html">
      <table>
       <tr>
           <td><form:label path="expcategoryId">Expense Category ID:</form:label></td>
           <td><form:input path="expcategoryId" value="${expenseCategory.expcategoryId}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="expcatName">Expense Category Name:</form:label></td>
           <td><form:input path="expcatName" value="${expenseCategory.expcatName}"/></td>
       </tr>
       <tr>
           <td><form:label path="expcatDescription">Expense Category Desc:</form:label></td>
           <td><form:input path="expcatDescription" value="${expenseCategory.expcatDescription}"/></td>
       </tr>
      
          <tr>
         <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
   </table> 
  </form:form>
  
  <c:if test="${!empty expenseCategories}">
  <h2>List Categories</h2>
 <table align="left" border="1">
  <tr>
   <th>Category ID</th>
   <th>Category Name</th>
   <th>Category Description</th>
   
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${expenseCategories}" var="category">
   <tr>
    <td><c:out value="${category.expcategoryId}"/></td>
    <td><c:out value="${category.expcatName}"/></td>
    <td><c:out value="${category.expcatDescription}"/></td>
    <td align="center"><a href="editExpenseCategory.html?expcategoryId=${category.expcategoryId}">Edit</a> | <a href="deleteExpenseCategory.html?expcategoryId=${category.expcategoryId}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </body>
</html>