<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script type="text/javascript">

    function submitExpenseCategory(){
    	  $.ajax({
               url: $("#addExpenseCatForm").attr("action"),
               context: document.body,
               type: 'post',
               data:$("#addExpenseCatForm").serialize(),
               success : function(res) {
                    $("#centerpane").html(res);
              
           }
        });
 
};
</script>
 </head>
 <body>
  <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Expense Group</h5>
                        </div>
                        <div class="ibox-content add-company">
                        <form:form method="POST" action="${request.contextPath}/goEasy/seller/saveExpenseCategory.html" 
                             id="addExpenseCatForm" role="form" class="form-horizontal">
                              <c:if test="${!empty expenseCategory.expcategoryId}">
                        <input type="hidden" name="expcategoryId" id="expcategoryId" value="${expenseCategory.expcategoryId}"/>
                         </c:if>
  							<div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-5 control-label">Expences Group Name</label>

                                    <div class="col-sm-7"><form:input path="expcatName" value="${expenseCategory.expcatName}"
                                    class="form-control"/>
                                  </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group"><label class="col-sm-4 control-label">Alias Name</label>

                                    <div class="col-sm-8"><form:input path="expcatDescription" value="${expenseCategory.expcatDescription}"
                                    class="form-control"/></div>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                <div class="hr-line-dashed"></div>
                                    <button class="btn btn-primary pull-right" type="submit" 
                                    onclick="submitExpenseCategory()">Save</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
 </body>
</html>