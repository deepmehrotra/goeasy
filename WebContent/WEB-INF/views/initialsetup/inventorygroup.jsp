<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InventoryGroup</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickviewCat(id) {
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
 <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Inventory Groups</h5>
                        
                         <div class="ibox-tools">
                                <a href="#" onclick="onclickAddInventoryGroup()" class="btn btn-primary btn-xs" >Create Inventory</a>
                            </div>
                            </div>
                        <div class="ibox-content">
                            <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                   
                                    <th>#</th>
                                    <th>Inventory Group Name</th>
                                    <th>Created on</th>
                                    <th>Product category</th>
                                    <th>No. Product</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${categories}" var="category" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td><a href="#" onclick="onclickviewCat(${category.id})">${category.catName}</a></td>
                                    <td>01/05/215 TBD</td>
                                     <c:if test="${!empty category.subCategory}">
                                    <%-- <td>${fn:length(category.subCategory)}</td> --%>
                                    <td>TBD</td>
                                     </c:if>
                                    <td>${category.productCount}</td>
                                   <%--  <td class="tooltip-demo"><a href="#" onclick="onclickviewCat(${category.id})"><i class="fa fa-list-alt text-navy" data-toggle="tooltip" data-placement="top" data-original-title="View"></i></a> 
                                    <a href=""><i class="fa fa-edit text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Edit"></i></a></td>
                                 --%> <td class="tooltip-demo"><a href=""><i class="fa fa-list-alt text-navy" data-toggle="tooltip" data-placement="top" data-original-title="View"></i></a> 
                                    <a href=""><i class="fa fa-edit text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Edit"></i></a></td>
                                 </tr>
                               </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <script>
    $(document).ready(function(){
        $('.panel').each(function() {
            animationHover(this, 'flipInY');
        });
    });
</script>
</body>
</html>