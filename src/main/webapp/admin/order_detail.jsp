<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Order Details - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Details Of Order ID: ${order.orderId}</h1>
    <h3 class="pageheading">Order Overview</h3>
</div>

<c:if test="${msg != null}">
    <div align="center">

        <i><h4 style="color: red">${msg}</h4></i>

    </div>
</c:if>

<div align="center">

    <table>
        <tr>
            <td><b>Ordered By: </b></td>
            <td>${order.customer.fullName}</td>
        </tr>

        <tr>
            <td><b>Number Of Books: </b></td>
            <td>${order.numberOfBooksInOrder}</td>
        </tr>

        <tr>
            <td><b>Total Amount: </b></td>

            <td><fm:formatNumber type="currency" value="${order.total}"/></td>
        </tr>

        <tr>
            <td><b>Recipient Name: </b></td>
            <td>${order.recipientName}</td>
        </tr>

        <tr>
            <td><b>Address: </b></td>
            <td>${order.shippingAddress}</td>
        </tr>

        <tr>
            <td><b>Payment Method: </b></td>
            <td>${order.paymentMethod}</td>
        </tr>

        <tr>
            <td><b>Status: </b></td>
            <td>${order.status}</td>
        </tr>

        <tr>
            <td><b>Order Date: </b></td>
            <td>${order.orderDate}</td>
        </tr>

    </table>

</div>

<br><br>

<div align="center">
    <h3 class="pageheading">Ordered Books</h3>
</div>

<div align="center">

    <table border="1">

        <tr>
            <th>Index</th>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>SubTotal</th>
        </tr>

        <c:forEach items="${order.orderDetails}" var="detail" varStatus="status">

            <tr>
                <td>${status.index+1}</td>
                <td>${detail.book.title}</td>
                <td>${detail.book.author}</td>
                <td><fm:formatNumber type="currency" value="${detail.book.price}"/></td>
                <td>${detail.quantity}</td>
                <td><fm:formatNumber type="currency" value="${detail.subtotal}"/></td>
            </tr>

            <tr>
                <td colspan="4" align="right"><b>Total:</b></td>
                <td><b>${order.numberOfBooksInOrder}</b></td>
                <td><b><fm:formatNumber type="currency" value="${order.total}"/></b></td>
            </tr>
        </c:forEach>
    </table>

</div>

<div align="center">

    <a href="">Edit This Order</a>
    &nbsp;&nbsp;&nbsp;
    <a href="">Delete This Order</a>

</div>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

<script>

    $(document).ready(
        function () {
            $(".deletelink").each(function () {
                $(this).on("click", function () {
                    orderId = $(this).attr("id");
                    if (confirm('Are you sure you want to delete the Order with ID ' + orderId + " ?")) {
                        window.location = "delete_order?orderId=" + orderId;
                    }
                })

            })
        }
    );

</script>

</body>
</html>
