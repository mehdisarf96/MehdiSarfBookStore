<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Manage Users - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">User Management</h1>
    <h3><a href="user_form.jsp">Create New User</a></h3>
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
            <th>Full Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>


        <c:forEach var="user" items="${usersList}" varStatus="status">

            <tr>

                <td>${status.index+1}</td>
                <td>${user.userId}</td>
                <td>${user.fullName}</td>
                <td>${user.email}</td>
                <td><a href="edit_user?userid=${user.userId}">Edit</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="deletelink" id="${user.userId}">Delete</a></td>
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
                    userId = $(this).attr("id");
                    if (confirm('Are you sure you want to delete the user with ID ' + userId + " ?")) {
                        window.location = "delete_user?userid=" + userId;
                    }
                })

            })
        }
    );

</script>

</body>
</html>
