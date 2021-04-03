<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div align="center">

    <div>
        <a href="${pageContext.request.contextPath}">
            <img src="images/MehdiSarfLogo.png" style="margin-top:-30px">
        </a>
    </div>

    <div style="margin-top: -40px">

        <form action="search" method="post">

            <input type="text" size="50" name="keyword">
            <input type="submit" value="Search">


            &nbsp;&nbsp;&nbsp;&nbsp;

            <c:choose>

                <c:when test="${sessionScope.theCustomer ne null}">

                    Welcome
                    <a href="view_profile">
                            ${sessionScope.theCustomer.fullName}
                    </a> |

                    <a href="view_orders">My Orders</a> |
                    <a href="logout">Sign Out</a> |
                    <a href="view_cart">Cart</a>

                </c:when>

                <c:otherwise>

                    <a href="login">Sign In</a> |
                    <a href="signup">Sign Up</a> |
                    <a href="admin/login.jsp">Admin Panel</a>

                </c:otherwise>

            </c:choose>

        </form>
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
