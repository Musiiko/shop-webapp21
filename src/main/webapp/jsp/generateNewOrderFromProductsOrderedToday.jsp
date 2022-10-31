<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Generate new order from products ordered today</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<c:choose>
    <c:when test="${not empty warningMessage}">
        <p class="warning">${warningMessage}</p><br>
    </c:when>
    <c:otherwise>
        <form action="generateNewOrderFromProductsOrderedToday" method="post">
            <div class="row">
                <div class="col-25"><label>Number:</label></div>
                <div class="col-75"><input type="text" name="number" placeholder="Order number"></div>
            </div>
            <div class="row">
                <input type="submit" value="Add">
            </div>
        </form>
        <c:choose>
            <c:when test="${not empty order}">
                <%@ include file="/jspf/orderInfo.jspf"%><br>
            </c:when>
            <c:when test="${not empty message}">
                <p>${message}</p>
            </c:when>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>
