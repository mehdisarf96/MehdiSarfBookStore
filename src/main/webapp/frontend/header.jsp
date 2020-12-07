<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div align="center">

    <div>

        <img src="images/MehdiSarfLogo.png" style="margin-top:-30px">

    </div>

    <div style="margin-top: -40px">

        <input type="text" size="50" name="keyword">
        <input type="button" value="Search">

        &nbsp;&nbsp;&nbsp;&nbsp;

        <a href="login">Sign In</a> |
        <a href="register">Register</a> |
        <a href="admin/login.jsp">Login</a> |
        <a href="view_cart">Cart</a>

    </div>

    <div>&nbsp;&nbsp;</div>

    <div>

        <c:forEach var="category" items="${categories}" varStatus="status">

            <font size="+1">
                <b>

                    <a href="view_category?id=${category.categoryId}"> ${category.name} </a>

                </b>

                <c:if test="${not status.last}">
                    &nbsp;|&nbsp;
                </c:if>

            </font>

        </c:forEach>


    </div>

</div>
