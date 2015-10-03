<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Expense Form</title>
 </head>
 <body>
  <h2>Add Expense Data</h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveExpense.html">
      <table>
       <tr>
           <td><form:label path="expenseId">Expense ID:</form:label></td>
           <td><form:input path="expenseId" value="${expense.expenseId}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="expenseName">expense Name:</form:label></td>
           <td><form:input path="expenseName" value="${expense.expenseName}"/></td>
       </tr>
       <tr>
           <td><form:label path="expenseDescription">expense Desc:</form:label></td>
           <td><form:input path="expenseDescription" value="${expense.expenseDescription}"/></td>
       </tr>
        <tr>
           <td><form:label path="amount">expense amount:</form:label></td>
           <td><form:input path="amount" value="${expense.amount}"/></td>
       </tr>
        <tr>
           <td><form:label path="expenseCatName">Chose Expense Category:</form:label></td>
            <td><ul>  
            <form:select path="expenseCatName" items="${categorymap}">  
        </form:select></ul></td>  
       </tr>
      
          <tr>
         <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
   </table> 
  </form:form>
  
  <c:if test="${!empty expenses}">
  <h2>List expenses</h2>
 <table align="left" border="1">
  <tr>
   <th>Expense ID</th>
   <th>Expense Name</th>
   <th>Expense Description</th>
    <th>Expense Parent Category</th>
    <th>Expense Amount</th>
   
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${expenses}" var="expense">
   <tr>
    <td><c:out value="${expense.expenseId}"/></td>
    <td><c:out value="${expense.expenseName}"/></td>
    <td><c:out value="${expense.expenseDescription}"/></td>
    <td><c:out value="${expense.expenseCatName}"/></td>
     <td><c:out value="${expense.amount}"/></td>
    <td align="center"><a href="editExpense.html?expenseId=${expense.expenseId}">Edit</a> | <a href="deleteExpense.html?expenseId=${expense.expenseId}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </body>
</html>