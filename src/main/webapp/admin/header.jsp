<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">

    <div>
        <a href="${pageContext.request.contextPath}/admin/">
            <img src="../images/MehdiSarfLogo.png" style="margin-top:-30px">
        </a>
    </div>

    <c:if test="${sessionScope.email != null}">

        <div style="margin-top: -40px">
            Welcome <b> ${sessionScope.email} </b> | <a href="logout">Logout</a>
        </div>

    </c:if>

    <br>

    <div id="headermenu">

        <div class="menu_item">
            <a href="list_users">
                <img src="../images/finaluser.png">
                <br>
                Users
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="list_category">
                <img src="../images/finalcategory.png">
                <br>
                Categories
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="list_book">
                <img src="../images/book.png">
                <br>
                Books
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="list_customer">
                <img src="../images/customer.png">
                <br>
                Customers
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="list_review">
                <img src="../images/finalreview.png">
                <br>
                Reviews
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="list_order">
                <img src="../images/finalorder.png">
                <br>
                Orders
            </a>
        </div>

    </div>

</div>
