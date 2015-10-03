<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Add Order</title>
  <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickNavigateOrder(value,id) {
    	var targeturl="";
    	switch(value)
    	{
    	case "editOrder" :
    		targeturl="editOrderDA.html?orderId="+id;
    	break;
    	case "deleteOrder" :
    		targeturl="deleteOrderDA.html?orderId="+id;
    	break;
    	case "addOrder" :
    		targeturl="addOrderDA.html";
    	break;
    	
    	}
        $.ajax({
            url : targeturl,
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
   
</script>
 </head>
 <body>
 <h2>1. <a href="ordersheet.html">Upload Orders</a></h2>
 <h2>2. <a href="download/xls.html">Download</a></h2>

  
  <c:if test="${!empty orders}">
  <h2>List Orders</h2>
 <table align="left" border="1">
  <tr>
   <th>Order ID</th>
   <th>Order Date</th>
   <th>Order SKUCode</th>
   <th>Order Partner</th>
   <th>Order Customer Name</th>
   <th>Payment Date</th>
   <th>AWB Number</th>
   <th>Order Status</th>
	<th>Channel Order ID</th>
	<th>Invoice ID</th>
	<th>Sub Order ID</th>
	<th>PI reference No</th>
	<th>Logistic Partner</th>
	<th>OrderMRP</th>
	<th>orderSP</th>
	<th>Shipping Charges</th>
	<th>Shipping Date</th>
	<th>Delivery Date</th>
	<th>Actions on Row</th>
  </tr>

  <c:forEach items="${orders}" var="order">
   <tr>
     <td><c:out value="${order.orderId}"/></td>
     <td><c:out value="${order.orderDate}"/></td>
      <td><c:out value="${order.productSkuCode}"/></td>
       <td><c:out value="${order.pcName}"/></td>
        <td><c:out value="${order.customerName}"/></td>
    <td><c:out value="${order.paymentDueDate}"/></td>
    <td><c:out value="${order.awbNum}"/></td>
    <td><c:out value="${order.status}"/></td>
    <td><c:out value="${order.channelOrderID}"/></td>
    <td><c:out value="${order.invoiceID}"/></td>
    <td><c:out value="${order.subOrderID}"/></td>
    <td><c:out value="${order.PIreferenceNo}"/></td>
    <td><c:out value="${order.logisticPartner}"/></td>
    <td><c:out value="${order.orderMRP}"/></td>
    <td><c:out value="${order.orderSP}"/></td>
    <td><c:out value="${order.shippingCharges}"/></td>
    <td><c:out value="${order.shippedDate}"/></td>
    <td><c:out value="${order.deliveryDate}"/></td>
    <td align="center"><a href="#" onclick="onclickNavigateOrder('editOrder',${order.orderId})">Edit</a> |<a href="#" onclick="onclickNavigateOrder('deleteOrder',${order.orderId})">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
<br>
<br>
<a href="#" onclick="onclickNavigateOrder('addOrder','0')">Add Order</a>
 </body>
</html>