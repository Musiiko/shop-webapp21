<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete orders with given count and product</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<c:choose>
    <c:when test="${not empty warningMessage}">
        <p class="warning">${warningMessage}</p><br>
    </c:when>
    <c:otherwise>
        <form action="deleteOrdersWithGivenCountAndProduct" method="post">
            <div class="row">
                <div class="col-25"><label>Count of products</label></div>
                <div class="col-75"><input type="text" name="count" placeholder="Count"></div>
            </div>
            <div class="row">
                <div class="col-25"><label>Product name</label></div>
                <div class="col-75"><input type="text" name="productName" placeholder="Product name"></div>
            </div>
            <div class="row">
                <input type="submit" value="Add">
            </div>
        </form>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </c:otherwise>
</c:choose>

</body>
</html>
