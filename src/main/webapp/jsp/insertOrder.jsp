<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>insert order</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<form action="insertOrder" method="post">
    <label>Number
        <input type="text" name="number" placeholder="Order number">
    </label>
    <input type="submit" value="Add">
</form>
<c:if test="${not empty message}">
    <c:out value="${message}"/>
</c:if>
<c:if test="${not empty order}">
    The order has been added to the database:
    <table>
        <tr>
            <td><c:out value="Number: ${order.number}"/></td>
            <td><c:out value="Date: ${order.date}"/></td>
        </tr>
    </table>
</c:if>
</body>
</html>
