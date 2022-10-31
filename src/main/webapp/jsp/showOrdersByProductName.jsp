<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>show orders by product name</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<form action="showOrdersByProductName" method="post">
    <div class="row">
        <div class="col-25"><label>Product name:</label></div>
        <div class="col-75"><input type="text" name="productName" placeholder="Product name"></div>
    </div>
    <br>
    <div class="row">
        <input type="submit" value="Search">
    </div>
</form>
<br>
<%@ include file="/jspf/showSearchResults.jspf"%>
</body>
</html>
