<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <body>
 
<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Order : ${order.channelOrderID}</h5>
                        </div>
                        <div class="ibox-content view-order">
                          
                          <div class="time-line-wrp">
                              <ul>
                                  <li class="active"><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li class="active"><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li class="active"><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                                  <li><i class="fa fa-check"></i> <span>May 10, 2015</span><p>Order</p></li>
                              </ul>
                          </div>

                        </div>
                </div>
				 <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Order Info</h5>
                        </div>
                          
                             <div class="ibox-content add-company view-order">
                             <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                                    <th>Partner</th>
                                    <th>Invoice Id</th>
                                    <th>PI Reference No</th>
									<th>Sub Order Id</th>
									<th>Order Date</th>
									<th>Status</th>
									<th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${order.pcName}</td>
                                    <td>${order.invoiceID}</td>
                                    <td>${order.PIreferenceNo}</td>
									<td>${order.subOrderID}</td>
									<td><fmt:formatDate value="${order.orderDate}" pattern="MMM-dd-YYYY"/></td>
									<td>${order.status}</td>
									<td>${order.finalStatus}</td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                        
                    
                </div>
                <div class="col-lg-12 order-info-block">
                    <div class="float-e-margins col-lg-6">
                        <div class="ibox-title">
                            <h5>Order Charges</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <div class="table-format-wrapper three-data-width">
                              <div>
                                  <span>Order MRP</span>
                                  <span></span>
                                  <span>${order.orderMRP}</span>
                              </div>
                              <div>
                                  <span>Order SP</span>
                                  <span></span>
                                  <span>${order.orderSP}</span>
                              </div>
                              <div>
                                  <span>Net Rate</span>
                                  <span></span>
                                  <span>${order.netRate}</span>
                              </div>
                              <div>
                                  <span>Quantity</span>
                                  <span></span>
                                  <span>${order.quantity}</span>
                              </div>
							  <div>
                                  <span>PO Price</span>
                                  <span></span>
                                  <span>${order.poPrice}</span>
                              </div>
                             <div>
                                  <span>Product Cost</span>
                                  <span></span>
                                  <span>NA</span>
                              </div>
                          </div>

                        </div>
                    </div>
                    <div class="float-e-margins col-lg-6">
                        <div class="ibox-title">
                            <h5>Shipping Details</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <div class="table-format-wrapper three-data-width">
                              <div>
                                  <span>AWB Number</span>
                                  <span></span>
                                  <span>${order.awbNum}</span>
                              </div>
							   <div>
                                  <span>SKU Code</span>
                                  <span></span>
                                  <span>${order.productSkuCode}</span>
                              </div>
                              <div>
                                  <span>Logistic Partner</span>
                                  <span></span>
                                  <span>${order.logisticPartner}</span>
                              </div>
                              <div>
                                  <span>Shipped Date</span>
                                  <span></span>
                                  <span><fmt:formatDate value="${order.shippedDate}" pattern="MMM-dd-YYYY"/></span>
                              </div>
                              <div>
                                  <span>Delivery Date</span>
                                  <span></span>
                                  <span><fmt:formatDate value="${order.deliveryDate}" pattern="MMM-dd-YYYY"/></span>
                              </div>
                              <div>
                                  <span>Shipping Charges</span>
                                  <span></span>
                                  <span>${order.shippingCharges}</span>
                              </div>
                          </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-12 order-info-block">
				
				
				<div class="float-e-margins col-lg-4">
                        <div class="ibox-title">
                            <h5>Payment Info</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <div class="table-format-wrapper three-data-width">
                              <div>
                                  <span>Payment Type</span>
                                  <span></span>
                                  <span>${order.paymentType}</span>
                              </div>
                              <div>
                                  <span>Payment Due Date</span>
                                  <span></span>
                                  <span><fmt:formatDate value="${order.paymentDueDate}" pattern="MMM-dd-YYYY"/></span>
                              </div>
                              <div>
                                  <span>Payment Date</span>
                                  <span></span>
                                  <span><fmt:formatDate value="${order.orderPayment.dateofPayment}" pattern="MMM-dd-YYYY"/></span>
                              </div>
                              <div>
                                  <span>Negative Amount</span>
                                  <span></span>
                                  <span>${order.orderPayment.negativeAmount}</span>
                              </div>
							  <div>
                                  <span>Positive Amount</span>
                                  <span></span>
                                  <span>${order.orderPayment.positiveamout}</span>
                              </div>
							   <div>
                                  <span>Net Payment Result</span>
                                  <span></span>
                                  <span>${order.orderPayment.netPaymentResult}</span>
                              </div>
                          </div>

                        </div>
                    </div>
                    <div class="float-e-margins col-lg-4">
                        <div class="ibox-title">
                            <h5>Order Account</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <div class="table-format-wrapper three-data-width">
                              <div>
                                  <span>Discount</span>
                                  <span></span>
                                  <span>${order.discount}</span>
                              </div>
                              <div>
                                  <span>PR</span>
                                  <span></span>
                                  <span>${order.pr}</span>
                              </div>
                              <div>
                                  <span>Partner Commission</span>
                                  <span></span>
                                  <span>${order.partnerCommission}</span>
                              </div>
                              <div>
                                  <span>Tax</span>
                                  <span></span>
                                  <span>${order.orderTax.tax}</span>
                              </div>
							  <div>
                                  <span>TDS</span>
                                  <span></span>
                                  <span>${order.orderTax.tdsToDeduct}</span>
                              </div>
							   <div>
                                 <span>Amount to receive</span>
                                  <span></span>
                                  <span>${order.totalAmountRecieved}</span>
                              </div>
							   <div class="table-total">
                                  <span>Payment Difference</span>
                                  <span></span>
                                  <span>${order.orderPayment.paymentDifference}</span>
                              </div>
                          </div>

                        </div>
                    </div>
                    <div class="float-e-margins col-lg-4">
                        <div class="ibox-title">
                            <h5>Return Details</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <div class="table-format-wrapper three-data-width">
                              <div>
                                  <span>Return ID</span>
                                  <span></span>
                                  <span>${order.orderReturnOrRTO.returnOrRTOId}</span>
                              </div>
                              <div>
                                  <span>Return or RTO</span>
                                  <span></span>
                                  <span>Return</span>
                              </div>
                              <div>
                                  <span>Return Limit</span>
                                  <span></span>
                                  <span>${order.returnLimitCrossed}</span>
                              </div>
                              <div>
                                  <span>RTO Limit</span>
                                  <span></span>
                                  <span>${order.returnLimitCrossed}</span>
                              </div>
							  <div>
                                  <span>Return Date</span>
                                  <span></span>
                                  <span><fmt:formatDate value="${order.orderReturnOrRTO.returnDate}" pattern="MMM-dd-YYYY"/></span>
                              </div>
							 <div>
                                  <span>Return Charges</span>
                                  <span></span>
                                  <span>${order.orderReturnOrRTO.returnOrRTOChargestoBeDeducted}</span>
                              </div>
							  <div>
                                  <span>Return Quantity</span>
                                  <span></span>
                                  <span>${order.orderReturnOrRTO.returnorrtoQty}</span>
                              </div>
							  <div>
                                  <span>Return Reason</span>
                                  <span></span>
                                  <span>${order.orderReturnOrRTO.returnOrRTOreason}</span>
                              </div>
                          </div>

                        </div>
                    </div>


                    
                </div>
				<div class="col-lg-12 order-info-block">
				<div class="float-e-margins col-lg-4">
                        <div class="ibox-title">
                            <h5>Customer Details</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <div class="table-format-wrapper three-data-width">
                              <div>
                                  <span>Name</span>
                                  <span></span>
                                  <span>${order.customer.customerName}</span>
                              </div>
                              <div>
                                  <span>Contact</span>
                                  <span></span>
                                  <span>${order.customer.customerPhnNo}</span>
                              </div>
                              <div>
                                  <span>Email</span>
                                  <span></span>
                                  <span>${order.customer.customerEmail}</span>
                              </div>
                              <div>
                                  <span>City</span>
                                  <span></span>
                                  <span>${order.customer.customerCity}</span>
                              </div>
                              <div>
                                  <span>Address</span>
                                  <span></span>
                                  <span>${order.customer.customerAddress}</span>
                              </div>
                          </div>

                        </div>
                    </div>
                    
                   
                </div>
                <div class="col-lg-12 order-info-block">
                    <div class="float-e-margins col-lg-12">
                        <div class="ibox-title">
                            <h5>Comments</h5>
                        </div>
                        <div class="ibox-content add-company view-order">
                                
                          <blockquote>
                              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                              <small><strong>Author name</strong> in <cite title="" data-original-title="">June 26, 2015</cite></small>
                          </blockquote>
                          <textarea class="form-control" placeholder="Type your comments here..."></textarea>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        
  <script>
    $(document).ready(function(){
        $("#form").validate({
                 rules: {
                     number: {
                         required: true,
                         number: true
                     }
                 }
             });
    });
</script>
 </body>
</html>