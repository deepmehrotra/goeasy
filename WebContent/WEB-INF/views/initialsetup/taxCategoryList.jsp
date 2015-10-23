<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <jsp:include page="../globalcsslinks.jsp"></jsp:include>
  <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickviewExpCat(id) {
       $.ajax({
            url : 'viewExpenseGroup.html?expcategoryId='+id,
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickAddTaxCategory() {
    	  $.ajax({
            url : 'addTaxCategory.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
 
</script>
 </head>
 <body>
  <div id="wrapper">
<jsp:include page="../sidenavigation.jsp"></jsp:include>
    <div id="page-wrapper" class="gray-bg">
     <jsp:include page="../globalheader.jsp"></jsp:include>  
      <div class="wrapper wrapper-content animated fadeInRight" id="centerpane"> 
  <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Tax Categories</h5>
                            <div class="ibox-tools">
                                <a href="#" onclick="onclickAddTaxCategory()" class="btn btn-primary btn-xs" >Add Tax Category</a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Modified On</th>
                                    <th>Percentage</th>
                                   <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty taxCategories}">
                                <c:forEach items="${taxCategories}" var="category" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td>${category.taxCatName}</td>
                                    <td>${category.taxCatDescription}</td>
                                    <td>${category.uploadDate}</td>
                                    <td>${category.taxPercent}</td>
                                     <td class="tooltip-demo">
                                    <a href=""><i class="fa fa-edit text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Edit"></i></a></td>
                                </tr>
                                </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
     </div>
 <jsp:include page="../globalfooter.jsp"></jsp:include>

    </div>
</div>

<jsp:include page="../globaljslinks.jsp"></jsp:include>
</html>