<!-- page directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<c:if test="${message != null}">

    <br>

    <div align="center">

        <i><h4 class="error">${message}</h4></i>

    </div>

</c:if>

<br><br><br>

<div align="center" class="error">
    <h2><b>New Releases</b></h2>
</div>


<div align="center" style="width: 80% ; margin: 0 auto">

    <c:forEach items="${newBooks}" var="book">

        <div style=" display: inline-block; margin: 20px">

            <div>
                <a href="view_book?id=${book.bookId}">
                    <div><img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"></div>
                </a>
            </div>

            <div>
                <a href="view_book?id=${book.bookId}">
                    <div><b> ${book.title} </b></div>
                </a>
            </div>

            <div>

                <c:forTokens items="${book.ratingString}" delims="," var="star">

                    <c:if test="${star eq 'on'}">
                        <img src="images/on-rate.png">
                    </c:if>

                    <c:if test="${star eq 'off'}">
                        <img src="images/off-rate.png">
                    </c:if>

                    <c:if test="${star eq 'half'}">
                        <img src="images/half-rate.png">
                    </c:if>

                </c:forTokens>

            </div>

            <div>By <i> ${book.author} </i></div>

            <div><b> $${book.price} </b></div>

        </div>

    </c:forEach>

</div>

<br><br><br>

<div align="center" class="error" style="clear:both;">
    <h2><b>BESTSELLERS</b></h2>
</div>

<div align="center" style="width: 80% ; margin: 0 auto">

    <c:forEach items="${bestSellers}" var="book">

        <div style=" display: inline-block; margin: 20px">

            <div>
                <a href="view_book?id=${book.bookId}">
                    <div><img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"></div>
                </a>
            </div>

            <div>
                <a href="view_book?id=${book.bookId}">
                    <div><b> ${book.title} </b></div>
                </a>
            </div>

            <div>

                <c:forTokens items="${book.ratingString}" delims="," var="star">

                    <c:if test="${star eq 'on'}">
                        <img src="images/on-rate.png">
                    </c:if>

                    <c:if test="${star eq 'off'}">
                        <img src="images/off-rate.png">
                    </c:if>

                    <c:if test="${star eq 'half'}">
                        <img src="images/half-rate.png">
                    </c:if>

                </c:forTokens>

            </div>

            <div>By <i> ${book.author} </i></div>

            <div><b> $${book.price} </b></div>

        </div>

    </c:forEach>

</div>



<br><br><br>

<div align="center" class="error" style="clear: both">
    <h2><b>Books by Interest</b></h2>
</div>
<div align="center" style="width: 80% ; margin: 0 auto">

    <c:forEach items="${mostInterested}" var="book">

        <div style=" display: inline-block; margin: 20px">

            <div>
                <a href="view_book?id=${book.bookId}">
                    <div><img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"></div>
                </a>
            </div>

            <div>
                <a href="view_book?id=${book.bookId}">
                    <div><b> ${book.title} </b></div>
                </a>
            </div>

            <div>

                <c:forTokens items="${book.ratingString}" delims="," var="star">

                    <c:if test="${star eq 'on'}">
                        <img src="images/on-rate.png">
                    </c:if>

                    <c:if test="${star eq 'off'}">
                        <img src="images/off-rate.png">
                    </c:if>

                    <c:if test="${star eq 'half'}">
                        <img src="images/half-rate.png">
                    </c:if>

                </c:forTokens>

            </div>

            <div>By <i> ${book.author} </i></div>

            <div><b> $${book.price} </b></div>

        </div>

    </c:forEach>

</div>



<br><br><br>


<!-- indclude directive -->

<%@ include file="footer.jsp" %>

</body>
</html>
