<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit User - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Edit User</h1>
</div>


<div align="center">

    <form method="post" action="update_user" onsubmit="return validateFormInput()">
        <!-- the onsubmit event occurs when a form is submitted.
           if it returns false - your form will not submit and will not redirect too.
           and if it returns true - your form will submit and will redirect
           -->

        <table class="tablesinform">

            <tr>
                <td align="right">E-mail:</td>
                <td align="left"><input type="text" value="${theUser.email}" name="email" size="30" id="email"></td>
                <!-- az attribute e value baraye prefill estefade kardam. -->
            </tr>

            <tr>
                <td align="right">Full Name:</td>
                <td align="left"><input type="text" value="${theUser.fullName}" name="fullname" size="30" id="fullname">
                </td>
            </tr>

            <tr>
                <td align="right">Password:</td>
                <td align="left"><input type="password" value="${theUser.password}" name="password" size="30"
                                        id="password"></td>
            </tr>

            <tr>
                <td>&nbsp;</td>
            </tr>

            <tr>
                <td align="center" colspan="2">
                    <input type="submit" value="Save">
                    &nbsp;&nbsp;&nbsp;
                    <input type="button" value="Cancel" onclick="javascript:history.go(-1)">
                </td>
            </tr>

        </table>

        &nbsp;

        <input type="hidden" value="${theUser.userId}" name="id">
        <!-- chon lazeme ke tu marahele bad id ro ham dashte bashim. -->

    </form>

</div>

<br><br><br>


<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    function validateFormInput() {
        var email = document.getElementById("email");
        var fullname = document.getElementById("fullname");
        var password = document.getElementById("password");

        if (email.value.length == 0) {
            alert("Email is required!")
            email.focus();
            return false;
        }

        if (fullname.value.length == 0) {
            alert("Full Name is required!")
            fullname.focus();
            return false;
        }

        if (password.value.length == 0) {
            alert("Password Name is required!")
            password.focus();
            return false;
        }

        return true;
    }

</script>
</html>
