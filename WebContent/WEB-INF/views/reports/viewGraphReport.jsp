<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

  <jsp:include page="../globalcsslinks.jsp"></jsp:include>

    <!-- orris -->
    <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

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
                            <h5>Reports</h5>
                        </div>
                        <div class="ibox-content overflow-h">
                            
                            <div class="panel-options">

                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#tab-1">View Graph</a></li>
                                    <li class=""><a data-toggle="tab" href="#tab-2">View Report</a></li>
                                </ul>
                            </div>
                            <div class="tab-content">
                            <div id="tab-1" class="tab-pane active col-sm-12 chart-even">
                                <div class="row">
                                    <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                    <div class="ibox-content">
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                             <tr>
                                                <th>Partner</th>
                                                <th>Total N/R</th>
                                                <th>% N/R</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:if test="${!empty NRsortedttso}">
                                 			 <c:forEach items="${NRsortedttso}" var="nrttso" varStatus="loop">
                                            <tr>
                                                <td>${nrttso.pcName}</td>
                                                <td>${nrttso.nr}</td>
                                                <td>${nrttso.nrPercent}</td>
                                            </tr>
                                            </c:forEach>
                                            </c:if>
                                                 </tbody>
                                        </table>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                        <div class="ibox-content">
                                            <div class="flot-chart" >
                                                    <div class="flot-chart-content" id="flot-bar-chart"></div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                </div>
								
                                <div class="row">
                                    <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                    <div class="ibox-content">
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                           <tr>
                                                <th>Partner</th>
                                                <th>Sale Quantity</th>
                                                <th>% Sale Quantity</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:if test="${!empty saleQsortedttso}">
                                 			 <c:forEach items="${saleQsortedttso}" var="saleQsortedttso" varStatus="loop">
                                            <tr>
                                                <td>${saleQsortedttso.pcName}</td>
                                                <td>${saleQsortedttso.saleQuantity}</td>
                                                <td>${saleQsortedttso.saleQuantityPercent}</td>
                                            </tr>
                                            </c:forEach>
                                            </c:if>
                                           </tbody>
                                        </table>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                        <div class="ibox-content">
                                            <div class="flot-chart">
                                                <div class="flot-chart-content" id="flot-line-chart"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                    <div class="ibox-content">
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                            <tr>
                                                <th>Partner</th>
                                                <th>Sale Amount</th>
                                                <th>Sale Amount Percent</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                             <c:if test="${!empty saleAmounrsortedttso}">
                                 			 <c:forEach items="${saleAmounrsortedttso}" var="saleAmounrsortedttso" varStatus="loop">
                                            <tr>
                                                <td>${saleAmounrsortedttso.pcName}</td>
                                                <td>${saleAmounrsortedttso.netSaleAmount}</td>
                                                <td>${saleAmounrsortedttso.netSaleAmountPercent}</td>
                                            </tr>
                                            </c:forEach>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                        <div class="ibox-content">
                                            <div class="flot-chart">
                                                    <div class="flot-chart-pie-content" id="flot-pie-chart"></div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                </div>
								<div class="row">
                                    <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                    <div class="ibox-content">
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                             <tr>
                                                <th>Partner</th>
                                                <th>Return Quantity</th>
                                                <th>% Return Quantity</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                           <c:if test="${!empty returnQsortedttso}">
                                 			 <c:forEach items="${returnQsortedttso}" var="returnQsortedttso" varStatus="loop">
                                            <tr>
                                                <td>${returnQsortedttso.pcName}</td>
                                                <td>${returnQsortedttso.returnQuantity}</td>
                                                <td>${returnQsortedttso.returnQuantityPercent}</td>
                                            </tr>
                                            </c:forEach>
                                            </c:if>
                                            
                                            </tbody>
                                        </table>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                        <div class="ibox-content">
                                            <div class="flot-chart">
                                                    <div class="flot-chart-content" id="flot-bar-chart-RQ"></div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                </div>
								<div class="row">
                                    <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                    <div class="ibox-content">
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                           <tr>
                                                <th>Partner</th>
                                                <th>Return Amount</th>
                                                <th>% Return Amount</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                              <c:if test="${!empty returnAmountsortedttso}">
                                 			 <c:forEach items="${returnAmountsortedttso}" var="returnAmountsortedttso" varStatus="loop">
                                            <tr>
                                                <td>${returnAmountsortedttso.pcName}</td>
                                                <td>${returnAmountsortedttso.returnAmount}</td>
                                                <td>${returnAmountsortedttso.returnAmountPercent}</td>
                                            </tr>
                                            </c:forEach>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                        <div class="ibox-content">
                                            <div class="flot-chart">
                                             <div class="flot-chart-content" id="flot-line-chart-RA"></div>
                                                    
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                </div>

                                 <div class="row">
                                    <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                    <div class="ibox-content">
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                            <tr>
                                                <th>Cities</th>
                                                <th>Order Count</th>
                                                <th>% Orders</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                             <c:if test="${!empty citicount}">
                                 			 <c:forEach items="${citicount}" var="citimap" varStatus="loop">
                                           
                                            <tr>
                                                <td>${citimap.key}</td>
                                                <td>${citimap.value}</td>
                                                <td>${citipercent[citimap.key]}</td>
                                            </tr>
                                           </c:forEach>
                                           </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="float-e-margins graph-brd">
                                        <div class="ibox-content">
                                            <div class="flot-chart">
                                                <div class="flot-chart-pie-content" id="flot-pie-chart-CITY"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="float-e-margins graph-brd">
                                        <div >
                                         <table class="table table-bordered custom-table">
                                            <thead>
                                            <tr>
                                                 <th>Partner</th>
                                                <th>Delivered</th>
                                                <th>% Delivered</th>
                                                <th>Return</th>
                                                <th>% Return</th>
                                                <th>RTO</th>
                                                <th>% RTO</th>
                                                <th>Actionable</th>
                                                <th>% Actionable</th>
                                                <th>Settled</th>
                                                <th>% Settled</th>
                                                <th>Return Limit Crossed</th>
                                                <th>% Return Limit Crossed</th>
                                                 <th>RTO Limit Crossed</th>
                                                <th>% RTO Limit Crossed</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                              <c:if test="${!empty NRsortedttso}">
                                 			 <c:forEach items="${NRsortedttso}" var="NRsortedttso" varStatus="loop">
                                            <tr>
                                                <td>${NRsortedttso.pcName}</td>
                                                <td>${NRsortedttso.noOfDeliveredOrder}</td>
                                                <td>${NRsortedttso.deliveredOrderPercent}</td>
                                                <td>${NRsortedttso.noOfReturnOrder}</td>
                                                <td>${NRsortedttso.returnOrderPercent}</td>
                                                <td>${NRsortedttso.noOfRTOOrder}</td>
                                                <td>${NRsortedttso.RTOOrderPercent}</td>
                                                <td>${NRsortedttso.noOfActionableOrders}</td>
                                                <td>${NRsortedttso.actionableOrdersPercent}</td>
                                                <td>${NRsortedttso.noOfSettledOrders}</td>
                                                <td>${NRsortedttso.settledOrdersPercent}</td>
                                                 <td>${NRsortedttso.noOfReturnLimitCrossed}</td>
                                                <td>${NRsortedttso.returnLimitCrossedPercent}</td>
                                                 <td>${NRsortedttso.noOfRTOLimitCrossed}</td>
                                                <td>${NRsortedttso.RTOLimitCrossedPercent}</td>
                                            </tr>
                                            </c:forEach>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                            
                                            <div class="ibox-content">
                                                <div id="morris-line-chart"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="submit">Print</button>
                                </div>
                            </div>
                            <div id="tab-2" class="tab-pane col-sm-12">
                            <form role="form" class="form-horizontal">
                            <div class="col-sm-12">
                                <table class="table table-striped table-bordered table-hover dataTables-example" >
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>#</th>
                                    <th>Partner Name</th>
                                    <th>Period</th>
                                    <th>Net Payment Result</th>
                                    <th>Sale Quantity</th>
                                    <th>Return Quantity</th>
                                    <th>Delivered Order</th>
                                    <th>Payment Difference Amount</th>
                                    <th>Return Order</th>
                                    <th>Settled Order</th>
                                    <th>Actionable Order</th>
                                    <th>Return Limit Crossed</th>
                                    <th>RTO limit Crossed</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:if test="${!empty ttsolist}">
                                 			 <c:forEach items="${ttsolist}" var="ttso" varStatus="loop">
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>${loop.index+1}</td>
                                    <td>${ttso.pcName}</td>
                                    <td>${period}</td>
                                    <td>${ttso.netPaymentResult}</td>
                                    <td>${ttso.saleQuantity}</td>
                                    <td>${ttso.returnQuantity}</td>
                                    <td>${ttso.noOfDeliveredOrder}</td>
                                     <td>${ttso.totalPaymentDiffference}</td>
                                    <td>${ttso.noOfReturnOrder}</td>
                                    <td>${ttso.noOfSettledOrders}</td>
                                    <td>${ttso.noOfActionableOrders}</td>
                                    <td>${ttso.noOfReturnLimitCrossed}</td>
                                    <td>${ttso.noOfRTOLimitCrossed}</td>
                                     </tr>
                                </c:forEach>
                                </c:if>
                                </tbody>
                                </table>
                            </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="submit">Save</button>
                                </div>

                            </form>
                            </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
         <jsp:include page="../globalfooter.jsp"></jsp:include>

    </div>
</div>
</div>

<jsp:include page="../globaljslinks.jsp"></jsp:include>

<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>
<script src="js/plugins/flot/jquery.flot.time.js"></script>
<script src="js/plugins/flot/jquery.flot.axislabels.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.symbol.js"></script>
<script src="js/plugins/flot/jquery.flot.spline.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>

<!-- Morris -->
<script src="js/plugins/morris/raphael-2.1.0.min.js"></script>
<script src="js/plugins/morris/morris.js"></script>

<!-- Morris demo data-->
<script src="js/demo/morris-demo.js"></script>

<!-- ChartJS-->
<script src="js/plugins/chartJs/Chart.min.js"></script>
<script src="js/demo/flot-demo.js"></script>


<!-- Data Tables -->
<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="js/plugins/dataTables/dataTables.responsive.js"></script>
<script src="js/plugins/dataTables/dataTables.tableTools.min.js"></script>
<script>

//Script for Bar Chart

var temp=[];
var arrayfortick=[];
var i=1;
<c:forEach items="${NRsortedttso}" var="NRsortedttso" varStatus="loop">
var arr1 = [i,'${NRsortedttso.nrPercent}'];
var arr2=[i++,'${NRsortedttso.pcName}'];
temp.push(arr1);
arrayfortick.push(arr2);
</c:forEach>
 var ticks = arrayfortick; 
    
    
   //Script for  return quantity
   
var RQBarData=[];
var ticksforRQBar=[];
i=1;
<c:forEach items="${returnQsortedttso}" var="returnQsortedttso" varStatus="loop">
var arr1 = [i,'${returnQsortedttso.returnQuantityPercent}'];
var arr2=[i++,'${returnQsortedttso.pcName}'];
RQBarData.push(arr1);
ticksforRQBar.push(arr2);
</c:forEach>

 
//Script for line chart saleQsortedttso

//Populating Line data
 var linetemp=[];
 var linearrayfortick=[];
 i=1;
 <c:forEach items="${saleQsortedttso}" var="saleQsortedttso" varStatus="loop">
 var salqpercent = [i,'${saleQsortedttso.saleQuantityPercent}'];
 var saleqpartner=[i++,'${saleQsortedttso.pcName}'];
 linetemp.push(salqpercent);
 linearrayfortick.push(saleqpartner);
 </c:forEach>

 
//Script for line chart returnAmountsortedttso

//Populating Line data
var lineDataRA=[];
var linearrayRA=[];
i=1;
<c:forEach items="${returnAmountsortedttso}" var="returnAmountsortedttso" varStatus="loop">
var arr1 = [i,'${returnAmountsortedttso.returnAmountPercent}'];
var arr2=[i++,'${returnAmountsortedttso.pcName}'];
lineDataRA.push(arr1);
linearrayRA.push(arr2);
</c:forEach>
   
//Script for pie chart saleAmounrsortedttso

//Populating Pie Chart 

 var piedata = [];
    var piecolor =['#d3d3d3','#bababa','#79d2c0','#1ab394','#bababa'];

    <c:forEach items="${saleAmounrsortedttso}" var="saleAmounrsortedttso" varStatus="loop">
    piedata.push({label:'${saleAmounrsortedttso.pcName}',data:'${saleAmounrsortedttso.netSaleAmountPercent}',color:piecolor['${loop.index}']});
	 </c:forEach>
	 
	   
	//Script for pie chart cities count

	//Populating Pie Chart 

	 var citipiedata = [];
	//    var piecolor =['#d3d3d3','#bababa','#79d2c0','#1ab394','#bababa'];

	    <c:forEach items="${citipercent}" var="citipercent" varStatus="loop">
	    citipiedata.push({label:'${citipercent.key}',data:'${citipercent.value}',color:piecolor['${loop.index}']});
		 </c:forEach>
		 
		 
	//Script for morris line chart 
	
		//Populating Morris line Chart 
	
		 var linedata = [];
		var deliverobj={y:'Delivered'};
		 var returnobj={y:'Return'};
		 var rtoobj={y:'RTO'};
		 var actionobj={y:'Actionable'};
		 var settlebj={y:'Settled'};
		 var rlcobj={y:'Return Limit Crossed'};
		 var rtlcobj={y:'RTO Limit Crossed'};
		 var yaxis=[];
		// alert(" before "+deliverobj);
		    <c:forEach items="${NRsortedttso}" var="morisNRsortedttso" varStatus="loop">
		    deliverobj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.deliveredOrderPercent};
		    returnobj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.returnOrderPercent};
		    rtoobj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.RTOOrderPercent};
		    settlebj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.settledOrdersPercent};
		    actionobj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.actionableOrdersPercent};
		    rlcobj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.returnLimitCrossedPercent};
		    rtlcobj['${morisNRsortedttso.pcName}']=${morisNRsortedttso.RTOLimitCrossedPercent};
		    yaxis.push('${morisNRsortedttso.pcName}');
			 </c:forEach>
	//alert("Incynk Deklivery "+deliverobj.Incynk);
	  
			 linedata.push(deliverobj);
			 linedata.push(returnobj);
			 linedata.push(rtoobj);
			 linedata.push(settlebj);
			 linedata.push(actionobj);
			 linedata.push(rlcobj);
			 linedata.push(rtlcobj);
			 
			 
			/* var tempdata=[{ y: 'Delivered', a: 100, b: 90, c:100 },
		            { y: 'Return', a: 75, b: 65, c:10},
		            { y: 'RTO', a: 50, b: 40, c:55 },
		            { y: 'Actionable', a: 75, b: 65, c:76 },
		            { y: 'Settled', a: 50, b: 40, c:40 },
		            { y: 'Return Limt', a: 75, b: 65, c:90 },
		            { y: 'RTO limit', a: 100, b: 90, c:80 } ]; */
  // alert(yaxis);
			 
	//Calling all the charts
 $(window).load(function() {
	 
			flotbar(temp,ticks,"#flot-bar-chart");
		flotline(linetemp,linearrayfortick,"#flot-line-chart");
		flotbar(RQBarData,ticksforRQBar,"#flot-bar-chart-RQ");
		flotline(lineDataRA,linearrayRA,"#flot-line-chart-RA");
		flotpie(piedata,"#flot-pie-chart");
		flotpie(citipiedata,"#flot-pie-chart-CITY");
		morrisline(linedata,yaxis,yaxis);
		
		
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
    div.dataTables_length select{
        padding: 0 10px;
    }
</style>
</body>

</html>
