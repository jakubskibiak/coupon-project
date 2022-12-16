
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
    <title>${product.title}</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

</head>

<body class="w3-white">

<!-- Navigation Upper bar -->
<div class="w3-display-container w3-content" style="max-width:1500px;">

    <div class="w3-bar w3-white w3-large">

    </div>
    <jsp:include page="headerBar.jsp"/>

    <form class="w3-center" method="POST" action="/redeem-coupon">

        <div class="w3-center">

            <label> Provide username:</label>
            <input name="username" placeholder="Username..." />
            <label> Provide coupon code:</label>
            <input name="code" placeholder="Code..." />
            <input type="submit"
                   class="w3-bar-item w3-button w3-khaki w3-mobile" value="Redeem" style="margin-left:5%; margin-top:25px; border-radius: 15px"/>

        </div>

    </form>

</div>
</body>
</html>
