<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <style>
        body, h1, h2, h3, h4, h5, h6 {
            font-family: Verdana, sans-serif
        }
        div.align-left {
            text-align: left;
        }
    </style>
    <!--  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <title>Coupon site</title>
</head>
<body>

<!-- Navigation Upper bar -->
<div class="w3-display-container w3-content" style="max-width:1500px;">

    <div class="w3-bar w3-large">
        <jsp:include page="headerBar.jsp"/>
    </div>

    <div class="w3-center">
        <h1>Welcome my friend!</h1>
    </div>

    <div class="w3-center" style="margin:15px 25% 15px; width:50%;">
        <div>On this website you can see details of your coupons and current balance</div>
        <div>Please choose what you want to do:</div>
        <div class="align-left">
            <ul>
                <li><a href="/details">See details</a></li>
                <li><a href="/redeem-coupon">Redeem a coupon</a></li>

            </ul>
        </div>
    </div>

</div>


</body>
</html>