<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create New Category - MehdiSarf Admin Page</title>
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
    <h1 class="pageheading">Create New Category</h1>
</div>


<div align="center">

    <form method="post" action="create_category" id="categoryform">

        <table class="tablesinform">

            <tr>
                <td align="right">Name:</td>
                <td align="left"><input type="text" size="30" name="name" id="name"></td>
                <!-- id ham moshakhas mikonam ke betunam ba js bashun kar konam. -->
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
        $("#categoryform").validate(
            {
                rules: {name: {required: true}}
                ,
                messages: {name: "Please Enter Category Name "}
            }
        );

        $("#buttonCancel").click(function () {
            history.go(-1)
        })
    });

</script>
</html>
