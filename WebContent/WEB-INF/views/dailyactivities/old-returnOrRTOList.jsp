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
//$(document).ready(function () {
	function jtableload() {
		$('#OrderTableContainer').jtable({
			title : 'Return Order List',
			paging: true,
            sorting: true,
			actions : {
				listAction : 'findOrderDA.html?action=list',
				 createAction : 'findOrderDA.html?action=create',
				updateAction : 'findOrderDA.html?action=update',
				deleteAction : 'findOrderDA.html?action=delete' 
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
				subOrderID : {
					title : 'SubOrderId',
					width : '10%',
					edit : false,
					list : true,
					create : false
				},
				PIreferenceNo : {
					title : 'PIreferenceNo',
					width : '10%',
					edit : false,
					list : true,
					create : false
				},
				uniqueItemId : {
					title : 'UniqueItemId',
					width : '10%',
					edit : false,
					list : true,
					create : false
				},
				orderDate : {
					title : 'Order Date',
					width : '10%',
					edit : false,
					list : true,
					create : false
				},
				status : {
					title : 'status',
					width : '10%',
					edit : true,
					list : true,
					create : true
				},
				returnId : {
					title : 'returnID',
					width : '10%',
					edit : false,
					list : false,
					create : false,
					type:'hidden',
					/* createAction:function(postData, jtParams) {
						  return $.Deferred(function($dfd) {
							    $.ajax({
							      url: '/create/api/url/',
							      type: 'POST',
							      dataType: 'json',
							      data: prepareJSON(postData), // function to convert inputs to your JSON format
							      success: function(data) {
							        $dfd.resolve({
							          "Result": "OK",
							          "Records": data.description,
							          "TotalRecordCount": 0
							        });
							      },
							      error: function() {
							        console.log("ERROR ....");
							        $dfd.reject();
							      }
							    });
							  });
							},  */
					display:function(data){
	                     return data.record.orderReturnOrRTO.returnId;
	           }
				},
				returnOrRTOId : {
					title : 'Return ID',
					width : '10%',
					edit : true,
					list : true,
					create : true,
					display:function(data){
	                     return data.record.orderReturnOrRTO.returnOrRTOId;
	           }
				},
				returnDate : {
					title : 'Return Date',
					width : '10%',
					edit : true,
					type: 'date',
                    displayFormat: 'dd/mm/yy',
					list : false,
					create : true,
					display:function(data){
	                     return data.record.orderReturnOrRTO.returnDate;
	           }
				},
				returnOrRTOCharges : {
					title : 'Return Charges',
					width : '20%',
					edit : true,
					list : false,
					create : true
				},
				returnorrtoQty : {
					title : 'Return Quantity',
					width : '20%',
					edit : true,
					list : false,
					create : true
				},
				returnOrRTOreason : {
					title : 'Return reason',
					width : '20%',
					edit : true,
					list : false,
					create : true
				}
				
			}
		});
		//$('#OrderTableContainer').jtable('load');
	}
	
$('#LoadOnSubmit').click(function (e) {
    e.preventDefault();
    jtableload();
    $('#OrderTableContainer').jtable('load', {
    	searchCriteria: $('#searchCriteria').val(),
    	searchType: $('#searchType').val(),
    	searchString: $('#searchString').val(),
   	 searchDateCriteria: $('#searchDateCriteria').val(),
   	 startDate: $('#startDate').val(),
   	 endDate: $('#endDate').val()
    });
});
	/*  $('#LoadOnSubmit').click(function (e) {
		 alert(" inside click function");
         e.preventDefault();
         $('#OrderTableContainer').jtable('load',{
        	 searchCriteria: $('#searchCriteria').val(),
        	 searchType: $('#searchType').val(),
        	 searchString: $('#searchString').val(),
        	 searchDateCriteria: $('#searchDateCriteria').val(),
        	 startDate: $('#startDate').val(),
        	 endDate: $('#endDate').val()
         });
     }); */

   /*  function submitOrderSearch() {
    	alert(" Inside submit search");
    	   $.ajax({
               url: $("#findOrderForm").attr("action"),
               context: document.body,
               type: 'post',
               data:$("#findOrderForm").serialize(),
               success : function(res) {
                             
                   $("#centerpane").html(res);
              
           }
        });
    } */
   
</script>
 </head>
 <body>
  <h2>Search for Your Order</h2>
  <form method="POST" action="findOrderDA.html?action=list" id="findOrderForm">
      <table>
      <tr>
      <td><input type="radio" name="searchType" id="searchType" value="searchByProperty">Search by search Category
      </td>
      </tr>
       <tr>
         <td>
         <select id="searchCriteria" name="searchCriteria" > 
    	<option value="channelOrderID">Order ID/PO No</option>
    	<option value="awbNum">AWB</option>
    	<option value="invoiceID">Invoice ID</option>
    	<option value="subOrderID">Sub Order ID</option>
    	<option value="PIreferenceNo">PI Reference No</option>
    	<option value="uniqueItemId">Unique Item ID</option>
    	<option value="returnOrRTOId">Sale Return ID/ Debit Note No</option>
    	<option value="pcName">Partner</option> 
    	<option value="productSkuCode">Product SKU</option>
    	<option value="customerName">Customer Name</option> 
    	<option value="customerCity">Customer City</option>
    	<option value="customerEmail">Customer mail</option>
    	<option value="customerPhnNo">Customer Phone No</option>    	
    	<option value="status"> Order Status</option>
    	<option value="sellerNote">Seller Notes</option> 
		</select>
         </td>
         <td><input type="text" id="searchString" name="searchString"/></td>
        
          </tr>
          <tr>
          <td><input type="radio" name="searchType" id="searchType" value="searchByDate" checked>Search by Date
      </td>
          </tr>
          <tr>
          <td> <select id="searchDateCriteria" name="searchDateCriteria" > 
    	<option value="orderDate">Order Received Date</option>
    	<option value="shippedDate">Order Shipped Date</option>
    	<option value="deliveryDate">Order Delivery Expected Date</option> 
    	<option value="actualPaymentDate">Date of Payment</option>
    	<option value="returnDate">Return or RTO date</option>
    	</select>
          </td>
          <td>Start Date:<br>
				<input type="date" name="startDate" id="startDate" min="2000-01-02"><br><br>
				End date:<br>
				<input type="date" name="endDate" id="endDate" min="2000-01-02"><br><br>
				
      </td>
          </tr>
          <tr><td><input type="Submit" value="Submit" id="LoadOnSubmit"/> </td>
          </tr>
   </table> 
  </form>
  
  
  <%--  <c:if test="${!empty returnOrders}">
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

  <c:forEach items="${returnOrders}" var="order">
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
</c:if> --%>

<div id="OrderTableContainer"></div>
 </body>
</html>