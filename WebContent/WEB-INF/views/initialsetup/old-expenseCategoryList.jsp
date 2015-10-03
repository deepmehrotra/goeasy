<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Expense Category</title>
  <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickviewExpCat(id) {
    	alert("calling expense cat id"+id );
        $.ajax({
            url : 'viewExpenseGroup.html?expcategoryId='+id,
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickAddExpenseGroup() {
    	alert(" add Expense group vcalled");
        $.ajax({
            url : 'addExpenseCategory.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
 
</script>
 </head>
 <body>
  <h2>Add Category Data</h2>
  
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
   
    <td><a href="#" onclick="onclickviewExpCat(${category.expcategoryId})">${category.expcatName}</a></td>
    <td><c:out value="${category.expcatDescription}"/></td>
    <td align="center"><a href="editExpenseCategory.html?expcategoryId=${category.expcategoryId}">Edit</a> | <a href="deleteExpenseCategory.html?expcategoryId=${category.expcategoryId}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
<div>
<br><br><br><br>
<input type="button" value="Add Expense Group" onclick="onclickAddExpenseGroup()">
</div>
 </body>
</html>