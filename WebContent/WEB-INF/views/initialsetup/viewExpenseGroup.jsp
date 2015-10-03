<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
    function onclickAddExpense() {
    	alert("calling add cat" );
        $.ajax({
            url : 'addExpense.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
    function onclickAddExpenseGroup() {
    	alert(" add Expense group vcalled");
        $.ajax({
            url : 'addExpenseCategory.html',
            success : function(data) {
                $('#centerpane').html(data);
            }
        });
    }
 
</script>
</head>
<body>
<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>All Expenses</h5>
                            <div class="ibox-tools">
                                <a href="addExpense.html"  class="btn btn-primary btn-xs" >Add Expenses</a>
                            </div>
                        </div>
                        <div class="ibox-content overflow-h">
                        <div class="col-sm-12">
                            <table class="table table-bordered custom-table">
                                <thead>
                                <tr>
                                    <th>SL No.</th>
                                    <th>Name</th>
                                    <th>Date of Expense</th>
                                    <th>Expense Category</th>
                                    <th>Particular</th>
                                    <th>Amount</th>
                                    <th>Paid To</th>
                                    <th>Expenditure</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${!empty expenses}">
                                <c:forEach items="${expenses}" var="expense" varStatus="loop">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td>${expense.expenseName}</td>
                                    <td>${expense.createdOn}</td>
                                    <td>${expense.expenseCatName}</td>
                                    <td>${expense.expenseDescription}</td>
                                    <td>${expense.amount}</td>
                                     <td>${expense.paidTo}</td>
                                      <td>${expense.expenditureByperson}</td>
                                    <td class="tooltip-demo">
                                    <a href="editExpense.html?expenseId=${expense.expenseId}"><i class="fa fa-edit text-navy" data-toggle="tooltip" data-placement="top" data-original-title="Edit"></i></a></td>
                                </tr>
                                </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                            </div>
                            <div class="col-sm-12">
                            <div class="hr-line-dashed"></div>
                                <a href=""  class="btn btn-primary btn-xs">Bulk Upload Expences</a>  
                            </div>
                           
                        </div>
                    </div>
                </div>
            </div>
</body>
</html>