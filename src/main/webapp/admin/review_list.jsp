<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Manage Reviews - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Review Management</h1>
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
            <th>ID</th>
            <th>Book</th>
            <th>Rating</th>
            <th>HeadLine</th>
            <th>Customer</th>
            <th>Review On</th>
            <th>Actions</th>
        </tr>


        <c:forEach var="review" items="${reviews}" varStatus="status">

            <tr>

                <td>${status.index+1}</td>
                <td>${review.reviewId}</td>
                <td>${review.book.title}</td>
                <td>${review.rating}</td>
                <td>${review.headline}</td>
                <td>${review.customer.fullName}</td>
                <td>
                    <fm:formatDate value="${review.reviewTime}"></fm:formatDate>
                </td>
                <td><a href="edit_review?reviewId=${review.reviewId}">Edit</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="deletelink" id="${review.reviewId}">Delete</a></td>
                <!-- void(0) do nothing -->

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
                    reviewId = $(this).attr("id");
                    if (confirm('Are you sure you want to delete the review with ID ' + reviewId + " ?")) {
                        window.location = "delete_review?reviewId=" + reviewId;
                    }
                })

            })
        }
    );

</script>

</body>
</html>
