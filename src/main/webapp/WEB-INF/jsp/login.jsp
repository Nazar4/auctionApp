<!DOCTYPE html>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Log in with your account</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>
        <div class="form-group ">
            <h4 style="color: red">${credentialsError.length() > 0 ? credentialsError : ""}</h4>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true" />
            <input name="password" type="password" class="form-control" placeholder="Password" />

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="/registration">Create an account</a></h4>
        </div>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>
