<%--
  Created by IntelliJ IDEA.
  User: 18762
  Date: 2019/6/21
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
</head>
<body>
<h1>Register</h1>
<form method="post">
    First Name: <input type="text" name="firstName"/><br/>
    Last Name: <input type="text" name="lastName"/><br/>
    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>