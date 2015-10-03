<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
 <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Create Orders</h5>
                        </div>
                        <div class="ibox-content overflow-h">
                        <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveOrderDA.html"
                         role="form" class="form-horizontal" id="addOrderForm">
                            <c:if test="${!empty order.orderId}">
                      		<input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>
                         	</c:if>
                            <div class="panel-options">

                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#tab-1">Order</a></li>
                                    <li class=""><a data-toggle="tab" href="#tab-2">Customer</a></li>
                                    <li class=""><a data-toggle="tab" href="#tab-3">Payment</a></li>
                                    <li class=""><a data-toggle="tab" href="#tab-4">RTO</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">
                            <div id="tab-1" class="tab-pane active col-sm-12">
                                <div class="col-sm-6">
                                   <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Order ID/PO No</label>
                                    <div class="col-sm-7"><form:input path="channelOrderID" value="${order.channelOrderID}" 
                                    class="form-control"/></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Invoice ID</label>
                                    <div class="col-sm-7"><form:input path="invoiceID" value="${order.invoiceID}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Sub Order ID</label>
                                    <div class="col-sm-7"><form:input path="subOrderID" value="${order.subOrderID}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Flipkart Unique Item ID</label>
                                    <div class="col-sm-7"><form:input path="uniqueItemId" value="${order.uniqueItemId}"
                                     class="form-control"/></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">PI Reference No</label>
                                    <div class="col-sm-7"><form:input path="PIreferenceNo" value="${order.PIreferenceNo}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Order MRP</label>
                                    <div class="col-sm-7"><form:input path="orderMRP" value="${order.orderMRP}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Order SP</label>
                                    <div class="col-sm-7"><form:input path="orderSP" value="${order.orderSP}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Quantity</label>
                                    <div class="col-sm-7"><form:input path="quantity" value="${order.quantity}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Payment Type</label>
                                    <div class="col-sm-7"> <select class="form-control" name="account" id="paymentType">
                                         <option value="COD">COD</option>
                                          <option value="Prepaid">Prepaid</option>
                                          </select></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Seller Note</label>
                                    <div class="col-sm-7"><form:input path="sellerNote" value="${order.sellerNote}"
                                     class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-6 ">
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Order Recieved Date</label>

                                    <div class="col-sm-8" id="data_1">
									<div class="input-group date">
                                     <span class="input-group-addon"><i class="fa fa-calendar"></i></span><form:input path="orderDate" value="${order.orderDate}"
                                     class="form-control"/></div></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">SKU</label>

                                    <div class="col-sm-8"><form:input path="productSkuCode" value="${order.productSkuCode}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Partner</label>

                                    <div class="col-sm-8"><form:select path="pcName" items="${partnermap}"
                                     class="form-control"></form:select></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">AWB</label>

                                    <div class="col-sm-8"><form:input path="awbNum" value="${order.awbNum}"
                                     class="form-control"/></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Shipping Charges</label>
									

									<c:choose>
									 <c:when test="${order.shippingCharges gt 0}">
									    <div class="col-sm-8">
									<form:input path="shippingCharges" value="${order.shippingCharges}"
                                     class="form-control"/></div>
									 </c:when>
									 <c:otherwise>
									    <div class="col-sm-8">
									<form:input path="shippingCharges" class="form-control" value=" "/></div>
									 </c:otherwise>
									</c:choose></div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Shipping Date</label>
									<div class="col-sm-8" id="data_1">
									<div class="input-group date">
                                     <span class="input-group-addon"><i class="fa fa-calendar"></i></span><form:input path="shippedDate" value="${order.shippedDate}"
                                     class="form-control"/></div></div>
                                    
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Delivery Date</label>

                                   <div class="col-sm-8" id="data_1">
									<div class="input-group date">
                                     <span class="input-group-addon"><i class="fa fa-calendar"></i></span><form:input path="deliveryDate" value="${order.deliveryDate}"
                                     class="form-control"/></div></div>
                                    </div>
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Gross N/R</label>
                            		<c:if test="${order.grossNetRate!=0.0}">
                                    <div class="col-sm-8"><form:input path="grossNetRate" value="${order.grossNetRate}"
                                     class="form-control"/></div>
                                     </c:if>
                                    </div>
                                    
									<div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Logistic Partner</label>

                                   <div class="col-sm-8"><form:input path="logisticPartner" value="${order.logisticPartner}"
                                     class="form-control"/></div>
                                    </div>
                                </div>
                            </div>
                            <div id="tab-2" class="tab-pane col-sm-12">
                                <div class="col-sm-6">
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Customer Name</label>
                                    <div class="col-sm-7"><form:input path="customer.customerName" value="${order.customer.customerName}"
                                     class="form-control"/></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Customer City</label>
                                    <div class="col-sm-7"><form:input path="customer.customerCity" value="${order.customer.customerCity}"
                                     class="form-control"/></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Customer Mobile</label>
                                    <div class="col-sm-7"><form:input path="customer.customerPhnNo" value="${order.customer.customerPhnNo}"
                                     class="form-control"/></div>
                                    </div>
                                </div>
                                 <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Customer Address</label>
                                    <div class="col-sm-8"><form:input path="customer.customerAddress" value="${order.customer.customerAddress}"
                                     class="form-control"/></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Customer email</label>
                                    <div class="col-sm-8"><form:input path="customer.customerEmail" value="${order.customer.customerEmail}"
                                     class="form-control"/></div>
                                    </div>
                                    
                            </div>
                            <div id="tab-3" class="tab-pane col-sm-12">
                                <div class="col-sm-6">
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">partner</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Logistic Partner</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">MRP</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                    <div><label class="col-sm-5 control-label">Shipping Chrgs from Custmr</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">SKU</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Qty</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">SP</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div ><label class="col-sm-4 control-label">N/R</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="tab-4" class="tab-pane col-sm-12">
                                <div class="col-sm-6">
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Customer Name</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Customer City</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Customer Mobile</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                    <div><label class="col-sm-5 control-label">Order Shipped Date</label>
                                    <div class="col-sm-7"><input type="text" class="form-control"></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Customer Address</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Customer mail</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Order Received Date</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                    <div ><label class="col-sm-4 control-label">Expected Delivery Date</label>
                                    <div class="col-sm-8"><input type="text" class="form-control"></div>
                                    </div>
                                </div>
                            </div>
                            </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="submit" onclick="submitOrder()">Save</button>
                                </div>
                            </form:form>
                        </div>
                </div>
            </div>
        </div>
         <script>
    $(document).ready(function(){
        $('#data_1 .input-group.date').datepicker({
                todayBtn: "linked",
                keyboardNavigation: false,
                forceParse: false,
                calendarWeeks: true,
                autoclose: true
            });
    });
</script>
 </body>
</html>