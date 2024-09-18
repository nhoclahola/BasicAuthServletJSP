<%--
  Created by IntelliJ IDEA.
  User: nhoclahola
  Date: 9/19/2024
  Time: 3:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>My Account</title>
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
    <%-- All users who have logged in can access this page  --%>
    <c:when test="${sessionScope.account == null}">
        <div class="homepage">
            <h1>You haven't login, please login or register</h1>
        </div>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="${pageContext.request.contextPath }/login">Login</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/register">Register</a>
                </li>
                <li>
                    <i class="search fa fa-search search-button"></i>
                </li>
            </ul>
        </div>
    </c:when>
    <c:otherwise>
        <div class="homepage">
            <h1>Hello ${sessionScope.account.fullName}</h1>
        </div>
        <ul>
            <li>
                <h4>Username: ${sessionScope.account.username}</h4>
            </li>
            <li>
                <h4>Phone numer: ${sessionScope.account.phone}</h4>
            </li>
            <li>
                <h4>Role:
                    <c:choose>
                        <c:when test="${sessionScope.account.roleId == 1}">
                            Admin
                        </c:when>
                        <c:when test="${sessionScope.account.roleId == 2}">
                            Manager
                        </c:when>
                        <c:when test="${sessionScope.account.roleId == 3}">
                            User
                        </c:when>
                    </c:choose>
                </h4>
            </li>
        </ul>
    </c:otherwise></c:choose>
</body>
</html>
