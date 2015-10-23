<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../globalcsslinks.jsp"></jsp:include>
<script type="text/javascript">
    function onclickaddpartner(value,id) {
    	var urltogo="";
    	if(value=="add")
    		{
    	if(id!=0)
    		urltogo='addPartner.html?pid='+id;
    	else
    		urltogo='addPartner.html';
    		
    	}
    	else if(value=="partnerDetails")
		{
    		urltogo='listPartners.html';
		}
    	else
  			{
  			urltogo='editPartner.html?pcId='+id;
  			}
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

<div id="wrapper">
<jsp:include page="../sidenavigation.jsp"></jsp:include>
    <div id="page-wrapper" class="gray-bg">
     <jsp:include page="../globalheader.jsp"></jsp:include>  
      <div class="wrapper wrapper-content animated fadeInRight" id="centerpane"> 
<div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Active Sales Channels</h5>
                            <div class="ibox-tools">
                                <a href="#"  onclick="onclickaddpartner('add',0)" class="btn btn-primary btn-xs" >Add Sales Channel</a>&nbsp;&nbsp;
                                 <a href="#"  onclick="onclickaddpartner('partnerDetails',0)" class="btn btn-primary btn-xs" >Channel Details</a>
                            </div>
                        </div>
                        <div class="ibox-content add-company">
                         <c:if test="${!empty partners}">
                         <c:forEach items="${partners}" var="partner">
                    <div class="col-lg-3">
                        <div class="panel panel-default add-logo-page">
                            <div class="panel-body text-center">
                                <img alt="image" src="img/logo-ebay.jpg" title="${partner.pcName}">
                                <a href="#"  onclick="onclickaddpartner('edit',${partner.pcId})"><i class="fa fa-pencil"></i></a>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    </c:if>
                   
                </div>
            </div>
        </div>

        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Choose Sales Channels</h5>
                        </div>
                        <div class="ibox-content add-company">
                         <c:if test="${!empty partnertoadd}">
                         <c:forEach items="${partnertoadd}" var="addpartner" varStatus="loop">
                    <div class="col-lg-3">
                        <div class="panel panel-default add-logo-page">
                            <div class="panel-body text-center">
                                <img alt="image" src="img/logo-ebay.jpg" title="${addpartner.pcName}">
                                <a href="#"  onclick="onclickaddpartner('add',${loop.index+1})"><i class="fa fa-plus"></i></a>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    </c:if>
                   
                </div>
            </div>
        </div>

</div>

        </div>
 <jsp:include page="../globalfooter.jsp"></jsp:include>

    </div>
</div>

<jsp:include page="../globaljslinks.jsp"></jsp:include>
</body>
</html>