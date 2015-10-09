<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="img/profile_small.jpg" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">David Williams</strong>
                             </span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="profile.html">Profile</a></li>
                                <li><a href="contacts.html">Partners</a></li>
                                <li class="divider"></li>
                                <li><a href="login.html">Logout</a></li>
                            </ul>
                            <div class="rating">
                                <span >☆</span><span>☆</span><span>☆</span><span class="active">☆</span><span class="active">☆</span>
                            </div>
                        </div>
                        <div class="logo-element">
                            GE
                        </div>
                    </li>
                <li class="active">
                    <a href="orderindex.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboard</span></a>
                </li>
                <li >
                    <a href="minor.html"><i class="fa fa-sitemap"></i> <span class="nav-label">Initial Setup</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li class="active"><a href="partners.html">Partner Info</a></li>
                        <li><a href="#">Product Info <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li><a href="#" onclick="onclickSideNavigation('InventoryGroup')">Inventory Groups</a></li>
                                <li><a href="Product.html">Product</a></li>
                                <li><a href="expenseCategories.html">Expense Group</a></li>
                            </ul>
                        </li>
                        <li><a href="listTaxCategories.html">Tax Categories</a></li>
                    </ul>
                </li>
               <li>
                <a href="minor.html"><i class="fa fa-laptop"></i> <span class="nav-label">Daily Activities</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li class="active"><a href="orderList.html">Order</a></li>
                         <li><a href="#" onclick="onclickSideNavigation('RTO/Return')">RTO/Return</a></li>
                         <li><a href="paymentUploadList.html">Payment</a></li>
                         <li><a href="#" onclick="onclickSideNavigation('Inventory')">Inventory</a></li>
                          <li><a href="expenselist.html">Expenses</a></li>
                       
                    </ul>
                </li>
                <li>
                    <a href="getAllReports.html"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Reports</span> </a>
                </li>
                 <li>
                <a href="minor.html"><i class="fa fa-wrench"></i> <span class="nav-label">Miscellaneous</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li class="active"><a href="#" onclick="onclickSideNavigation('ManualCharges')">Manual Charges</a></li>
                         <li><a href="#" onclick="onclickSideNavigation('Tax')">Tax/TDS</a></li>
                     
                    </ul>
                </li>
                <li>
                    <a href="orders.html"><i class="fa fa-envelope"></i> <span class="nav-label">Orders</span> </a>
                </li>
            </ul>
            <div class="SN-wrapper">
                <a href="" class="btn btn-info btn-circle"><i class="fa fa-facebook"></i></a>
                <a href="" class="btn btn-info btn-circle"><i class="fa fa-google-plus"></i></a>
                <a href="" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
                <a href="" class="btn btn-info btn-circle"><i class="fa fa-youtube"></i></a>
            </div>
        </div>
    </nav>

</body>
</html>