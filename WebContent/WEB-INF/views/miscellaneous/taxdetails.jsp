<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="css/plugins/datapicker/datepicker3.css" rel="stylesheet" type="text/css">
    <link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/jtable/jtable.css" rel="stylesheet" type="text/css" />
</head>
<body>

<script src="js/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
<!-- Data picker -->
<script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {

        //Prepare jtable plugin
        $('#TaxDetailContainer').jtable({
            title: 'Tax/TDS',
            paging: true,
            sorting: true,
            messagesNew: {
                addNewRecord: 'Add Tax Details',
                editRecord: 'Edit Tax Details'
            },
            selecting: true, //Enable selecting
            multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column
            //selectOnRowClick: false, //Enable this to only select using checkboxes
            actions: {
                listAction: 'listTaxDetailsJson.html',
                deleteAction: 'deleteTaxDetailsJson.html',
                updateAction: 'saveTaxDetailsJson.html',
                createAction: 'saveTaxDetailsJson.html'
            },
            fields: {
            	taxId : {
					title : '',
					width : '0%',
					key : true,
					list: false,
					type :'hidden',
					create :true,
					edit:true
				},
				description: {
                	title: 'Description',
                    width: '23%',
                    list : true,
					edit : true,
					create : true,
                    inputClass: 'validate[required]'
                },
                uploadDate: {
                    title: 'Created On',
                    list: true,
                    edit : false,
					create : false
                    },
               taxortdsCycle: {
                    title: 'Tax Cycle',
                    list : true,
					edit : true,
					create : true,				
                    inputClass: 'validate[required]'
                },
                particular: {
                    title: 'Particular',
                    width: '13%',
                    list : true,
					edit : true,
					create : true,				
					inputClass: 'validate[required]'
                },
                paidAmount: {
                    title: 'Paid Amount',
                    width: '12%',
                    list : true,
					edit : true,
					create : true				
                    
                },
                balanceRemaining: {
                    title: 'Remianing Balance',
                    width: '12%',
                    list : true,
					edit : true,
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
                 status: {
                    title: 'Status',
                    width: '15%',
                    list : true,
					edit : true,
					create : true,
					options: {'Save' : 'Save','Close As Bad Loss': 'Close As Bad Loss','Reimbursed': 'Reimbursed'}
                },      
                taxortds: {
                    title: 'Tax/TDS',
                    width: '25%',
                    list : true,
					edit : true,
					create : true,
					options: { 'Tax': 'Tax','TDS':'TDS'}
                }
               
            },
            //Register to selectionChanged event to hanlde events                                     
            recordAdded: function(event, data){
                //after record insertion, reload the records
                $('#TaxDetailContainer').jtable('load');
            },
        });

        //Load product list from server
        $('#TaxDetailContainer').jtable('load');
    });
 </script>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Tax/TDS Details</h5>
				</div>
				<div class="ibox-content overflow-h">
					<div class="col-lg-12">

						<div id="TaxDetailContainer"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>