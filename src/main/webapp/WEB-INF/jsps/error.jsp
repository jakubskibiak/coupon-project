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

</head>

<div class="w3-display-container w3-content" style="max-width:1500px;">

    <div class="w3-bar w3-white w3-large">

        <jsp:include page="headerBar.jsp"/>
        <div class="w3-center">
            <p>What a pity...</p>
            <p>Following problem occurred:</p>
            <p style="color: red"> ${message}</p>
        </div>

    </div>


</div>
</body>
</html>
