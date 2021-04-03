<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <
    <title>Edit User - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
    <!-- <script> is used to embed or reference an executable script within an HTML doc.-->
    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Edit Customer</h1>
</div>

<div align="center">

    <form method="post" action="update_customer" id="customerForm">

        <table class="tablesinform">

            <tr>
                <td align="right">E-mail:</td>
                <td align="left"><input type="text" size="30" value="${customer.email}" name="email" id="email"></td>
            </tr>

            <tr>
                <td align="right">Full Name:</td>
                <td align="left"><input type="text" size="30" value="${customer.fullName}" name="fullname"
                                        id="fullname"></td>
            </tr>

            <tr>
                <td align="right">Password:</td>
                <td align="left"><input type="password" size="30" value="${customer.password}" name="password"
                                        id="password"></td>
            </tr>

            <tr>
                <td align="right">Confirm Password:</td>
                <td align="left"><input type="text" size="30" name="confPass" id="confPass"></td>
            </tr>

            <tr>
                <td align="right">Phone:</td>
                <td align="left"><input type="text" size="30" value="${customer.phone}" name="phone" id="phone"></td>
            </tr>

            <tr>
                <td align="right">Address:</td>
                <td align="left"><input type="text" size="30" value="${customer.address}" name="address" id="address">
                </td>
            </tr>

            <tr>
                <td align="right">City:</td>
                <td align="left"><input type="text" size="30" value="${customer.city}" name="city" id="city"></td>
            </tr>

            <tr>
                <td align="right">Zip Code:</td>
                <td align="left"><input type="text" size="30" value="${customer.zipcode}" name="zip" id="zip"></td>
            </tr>

            <tr>
                <td align="right">Country:</td>
                <td align="left"><input type="text" size="30" value="${customer.country}" name="country" id="country">
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
            </tr>

            <tr>
                <td align="center" colspan="2">
                    <input type="submit" value="Save">
                    &nbsp;&nbsp;&nbsp;
                    <input type="button" value="Cancel" id="buttonCancel">
                </td>
            </tr>

        </table>

        &nbsp;

        <input type="hidden" name="registerDate" value="${customer.registerDate}">
        <input type="hidden" name="customerId" value="${customer.customerId}">

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
