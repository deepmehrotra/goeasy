<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
 <!-- Jquery Validate -->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- Data picker -->
<script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>

<script type="text/javascript">
function onclickSideNavigation(value) {
	var targeturl="";
	switch(value)
	{
	case "Partner" :
		targeturl="partners.html";
	break;
	case "ManualCharges" :
		targeturl="manualCharges.html";
	break;
	case "Payment" :
		targeturl="paymentUploadList.html";
	break;
	case "InventoryGroup" :
		targeturl="inventoryGroups.html";
	break;
	case "Product" :
		targeturl="Product.html";
	break;
	case "Expenses" :
		targeturl="expenseCategories.html";
	break;
	case "OrderDA" :
		targeturl="orderList.html";
	break;
	case "RTO/Return" :
		targeturl="returnOrderList.html";
	break;
	case "Inventory" :
		targeturl="inventoryList.html";
	break;
	case "ExpensDA" :
		targeturl="expenselist.html";
	break;
	case "Tax" :
		targeturl="taxDetailList.html";
	break;
	case "TDS" :
		targeturl="tdsDetailList.html";
	break;
	case "Reports" :
		targeturl="getAllReports.html";
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
<script>
    $(document).ready(function(){
        // $('.panel').each(function() {
        //     animationHover(this, 'fadeIn');
        // });
    });
</script>