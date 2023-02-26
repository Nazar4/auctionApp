<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <form:form method="POST" modelAttribute="productForm" action="/add-product" enctype="multipart/form-data" class="form-signin">
        <h2 class="form-signin-heading">Add picture</h2>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Name"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="author">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="author" class="form-control" placeholder="Author"
                            autofocus="true"></form:input>
                <form:errors path="author"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="price">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="number" step=".01" min="0" path="price" class="form-control" placeholder="price"></form:input>
                <form:errors path="price"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="genre">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select type="text" path="genre" class="form-control" items="${generItems}"></form:select>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
    </form:form>

</div>

</body>
</html>
