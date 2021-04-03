<!-- page directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${category.name} Books - MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br><br><br>

<div align="center">
    <h2>${category.name}</h2>
</div>

<div align="center" style="width: 80% ; margin: 0 auto">

    <c:forEach items="${books}" var="book">

        <div style="float: left; display: inline-block; margin: 20px">

            <a href="view_book?id=${book.bookId}">
                <div><img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"></div>
            </a>

            <a href="view_book?id=${book.bookId}">
                <div><b> ${book.title} </b></div>
            </a>

            <div>

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

            </div>

            <div>By <i> ${book.author} </i></div>

            <div><b> $${book.price} </b></div>

        </div>

    </c:forEach>

</div>

<div align="center">


</div>


<br><br><br>


<!-- indclude directive -->

<%@ include file="footer.jsp" %>

</body>
</html>
