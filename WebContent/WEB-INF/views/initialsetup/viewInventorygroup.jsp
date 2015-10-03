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

$(document).ready(function() {
	
	var inventorygroup='${category.id}';
	if(inventorygroup!=null&&inventorygroup.length!=0)
		$("#inventorygroupdwopdown").val(inventorygroup);
	
	 $('#inventorygroupdwopdown').change(function () {
         var val=$(this).val();
         $.ajax({
             url : 'changeInventorygroup.html?catId='+val,
             success : function(data) {
                 $('#centerpane').html(data);
             }
         });
     });
	
});


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
    function submitCategory(){
	     $.ajax({
               url: $("#addCategoryForm").attr("action"),
               context: document.body,
               type: 'post',
               data:$("#addCategoryForm").serialize(),
               success : function(res) {
                             
                   $("#centerpane").html(res);
              
           }
        });
 
};
 
</script>
</head>
<body>
<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Inventory Group</h5>
                        </div>
                        <div class="ibox-content add-company">
                         <form role="form" class="form-horizontal">
                                <div class="col-sm-12" >
                                    <div class="form-group"><label class="col-sm-5 control-label">Inventory Group</label>

                                    <div class="col-sm-4">
                                            <select class="form-control" name="account" id="inventorygroupdwopdown">
                                             <c:forEach items="${categorymap}" var="cat">
										       <%--  <c:if test="${category. != selected}">
										            <option value="${role}">${role}</option>
										        </c:if> --%>
										         <option value="${cat.key}">${cat.value}</option>
										    </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                <br><div class="hr-line-dashed"></div>
                                </div>
                                 </form>
                                <div class="col-sm-12">
                                    <table class="table table-bordered custom-table">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Product Category Name</th>
                                            <th>Created on</th>
                                             <th>Product Count</th>
                                            <th>Alias Name</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${subcategory}" var="subcategory" varStatus="loop">
                                        <tr>
                                            <td>${loop.index+1}</td>
                                            <td>${subcategory.catName}</td>
                                            <td>TBD01/05/215</td>
                                             <td>${subcategory.productCount}</td>
                                            <td>${subcategory.catDescription}</td>
                                            <td class="tooltip-demo">
                                            <a href=""><i class="fa fa-edit text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Edit"></i></a></td>
                                        </tr>
                                       </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="hr-line-dashed"></div>
                                </div>
                                <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveCatInventory.html" id="addCategoryForm"
                                role="form" class="form-horizontal">
								<%-- <c:if test="${!empty category.id}">
		                         <input type="hidden" name="id" id="id" value="${category.id}"/>
		                         </c:if> --%>
		                         <input type="hidden" name="parentid" id="parentid" value="${category.id}"/>
		                          <input type="hidden" name="parentCatName" id="parentCatName" value="${category.catName}"/>
                                <div class="col-sm-12">
                                <div class="col-md-5"><form:input path="catName"  placeholder="Product Category" class="form-control"/></div>
                                <div class="col-md-5"><form:input path="catDescription" placeholder="Alias Name" class="form-control"/></div>
                                <!-- <div class="col-md-2"><button class="btn btn-success" type="submit">Add More</button></div> -->
                                </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="button" onclick="submitCategory()">Save</button>
                                </div>
                                </form:form>
                   
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
</html>