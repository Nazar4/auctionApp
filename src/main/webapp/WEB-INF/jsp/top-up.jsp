<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>

<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
</head>

<body style="padding: 0;">

<div class="container">
    <form method="POST" action="/top-up" class="form-signin">
        <h2 class="form-heading">Top up balance</h2>
        <div class="form-group">
            <input name="value" type="number" step=".01" class="form-control" placeholder="0"
                   autofocus="true" />

            <button class="btn btn-lg btn-primary btn-block" type="submit">Top up</button>
            <h4 style="color: red">${wrongValue}</h4>
        </div>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>

</body>
</html>