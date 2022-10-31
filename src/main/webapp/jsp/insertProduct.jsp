<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>insert product</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<form action="insertProduct" method="post">

    <label>Price:
        <input type="text" name="price" placeholder="Product price">
    </label><br>
    <label>Name:
        <input type="text" name="name" placeholder="Product name">
    </label><br>
    <label>Description:
        <input type="text" name="description" placeholder="Product description">
    </label><br>
    <input type="submit" value="Add">
</form>
<c:if test="${not empty message}">
    <c:out value="${message}"/>
</c:if>
<c:if test="${not empty phone}">
    The product has been added to the database:
    <table>
        <tr>
            <td><c:out value="Price: ${phone.price}"/></td>
            <td><c:out value="Name: ${phone.name}"/></td>
            <td><c:out value="Description: ${phone.description}"/></td>
        </tr>
    </table>
</c:if>
</body>
</html>
