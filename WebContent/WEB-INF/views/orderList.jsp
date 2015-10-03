<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Orders</title>
</head>
<body>
<h1>List Orders</h1>
<h3><a href="addOrder.html">Add More Orders</a></h3>

<c:if test="${!empty orders}">
  <h2>List Orders</h2>
 <table align="left" border="1">
  <tr>
   <th>Order ID</th>
   <th>Order Date</th>
   <th>Order SKUCode</th>
   <th>Order Partner</th>
   <th>Order Customer Name</th>
   <th>Payment Due Date</th>
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
    </tr>
  </c:forEach>
 </table>
</c:if>
</body>
</html>