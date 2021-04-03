<!-- page directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Result - MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br><br><br>

<div align="center">

    <c:if test="${ fn:length(foundBooks) == 0 }">
        <h2><b>No Result For ${keyword}</b></h2>
    </c:if>

    <c:if test="${fn:length(foundBooks) > 0 }">

    <div align="left" style="width: 80% ; margin: 0 auto">

        <c:if test="${isBlank eq null}">
            <center><h2>Result For "${keyword}"</h2></center>
        </c:if>

        <c:forEach items="${foundBooks}" var="book">
            <div>
                <div style=" display: inline-block; margin: 20px; width: 10%">
                    <div align="left">
                        <a href="view_book?id=${book.bookId}">
                            <div><img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"></div>
                        </a>
                    </div>
                </div>

                <div style=" display: inline-block; margin: 20px; vertical-align: top;width: 60%" align="left" >
                    <div>
                        <h2>
                            <a href="view_book?id=${book.bookId}">
                                <div><b> ${book.title} </b></div>
                            </a>
                        </h2>
                    </div>

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

                    <div>

                        <c:set var="description" value="${book.description}"></c:set>
                        <c:set var="shortDescription" value="${fn:substring(description, 0, 100)}"></c:set>

                            ${shortDescription} ...
                    </div>
                </div>

                <div style=" display: inline-block; margin: 20px;vertical-align: top">
                    <h3><b> $${book.price} </b></h3>
                    <h3><a href="add_book_to_cart?bookId=${book.bookId}">Add To Cart</a></h3>
                </div>
            </div>
        </c:forEach>
        </c:if>
    </div>
</div>
<br><br><br>
<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
</html>
