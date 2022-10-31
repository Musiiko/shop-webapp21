<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>add product to order</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<form action="addProductToOrder" method="post">
    <label>Order Id
        <input type="text" name="orderId" placeholder="Order Id">
    </label><br>
    <label>Product Id
        <input type="text" name="productId" placeholder="Product Id">
    </label><br>
    <label>Count
        <input type="text" name="count" placeholder="Count">
    </label><br>
    <input type="submit" value="Add">
</form>
<c:if test="${not empty message}">
<c:out value="${message}"/>
</c:if>
</body>
</html>
