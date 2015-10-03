<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <jsp:include page="../globalcsslinks.jsp"></jsp:include>
  <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/plugins/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="css/plugins/dataTables/dataTables.tableTools.min.css" rel="stylesheet">
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
    	case "viewOrder" :
    		targeturl="viewOrderDA.html?orderId="+id;
    	break;
    	case "addOrder" :
    		targeturl="addOrderDA.html";
    	break;
    	case "upload" :
    		targeturl="uploadOrderDA.html?value=ordersummary";
    	break;
    	case "download" :
    		targeturl="downloadOrderDA.html?value=ordersummary";
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
 <div id="wrapper">
<jsp:include page="../sidenavigation.jsp"></jsp:include>
    <div id="page-wrapper" class="gray-bg">
     <jsp:include page="../globalheader.jsp"></jsp:include>  
      <div class="wrapper wrapper-content animated fadeInRight" id="centerpane"> 
<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Orders(${orders.size()})</h5>
                            <div class="ibox-tools">
                                <a href="#" onclick="onclickNavigateOrder('addOrder','0')" class="btn btn-primary btn-xs" >Create New Order</a>
                            </div>
                        </div>
                        <div class="ibox-content overflow-h cus-table-filters">
                        <div class="scroll-y">
                             <table class="table table-striped table-bordered table-hover dataTables-example" >
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>#</th>
                                    <th>Order ID/PO No</th>
                                     <th>Order Recieved Date</th>
                                    <th>AWD</th>
                                    <th>Invoice ID</th>
                                    <th>Sub Order ID</th>
                                    <th>PI Reference</th>
                                    <th>Logistic Partner</th>
                                    <th>Partner</th>
                                    <th>Customer</th>
                                    <th>SKU</th>
                                   <th>Shipped Date</th>
                                    <th>Delivery Date</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                 <c:if test="${!empty orders}">
                                  <c:forEach items="${orders}" var="order" varStatus="loop">
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>${loop.index+1}</td>
                                    <td><a href="#" onclick="onclickNavigateOrder('viewOrder',${order.orderId})">${order.channelOrderID}</a></td>
                                    <td><fmt:formatDate value="${order.orderDate}" pattern="MMM-dd-YYYY"/></td>
                                    <td>${order.awbNum}</td>
                                    <td>${order.invoiceID}</td>
                                    <td>${order.subOrderID}</td>
                                    <td>${order.PIreferenceNo}</td>
                                    <td>${order.logisticPartner}</td>
                                    <td>${order.pcName}</td>
                                    <td class="tooltip-demo"> <a href="" data-toggle="tooltip" data-placement="top" data-original-title="${order.customer.customerEmail}, ${order.customer.customerPhnNo} , ${order.customer.customerCity}">${order.customer.customerName}</a></td>
                                    <td>${order.productSkuCode}</td>
                                    <td><fmt:formatDate value="${order.shippedDate}" pattern="MMM-dd-YYYY"/></td>
                                    <td><fmt:formatDate value="${order.deliveryDate}" pattern="MMM-dd-YYYY"/></td>
                                    <td class="tooltip-demo">
                                    <a href="#" onclick="onclickNavigateOrder('editOrder',${order.orderId})"><i class="fa fa-edit text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Edit"></i></a></td>
                                </tr>
                                </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                            </div>
                            <div class="col-sm-12">
                            <div class="hr-line-dashed"></div>
                                <a href="#" onclick="onclickNavigateOrder('upload',0)"  class="btn btn-success btn-xs">Bulk Upload Order</a>&nbsp;&nbsp;
                                <a href="#" onclick="onclickNavigateOrder('download',0)" class="btn btn-success btn-xs">Download Order Format</a> 
                            </div>
                          
                        </div>
                    </div>
                </div>
            </div>
  </div>
 <jsp:include page="../globalfooter.jsp"></jsp:include>

    </div>
</div>

<jsp:include page="../globaljslinks.jsp"></jsp:include>

<!-- Scripts ro Table -->



<!-- Data Tables -->
<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="js/plugins/dataTables/dataTables.responsive.js"></script>
<script src="js/plugins/dataTables/dataTables.tableTools.min.js"></script>

<script>
    $(document).ready(function(){
        $('.dataTables-example').dataTable({
                responsive: true,
                "dom": 'T<"clear">lfrtip',
                "tableTools": {
                    "sSwfPath": "js/plugins/dataTables/swf/copy_csv_xls_pdf.swf"
                }
        });
    });
</script>
<style>
    body.DTTT_Print {
        background: #fff;

    }
    .DTTT_Print #page-wrapper {
        margin: 0;
        background:#fff;
    }

    button.DTTT_button, div.DTTT_button, a.DTTT_button {
        border: 1px solid #e7eaec;
        background: #fff;
        color: #676a6c;
        box-shadow: none;
        padding: 6px 8px;
    }
    button.DTTT_button:hover, div.DTTT_button:hover, a.DTTT_button:hover {
        border: 1px solid #d2d2d2;
        background: #fff;
        color: #676a6c;
        box-shadow: none;
        padding: 6px 8px;
    }

    .dataTables_filter label {
        margin-right: 5px;

    }
</style>
 </body>
</html>