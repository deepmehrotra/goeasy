<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
 
    <title>O2R</title>
    <link href="seller/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="seller/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <link href="seller/css/animate.css" rel="stylesheet"/>
    <link href="seller/css/style.css" rel="stylesheet"/>
<style>
.error {
	color: red;
}
</style>
</head>

<body class="gray-bg">

    <div class="col-lg-7 loginscreen animated fadeInDown center-align">
        <div>
            <div class="text-center">

                <img src="seller/img/go-easy.png" alt="" class="login-logo"/>

            </div>
            <form method="post" action="<c:url value='j_spring_security_check'/>"
            class="m-t" role="form" action="index.html">
                 <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-6 b-r"><h3 class="m-t-none m-b">Sign in</h3>
                                <p>Sign in today for more experience.</p>
                                <p>
									<c:if test="${error == true}">
										<b class="error">Invalid login or password.</b>
									</c:if>
									</p>
                               
                                    <div class="form-group"><label>Email</label><input type="text" name="j_username" id="j_username" placeholder="Enter email" class="form-control"/></div>
                                    <div class="form-group"><label>Password</label> <input type="password" name="j_password" id="j_password" placeholder="Password" class="form-control"/></div>
                                    <div class="btn-forgot">
                                        <a href="">Forgot Password?</a>
                                        <label> <input type="checkbox" class="i-checks"/> Remember me </label>
                                    </div>
                                    <div class="mar-top-10">
                                        <input type="submit" class="btn btn-primary block full-width m-b" value="Log in"/>
                                    </div>
                               
                            </div>
                            <div class="col-sm-6"><h4>Not a member?</h4>
                                <p>You can create an account:</p>
                                <p class="text-center">
                                    <a href="register.html"><i class="fa fa-sign-in big-icon"></i></a>
                                </p>
                            </div>
                        </div>
                    </div>
            </form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="seller/js/jquery-2.1.1.js"></script>
    <script src="seller/js/bootstrap.min.js"></script>

</body>

</html>