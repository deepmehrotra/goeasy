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
    function onclickviewCat(value,id) {
    	var targetUrl="";
    	if(value=='view')
    		{
    		targetUrl='viewInventorygroup.html?id='+id;
    		}
    	else
    		{
    		alert(" Deleting inventory group");
    		targetUrl='deleteInventoryGroup.html?id='+id;
    		}
    	$.ajax({
            url : targetUrl,
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickAddInventoryGroup() {
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
                        ${error}
                            <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                   
                                    <th>#</th>
                                    <th>Inventory Group Name</th>
                                    <th>Created on</th>
                                    <th>Total SKU count</th>
                                     <th>Current Stock</th>
                                      <th>Monthly Opening Stock</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${categories}" var="category" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td><a href="#" onclick="onclickviewCat('view',${category.id})">${category.catName}</a></td>
                                    <td>${category.createdOn}</td>
                                     <td>${category.skuCount}</td>
                                      <td>${category.productCount}</td>
                                       <td>${category.openingStock}</td>
                                    <td class="tooltip-demo"><a href="#" onclick="onclickviewCat('delete',${category.id})"><i class="fa fa-list-alt text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Delete"></i></a>
                                    </td>
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