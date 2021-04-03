<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Manage Books - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Book Management</h1>
    <h3><a href="new_book">Create New Book</a></h3>
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
            <th>Image</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Price</th>
            <th>Last Updated</th>
            <th>Action</th>
        </tr>


        <c:forEach var="book" items="${books}" varStatus="status">

            <tr>

                <td>${status.index+1}</td>
                <td>${book.bookId}</td>
                <td><img src="data:image/jpg;base64,${book.base64Image}" width="80" height="120"></td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.category.name}</td>
                <td>$ ${book.price}</td>

                <td>
                    <fmt:formatDate value="${book.lastUpdateTime}" pattern="MM/dd/yyyy"/>
                </td>

                <td><a href="edit_book?bookid=${book.bookId}">Edit</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="deletelink" id="${book.bookId}">Delete</a></td>
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
                    bookId = $(this).attr("id");
                    if (confirm('Are you sure you want to delete the user with ID ' + bookId + " ?")) {
                        window.location = "delete_book?bookid=" + bookId;
                    }
                })
            })
        }
    );

</script>

</body>
</html>
