<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

 <body>
 <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Reports</h5>
                        </div>
                        <div class="ibox-content report-links">
                        <div class="col-sm-12">
                             <div class="form-group">
                             <label class="col-sm-2 control-label label-text-mrg">Select Here</label>
                                    <div class="col-sm-10">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <select class="form-control" name="account">
                                                    <option>AWB</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                </select>
                                            </div>
                                            <div class="col-md-4">
                                                <select class="form-control" name="account">
                                                    <option>AWB</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                </select></div>
                                            <div class="col-md-2">
                                                <button class="btn btn-success " type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;<span class="bold">Search</span></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Order Related</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Total Shipped Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Payment Difference Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Return/RTO reason Analysis</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Settled Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Sale return Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Actionable Orders</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Disputed Orders</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Return/RTO limit crossed Orders</a>
                            </div>
                        </div>
                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Taxation</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> TDS to be deposited</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Sales Tax Liability Report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> List of Invoices</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Partner Related</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Partner Commission Paid Report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Partner Business Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Expense</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Expenditure Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Inventory</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Inventory Upload History</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Closing stock report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Inventory Movement Details</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Inventory Threshold Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>General</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Customer Database</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Best Selling SKU report</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Best Selling Region Report</a>
                            </div>
                        </div>

                        <div class="col-sm-12">
                        <div class="hr-line-dashed"></div>
                            <h2><small>Net Reports</small></h2>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Net Sale Report</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Net Debtors</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Total Payments Received</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Gross Profitability Report</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Performance Analysis</a>
                            </div>
                            <div class="col-md-4">
                                <a href=""><i class="fa fa-arrow-right"></i> Net Profitability Report</a>
                                <a href=""><i class="fa fa-arrow-right"></i> Total Turnover</a>
                            </div>
                        </div>
                                
                        </div>
                    </div>
                </div>
            </div>
 </body>
</html>