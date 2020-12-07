<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Create New User - MehdiSarf Admin Page</title>
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
    <h1 class="pageheading">Create New User</h1>
</div>


<div align="center">

    <form method="post" action="create_user" id="userForm">

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

    </form>

</div>

<br><br><br>


<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    $(document).ready(function () {

        $("#userForm").validate(
            {
                rules: {
                    email: {required: true, email: true}, fullname: "required", password: "required"
                }
                ,
                messages: {

                    email: {
                        required: "Email is required! Please Enter Email.",
                        email: "Please Enter A Valid Email"
                    },

                    fullname: "FullName is required! Please Enter FullName.",
                    password: "Password is required! Please Enter Password."
                }
            });

        $("#buttonCancel").click(function () {
            history.go(-1)
        })
    });
</script>
</html>
