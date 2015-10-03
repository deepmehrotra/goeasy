<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense Group</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickAddExpense() {
    	alert("calling add cat" );
        $.ajax({
            url : 'addExpense.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickAddExpenseGroup() {
    	alert(" add Inventory group vcalled");
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
<div>
<br>
<input type="button" value="Create New Expense Group" onclick="onclickAddExpenseGroup()">
<br>
<br>
Expense Group : <c:out value="${expenseCategory.expcatName}"/>
<br>
<br>
 <c:if test="${!empty expenses}">
  <h2>List Categories</h2>
 <table align="left" border="1">
  <tr>
   <th>Expense ID</th>
   <th>Expense Name</th>
   <th>Expense Category name</th>
   <th>Expense Description</th>
   <th>Expense Created On</th>
   <th>Expense Amount</th>
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${expenses}" var="expense">
   <tr>
    <td><c:out value="${expense.expenseId}"/></td>
    <td><c:out value="${expense.expenseName}"/></td>
    <td><c:out value="${expense.expenseCatName}"/></td>
     <td><c:out value="${expense.expenseDescription}"/></td>
    <td><c:out value="${expense.createdOn}"/></td>
    <td><c:out value="${expense.amount}"/></td>
    <td align="center"><a href="editExpense.html?id=${subcategory.id}">Edit</a> | <a href="deleteExpense.html?id=${subcategory.id}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</div>
<br><br>
<input type="button" value="Add Expense" onclick="onclickAddExpense()">
</body>
</html>