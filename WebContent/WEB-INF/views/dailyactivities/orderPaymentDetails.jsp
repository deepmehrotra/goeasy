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
		$('#OrderPaymentContainer').jtable({
			title : 'Payment List',
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
	
function onclickAddPayment() {
	var targeturl="addManualPayment.html";
	
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
 <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Upload Payment Details</h5>
                             <div class="ibox-tools">
                                <a href="#" onclick="onclickAddPayment()" class="btn btn-primary btn-xs" >Add Manual Payments</a>
                            </div>
                        </div>
                        <div class="ibox-content overflow-h">
                          <div class="col-lg-12">
                                <div class="hr-line-dashed"></div>
						<div id="OrderPaymentContainer"></div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>

 </body>
</html>