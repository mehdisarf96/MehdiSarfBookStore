<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Category - MehdiSarf Admin Page</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Edit Category</h1>
</div>


<div align="center">

    <form method="post" action="update_category" onsubmit="return validateFormInput()">

        <table class="tablesinform">

            <tr>
                <td align="right">Name:</td>
                <td align="left"><input type="text" value="${theCategory.name}" name="name" size="30" id="name"></td>
                <!-- az attribute e value baraye prefill estefade kardam. -->
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

        <input type="hidden" name="id" value="${theCategory.categoryId}">
        <!-- chon lazeme ke tu marahele bad id ro ham dashte bashim. -->

    </form>

</div>

<br><br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    function validateFormInput() {
        var name = document.getElementById("name");

        if (name.value.length == 0) {
            alert("Name is required!")
            name.focus();
            return false;
        }

        return true;
    }

</script>
</html>
