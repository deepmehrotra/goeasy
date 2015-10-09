<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <body>
 <script type="text/javascript"
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
    $(document).ready(function(){
        $("[name=paymentType]").click(function(){
        	 $('.radio1').hide();
            $("#blk-"+$(this).val()).slideDown();
        });
        $('#paymentField').change(function () {
            $('.payment-box').hide();
            $('#'+$(this).val()).fadeIn();
        });
        $('#paymentField1').change(function () {
        	$('.payment-box').hide();
            $('#'+$(this).val()).fadeIn();
        });
        $('#data_1 .input-group.date').datepicker({
                todayBtn: "linked",
                keyboardNavigation: false,
                forceParse: false,
                calendarWeeks: true,
                autoclose: true
            });
    });
</script>
 <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Partner</h5>
                        </div>
                        <div class="ibox-content add-company">
                        <form:form method="POST" action="${request.contextPath}/goEasy/seller/savePartner.html" id="addpartnerform"
                         role="form" class="form-horizontal" enctype="multipart/form-data">
                           <c:if test="${!empty partner.pcId}">
                        <%--  <form:hidden path="pcId" value="${partner.pcId}"/> --%>
                         <input type="hidden" name="pcId" id="pcId" value="${partner.pcId}"/>
                         </c:if>
                        
                                <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-4 control-label">Partner Name</label>

                                    <div class="col-sm-8"><form:input path="pcName" value="${partner.pcName}" class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-4 control-label">Alias Name</label>

                                    <div class="col-sm-8"><form:input path="pcDesc" value="${partner.pcDesc}" class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="form-group"><label class="col-sm-2 control-label">Upload Logo Name</label>

                                    <div class="col-md-4">
                                        <label title="Upload image file" for="image" class="btn btn-white btn-block">
                                        <i class="fa fa-upload"></i>
                                            <input type="file" accept="image/*" name="image" id="image" class="hide" >
                                            Upload Logo
                                        </label>
                                    </div>
                                    
                                </div>
                                

                                    <div class="hr-line-dashed"></div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="radio"><label><form:radiobutton path="paymentType" value="paymentcycle" id="optionsRadios1" name="toggler"/>Payment Cycle</label>
                                    </div>
                                    <!-- <div class="radio"><label> <input type="radio" value="paymentcycle" id="optionsRadios1" name="toggler">Payment Cycle</label>
                                    </div> -->
                                </div>
                                <div class="col-sm-4">
                                    <div class="radio"><label><form:radiobutton path="paymentType" value="datewisepay" id="optionsRadios1" name="toggler"/>Payment From Delivery </label>
                                    </div>
                                    <!-- <div class="radio"><label> <input type="radio" value="datewisepay" id="optionsRadios1" name="toggler"> Payment From Delivery </label>
                                    </div> -->
                                </div>
                                <div class="col-sm-4">
                                    <div class="radio"><label><form:radiobutton path="paymentType" value="monthly" id="optionsRadios1" name="toggler"/> Monthly payment </label>
                                    </div>
                                    <!-- <div class="radio"><label> <input type="radio" value="monthly" id="optionsRadios1" name="toggler"> Monthly payment </label>
                                    </div> -->
                                </div>
                                <div class="col-sm-12 radio1" id="blk-paymentcycle">
                                    <div class="form-group">
                                        <div class="col-md-3"><form:input path="startcycleday" value="${partner.startcycleday}"  placeholder="Start Date" class="form-control"/></div>
										<div class="col-md-3"><form:input path="paycycleduration" value="${partner.paycycleduration}" placeholder="Duration of Cycle" class="form-control"/></div>
                                        <div class="col-md-3"><form:input path="paydaysfromstartday" value="${partner.paydaysfromstartday}"  placeholder="Duration of Payment from Start Date" class="form-control"/></div>
                                     </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Payment from</label>
                                        <div class="col-md-4">
                                        <form:select path="paycyclefromshipordel" items="${datemap}" class="form-control" id="paymentField"> 
                                        </form:select>
                                        </div>
                                    </div>
                                    <small class="help-block">(For ex: If your Payment cycle is staring from 5th May to 10th May and Payment date for that cycel is 15th May , then you Start Date will have 5 Duration will have 5 and Payment from SD will have 10)</small>
                                </div>
                                <div class="col-sm-12 radio1" id="blk-datewisepay">
                                    <div class="row">
                                        <div class="col-md-6">
                                             <form:select path="isshippeddatecalc" items="${datemap}" class="form-control" id="paymentField1"> 
                                        </form:select>
                                        </div>
                                        
                                        <div class="col-md-6 payment-box" id="true"><form:input path="noofdaysfromshippeddate" value="${partner.noofdaysfromshippeddate}"
                                        placeholder="Payment Days From Shipped Date" class="form-control"/></div>
                                        <div class="col-md-6 payment-box" id="false"><form:input path="noofdaysfromdeliverydate" value="${partner.noofdaysfromdeliverydate}"
                                        placeholder="Payment Days From Delivery Date" class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-12 radio1" id="blk-monthly">
                                     <div class="row">
                                        <div class="col-md-4">
                                           <form:input path="monthlypaydate" value="${partner.monthlypaydate}"  placeholder="Enter Day" class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="hr-line-dashed"></div>
                                    <div class="form-group"><label class="col-sm-3 control-label">Max RTO Acceptance Period</label>
                                            <div class="col-md-4"><form:input path="maxRTOAcceptance" value="${partner.maxRTOAcceptance}"  placeholder="Enter Value" class="form-control"/></div>
                                            <div class="col-md-4"><select class="form-control" name="account">
                                            <option>Days</option>
                                            </select></div>
                                </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="form-group"><label class="col-sm-3 control-label">Max Return Acceptance</label>
                                            <div class="col-md-4"><form:input path="maxReturnAcceptance" value="${partner.maxReturnAcceptance}"  placeholder="Enter Value" class="form-control"/></div>
                                            <div class="col-md-4"><select class="form-control" name="account">
                                            <option>Days</option>
                                            </select></div>
                                </div>
                                </div>
                                <div class="col-sm-12">
                                    <div class="form-group">
                                    <%-- <div class="col-md-3"><form:input path="taxcategory" value="${partner.taxcategory}" placeholder="Tax Category" class="form-control"/></div>
                                    <div class="col-md-4 content-rgt"><form:input path="taxrate" value="${partner.taxrate}" placeholder="Tax rate" class="form-control"/> <span>%</span></div>
                                     --%><div class="col-md-4"><form:checkbox path="tdsApplicable"/> TDS Applicable</div>
                                </div>
                                </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                 <!--    <button class="btn btn-primary pull-right" type="button"  onclick="submitform()">Save</button> -->
                                       <input type="Submit" class="btn btn-primary pull-right" value="Save"/>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
          
 </body>
</html>