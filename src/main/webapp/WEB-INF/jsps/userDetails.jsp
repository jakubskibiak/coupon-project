<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="w3-center">
  <h1>User details</h1>
</div>

<table style="margin:auto">
  <tr style="font-size:18px">
    <th>FirstName</th>
    <th>LastName</th>
    <th>Username</th>
    <th>Total price</th>
  </tr>
    <tr>
      <td>${userDetails.firstName}</td>
      <td>${userDetails.lastName}</td>
      <td>${userDetails.username}</td>
      <td>${userDetails.totalPrice}</td>

    </tr>
</table>