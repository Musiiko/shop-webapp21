<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show order by number</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<form action="showOrderByNumber" method="post">
    <div class="row">
        <div class="col-25"><label>Order number:</label></div>
        <div class="col-75"><input type="text" name="number" placeholder="Order number"></div>
    </div>
    <br>
    <div class="row">
        <input type="submit" value="Search">
    </div>
</form>
<br>
<c:if test="${not empty message}">
    <c:out value="${message}"/>
</c:if>

<c:if test="${not empty order}">
    <%@ include file="/jspf/orderInfo.jspf"%>
</c:if>
</body>
</html>
