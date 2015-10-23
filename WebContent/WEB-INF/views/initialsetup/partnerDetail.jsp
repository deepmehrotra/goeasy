<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Partner Details</title>
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
    	 $.ajax({
            url : 'addPartner.html',
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
                            <h5>Partners</h5>
                         <div class="ibox-tools">
                                <a href="#" onclick="onclickAddInventoryGroup()" class="btn btn-primary btn-xs" >Add Sales Channel</a>
                            </div>
                            </div>
                        <div class="ibox-content">
                            <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                   
                                    <th>#</th>
                                    <th>Partner Name</th>
                                    <th>Description</th>
                                     <th>TDS Applicable</th>
                                     <th>RTO Period</th>
                                     <th>Return Period</th>
                                     <th>Payment Type</th>
                                     <th>Payment Detail</th>
                                     <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${partners}" var="category" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td>${category.pcName}</td>
                                    <td>${category.pcDesc}</td>
                                    <td>${category.tdsApplicable}</td>
                                    <td>${category.maxRTOAcceptance}</td>
                                    <td>${category.maxReturnAcceptance}</td>
                                    <c:choose>
									    <c:when test="${category.paymentType == 'paymentcycle'}">
									    	<td>Subdivided Monthly</td>                                     
									        <td>${category.startcycleday} to ${category.paycycleduration} ,payment on ${category.paydaysfromstartday}</td>
									    </c:when>
									    <c:when test="${category.paymentType == 'datewisepay'}">
										    <c:choose>
										    	<c:when test="${category.isshippeddatecalc}">
										    	<td>Payment From Shipped date</td> 
										    	 <td>${category.noofdaysfromshippeddate} days from shipped date</td>
										    	 </c:when>
										    	 <c:otherwise>
										    	 <td>Payment From Delivery date</td> 
										    	  <td>${category.noofdaysfromshippeddate} days from delivery date</td>
										    	 </c:otherwise>
										    </c:choose>
									       
									    </c:when>
									    <c:otherwise>
									     <td>Monthly</td>
									        <td>${category.monthlypaydate} of every month</td>
									    </c:otherwise>
									</c:choose>
                                   
                                   
                                   <td class="tooltip-demo"> 
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