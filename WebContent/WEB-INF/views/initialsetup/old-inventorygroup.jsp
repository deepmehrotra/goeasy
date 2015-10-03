<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InventoryGroup</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickviewCat(id) {
    	alert("calling online id"+id );
        $.ajax({
            url : 'viewInventorygroup.html?id='+id,
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
 <c:if test="${!empty categories}">
  <h2>List Categories</h2>
 <table align="left" border="1">
  <tr>
   <th>Category ID</th>
   <th>Category Name</th>
   <th>Category Description</th>
   <th>Created On</th>
   <th>Product Count</th>
   
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${categories}" var="category">
   <tr>
    <td><c:out value="${category.id}"/></td>
    <td><a href="#" onclick="onclickviewCat(${category.id})">${category.catName}</a></td>
    <td><c:out value="${category.catDescription}"/></td>
    <td><c:out value="TBD"/></td>
    <td><c:out value="${category.productCount}"/></td>
    <td align="center"><a href="editCategory.html?id=${category.id}">Edit</a> | <a href="deleteCategory.html?id=${category.id}">Delete</a> | <a href="viewCategoryProduct.html?id=${category.id}">View Products</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
<div>
<br><br><br><br>
<input type="button" value="Add Inventory Group" onclick="onclickAddInventoryGroup()">
</div>
</body>
</html>