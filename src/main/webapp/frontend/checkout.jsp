<%@ page import="com.mehdisarf.mehdisarfbookstore.controller.frontend.shoppingcart.ShoppingCart" %>
<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <title>Review Order - MehdiSarf BookStore</title>
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
    <h1 class="pageheading">Review Your Order Detail</h1>
</div>

<c:if test="${message != null}">

    <div align="center">

        <i><h4 class="error">${message}</h4></i>

    </div>

</c:if>


<div align="center">

    <div>
        <table border="1">

            <tr>
                <th>No</th>
                <th>Book</th>
                <th>Author</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>SubTotal</th>
            </tr>

            <c:forEach items="${sessionScope.cart.items}" var="item" varStatus="status">
                <tr>

                    <td>${status.index + 1}</td>
                    <td><span>${item.key.title}</span></td>
                    <td><span>${item.key.author}</span></td>
                    <td>${item.value}</td>
                    <td>
                        <fmt:formatNumber value="${item.key.price}" type="currency"/>
                    </td>
                    <td>
                        <fmt:formatNumber value="${item.value * item.key.price}" type="currency"/>
                    </td>
                </tr>

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
    <br><br>

    <div align="center">
        <h2 class="pageheading"> Shopping Information</h2>
        <form method="post" action="place_order" id="orderForm" style="border: 1px">
            <table>

                <tr>
                    <td><b>Recipient Name</b></td>
                    <td><input name="recipientName" value="${theCustomer.fullName}"></td>
                </tr>
                <tr>
                    <td><b>Recipient Phone</b></td>
                    <td><input name="recipientPhone" value="${theCustomer.phone}"></td>
                </tr>
                <tr>
                    <td><b>Address</b></td>
                    <td><input name="address" value="${theCustomer.address}"></td>
                </tr>
                <tr>
                    <td><b>City</b></td>
                    <td><input name="city" value="${theCustomer.city}"></td>
                </tr>
                <tr>
                    <td><b>Zip</b></td>
                    <td><input name="zip" value="${theCustomer.zipcode}"></td>
                </tr>
                <tr>
                    <td><b>Country</b></td>
                    <td><input name="country" value="${theCustomer.country}"></td>
                </tr>

            </table>
            <br><br>

            <h2 class="pageheading">Payment:</h2>

            <table style="border: 0px">
                <tr>
                    <td><b>Payment Method:</b></td>
                    <td>
                        <select name="paymentMethod">
                            <option value="Cash On Delivery">Cash On Delivery</option>
                            <option value="Checks">Checks</option>
                            <option value="Debit Card">Debit Cards</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr align="center">
                    <td>
                        <input type="submit" value="Place Order"/>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/">Continue</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>

<br><br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    $(document).ready(function () {

        $("#orderForm").validate(
            {
                rules: {
                    fullName: "required",
                    phone: "required",
                    address: "required",
                    city: "required",
                    zip: "required",
                    country: "required"

                }
                ,
                messages: {
                    fullName: "Full Name Is Required. Please Enter Full Name",
                    phone: "Phone Is Required. Please Enter Phone",
                    address: "Address Is Required. Please Enter Address",
                    city: "City Is Required. Please Enter City",
                    zip: "Zip Code Is Required. Please Enter Zip Code",
                    country: "Country Is Required. Please Enter Country"
                }
            });
    });
</script>
</html>
