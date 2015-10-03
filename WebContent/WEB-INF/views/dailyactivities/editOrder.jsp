<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Order</title>
  <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function submitOrder(){
    	     $.ajax({
                    url: $("#addOrderForm").attr("action"),
                    context: document.body,
                    type: 'post',
                    data:$("#addOrderForm").serialize(),
                    success : function(res) {
                                  
                        $("#centerpane").html(res);
                   
                }
             });
      
    };
</script>
 </head>
 <body>
 <h2>1. <a href="ordersheet.html">Upload Orders</a></h2>
 <h2>2. <a href="download/xls.html">Download</a></h2>
  <h2>Add Order Data</h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveOrderDA.html" id="addOrderForm">
      <table>
       <tr>
           <td><form:label path="orderId">Order ID:</form:label></td>
           <td><form:input path="orderId" value="${order.orderId}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="orderDate">Order Date:</form:label></td>
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
         <td colspan="2"><input type="button" value="Submit" onclick="submitOrder()"/></td>
        </tr>
   </table> 
  </form:form>
  
  
 </body>
</html>