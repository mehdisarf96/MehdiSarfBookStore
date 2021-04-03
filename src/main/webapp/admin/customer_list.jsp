<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Manage Customers - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Customer Management</h1>
    <h3><a href="customer_form.jsp">Create New Customer</a></h3>
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
            <th>City</th>
            <th>Country</th>
            <th>Registered Date</th>
            <th>Actions</th>
        </tr>


        <c:forEach var="customer" items="${customers}" varStatus="status">

            <tr>

                <td>${status.index+1}</td>
                <td>${customer.customerId}</td>
                <td>${customer.fullName}</td>
                <td>${customer.email}</td>
                <td>${customer.city}</td>
                <td>${customer.country}</td>
                <td>
                    <fm:formatDate value="${customer.registerDate}"></fm:formatDate>
                </td>
                <td><a href="edit_customer?customerId=${customer.customerId}">Edit</a>&nbsp;&nbsp;
                    <a href="javascript:void(0);" class="deletelink" id="${customer.customerId}">Delete</a></td>
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
                    customerId = $(this).attr("id");
                    if (confirm('Are you sure you want to delete the customer with ID ' + customerId + " ?")) {
                        window.location = "delete_customer?customerId=" + customerId;
                    }
                })

            })
        }
    );

</script>

</body>
</html>
