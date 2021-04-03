<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>MehdiSarf Admin Page</title>

    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Admin Dashboard</h1>
</div>


<div align="center">
    <hr width="80%">

    <h1 class="pageheading">Quick Actions</h1>
    <b>
        <a href="new_book">New Book</a> &nbsp;
        <a href="user_form.jsp">New User</a> &nbsp;
        <a href="category_form.jsp">New Category</a> &nbsp;
        <a href="customer_form.jsp">New Customer</a>
        <!-- engar best practicesh ine ke baraye inke esme filet malum nashe, request be samte ye servlet
        ersal beshe. mese kari ke bara new book kardam. -->
    </b>

</div>

<br><br>

<div align="center">
    <hr width="80%">
    <h2 class="pageheading"> Recent Sale </h2>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Order By</th>
            <th>Num Of Books</th>
            <th>Total</th>
            <th>Payment Method</th>
            <th>Status</th>
            <th>Order Date</th>
        </tr>

        <c:forEach items="${mostRecentOrder}" var="order" varStatus="status">
            <tr>

                <td>
                        ${order.orderId}
                </td>

                <td>
                        ${order.customer.fullName}
                </td>

                <td>
                        ${order.numberOfBooksInOrder}
                </td>

                <td>
                    <fmt:formatNumber value="${order.total}" type="currency"/>
                </td>

                <td>
                        ${order.paymentMethod}
                </td>

                <td>
                        ${order.status}
                </td>

                <td>
                        ${order.orderDate}
                </td>

            </tr>

        </c:forEach>
    </table>
</div>

<div align="center">
    <hr width="60%">

    <h2 class="pageheading"> Recent Review </h2>

    <table border="1">
        <tr>
            <th>Book</th>
            <th>Rating</th>
            <th>HeadLine</th>
            <th>Customer</th>
            <th>Review On</th>
        </tr>
        <c:forEach items="${mostRecentReviews}" var="review" varStatus="status">

            <tr>

                <td>
                        ${review.book.title}
                </td>

                <td>
                        ${review.rating}
                </td>

                <td>
                        ${review.headline}
                </td>

                <td>
                        ${review.customer.fullName}
                </td>

                <td>
                        ${review.reviewTime}
                </td>

            </tr>

        </c:forEach>
    </table>

</div>

<div align="center">
    <hr width="60%">

    <h2 class="pageheading"> Statistic </h2>

    <table border="5" style="text-align: center">

        <tr>
            <th>number Of Users</th>
            <th>number Of Books</th>
            <th>number Of Customers</th>
            <th>number Of Reviews</th>
            <th>number Of Orders</th>
        </tr>

        <tr>
            <td>${numOfUsers}</td>
            <td>${numOfBooks}</td>
            <td>${numOfCustomers}</td>
            <td>${numOfReviews}</td>
            <td>${numOfOrders}</td>
        </tr>

    </table>
</div>


<!-- indclude directive -->
<
<%@ include file="footer.jsp" %>

</body>
</html>
