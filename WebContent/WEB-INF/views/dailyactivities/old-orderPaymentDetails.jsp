<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Add Order</title>
 <link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function () {
		$('#OrderPaymentContainer').jtable({
			title : 'Return Order List',
			paging: true,
            sorting: true,
			actions : {
				listAction : 'paymentDetails.html?action=list&uploadId=${uploadId}',
				updateAction : 'paymentDetails.html?action=update',
			},
			fields : {
				orderId : {
					title : '',
					width : '0%',
					key : true,
					list: false,
					type :'hidden',
					create :true,
					edit:true
				},
				channelOrderID : {
					title : 'Channel Order Id',
					width : '10%',
					key : true,
					list : true,
					edit : true,
					create : true
				},
				geOrderId : {
					title : 'GE Order Id',
					width : '10%',
					list : true,
					edit : false,
					create : false
				},
				awbNum : {
					title : 'AWB No',
					width : '20%',
					edit : false,
					list : true,
					create : false
				},
				invoiceID : {
					title : 'InvoiceID',
					width : '10%',
					edit : false,
					list : true,
					create : false
				},
				
				status : {
					title : 'status',
					width : '10%',
					edit : false,
					list : true,
					create : true
				},
				paymentdesc : {
					title : 'Payment Description',
					width : '10%',
					edit : false,
					list : true,
					create : false,
					display:function(data){
	                     return data.record.orderPayment.paymentdesc;
	           }
				},
				dateofPayment : {
					title : 'Payment Date',
					width : '10%',
					edit : true,
					list : true,
					create : true,
					display:function(data){
	                     return data.record.orderPayment.dateofPayment;
	           }
				},
				negativeCharges : {
					title : 'Negative Charges',
					width : '10%',
					edit : true,
					list : true,
					create : true,
					display:function(data){
	                     return data.record.orderPayment.negativeCharges;
	           }
				},
				recievedamout : {
					title : 'Actual Recieved',
					width : '20%',
					edit : true,
					list : true,
					create : true,
					display:function(data){
	                     return data.record.orderPayment.recievedamout;
	           }
				},
				actualrecived2 : {
					title : 'Actual Recieved 2',
					width : '10%',
					edit : true,
					list : true,
					create : true,
					display:function(data){
	                     return data.record.orderPayment.actualrecived2;
	           }
				}
				
			}
		});
		$('#OrderPaymentContainer').jtable('load');
	});
	

</script>
 </head>
 <body>
  <h2>Order Payment Details</h2>
 

<div id="OrderPaymentContainer"></div>
 </body>
</html>