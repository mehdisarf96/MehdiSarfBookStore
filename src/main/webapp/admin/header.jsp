<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">

    <div>
        <img src="../images/MehdiSarfLogo.png" style="margin-top:-30px">
    </div>

    <c:if test="${sessionScope.email != null}">

        <div style="margin-top: -40px">
            Welcome <b> ${sessionScope.email} </b> | <a href="logout">Logout</a>
        </div>

    </c:if>

    <br>

    <div id="headermenu">
        <!-- The display property specifies the display behavior of an element. -->
        <!-- meghdare "table" : Let the element behave like a <table> element -->
        <!-- meghdare "table-cell" : Let the element behave like a <td> element -->

        <!--
        In the CSS, a class selector is a name preceded by a full stop (“.”)
        and an ID selector is a name preceded by a hash character (“#”).
        The difference between an ID and a class is that an ID can be used to identify one element
        , whereas a class can be used to identify more than one
        -->


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
            <a href="">
                <img src="../images/book.png">
                <br>
                Books
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="">
                <img src="../images/customer.png">
                <br>
                Customers
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="">
                <img src="../images/finalreview.png">
                <br>
                Reviews
            </a> &nbsp;
        </div>

        <div class="menu_item">
            <a href="">
                <img src="../images/finalorder.png">
                <br>
                Orders
            </a>
        </div>

    </div>

</div>
