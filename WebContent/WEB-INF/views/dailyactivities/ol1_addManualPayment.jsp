<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>

<script type="text/javascript">
    function submitExpense(){
    	     $.ajax({
                    url: $("#addManualPaymentForm").attr("action"),
                    context: document.body,
                    type: 'post',
                    data:$("#addManualPaymentForm").serialize(),
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
                            <h5>Create Payment</h5>
                        </div>
                        <div class="ibox-content overflow-h">
                            <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveManualPayment.html" id="addManualPaymentForm"
                             role="form" class="form-horizontal">
 							<%--  <c:if test="${!empty order.orderId}">
                      		<input type="hidden" name="orderId" id="orderId" value="${order.orderId}"/>
                         	</c:if> --%>
                            <div class="col-sm-12">
                                <div class="col-sm-6">
                                 <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Select OrderId</label>
                                    <div class="col-sm-7"><form:select path="orderId" items="${orderIdmap}"
                                     class="form-control">  
       								 </form:select>
                                     </div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Date of Payment</label>
                                    <div class="col-md-7" id="data_1">
                                            <div class="input-group date">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i>
                                            </span><form:input path="orderPayment.dateofPayment" value="${order.orderPayment.dateofPayment}" 
                                    class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mar-btm-20-oh"><label class="col-sm-5 control-label">Negative Amount</label>
                                    <div class="col-sm-7"><form:input path="orderPayment.negativeAmount" value="${order.orderPayment.negativeAmount}" 
                                    class="form-control"/></div>
                                    </div>
                                    <div><label class="col-sm-5 control-label">Positive Amount</label>
                                    <div class="col-sm-7"><form:input path="orderPayment.positiveamout" value="${order.orderPayment.positiveamout}" 
                                    class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                
                                    
                                    <div class="mar-btm-20-oh"><label class="col-sm-4 control-label">Quantity</label>
                                    <div class="col-sm-8"><form:input path="quantity" value="${order.quantity}" 
                                    class="form-control"/></div>
                                    </div>
                                    <div ><label class="col-sm-4 control-label">Select Partner</label>
                                    <div class="col-sm-8"><form:select path="orderId" items="${partnermap}"
                                     class="form-control">  
       								 </form:select></div>
                                    </div>
                                </div>
                            </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="submit">Save</button>
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