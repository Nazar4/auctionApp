<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<jsp:include page="header.jsp"/>

<div class="container">

    <div style="max-width: 350px;">
    <form:form method="GET" modelAttribute="productDTOForm" action="/search-product" enctype="multipart/form-data">
        <h2 >Search picture</h2>
        <spring:bind path="name">
                <label>Name</label>
                <form:input type="text" path="name"></form:input>
        </spring:bind>
        <br>
        <spring:bind path="author">
                <label>Author</label>
                <form:input type="text" path="author"></form:input>
        </spring:bind>
        <br>
        <spring:bind path="genre">
                <label>Genre</label>
                <form:select type="text" path="genre" class="form-control" items="${generItems}"></form:select>
        </spring:bind>
        <br>
        <spring:bind path="bottomRange">
                <label>Bottom price range</label>
                <form:input type="number" min="0" step=".01" path="bottomRange"></form:input>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Search</button>

    </form:form>
    </div>
</div>

<c:if test="${!empty(products)}">
    <section id="products" class="section">
        <c:forEach var="product" items="${products}">
            <div class="container">

                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${product.author}</h6>
                        <h6 class="card-subtitle mb-2 text-muted">${product.genre}</h6>
                        <p  class="card-text">${product.price}</p>
                    </div>
                </div>

            </div>
        </c:forEach>
    </section>
</c:if>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
