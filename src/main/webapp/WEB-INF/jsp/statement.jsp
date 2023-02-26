<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<jsp:include page="header.jsp"/>

<head>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
</head>
<body style="padding: 0">
<div class="container">

    <form:form method="POST" modelAttribute="statementForm" action="/contest-register" enctype="multipart/form-data" class="form-signin">
        <h2 class="form-signin-heading">Create statement</h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input value="${username}" type="text" disabled="true" path="username" class="form-control"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="userMoneyAccountName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label>Money Account id</label>
                <form:input type="text" disabled="true" path="userMoneyAccountName" class="form-control" value="${credentials}"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="sum">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label>Enter sum for the picture</label>
                <form:input type="number" step=".01" min="0" path="sum" class="form-control" placeholder="0"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="productName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label>Select picture</label>
                <form:select type="text" path="productName" class="form-control" items="${productNames}"></form:select>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

        <h4 style="color: red">${!empty(unable) ? unable : ""}</h4>
    </form:form>

</div>

</body>
</html>