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
		$('#ManualChargesContainer').jtable({
			title : 'Manual Charges',
			paging: true,
            sorting: true,
            messagesNew: {
                addNewRecord: 'Add Manual Charges',
                editRecord: 'Edit Manual Charges'
            },
			actions : {
				listAction : 'listManualChargesJson.html',
				updateAction : 'saveManualChargesJson.html',
				createAction: 'saveManualChargesJson.html'
			},
			fields : {
				mcId : {
					title : '',
					width : '0%',
					key : true,
					list: false,
					type :'hidden',
					create :true,
					edit:true
				},
				chargesDesc : {
					title : 'Description',
					width : '10%',
					key : true,
					list : true,
					edit : true,
					create : true
				},
				partner : {
					title : 'Partner',
					width : '10%',
					list : true,
					edit : true,
					create : true,
					options:'getPartnerForMC.html'
				},
				particular : {
					title : 'Particular',
					width : '20%',
					edit : true,
					list : true,
					create : true
				},
				paidAmount : {
					title : 'Paid Amount',
					width : '10%',
					edit : true,
					list : true,
					create : true
				},
				 dateOfPayment: {
                     title: 'Payment Date',
                     width: '12%',
                     type: 'date',
                    displayFormat: 'yy-mm-dd',
                     list : true,
 					edit : true,
 					create : true				
                    },
                uploadDate: {
                        title: 'Created On',
                        list: true,
                        edit : false,
                      //  type: 'date',
                       // displayFormat: 'mm/dd/yy',
    					create : false
                        },
                paymentCycle : {
					title : 'Payment Cycle',
					width : '10%',
					edit : true,
					list : true,
					create : true
				}
				
			}
		});
		$('#ManualChargesContainer').jtable('load');
	});

</script>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Manual Charges</h5>
				</div>
				<div class="ibox-content overflow-h">
					<div class="col-lg-12">
 						<div class="hr-line-dashed"></div>
						<div id="ManualChargesContainer"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>