<%--
  Created by IntelliJ IDEA.
  User: nhoclahola
  Date: 9/16/2024
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Administrator's Home Page</title>
    <style>
        .homepage {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <div class="homepage">
            <h1>You doesn't have administrator role or haven't login, please login or register</h1>
        </div>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li><a href="${pageContext.request.contextPath }/login">Login</a>
                    | <a href="${pageContext.request.contextPath }/register">Register</a></li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:when>
    <c:when test="${sessionScope.account.roleId != 1}">
        <div class="homepage">
            <h1>You doesn't have administrator role</h1>
        </div>
    </c:when>
    <c:otherwise>
        <div class="homepage">
            <h1>Administrator's Home Page</h1>
        </div>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li><a href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a> |
                    <a href="${pageContext.request.contextPath }/logout">Logout</a></li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:otherwise></c:choose>

</body>
</html>
