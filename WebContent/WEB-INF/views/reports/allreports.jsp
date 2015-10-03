<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <jsp:include page="../globalcsslinks.jsp"></jsp:include>

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
                            <h5>Reports</h5>
                        </div>
                        <div class="ibox-content report-links">
                        <div class="col-sm-12">
                        
                             <div class="form-group">
                             <label class="col-sm-2 control-label label-text-mrg">Select Report</label>
                                    <div class="col-sm-10">
                                    
                                        <div class="row">
                                            <div class="col-md-4">
                                                <select class="form-control" name="reportGroup" id="reportGroup" >
                                                    <option value="">Select Report Category</option>
                                                    <option value="OrderRelatedReport">Order Related Reports</option>
                                                    <option value="PartnerRelated">Partner Related Reports</option>
                                                    <option value="ExpenseReports">Expense Reports</option>
                                                    <option value="InventoryReports">Inventory Reports</option>
                                                    <option value="GeneralReports">General Reports</option>
                                                    <option value="RevenueReports">Revenue Reports</option>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <select class="form-control" name="reportName" id="reportName">
                                                   
                                                </select></div>
                                            <div class="col-md-2">
                                                <button class="btn btn-success " type="button" onclick="submitReport('SelectBox')"><i class="fa fa-search"></i>&nbsp;&nbsp;<span class="bold">Search</span></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    
                        </div>
                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Order Related</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Total Shipped Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Payment Difference Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Return/RTO reason Analysis</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Settled Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Sale return Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Actionable Orders</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Disputed Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Return/RTO limit crossed Orders</a>
                            </div>
                        </div>
                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Taxation</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> TDS to be deposited</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Sales Tax Liability Report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> List of Invoices</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Partner Related</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Partner Commission Paid Report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Partner Business Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Expense</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Expenditure Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Inventory</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Inventory Upload History</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Closing stock report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Inventory Movement Details</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Inventory Threshold Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>General</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Customer Database</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Best Selling SKU report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Best Selling Region Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Revenue Reports</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Net Sale Report</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Net Debtors</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Total Payments Received</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Gross Profitability Report</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Performance Analysis</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Net Profitability Report</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Total Turnover</a>
                            </div>
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
<script type="text/javascript">

$(document).ready(function(){
var OrderRelatedReport = [
                  {display: "Total Shipped Orders", value: "totalShippedOrders" }, 
                  {display: "Payment Difference Orders", value: "paymentDifferenceOrders" }, 
                  {display: " Return/RTO reason Analysis", value: " returnRTOReasonAnalysis" },
                  {display: "Sale return Orders", value: "saleReturnOrders" }, 
                  {display: "Actionable Orders", value: "actionableOrders" },
                  {display: "Disputed Orders", value: "disputedOrders" }, 
                  {display: " Return/RTO limit crossed Orders", value: " returnRTOLimitCrossed" },
                  {display: "Settled Orders", value: "settledOrders" }];
                  
var PartnerRelated = [
                  {display: "Partner Commission Paid Report", value: "partnerCommissionReport" }, 
                  {display: "Partner Business Report", value: "partnerBusinessReport" }];
                  
var ExpenseReports = [
                  {display: "Expenditure Report", value: "expenditureReport" }
                  ];
var InventoryReports = [
                 {display: "Inventory Upload History", value: "inventoryUploadHistory" }, 
                 {display: "Inventory Movement Details", value: "inventoryMovementDetails" }, 
                 {display: "Inventory Threshold Report", value: "inventoryThresholdReport" },
                 {display: "Closing stock report", value: "closingStockReport" }];
var GeneralReports = [
                 {display: "Customer Database", value: "customerDatabase" }, 
                 {display: "Best Selling SKU", value: "bestSellingSKUreport" }, 
                 {display: "Best Selling Region Report", value: "bestSellingRegionReport" }];
var RevenueReports = [
                 {display: "Net Sale Report", value: "netSaleReport" }, 
                 {display: "Net Debtors", value: "netDebtors" }, 
                 {display: "Gross Profitability Report", value: "grossProfitabilityReport" },
                 {display: "Net Profitability Report", value: "netProfitabilityReport" }, 
                 {display: "Net Debtors", value: "netDebtors" },
                 {display: "Performance Analysis", value: "performanceAnalysis" }, 
                 {display: "Total Turnover", value: "totalTurnover" },
                 {display: "Total Payments Received", value: "totalPaymentsReceived" }];
                 
$("#reportGroup").change(function() {
	alert(" On change event");
    var parent = $(this).val(); 
    switch(parent){ 
        case 'OrderRelatedReport':
             list(OrderRelatedReport);
            break;
        case 'PartnerRelated':
             list(PartnerRelated);
            break;              
        case 'ExpenseReports':
             list(ExpenseReports);
            break; 
        case 'InventoryReports':
            list(InventoryReports);
           break; 
        case 'GeneralReports':
            list(GeneralReports);
           break; 
        case 'RevenueReports':
            list(RevenueReports);
           break; 
        default: //default child option is blank
            $("#reportName").html('');  
            break;
           }
});


//function to populate child select box
function list(array_list)
{
    $("#reportName").html(""); //reset child options
    $(array_list).each(function (i) { //populate child options 
        $("#reportName").append("<option value=\""+array_list[i].value+"\">"+array_list[i].display+"</option>");
    });
}

 

});

function submitReport(value){
	alert("insir=de submit"+value);
	var targeturl='';
	if(value=='SelectBox')
		{
		value=$("#reportName").val();
		}
	
	targeturl="getReportsFilter.html?reportName="+value;
	
	     $.ajax({
                url: targeturl,
                success : function(res) {
                              
                    $("#centerpane").html(res);
               
            }
         });
}

 
</script>
 </body>
</html>