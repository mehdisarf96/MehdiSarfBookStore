<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

    <title>Edit Review - MehdiSarf Admin Page</title>
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
    <h1 class="pageheading">Edit Review</h1>
</div>

<div align="center">

    <form method="post" action="update_review" id="reviewForm">

        <table class="tablesinform">

            <tr>
                <td align="right">Book:</td>
                <td align="left"><b>${review.book.title}</b></td>
            </tr>

            <tr>
                <td align="right">Rating:</td>
                <td align="left">${review.rating}</td>
            </tr>

            <tr>
                <td align="right">Customer:</td>
                <td align="left">${review.customer.fullName}</td>
            </tr>

            <tr>
                <td align="right">Headline:</td>
                <td align="left"><input type="text" value="${review.headline}" size="30" name="headline" id="headline">
                </td>
            </tr>

            <tr>
                <td align="right">Comment:</td>
                <td align="left"><textarea name="comment" id="comment" rows="4" cols="50"> ${review.comment} </textarea>
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

        <input type="hidden" name="reviewId" value="${review.reviewId}">

    </form>

</div>

<br><br><br>


<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    $(document).ready(function () {

        $("#reviewForm").validate(
            {
                rules: {
                    headline: "required",
                    comment: "required"
                }
                ,
                messages: {

                    headline: "Headline is required! Please Enter Headline.",
                    comment: "Comment is required! Please Enter Comment.",
                }
            });

        $("#buttonCancel").click(function () {
            history.go(-1)
        })
    });
</script>
</html>
