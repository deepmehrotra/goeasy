<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Spring MVC Form Handling</title>
<!--   <script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function submitform(){
    	     $.ajax({
                    url: $("#addpartnerform").attr("action"),
                    context: document.body,
                    type: 'post',
                    data:$("#addpartnerform").serialize(),
                    success : function(res) {
                                  
                        $("#centerpane").html(res);
                   
                }
             });
      
    };
</script> -->
  
 </head>
 <body>
 
  <h2>Add Partner Data </h2>
  <form:form method="POST" action="${request.contextPath}/goEasy/seller/savePartnertest.html" id="addpartnerform">
      <table>
       <tr>
           <td><form:label path="pcId">Partner ID:</form:label></td>
           <td><form:input path="pcId" value="${partner.pcId}" readonly="true"/></td>
       </tr>
       <tr>
           <td><form:label path="pcName">Partner Name:</form:label></td>
           <td><form:input path="pcName" value="${partner.pcName}"/></td>
       </tr>
       <tr>
           <td><form:label path="pcDesc">Partner Desc:</form:label></td>
           <td><form:input path="pcDesc" value="${partner.pcDesc}"/></td>
       </tr>
       <tr>
           <td><form:label path="pcLogoUrl">Partner LogoURL:</form:label></td>
                    <td><form:input path="pcLogoUrl" value="${partner.pcLogoUrl}"/></td>
       </tr>
       <tr>
                
                <td><form:radiobutton path="paymentType" value="paymentcycle"/> Payment Cycle</td>
                <td> <form:radiobutton path="paymentType" value="datewisepay"/> Date wise payment </td>
                 <td> <form:radiobutton path="paymentType" value="monthly"/> Monthly Payment </td>
            </tr>
       
       <tr>
                
                <td><form:radiobutton path="isshippeddatecalc" value="true"/> Shipping Date Calc
                 <form:radiobutton path="isshippeddatecalc" value="false"/> Delivery date calc </td>
            </tr>
       <tr>
           <td><form:label path="noofdaysfromshippeddate">Partner noofdaysfromshippeddate:</form:label></td>
                    <td><form:input path="noofdaysfromshippeddate" value="${partner.noofdaysfromshippeddate}"/></td>
       </tr>
       
        <tr>
           <td><form:label path="startcycleday">Partner startcycleday:</form:label></td>
                    <td><form:input path="startcycleday" value="${partner.startcycleday}"/></td>
       <td><form:label path="paycycleduration">Partner paycycleduration:</form:label></td>
                    <td><form:input path="paycycleduration" value="${partner.paycycleduration}"/></td>
       <td><form:label path="paydaysfromstartday">Partner paydaysfromstartday:</form:label></td>
                    <td><form:input path="paydaysfromstartday" value="${partner.paydaysfromstartday}"/></td>
        <td><form:radiobutton path="paycyclefromshipordel" value="true"/> Payment from Shipping date
                 <form:radiobutton path="paycyclefromshipordel" value="false"/>Payment from Delivery date</td>
      
       </tr>
         <tr>
           <td><form:label path="monthlypaydate">Partner monthlypaydate:</form:label></td>
                    <td><form:input path="monthlypaydate" value="${partner.monthlypaydate}"/></td>
       </tr>
        <tr>
           <td><form:label path="taxcategory">Partner taxcategory:</form:label></td>
                    <td><form:input path="taxcategory" value="${partner.taxcategory}"/></td>
   
           <td><form:label path="taxrate">Partner taxrate:</form:label></td>
                    <td><form:input path="taxrate" value="${partner.taxrate}"/></td>
                    
          <td><form:label path="tdsApplicable">Partner tdsApplicable:</form:label></td>
                    <td><form:checkbox path="tdsApplicable" /></td>
                    
          
       </tr>
      <tr>
         <td colspan="2">
         <!-- <input type="button" value="Submit" onclick="submitform()"> -->
         <input type="submit" value="Submit"/></td>
       </tr>
   </table> 
  </form:form>
  
  <c:if test="${!empty partners}">
  <h2>List Partners</h2>
 <table align="left" border="1">
  <tr>
   <th>Partner ID</th>
   <th>Partner Name</th>
   <th>Partner Description</th>
   <th>Partner LogoURL</th>
   <th>Partner paymentType</th>
   <th>Partner isshippeddatecalc</th>
   <th>Partner noofdaysfromshippeddate</th>
   <th>Partner startcycleday</th>
   <th>Partner paycycleduration</th>
   <th>Partner paycyclefromshipordel</th>
    <th>Partner monthlypaydate</th>
   <th>Partner taxcategory</th>
   <th>Partner tdsApplicable</th>
           <th>Actions on Row</th>
  </tr>

  <c:forEach items="${partners}" var="partner">
   <tr>
    <td><c:out value="${partner.pcId}"/></td>
    <td><c:out value="${partner.pcName}"/></td>
    <td><c:out value="${partner.pcDesc}"/></td>
    <td><c:out value="${partner.pcLogoUrl}"/></td>
    <td><c:out value="${partner.paymentType}"/></td>
    <td><c:out value="${partner.isshippeddatecalc}"/></td>
    <td><c:out value="${partner.noofdaysfromshippeddate}"/></td>
    <td><c:out value="${partner.startcycleday}"/></td>
    <td><c:out value="${partner.paycycleduration}"/></td>
    <td><c:out value="${partner.paycyclefromshipordel}"/></td>
    <td><c:out value="${partner.monthlypaydate}"/></td>
    <td><c:out value="${partner.taxcategory}"/></td>
    <td><c:out value="${partner.tdsApplicable}"/></td>
    <td align="center"><a href="editPartner.html?pcId=${partner.pcId}">Edit</a> | <a href="deletePartner.html?pcId=${partner.pcId}">Delete</a></td>
   </tr>
  </c:forEach>
 </table>
</c:if>
 </body>
</html>