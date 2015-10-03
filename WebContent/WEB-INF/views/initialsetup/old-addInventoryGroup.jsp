<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Inventory Group</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function submitInventoryGroup(){
    	     $.ajax({
                    url: $("#addInventoryGroupForm").attr("action"),
                    context: document.body,
                    type: 'post',
                    data:$("#addInventoryGroupForm").serialize(),
                    success : function(res) {
                                  
                        $("#centerpane").html(res);
                   
                }
             });
      
    };
</script>
</head>
<body>
 <h2>Add Category Data</h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveInventoryGroup.html" id="addInventoryGroupForm">
      <table>
       <tr>
           <td><form:label path="id">Category ID:</form:label></td>
           <td><form:input path="id" value="${category.id}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="catName">Category Name:</form:label></td>
           <td><form:input path="catName" value="${category.catName}"/></td>
       </tr>
       <tr>
           <td><form:label path="catDescription">Category Desc:</form:label></td>
           <td><form:input path="catDescription" value="${category.catDescription}"/></td>
       </tr>
      
          <tr>
         <td colspan="2"><input type="button" value="Submit" onclick="submitInventoryGroup()"></td>
        </tr>
   </table> 
  </form:form>
</body>
</html>