<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickAddCategory() {
    	alert("calling add cat" );
        $.ajax({
            url : 'addCatInventorygroup.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickAddInventoryGroup() {
    	alert(" add Inventory group vcalled");
        $.ajax({
            url : 'addInventoryGroup.html',
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
<input type="button" value="Create New Inventory Group" onclick="onclickAddInventoryGroup()">
<br>
<br>
Inventory Group : <c:out value="${category.catName}"/>
<br>
<br>
 <c:if test="${!empty subcategory}">
  <h2>List Categories</h2>
 <table align="left" border="1">
  <tr>
   <th>Category ID</th>
   <th>Category Name</th>
   <th>Category Description</th>
   
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${subcategory}" var="subcategory">
   <tr>
    <td><c:out value="${subcategory.id}"/></td>
    <td><c:out value="${subcategory.catName}"/></td>
    <td><c:out value="${subcategory.catDescription}"/></td>
    <td align="center"><a href="editSubCategory.html?id=${subcategory.id}">Edit</a> | <a href="deleteSubCategory.html?id=${subcategory.id}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
</div>
<br><br>
<input type="button" value="Add Category" onclick="onclickAddCategory()">
</body>
</html>