<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickpartnerInfo() {
    	
        $.ajax({
            url : 'partnerInfo.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickproductInfo() {
    	
        $.ajax({
            url : 'productInfo.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
 function onclickInventoryGroups() {
	 alert(" add Inventory group vcalled");
        $.ajax({
            url : 'inventoryGroups.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
 function onclickProducts() {
	 alert(" Product group vcalled");
        $.ajax({
            url : 'Products.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
 function onclickExpenseGroups() {
	 alert(" Expense group vcalled");
        $.ajax({
            url : 'expenseCategories.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
</script>
</head>
<body>
Initial Set up
<a href="#" onclick="onclickproductInfo()">Product Info</a>&nbsp;(<a href="#" onclick="onclickInventoryGroups()">Inventory Groups</a> &nbsp;<a href="#" onclick="onclickExpenseGroups()">Expense Groups</a> &nbsp;<a href="#" onclick="onclickProducts()">Products</a>)&nbsp;&nbsp;
<a href="#" onclick="onclickpartnerInfo()">Partner Info</a>&nbsp;&nbsp;&nbsp;
</body>
</html>