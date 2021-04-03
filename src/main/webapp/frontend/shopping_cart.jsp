<%@ page import="com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart.ShoppingCart" %>
<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <title>Shopping Cart - MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- <script> is used to embed or reference an executable script within an HTML doc.-->
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Your Shopping Cart</h1>
</div>

<c:if test="${message != null}">

    <div align="center">

        <i><h4 class="error">${message}</h4></i>

    </div>

</c:if>


<div align="center">

    <c:if test="${sessionScope.cart.totalItem == 0}">
        <h4>There Is No Item In Your Cart.</h4>
    </c:if>

    <c:if test="${sessionScope.cart.totalItem > 0}">

        <div>
            <form method="post" action="update_cart" id="cartForm" style="border: 0px">
                <div>
                    <table style="border: 1px">

                        <tr>
                            <th>No</th>
                            <th>Book</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>SubTotal</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach items="${sessionScope.cart.items}" var="item" varStatus="status">
                            <tr>

                                <td>${status.index + 1}</td>
                                <td><span>${item.key.title}</span></td>
                                <!--<td><input type="text" name="quantity${status.index + 1}" value="${item.value}"
                                           size="6"/></td>
                                <td>-->
                                <td><input type="text" name="quantity" value="${item.value}"
                                           size="6"/></td>
                                <td>
                                    <fmt:formatNumber value="${item.key.price}" type="currency"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${item.value * item.key.price}" type="currency"/>
                                </td>
                                <td><a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a></td>
                            </tr>

                            <input type="hidden" name="bookId" value="${item.key.bookId}">

                        </c:forEach>

                        <tr>

                            <td></td>
                            <td></td>
                            <td></td>

                            <td><b>${sessionScope.cart.totalQuantity} Book(s)</b></td>
                            <td>Total:</td>
                            <td colspan="2">
                                <b>
                                    <fmt:formatNumber value="${sessionScope.cart.totalAmount}" type="currency"/>
                                </b>
                            </td>

                        </tr>

                    </table>
                </div>

                <div>
                    &nbsp;&nbsp;<br>
                    <table style="border: 0px">
                        <tr>
                            <td></td>
                            <td>
                                <button type="submit">Update</button>
                            </td>
                            <td>
                                <input type="button" value="Clear Cart" id="clearCart"/>
                            </td>
                            <td><a href="${pageContext.request.contextPath}/">Continue</a></td>
                            <td><a href="checkout">Check Out</a></td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>

    </c:if>

</div>

<br><br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    $(document).ready(function () {

        $("#clearCart").click(function () {
            window.location = 'clear_cart';
        });

        $("#cartForm").validate(
            {
                rules: {

                    <c:forEach items="${sessionScope.cart.items}" var="item" varStatus="status">
                    quantity${status.index + 1}: {required: true, number: true, min: 1},
                    </c:forEach>
                }
                ,
                messages: {

                    <c:forEach items="${sessionScope.cart.items}" var="item" varStatus="status">
                    quantity${status.index + 1}: {
                        required: "Enter Quantity",
                        number: "Quantity Must Be A Number",
                        min: "Quantity Must Be Greater Than 0"
                    },
                    </c:forEach>

                }
            });
    });
</script>
</html>
