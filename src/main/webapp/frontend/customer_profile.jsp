<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Welcome, ${sessionScope.theCustomer.fullName}</h1>
</div>

<c:if test="${msg != null}">
    <div align="center">

        <i><h4 style="color: red">${msg}</h4></i>

    </div>
</c:if>

<div align="center">

    <div>
        <b> Full Name</b> : ${sessionScope.theCustomer.fullName}
    </div>


    <br>
    <div>
        <b>Email</b> : ${sessionScope.theCustomer.email}
    </div>

    <br>
    <div>
        <b>Phone</b> : ${sessionScope.theCustomer.phone}
    </div>

    <br>
    <div>
        <b>Address</b> : ${sessionScope.theCustomer.address}
    </div>

    <br>
    <div>
        <b>City</b> :${sessionScope.theCustomer.city}
    </div>

    <br>
    <div>
        <b>Zip Code</b> : ${sessionScope.theCustomer.zipcode}
    </div>

    <br>
    <div>
        <b>Country</b> : ${sessionScope.theCustomer.country}
    </div>

    <br><br><br>
    <form method="post" action="edit_profile">
        <input type="submit" value="Edit My Profile">
    </form>

</div>


<br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
</html>
