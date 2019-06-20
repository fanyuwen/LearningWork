<%--
  Created by IntelliJ IDEA.
  User: bdyuwenf
  Date: 2019/6/20
  Time: 18:25
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
<div class="spittleView">
    <div class="spittleMessage">
        <c:out value="${spittle.message}"/>
    </div>
    <div>
        <span class="spittleTime"><c:out value="${spittle.time}"/></span>
    </div>
</div>
</body>
</html>
