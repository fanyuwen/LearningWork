<%--
  Created by IntelliJ IDEA.
  User: bdyuwenf
  Date: 2019/6/20
  Time: 16:48
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
<c:forEach items="${spittleList}" var="spittle">
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}"/></span>
            <span class="spittleLocation">
                   (<c:out value="${spittle.latitude}"/>,
                   <c:out value="${spittle.longitude}"/>)
               </span>
        </div>
    </li>
</c:forEach>
</body>
</html>
