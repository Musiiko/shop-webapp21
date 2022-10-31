<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>show orders by sum and count</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<form action="showOrdersBySumAndCount" method="post">
    <div class="row">
        <div class="col-25"><label>Sum:</label></div>
        <div class="col-75"><input type="text" name="sum" placeholder="Sum"></div>
    </div>
    <br>
    <div class="row">
        <div class="col-25"><label>Count of products:</label></div>
        <div class="col-75"><input type="text" name="count" placeholder="Count"></div>
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
