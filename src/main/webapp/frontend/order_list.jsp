<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Orders History</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>
<br>
<div align="center">
    <h1 class="pageheading">${theCustomer.fullName} Orders History</h1>
</div>

<c:if test="${msg != null}">
    <div align="center">

        <h4 class="message">${msg}</h4>

    </div>
</c:if>

<div align="center">
    <table border="1" cellpadding="5" style="text-align: center">

        <tr>
            <th>Index</th>
            <th>Order ID</th>
            <th>Quantity</th>
            <th>Total Amount</th>
            <th>Order Date</th>
            <th>Status</th>
            <th></th>
        </tr>


        <c:forEach var="order" items="${orders}" varStatus="status">
            <tr>
                <td><b>${status.index+1}</b></td>
                <td>${order.orderId}</td>
                <td>${order.numberOfBooksInOrder}</td>
                <td><fmt:formatNumber value="${order.total}" type="currency"/></td>
                <td>${order.orderDate}</td>
                <td>${order.status}</td>
                <td><a href="view_details?orderId=${order.orderId}">View Detail</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<br><br><br><br>
<%@ include file="footer.jsp" %>
</body>
</html>