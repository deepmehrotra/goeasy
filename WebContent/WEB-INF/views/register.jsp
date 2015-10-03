<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <img src="img/go-easy.png"/>

            </div>
             <form:form method="POST" action="saveSeller.html" class="m-t" role="form">
            <div class="form-group">
                    <form:input path="name" class="form-control" placeholder="User Name" required=""/>
                </div>
                <div class="form-group">
                    <form:input path="email" class="form-control" placeholder="Email Id" required=""/>
                </div>
                <div class="form-group">
                    <form:password path="password" class="form-control" placeholder="Password" required=""/>
                </div>
                <div class="form-group">
                   <input type="password" name="ConfirmPassword" class="form-control" placeholder="Confirm Password" required=""/>
                </div>
                <div class="form-group">
                        <div class="checkbox i-checks"><label> <input type="checkbox"/><i></i> Agree the terms and policy </label></div>
                </div>
                <button type="submit" class="btn btn-success block full-width m-b">Register</button>

                <p class="text-muted text-center"><small>Already have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="login-form.html">Login</a>
            </form:form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="seller/js/jquery-2.1.1.js"></script>
    <script src="seller/js/bootstrap.min.js"></script>

</body>

</html>