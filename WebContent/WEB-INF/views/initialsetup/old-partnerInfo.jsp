<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function onclickaddpartner(id) {
    	var urltogo="";
    	if(id!=0)
    		urltogo='addPartner.html?pid='+id;
    	else
    		urltogo='addPartner.html';
        $.ajax({
        	
            url : urltogo,
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
</script>
</head>
<body>
<table align="left" border="1">
  <tr>
   <th>S.No</th>
   <th>Partner Name</th>
   <th>Logo</th>
   <th>Actions</th>
  </tr>

 
   <tr>
    <td><c:out value="1"/></td>
    <td><c:out value="Amazon"/></td>
    <td><img src="images/amazon.PNG" alt="Amazon" /></td>
     <td align="center"><a href="#" onclick="onclickaddpartner(1)">ADD</a></td>
   </tr>
    <tr>
    <td><c:out value="2"/></td>
    <td><c:out value="Flipkart"/></td>
    <td><img src="images/FlipkartCom.jpg" alt="Flipkart" /></td>
     <td align="center"><a href="#" onclick="onclickaddpartner(2)">ADD</a></td>
   </tr>
 
 </table>
 <div>
 <a href="#" onclick="onclickaddpartner(0)">ADD NEW</a>
 <img src="images/goeasy_logo.jpg" alt="goeasylogo" /></div>
</body>
</html>