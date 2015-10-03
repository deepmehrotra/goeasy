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
    function onclickintialsetup() {
    	
        $.ajax({
            url : 'initialsetup.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    
function onclickdailyactivity() {
    	
        $.ajax({
            url : 'dailyactivities.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
</script>
</head>
<body>
Its navigation
<a href="dashboard.html" onclick="onclickdashboard()">Dashboard</a>&nbsp;&nbsp;&nbsp;
<a href="#" onclick="onclickintialsetup()">Initial Setup</a>&nbsp;&nbsp;&nbsp;
<a href="#" onclick="onclickdailyactivity()">Daily Activity</a>&nbsp;&nbsp;&nbsp;
<a href="reports.html">Reports</a>&nbsp;&nbsp;&nbsp;
<a href="ordernav.html">Order</a>
</body>
</html>