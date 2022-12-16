<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="w3-center">
  <h1>Coupons</h1>
</div>

<table style="margin:auto">
  <tr style="font-size:18px">
    <th>Code</th>
    <th>Value</th>
    <th>Allowed usages</th>
    <th>Current usages</th>
    <th>Coupon owner</th>
  </tr>
  <c:forEach items="${userCoupons}" var="coupon">
    <tr>
      <td>${coupon.code}</td>
      <td>${coupon.value} EUR</td>
      <td>${coupon.allowedUsages}</td>
      <td>${coupon.currentUsages}</td>
      <td>${coupon.user.username}</td>

    </tr>
  </c:forEach>
</table>