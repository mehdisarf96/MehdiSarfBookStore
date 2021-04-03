<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Customer Login - MehdiSarf BookStore</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>


</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br><br><br>

<c:if test="${msg != null}">

    <div align="center" style="font-size: large; color: #d98ba0">
        <b> << ${msg} >></b>
    </div>

    <br><br>
</c:if>


<div align="center">

    <form method="post" action="submit_review" id="reviewForm">

        <table class="tablesinform" width="60%">

            <tr>
                <c:if test="${requestScope.canPostComment eq true}">
                    <td><h2>Your Comment</h2></td>
                </c:if>

                <c:if test="${canPostComment eq false}">
                    <td>
                        <div style="color: #6ed99c;font-size:large; ">
                            <b> Your Already Wrote A Review!</b>
                        </div>
                    </td>
                </c:if>

                <td>
                    <div style="color: #6ed99c; font-size:large; text-align: end">
                        ${sessionScope.theCustomer.fullName}
                    </div>
                </td>

            </tr>

            <tr>
                <td colspan="3">
                    <hr>
                </td>
            </tr>

            <tr>
                <td>
                    <b>${book.title}</b> <br><br>
                    <img src="data:image/jpg;base64,${book.base64Image}" width="240" height="300">
                </td>

                <td>

                    <c:if test="${requestScope.canPostComment eq true}">
                        <div id="rateYo"></div>
                        <input type="hidden" name="rating" id="rating">
                        <input type="hidden" name="bookId" value="${book.bookId}">

                        <br>
                    </c:if>

                    <c:if test="${requestScope.canPostComment eq false}">

                        <c:forTokens items="${theReview.ratingString}" delims="," var="star">

                            <c:if test="${star eq 'on'}">
                                <img src="images/on-rate.png">
                            </c:if>

                            <c:if test="${star eq 'half'}">
                                <img src="images/half-rate.png">
                            </c:if>

                            <c:if test="${star eq 'off'}">
                                <img src="images/off-rate.png">
                            </c:if>

                        </c:forTokens>
                        <br> <br>
                    </c:if>


                    <c:if test="${requestScope.canPostComment eq true}">
                        <input type="text" name="headline" placeholder="Headline (Required)" size="60">
                    </c:if>

                    <c:if test="${requestScope.canPostComment eq false}">
                        ${theReview.headline}
                    </c:if>

                    <br><br>

                    <c:if test="${requestScope.canPostComment eq true}">
                        <textarea name="comment" cols="70" rows="10" placeholder="Comment Here ..."></textarea>
                    </c:if>

                    <c:if test="${requestScope.canPostComment eq false}">
                        ${theReview.comment}
                    </c:if>


                </td>
            </tr>

            <c:if test="${requestScope.canPostComment eq true}">
                <tr>

                    <td colspan="3" align="center">
                        <input type="submit" value="Post Comment">
                        &nbsp;&nbsp;
                        <input type="button" value="Cancel" id="buttonCancel">
                    </td>

                </tr>
            </c:if>

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

        $("#reviewForm").validate({
            rules: {
                headline: "required",
                comment: "required"
            },

            messages: {
                headline: "HeadLine is required! Please Enter HeadLine.",
                comment: "Comment is required! Please Enter Comment."
            }
        });

        $("#rateYo").rateYo({
            starWidth: "30px",
            fullStar: true,
            ratedFill: "#6ed99c",

            onSet: function (rating, rateYoInstance) {

                $("#rating").val(rating)
            }
        });

        $("#buttonCancel").click(function () {
            history.go(-1)
        })
    });
</script>
</html>
