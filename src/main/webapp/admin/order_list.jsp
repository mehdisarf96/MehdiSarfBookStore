<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Manage Orders - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Book Orders Management</h1>
</div>

<c:if test="${msg != null}">
    <div align="center">

        <i><h4 style="color: red">${msg}</h4></i>

    </div>
</c:if>

<div align="center">

    <table border="1" cellpadding="5">

        <tr>
            <th>Index</th>
            <th>Order ID</th>
            <th>Ordered By</th>
            <th>Number Of Books</th>
            <th>Total</th>
            <th>Payment Method</th>
            <th>Status</th>
            <th>Order Date</th>
            <th>Actions</th>
        </tr>


        <c:forEach var="order" items="${orders}" varStatus="status">

            <tr>

                <td>${status.index+1}</td>
                <td>${order.orderId}</td>
                <td>${order.customer.fullName}</td>
                <td>${order.numberOfBooksInOrder}</td>
                <td><fm:formatNumber value="${order.total}" type="currency"/></td>
                <td>${order.paymentMethod}</td>
                <td>${order.status}</td>
                <td>${order.orderDate}</td>
                <td>
                    <a href="view_order?orderId=${order.orderId}">Details</a>&nbsp;&nbsp;
                    <a href="edit_order?orderId=${order.orderId}">Edit</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="deletelink" id="${order.orderId}">Delete</a></td>
            </tr>

        </c:forEach>


    </table>

</div>

<br><br>

<div align="center">

</div>

<div align="center">

</div>

<div align="center">

</div>


<!-- indclude directive -->
<
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
