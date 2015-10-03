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
    function onclickNavigatePayment(value,id) {
    	var targeturl="";
    	alert(" Inside navigate payments value :"+value);
    	switch(value)
    	{
    	
    	case "viewPayments" :
    		targeturl="viewPayments.html?uploadId="+id;
    	break;
    	case "orderPaymentSheet" :
    		targeturl="orderPaymentSheet.html";
    	break;
    	case "download" :
    		targeturl="download/paymentXls.html";
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
 <h2>1. <a href="#" onclick="onclickNavigatePayment('orderPaymentSheet', 0)">Upload Payment Details</a></h2>
 <h2>2. <a href="download/paymentXls.html">Download Payment Details</a></h2>
  <c:if test="${!empty payments}">
  <h2>List Orders</h2>
 <table align="left" border="1">
  <tr>
   <th>Payment ID</th>
   <th>uploadDesc</th>
   <th>uploadDate</th>
   <th>totalpositivevalue</th>
   <th>totalnegativevalue</th>
   <th>netRecievedAmount</th>
   <th>uploadStatus</th>
	<th>Actions on Row</th>
  </tr>

  <c:forEach items="${payments}" var="payment">
   <tr>
     <td><c:out value="${payment.uploadId}"/></td>
     <td><c:out value="${payment.uploadDesc}"/></td>
      <td><c:out value="${payment.uploadDate}"/></td>
       <td><c:out value="${payment.totalpositivevalue}"/></td>
        <td><c:out value="${payment.totalnegativevalue}"/></td>
    <td><c:out value="${payment.netRecievedAmount}"/></td>
    <td><c:out value="${payment.uploadStatus}"/></td>
  
    <td align="center"><a href="#" onclick="onclickNavigatePayment('viewPayments',${payment.uploadId})">View</a> </td>
   </tr>
  </c:forEach>
 </table>
</c:if>
<br>
<br>

 </body>
</html>