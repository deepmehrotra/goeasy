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
<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Inventory Group</h5>
                        </div>
                        <div class="ibox-content add-company">
                        <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveInventoryGroup.html" id="addInventoryGroupForm"
                         role="form" class="form-horizontal">
                         <c:if test="${!empty category.id}">
                         <input type="hidden" name="id" id="id" value="${category.id}"/>
                         </c:if>
                        
                            <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-5 control-label">Inventory Group Name</label>

                                    <div class="col-sm-7">
                                    <form:input path="catName" value="${category.catName}"  class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-4 control-label">Description</label>

                                    <div class="col-sm-8">
                                    <form:input path="catDescription" value="${category.catDescription}"  class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="button" onclick="submitInventoryGroup()">Save</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
</body>
</html>