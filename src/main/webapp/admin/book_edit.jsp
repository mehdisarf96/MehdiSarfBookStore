<!-- Page Directive -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Taglib Directive -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <title>Edit New Book - MehdiSarf Admin Page</title>

    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/richtext.min.css">

    <script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="../js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="../js/jquery.richtext.min.js"></script>

</head>
<body>

<!-- indclude directive -->
<%@ include file="header.jsp" %>

<br>


<div align="center">
    <h1 class="pageheading">Edit New Book</h1>
</div>


<div align="center">

    <form method="post" action="update_book" id="bookForm" enctype="multipart/form-data">

        <table class="tablesinform">

            <tr>
                <td align="right">Category:</td>
                <td align="left">
                    <select name="categoryId">

                        <c:forEach items="${categories}" var="category">

                            <option value="${category.categoryId}">
                                    ${category.name}
                            </option>

                        </c:forEach>

                    </select>
                </td>
            </tr>

            <tr>
                <td align="right">Title:</td>
                <td align="left"><input type="text" size="30" name="title" id="title" value="${book.title}"></td>
            </tr>

            <tr>
                <td align="right">Author:</td>
                <td align="left"><input type="text" size="30" name="author" id="author" value="${book.author}"></td>
            </tr>

            <tr>
                <td align="right">ISBN:</td>
                <td align="left"><input type="text" size="30" name="isbn" id="isbn" value="${book.isbn}"></td>
            </tr>

            <tr>
                <td align="right">Publish Date:</td>
                <td align="left">

                    <input type="text" size="30" name="publishDate" id="publishDate"
                           value="<fmt:formatDate pattern='MM/dd/yyyy' value="${book.publishDate}" />">

                </td>
            </tr>

            <tr>
                <td align="right">Book Image:</td>
                <td align="left">
                    <input type="file" size="30" name="bookImage" id="bookImage"><br>
                    <img src="data:image/jpg;base64,${book.base64Image}" id="thumbnail" alt="Preview"
                         style="width: 20%; margin-top: 10px">
                </td>
            </tr>

            <tr>
                <td align="right">Price:</td>
                <td align="left"><input type="text" size="30" name="price" id="price" value="${book.price}"></td>
            </tr>

            <tr>
                <td align="right">Description:</td>
                <td align="left">
                    <textarea rows="5" cols="50" name="description" id="description">${book.description}</textarea>
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

        <input type="hidden" name="id" value="${book.bookId}">

    </form>

</div>

<br><br><br>

<!-- indclude directive -->
<%@ include file="footer.jsp" %>

</body>
<script type="text/javascript">

    $(document).ready(function () {

        $("#publishDate").datepicker();

        $("#description").richText();

        $('#bookImage').change(function () {
            showThumbnail(this);
        })

        $("#bookForm").validate(
            {
                rules: {
                    category: "required",
                    title: "required",
                    author: "required",
                    isbn: "required",
                    publishDate: "required",
                    bookImage: "required",
                    price: "required",
                    description: "required"
                }
                ,
                messages: {
                    category: "Category is required! Please Select Category Of The Book.",
                    title: "Title is required! Please Enter Title Of The Book.",
                    author: "Author is required! Please Enter Author Of The Book.",
                    isbn: "ISBN is required! Please Enter ISBN Of The Book.",
                    publishDate: "Publish Date is required! Please Enter Publish Date Of The Book.",
                    bookImage: "Book Image is required! Please Enter Book Image Of The Book.",
                    price: "Price is required! Please Enter Price Of The Book.",
                    description: "Description is required! Please Enter Description Of The Book.",

                }
            });

        $("#buttonCancel").click(function () {
            history.go(-1)
        })
    });

    function showThumbnail(fileInput) {
        var file = fileInput.files[0];
        var reader = new FileReader();
        reader.onload = function (ev) {
            $('#thumbnail').attr('src', ev.target.result);
        };
        reader.readAsDataURL(file)
    }
</script>
</html>
