<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="/jspf/header.jspf" %>
<c:if test="${not empty warningMessage}">
    <div class="warning">${warningMessage}</div><br>
</c:if>
<p>Please enter login and password!</p>
<form method="post" action="login">
    <div class="row">
        <div class="col-25"><label>Login:</label></div>
        <div class="col-75"><input type="text" name="login" placeholder="Login"></div>
    </div>
    <br>
    <div class="row">
        <div class="col-25"><label>Password:</label></div>
        <div class="col-75"><input type="text" name="password" placeholder="Password"></div>
    </div>
    <br>
    <div class="row">
        <input value="Login" type="submit">
    </div>
</form>
</body>
</html>
