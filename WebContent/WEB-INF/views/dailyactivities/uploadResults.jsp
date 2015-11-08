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
                            <h5>Upload Status</h5>
                           
                        </div>
                          <div class="ibox-title">
                         <table>
	                       
	                       <tbody>
	                       <tr><td>Uploaded File</td><td></td><td>:    ${fileName}</td></tr>
	                        <tr><td>Status</td><td></td><td>:     ${status}</td></tr>
	                        <tr><td>Time taken</td><td></td><td>:    ${timeTaken} seconds</td></tr>
	                        <tr><td>Failed Results</td><td></td><td>:    ${orderMap.size()}</td></tr>
	                        <tr><td>Action</td><td></td><td>:    ${action}</td></tr>                       
	                       </tbody>
                       </table>
                       </div>
                        <div class="ibox-content overflow-h cus-table-filters">
                        <div class="scroll-y">
                      
                        <table class="table table-striped table-bordered table-hover dataTables-example" >
                                <thead>
                                <c:choose>
								    <c:when test="${mapType=='orderMap'}">
								       <tr>
	                                    <th></th>
	                                    <th>#</th>
	                                    <th>ChannelOrderID</th>
	                                    <th>OrderRecievedDate</th>
	                                    <th>SkUCode</th>
	                                    <th>Partner</th>
	                                    <th>Customer Name</th>
	                                    <th>Payment Type</th>
	                                    <th>AWB No.</th>
	                                    <th>InvoiceID</th>
	                                    <th>subOrderID</th>
	                                    <th>PIreferenceNo</th>
	                                    <th>Logistic Partner</th>
	                                    <th>Order MRP</th>
	                                    <th>Order SP</th>
	                                     <th>Shipping Charges</th>
	                                    <th>Shipped Date</th>
	                                    <th>Delivery Date</th>
	                                    <th>Quantity</th>
	                                    <th>Net Rate</th>
	                                    <th>Customer Email</th>
	                                    <th>Customer Phone No</th>
	                                    <th>Customer City</th>
	                                    <th>Customer Address</th>
	                                    <th>Tax Category</th>
	                                    <th>Seller Notes</th>
	                                    <th>Error Message</th>
	                                    </tr>
		                                </thead>
		                                
		                                <tbody>
		                                   <c:if test="${!empty orderMap}">
                                  <c:forEach items="${orderMap}" var="orderMap" varStatus="loop">
                                  <c:set var="order" value="${orderMap.value}" />
		                                <tr>
	                                    <td><input type="checkbox"></td>
	                                    <td>${loop.index+1}</td>
	                                    <td>${order.channelOrderID}</td>
	                                    <td><fmt:formatDate value="${order.orderDate}" pattern="MM/dd/yyyy"/></td>
	                                    <%-- <td>${order.orderDate}</td> --%>
	                                    <td>${order.productSkuCode}</td>
	                                    <td>${order.pcName}</td>
	                                    <td>${order.customer.customerName}</td>
	                                    <td>${object.paymentType}</td>
	                                     <td>${order.awbNum}</td>
	                                    <td>${order.invoiceID}</td>
	                                    <td>${order.subOrderID}</td>
	                                    <td>${order.PIreferenceNo}</td>
	                                    <td>${order.logisticPartner}</td>
	                                    <td>${order.orderMRP}</td>
	                                    <td>${order.orderSP}</td>
	                                    <td>${order.shippingCharges}</td>
	                                    <td><fmt:formatDate value="${order.shippedDate}" pattern="MM/dd/yyyy"/></td>
                                        <td><fmt:formatDate value="${order.deliveryDate}" pattern="MM/dd/yyyy"/></td>
                                        <td>${order.quantity}</td>
	                                    <td>${order.grossNetRate}</td>
	                                    <td>${order.customer.customerEmail}</td>
	                                    <td>${order.customer.customerPhnNo}</td>
	                                    <td>${order.customer.customerCity}</td>
	                                    <td>${order.customer.customerAddress}</td>
	                                    <td>${order.orderTax.taxCategtory}</td>
	                                    <td>${order.sellerNote}</td>
                                    	<td style="color:red;font-weight:bold">${orderMap.key}</td>
	                                   </tr>
	                                	</c:forEach>
	                                	</c:if>
								    	</c:when>
									    <c:when test="${condition2}">
									        //do something if condition2 is true
									    </c:when>
									    <c:otherwise>
									       
									    </c:otherwise>
								</c:choose>
                                
                                </tbody>
                            </table>
                            </div>
                           <!--  <div class="col-sm-12">
                            <div class="hr-line-dashed"></div>
                                <a href=""  class="btn btn-success btn-xs">Print Recent Actions</a> 
                            </div>
                            <div class="col-sm-12">
                            <div class="hr-line-dashed"></div>
                                <button class="btn btn-primary pull-right" type="submit">Save</button>
                            </div> -->
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