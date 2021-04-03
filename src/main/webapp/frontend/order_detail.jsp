<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Shopping Cart - MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>

<div align="center">
    <h3 class="pageheading">Your Order Details</h3>
    <h5 class="pageheading">Your Order ID : ${order.orderId}</h5>
</div>

<c:if test="${message != null}">

    <div align="center">

        <i><h4 class="error">${message}</h4></i>

    </div>

</c:if>

<c:if test="${order == null}">
    <h3 class="pageheading">Sorry! You Are Not Authorized :(</h3>
</c:if>

<c:if test="${order != null}">

    <div align="center">
        <div>
            <table style="text-align: center" border="1">

                <tr>
                    <td><b>Order Status</b></td>
                    <td>${order.status}</td>
                </tr>

                <tr>
                    <td><b>Order Date</b></td>
                    <td>${order.orderDate}</td>
                </tr>

                <tr>
                    <td><b>Quantity</b></td>
                    <td>${order.numberOfBooksInOrder}</td>
                </tr>

                <tr>
                    <td><b>Total Amount</b></td>
                    <td>${order.total}</td>
                </tr>

                <tr>
                    <td><b>Recipient Name</b></td>
                    <td>${order.recipientName}</td>
                </tr>

                <tr>
                    <td><b>Recipient Phone</b></td>
                    <td>${order.recipientPhone}</td>
                </tr>

                <tr>
                    <td><b>Final Address</b></td>
                    <td>${order.shippingAddress}</td>
                </tr>

                <tr>
                    <td><b>Payment Method</b></td>
                    <td>${order.paymentMethod}</td>
                </tr>


            </table>
        </div>

        <br><br><br>
        <div>
            <table border="1" style="text-align: center">

                <tr>
                    <th>No</th>
                    <th>Book</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>SubTotal</th>
                </tr>

                <c:forEach items="${order.orderDetails}" var="detail" varStatus="status">
                    <tr>

                        <td>${status.index + 1}</td>
                        <td><span>${detail.book.title}</span></td>
                        <td>${detail.book.author}</td>
                        <td>${detail.book.price}</td>
                        <td>${detail.quantity}</td>
                        <td><fmt:formatNumber value="${detail.subtotal}" type="currency"/></td>
                    </tr>

                </c:forEach>

                <tr>


                    <td colspan="4" align="right"><b>Total:</b></td>
                    <td>${order.numberOfBooksInOrder}</td>
                    <td align="center">
                        <b>
                            <fmt:formatNumber value="${order.total}" type="currency"/>
                        </b>
                    </td>

                </tr>

            </table>
        </div>

    </div>
</c:if>
<br><br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
</html>
