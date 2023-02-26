<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<div class="container">
    <c:if test="${sessionScope.username != null}">

        <h2>Welcome ${sessionScope.username}<img style="width: 100px; height: 100px" src="images/avocado.jpg"></h2>
            <h2>Your balance: ${sessionScope.balance.doubleValue() > 0.0 ? sessionScope.balance.doubleValue() : 0.0}
        </h2>
    </c:if>
</div>
