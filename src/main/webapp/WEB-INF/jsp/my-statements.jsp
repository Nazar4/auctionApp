<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<jsp:include page="header.jsp"/>

<head>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
</head>
<body style="padding: 0">
<div class="container">
    
    <c:if test="${!empty(statements)}">
        <section id="products" class="section">
            <c:forEach var="statement" items="${statements}">

                <div class="container">

                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${statement.owner.username}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${statement.product.name}</h6>
                            <h6 class="card-subtitle mb-2 text-muted">${statement.sum}</h6>
                            <form method="POST" action="/delete-statement" enctype="multipart/form-data">
                                <input hidden value="${statement.id}" name="id"
                                    style="display: none" class="card-subtitle mb-2 text-muted">
                                <button type="Submit" class="btn btn-primary">Cancel</button>
                            </form>
                        </div>
                    </div>

                </div>
            </c:forEach>
        </section>
    </c:if>

    <c:if test="${empty(statements)}">
        <h4 style="color: red">You have not created any statements yet</h4>
    </c:if>

</div>

</body>
</html>