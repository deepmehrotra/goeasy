<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<jsp:include page="../globalcsslinks.jsp"></jsp:include>
<script type="text/javascript">
    function onclickNavigatePayment(value,id) {
    	var targeturl="";
    	switch(value)
    	{
    	
    	case "viewPayments" :
    		targeturl="viewPayments.html?uploadId="+id;
    	break;
    	case "orderPaymentSheet" :
    		targeturl="orderPaymentSheet.html";
    	break;
    	case "download" :
    		targeturl="downloadOrderDA.html?value=paymentSummary";
    	break;
    	case "upload" :
    		targeturl="uploadOrderDA.html?value=paymentSummary";
    	break;
    	case "viewManualPayments" :
    		targeturl="viewPayments.html?manualPay=true";
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
                            <h5>Upload Payment Details</h5>
                            <div class="ibox-tools">
                                <a href="#" onclick="onclickNavigatePayment('viewManualPayments',0)" class="btn btn-primary btn-xs" >View Manual Payments</a>
                            </div>
                        </div>
                        <div class="ibox-content overflow-h">
                        <div class="col-sm-12">
                            <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                                    <th>SL No.</th>
                                    <th>Payment Upload ID</th>
                                    <th>Total Positive Value</th>
                                    <th>Total Negative Value</th>
                                    <th>Manual Charges</th>
                                    <th>Net Payment Received</th>
                                    <th>Uploaded on</th>
                                    <th>Upload Status</th>
                                  <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                 <c:if test="${!empty payments}">
                                 <c:forEach items="${payments}" var="payment" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td>${payment.uploadDesc}</td>
                                   <td>${payment.totalpositivevalue}</td>
                                    <td>${payment.totalnegativevalue}</td>
                                     <td>${payment.manualCharges}</td>
                                    <td>${payment.netRecievedAmount-payment.manualCharges}</td>
                                    <td>${payment.uploadDate}</td>
                                    <td>${payment.uploadStatus}</td>
                                    <td class="tooltip-demo">
                                    <a href="#" onclick="onclickNavigatePayment('viewPayments',${payment.uploadId})"><i class="fa fa-list-alt text-navy" data-toggle="tooltip" data-placement="top" data-original-title="View"></i></a></td>
                                </tr>
                                </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                            </div>
                            <div class="col-sm-12">
                                <a href="#" onclick="onclickNavigatePayment('upload', 0)"  class="btn btn-success btn-xs">Bulk Upload payment Summary</a>&nbsp;&nbsp;
                                <a href="#" onclick="onclickNavigatePayment('download', 0)"  class="btn btn-success btn-xs">Download payment Summary</a>  
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

 </body>
</html>