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
        $('#ProductTableContainer').jtable({
            title: 'Product List',
            paging: true,
            sorting: true,
            selecting: true, //Enable selecting
            multiselect: true, //Allow multiple selecting
            selectingCheckboxes: true, //Show checkboxes on first column
            //selectOnRowClick: false, //Enable this to only select using checkboxes
            actions: {
                listAction: 'listProductJson.html',
                //deleteAction: '/Demo/DeleteStudent',
                updateAction: 'saveProductJson.html',
                createAction: 'saveProductJson.html'
            },
            fields: {
            	productId : {
					title : '',
					width : '0%',
					key : true,
					list: false,
					type :'hidden',
					create :true,
					edit:true
				},
				productName: {
                	title: 'Product Title',
                    width: '23%',
                    list : true,
					edit : true,
					create : true,
                    inputClass: 'validate[required]'
                },
                productDate: {
                    title: 'Created On',
                    list: true,
                    edit : false,
					create : false
                    },
              productSkuCode: {
                    title: 'SKU Code',
                    list : true,
					edit : true,
					create : true,				
                    inputClass: 'validate[required]'
                },
              productPrice: {
                    title: 'Product Price',
                    width: '13%',
                    list : true,
					edit : true,
					create : true,				
					inputClass: 'validate[required]'
                },
              categoryName: {
                    title: 'Category',
                    width: '12%',
                    list : true,
					edit : true,
					create : true				
                    //options: 'getProductCategories.html'
                },
                quantity: {
                    title: 'Quantity',
                    width: '15%',
                    list : true,
					edit : true,
					create : true,
					inputClass: 'validate[required]'
                }
               
            }
        });

        //Load product list from server
        $('#ProductTableContainer').jtable('load');
    });
    
    

function onclickNavigateProduct(value) {
	var targetUrl ="";
	if(value=="upload")
		{
		targetUrl="uploadOrderDA.html?value=productSummary";
		}
	else
		{
		targetUrl="downloadOrderDA.html?value=productSummary";
		}
    $.ajax({
        url : targetUrl,
        success : function(data) {
            $('#centerpane').html(data);
        }
    });
}
 </script>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Product</h5>
				</div>
				<div class="ibox-content overflow-h">
					<div class="col-lg-12">

						<div id="ProductTableContainer"></div>
					</div>
					 <div class="col-sm-12">
                            <div class="hr-line-dashed"></div>
                                <a href="" onclick="onclickNavigateProduct('upload')" class="btn btn-success btn-xs">Bulk Upload Product</a>&nbsp;&nbsp; 
                                  <a href="" onclick="onclickNavigateProduct('download')" class="btn btn-success btn-xs">Download Product Summary</a> 
                            </div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>