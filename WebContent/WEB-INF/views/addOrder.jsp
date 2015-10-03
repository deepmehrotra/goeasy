<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Spring MVC Form Handling</title>
 </head>
 <body>
 <h2>1. <a href="ordersheet.html">Upload Orders</a></h2>
 <h2>2. <a href="download/xls.html">Download</a></h2>
  <h2>Add Order Data</h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveOrder.html">
      <table>
       <tr>
           <td><form:label path="orderId">Order ID:</form:label></td>
           <td><form:input path="orderId" value="${order.orderId}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="orderDate">Order Recieved Date:</form:label></td>
           <td><form:input path="orderDate" value="${order.orderDate}"/></td>
       </tr>
       <tr>
           <td><form:label path="productSkuCode">GE Order SKU:</form:label></td>
           <td><form:input path="productSkuCode" value="${order.productSkuCode}"/></td>
       </tr>
       <tr>
           <td><form:label path="pcName">Order Partner:</form:label></td>
           <td><form:input path="pcName" value="${order.pcName}"/></td>
       </tr>
       <tr>
           <td><form:label path="customer.customerName">Customer Name:</form:label></td>
           <td><form:input path="customer.customerName"/></td>
       </tr>
       <tr>
           <td><form:label path="customer.customerEmail">Customer Email:</form:label></td>
           <td><form:input path="customer.customerEmail"/></td>
       </tr>
       <tr>
           <td><form:label path="customer.customerCity">Customer City:</form:label></td>
           <td><form:input path="customer.customerCity"/></td>
       </tr>
       <tr>
           <td><form:label path="awbNum">AWB NO:</form:label></td>
           <td><form:input path="awbNum" value="${order.awbNum}"/></td>
       </tr>
       <tr>
           <td><form:label path="status">Status:</form:label></td>
           <td><form:input path="status" value="${order.status}"/></td>
       </tr>
       <tr>
           <td><form:label path="channelOrderID">Channel Order ID:</form:label></td>
           <td><form:input path="channelOrderID" value="${order.channelOrderID}"/></td>
       </tr>
       <tr>
           <td><form:label path="invoiceID">Invoice ID:</form:label></td>
           <td><form:input path="invoiceID" value="${order.invoiceID}"/></td>
       </tr>
        <tr>
           <td><form:label path="subOrderID">Sub Order ID:</form:label></td>
           <td><form:input path="subOrderID" value="${order.subOrderID}"/></td>
       </tr>
       <tr>
           <td><form:label path="PIreferenceNo">PI reference No:</form:label></td>
           <td><form:input path="PIreferenceNo" value="${order.PIreferenceNo}"/></td>
       </tr>
       <tr>
           <td><form:label path="logisticPartner">Logistic Partner:</form:label></td>
           <td><form:input path="logisticPartner" value="${order.logisticPartner}"/></td>
       </tr>
        <tr>
           <td><form:label path="orderMRP">OrderMRP:</form:label></td>
           <td><form:input path="orderMRP" value="${order.orderMRP}"/></td>
       </tr>
       
        <tr>
           <td><form:label path="orderSP">orderSP:</form:label></td>
           <td><form:input path="orderSP" value="${order.orderSP}"/></td>
       </tr>
       
         <tr>
           <td><form:label path="shippingCharges">Shipping Charges:</form:label></td>
           <td><form:input path="shippingCharges" value="${order.shippingCharges}"/></td>
       </tr>
        <tr>
           <td><form:label path="shippedDate">Shipping Date:</form:label></td>
           <td><form:input path="shippedDate" value="${order.shippedDate}"/></td>
       </tr>
        <tr>
           <td><form:label path="deliveryDate">Delivery Date:</form:label></td>
           <td><form:input path="deliveryDate" value="${order.deliveryDate}"/></td>
       </tr>
        <tr>
           <td><form:label path="quantity">Quantity:</form:label></td>
           <td><form:input path="quantity" value="${order.quantity}"/></td>
       </tr>
       <tr>
           <td><form:label path="grossNetRate">Gross Net Rate:</form:label></td>
           <td><form:input path="grossNetRate" value="${order.grossNetRate}"/></td>
       </tr>
        <tr>
           <td><form:label path="orderPayment.paymentType">Payment Type:</form:label></td>
           <td><form:input path="orderPayment.paymentType" value="${order.orderPayment.paymentType}"/></td>
       </tr>
       
        <tr>
         <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
   </table> 
  </form:form>
  
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
    <td align="center"><a href="editOrder.html?orderId=${order.orderId}">Edit</a> | <a href="deleteOrder.html?orderId=${order.orderId}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </body>
</html>