<!-- page directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>${book.title} - MehdiSarf BookStore</title>
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>
<br><br><br>


<div align="center">

    <table width="80%" style="border: 0">

        <tr>
            <td colspan="3" align="left">
                <h2>${book.title}</h2>
                by ${book.author}
            </td>
        </tr>

        <tr>
            <td rowspan="2">
                <img src="data:image/jpg;base64,${book.base64Image}" width="240" height="300">
            </td>
            <td valign="top" align="left">

                <c:forTokens items="${book.ratingString}" delims="," var="star">

                    <c:if test="${star eq 'on'}">
                        <img src="images/on-rate.png">
                    </c:if>

                    <c:if test="${star eq 'half'}">
                        <img src="images/half-rate.png">
                    </c:if>

                    <c:if test="${star eq 'off'}">
                        <img src="images/off-rate.png">
                    </c:if>

                </c:forTokens>
                &nbsp;&nbsp;

                (<a href="#reviews"> ${fn:length(book.reviews)} Reviews</a>)

            </td>
            <td valign="top" rowspan="2" width="20%">
                <h2>$${book.price}</h2>
                <br>

                <button id="addToCartBtn">Add To Cart</button>

            </td>
        </tr>

        <tr>
            <td valign="top" style="text-align: justify">
                ${book.description}
            </td>
        </tr>

        <tr>
            <td>&nbsp;&nbsp;</td>
        </tr>

        <tr>
            <td>
                <h2>
                    <a id="reviews">Customer Reviews</a>
                </h2>
            </td>
            <td colspan="2" align="center">
                <form method="post" action="write_review">
                    <input type="submit" value="Comment Your Opinion">
                    <input type="hidden" name="bookId" value="${book.bookId}">
                </form>
            </td>
        </tr>

        <tr>

            <td colspan="3" align="left">

                <table style="border: 0">

                    <c:forEach items="${book.reviews}" var="review">

                        <tr>
                            <td>

                                <c:forTokens items="${review.ratingString}" delims="," var="star">

                                    <c:if test="${star eq 'on'}">
                                        <img src="images/on-rate.png">
                                    </c:if>

                                    <c:if test="${star eq 'half'}">
                                        <img src="images/half-rate.png">
                                    </c:if>

                                    <c:if test="${star eq 'off'}">
                                        <img src="images/off-rate.png">
                                    </c:if>

                                </c:forTokens>

                                - <b> ${review.headline} </b>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                by ${review.customer.fullName} on
                                <fmt:formatDate value="${review.reviewTime}"></fmt:formatDate>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                "${review.comment}"
                            </td>
                        </tr>

                        <tr>
                            <td>&nbsp;&nbsp;</td>
                        </tr>

                    </c:forEach>

                </table>

            </td>

        </tr>

    </table>
</div>

<br><br><br>
<!-- indclude directive -->
<%@ include file="footer.jsp" %>


<script>

    $(document).ready(function () {

        $("#addToCartBtn").click(function () {
            window.location = 'add_book_to_cart?bookId=' +${book.bookId};
        });
    });


</script>

</body>
</html>
