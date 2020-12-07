<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Manage Categories - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Category Management</h1>
    <h3><a href="category_form.jsp">Create New Category</a></h3>
</div>

<c:if test="${msg != null}">
    <div align="center">

        <h4 class="message">${msg}</h4>

    </div>
</c:if>

<div align="center">

    <table border="1" cellpadding="5">

        <tr>
            <th>Index</th>
            <th>ID</th>
            <th>Category Name</th>
            <th>Actions</th>
        </tr>


        <c:forEach var="category" items="${categories}" varStatus="status">

            <tr>

                <td>${status.index+1}</td>
                <td>${category.categoryId}</td>
                <td>${category.name}</td>
                <td><a href="edit_category?categoryid=${category.categoryId}">Edit</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="deletelink" id="${category.categoryId}">Delete</a></td>

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

    $(document).ready(function () {
            $(".deletelink").each(function () {
                $(this).on("click", function () {
                    categoryId = $(this).attr("id");
                    if (confirm('Are you sure you want to delete the category with ID ' + categoryId + " ?")) {
                        window.location = "delete_category?categoryid=" + categoryId;
                    }
                })
            })
        }
    )
</script>

</body>
</html>
