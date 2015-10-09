<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
   <link href="css/plugins/datapicker/datepicker3.css" rel="stylesheet" type="text/css">
    <link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/jtable/jtable.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
<!-- Data picker -->
<script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function () {

    //Radio toggle
    $("[name=toggler]").click(function(){
        $('.radio1').hide();
        $("#blk-"+$(this).val()).slideDown();
    });

    $('#data_1 .input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
    $('#data_2 .input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
	});
	
//$(document).ready(function () {
	function jtableload() {
		$('#RTOTableContainer').jtable({
			title : 'Return Order List',
			paging: true,
            sorting: true,
			actions : {
				listAction : 'findOrderDA.html?action=list',
				 createAction : 'findOrderDA.html?action=create',
				updateAction : 'findOrderDA.html?action=update'
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
					edit : true,
					list : true,
					create : true
				},
				netPaymentResult : {
					title : 'Payment Result',
					width : '15%',
					edit : false,
					list : true,
					create : false,
					display:function(data){
						return data.record.orderPayment.netPaymentResult;
	           }
				},
				returnId : {
					title : "",
					width : '0%',
					edit : true,
					list : false,
					create : false,
					input: function (data) {
				            return '<input type="hidden" name="returnId" style="width:200px" value="' + data.record.orderReturnOrRTO.returnId + '" />';
				        } 
				},
				returnOrRTOId : {
					title : 'Return/RTO ID',
					width : '10%',
					edit : true,
					list : true,
					create : false,
					display:function(data){
						return data.record.orderReturnOrRTO.returnOrRTOId;
	           },
	           input: function (data) {
		           if(data.record)
		        	   {
		        	   return '<input type="text" name="returnOrRTOId" style="width:200px" value="' + data.record.orderReturnOrRTO.returnOrRTOId + '" />';
		        		 
	        	     }
	        	   else
	        		   {
	        		   return '<input type="text" name="returnOrRTOId" style="width:200px" value="test"/>'; 
	        		   }
	        		 }
	           },
				returnDate : {
					title : 'Return Date',
					width : '10%',
					edit : true,
					list : true,
					create : true,
					display:function(data){
	                     return data.record.orderReturnOrRTO.returnDate;
	           },
	           input: function (data) {
	        	   if(data.record)
	        		   {
		        	   return '<input type="text" name="returnDate" style="width:200px" value="' + data.record.orderReturnOrRTO.returnDate + '" />';
	        		   }
		        	   else
		        		   {
		        		  return '<input type="text" name="returnDate" style="width:200px" value="" />'; 
		        		   }
			        } 
		         
				},
				returnOrRTOCharges : {
					title : 'Return Charges',
					width : '10%',
					edit : true,
					list : false,
					create : true,
					display:function(data){
	                     return data.record.orderReturnOrRTO.returnOrRTOCharges;
	           },
	           input: function (data) {
	        	   if(data.record)
	        		   {
		        	   return '<input type="text" name="returnOrRTOCharges" style="width:200px" value="' + data.record.orderReturnOrRTO.returnOrRTOCharges + '" />';
	        		   }
		        	   else
		        		   {
		        		  return '<input type="text" name="returnOrRTOCharges" style="width:200px" value="" />'; 
		        		   }
			        } 
		         
				},
				returnorrtoQty : {
					title : 'Return Quantity',
					width : '20%',
					edit : true,
					list : false,
					create : true,
					input: function (data) {
			        	   if(data.record)
			        		   {
				        	   return '<input type="text" name="returnorrtoQty" style="width:200px" value="' + data.record.orderReturnOrRTO.returnorrtoQty + '" />';
			        		   }
				        	   else
				        		   {
				        		  return '<input type="text" name="returnorrtoQty" style="width:200px" value="" />'; 
				        		   }
					        } 
				         
						},
						returnOrRTOstatus : {
							title : 'Return or RTO',
							width : '10%',
							edit : true,
							list : false,
							create : true,
							options: {'Return' : 'Return','RTO': 'RTO'}
						         
					},
				returnOrRTOreason : {
					title : 'Return reason',
					width : '20%',
					edit : true,
					list : false,
					create : true,
					input: function (data) {
			        	   if(data.record)
			        		   {
				        	   return '<input type="text" name="returnOrRTOreason" style="width:200px" value="' + data.record.orderReturnOrRTO.returnOrRTOreason + '" />';
			        		   }
				        	   else
				        		   {
				        		  return '<input type="text" name="returnOrRTOreason" style="width:200px" value="" />'; 
				        		   }
					        } 
				         
						}
				
				
			}
		});
		$('#OrderTableContainer').jtable('load');
	}
	
$('#LoadOnSubmit').click(function (e) {
    e.preventDefault();
    jtableload();
    $('#RTOTableContainer').jtable('load', {
    	searchCriteria: $('#searchCriteria').val(),
    	searchType: $('input[type="radio"]:checked').val(),
    	searchString: $('#searchString').val(),
   	 searchDateCriteria: $('#searchDateCriteria').val(),
   	 startDate: $('#startDate').val(),
   	 endDate: $('#endDate').val()
    });
});
	
</script>
 </head>
 <body>
  <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Returns</h5>
                        </div>
                        <div class="ibox-content overflow-h">
                        <form method="POST" action="findOrderDA.html?action=list" id="findOrderForm"
                        class="form-horizontal">
                        <div class="col-sm-12">
                            <div class="col-sm-4">
                                <div class="radio"><label>
                                 <input type="radio" value="searchByProperty" id="searchType" name="toggler">Search by Category</label>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="radio"><label>
                                 <input type="radio" value="searchByDate" id="searchType" name="toggler"> Search by Date </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 radio1" id="blk-searchByProperty">
                             <div class="row">
                                <div class="col-md-4">
                                    <select class="form-control" id="searchCriteria" name="searchCriteria">
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
                                </div>
                                <div class="col-md-5">
                                    <input type="text" id="searchString" name="searchString" class="form-control" placeholder="Search String">
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-sm-12 radio1" id="blk-searchByDate">
                             <div class="form-group">
                                        <div class="col-md-4">
                                            <select class="form-control" id="searchDateCriteria" name="searchDateCriteria">
                                            <option value="orderDate">Order Received Date</option>
									    	<option value="shippedDate">Order Shipped Date</option>
									    	<option value="deliveryDate">Order Delivery Expected Date</option> 
									    	<option value="actualPaymentDate">Date of Payment</option>
									    	<option value="returnDate">Return or RTO date</option>
                                            </select>
                                        </div>
                                        <div class="col-md-3" id="data_1">
                                            <div class="input-group date">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" name="startDate" id="startDate" class="form-control" value="Start Date">
                                            </div>
                                        </div>
                                        <div class="col-md-3" id="data_2">
                                            <div class="input-group date">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text"  name="endDate" id="endDate" class="form-control" value="End Date">
                                            </div>
                                        </div>
                                       
                                     </div>
                        </div>
                         <div class="col-md-2">
                                    <button class="btn btn-success " type="button" id="LoadOnSubmit"><i class="fa fa-search"></i>&nbsp;&nbsp;<span class="bold">Search</span></button>
                                </div>
                        </form>
                        <div class="col-lg-12">
                                <div class="hr-line-dashed"></div>
						<div id="RTOTableContainer"></div>
                        </div>
						</div>
					</div>
				</div>
			</div>
			
			

 </body>
</html>