<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Raleway">
    <style>
        body, h1, h2, h3, h4, h5, h6 {
            font-family: Verdana, sans-serif
        }

        table, th, td {
            border: 1px solid;
        }

        td {
            padding: 5px;
        }
    </style>
</head>

<body class="w3-white">

<!-- Navigation Upper bar -->
<div class="w3-display-container w3-content" style="max-width:1500px;">

    <div class="w3-bar w3-white w3-large">

        <jsp:include page="headerBar.jsp"/>
        <div class="w3-center">
            GREAT JOB! You have redeemed following coupon:
        </div>
    </div>
    <table style="margin:auto">
        <tr style="font-size:18px">
            <th>Code</th>
            <th>Value</th>
            <th>Allowed usages</th>
            <th>Current usages</th>
            <th>Coupon owner</th>
        </tr>
        <tr>
            <td>${coupon.code}</td>
            <td>${coupon.value} EUR</td>
            <td>${coupon.allowedUsages}</td>
            <td>${coupon.currentUsages}</td>
            <td>${coupon.user.username}</td>

        </tr>
    </table>

</div>
</body>
</html>
