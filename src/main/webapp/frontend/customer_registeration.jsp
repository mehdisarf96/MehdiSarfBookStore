<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

    <title>Sign Up - MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Create Your Mehdisarf BookStore Account </h1>
</div>


<div align="center">

    <form method="post" action="register_customer" id="customerForm">

        <table class="tablesinform">

            <tr>
                <td align="right">E-mail:</td>
                <td align="left"><input type="text" size="30" name="email" id="email"></td>
                <!-- id ham moshakhas mikonam ke betunam ba js bashun kar konam. -->
            </tr>

            <tr>
                <td align="right">Full Name:</td>
                <td align="left"><input type="text" size="30" name="fullname" id="fullname"></td>
            </tr>

            <tr>
                <td align="right">Password:</td>
                <td align="left"><input type="password" size="30" name="password" id="password"></td>
            </tr>

            <tr>
                <td align="right">Confirm Password:</td>
                <td align="left"><input type="text" size="30" name="confPass" id="confPass"></td>
            </tr>

            <tr>
                <td align="right">Phone:</td>
                <td align="left"><input type="text" size="30" name="phone" id="phone"></td>
            </tr>

            <tr>
                <td align="right">Address:</td>
                <td align="left"><input type="text" size="30" name="address" id="address"></td>
            </tr>

            <tr>
                <td align="right">City:</td>
                <td align="left"><input type="text" size="30" name="city" id="city"></td>
            </tr>

            <tr>
                <td align="right">Zip Code:</td>
                <td align="left"><input type="text" size="30" name="zip" id="zip"></td>
            </tr>

            <tr>
                <td align="right">Country:</td>
                <td align="left"><input type="text" size="30" name="country" id="country"></td>
            </tr>

            <tr>
                <td>&nbsp;</td>
            </tr>

            <tr>
                <td align="center" colspan="2">
                    <input type="submit" value="Submit">
                    &nbsp;&nbsp;&nbsp;
                    <input type="button" value="Cancel" id="buttonCancel">
                </td>
            </tr>

        </table>

        &nbsp;

        <jsp:useBean id="now" class="java.util.Date"/>
        <input type="hidden" name="registerDate" value="${now}">

    </form>

</div>

<br><br><br>


<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    $(document).ready(function () {

        $("#customerForm").validate(
            {
                rules: {
                    email: {
                        required: true, email: true
                    },

                    fullname: "required", password: "required",

                    confPass: {
                        required: true,
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
                    password: "Password is required! Please Enter Password.",
                    confPass: {
                        required: "Confirm Password is required! Please Enter Confirm Password.",
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
</html>
