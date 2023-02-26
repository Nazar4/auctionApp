<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<div class="container">
        <c:if test="${!empty(sum.username)}">
                <h2>User with greatest sum registered for contest: ${sum.username}</h2>
        </c:if>
        <c:if test="${empty(sum.username)}">
                <h4 style="color: red">There are no users who submit any statements</h4>
        </c:if>
</div>

