<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickNavigate(value) {
    	var targeturl="";
    	alert("Daily activity getting value "+value);
    	switch(value)
    	{
    	case "Order" :
    		targeturl="orderList.html";
    	break;
    	case "RTO" :
    		targeturl="returnOrderList.html";
    	break;
    	case "Payment" :
    		targeturl="paymentUploadList.html";
    	break;
    	case "Inventories" :
    		targeturl="inventoryList.html";
    	break;
    	case "Expenses" :
    		targeturl="expenseList.html";
    	break;
    	
    	}
        $.ajax({
            url : targeturl,
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickReturnOrder() {
    	alert("Return Order value ");
        $.ajax({
            url : "returnOrderList.html",
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
</script>
</head>
<body>
Daily Activity
<a href="#" onclick="onclickNavigate('Order')">Upload Order</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="#" onclick="onclickReturnOrder()">Upload RTO</a>&nbsp;&nbsp;&nbsp;
<a href="#" onclick="onclickNavigate('Payment')">Upload Payment Detail</a>&nbsp;&nbsp;&nbsp;
<a href="#" onclick="onclickNavigate('Inventories')">Upload Inventories</a>&nbsp;&nbsp;&nbsp;
<a href="#" onclick="onclickNavigate('Expenses')">Upload Expenses</a>&nbsp;&nbsp;&nbsp;
</body>
</html>