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
		$('#ProductInventoryContainer').jtable({
			title : 'Product Inventories',
			paging: true,
            sorting: true,
			actions : {
				listAction : 'showInventory.html?action=list',
				updateAction : 'showInventory.html?action=update',
			},
			fields : {
				productId : {
					title : '',
					width : '0%',
					key : true,
					list: false,
					type :'hidden',
					create :true,
					edit:true
				},
				productName : {
					title : 'ProductName',
					width : '10%',
					list : true
				},
				productSkuCode : {
					title : 'SKU',
					width : '10%',
					list : true,
					edit : true,
					create : false
				},
				quantity : {
					title : 'Present Inventory',
					width : '20%',
					edit : false,
					list : true
				},
				categoryName : {
					title : 'Inventory Group',
					width : '10%',
					edit : false,
					list : true
				},
				
				currentInventory : {
					title : 'Current Inventory',
					width : '10%',
					edit : false,
					list : false
				},
				quantityToAdd : {
					title : 'Add to Inventory',
					width : '10%',
					edit : true,
					list : false
				},
				quantityToSubstract : {
					title : 'Substract from Inventory',
					width : '10%',
					edit : true,
					list : false
				}
				
			}
		});
		$('#ProductInventoryContainer').jtable('load');
	});
	

</script>
 </head>
 <body>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Inventory Update</h5>
				</div>
				<div class="ibox-content overflow-h">
					<div class="col-lg-12">
 						<div class="hr-line-dashed"></div>
						<div id="ProductInventoryContainer"></div>
					</div>
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>