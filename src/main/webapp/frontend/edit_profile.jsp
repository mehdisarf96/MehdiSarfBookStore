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
    <h1 class="pageheading">Edit Profile</h1>
</div>

<c:if test="${msg != null}">
    <div align="center">

        <i><h4 style="color: red">${msg}</h4></i>
    </div>
</c:if>

<div align="center">

    <form action="update_profile" method="post" id="customerForm">
        <div>
            <b> Email</b> : ${sessionScope.theCustomer.email}
        </div>

        <br>
        <div>
            <b>Full Name</b> : <input type="text" name="fullname" value="${sessionScope.theCustomer.fullName}" size="30">
        </div>

        <br>
        <div>
            <b>Phone</b> : <input type="text" name="phone" value="${sessionScope.theCustomer.phone}" size="30">
        </div>

        <br>
        <div>
            <b>Address</b> : <input type="text" name="address" value="${sessionScope.theCustomer.address}" size="30">
        </div>

        <br>
        <div>
            <b>City</b> :<input type="text" name="city" value="${sessionScope.theCustomer.city}" size="30">
        </div>

        <br>
        <div>
            <b>Zip Code</b> : <input type="text" name="zip" value="${sessionScope.theCustomer.zipcode}">
        </div>

        <br>
        <div>
            <b>Country</b> : <input type="text" name="country" value="${sessionScope.theCustomer.country}">
        </div>

        <br>

        <i>Leave Password Fields Blank If You Don't Want To Change Password.</i>

        <br><br>

        <div>
            <b>New Password</b> : <input type="password" name="password" id="password" size="30">
        </div>

        <br>
        <div>
            <b>Confirm New Password</b> : <input type="password" name="confPass" id="confPass" size="30">
        </div>


        <br><br><br>

        <input type="submit" value="Save">
        &nbsp;&nbsp;&nbsp;
        <input type="button" value="Cancel" id="buttonCancel">

    </form>

</div>


<br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

<script type="text/javascript">

    $(document).ready(function () {

        $("#customerForm").validate(
            {
                rules: {
                    email: {
                        required: true, email: true
                    },

                    fullname: "required",

                    confPass: {
                        equalTo: "#password"
                    },
                    phone: "required",
                    address: "required",
                    city: "required",
                    zip: "required",
                    country: "required"
                }
                ,
                messages: {

                    email: {
                        required: "Email is required! Please Enter Email.",
                        email: "Please Enter A Valid Email"
                    },

                    fullname: "FullName is required! Please Enter FullName.",
                    confPass: {
                        equalTo: "Confirm Password does not match Password"
                    },
                    phone: "Phone is required! Phone Enter Phone.",
                    address: "Address is required! Address Enter Address.",
                    city: "City is required! Please Enter City.",
                    zip: "Zip Code is required! Please Enter Zip Code.",
                    country: "Country is required! Please Enter Country."
                }
            });

        $("#buttonCancel").click(function () {
            history.go(-1)
        })
    });
</script>

</body>
</html>
