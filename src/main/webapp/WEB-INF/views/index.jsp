<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Title</title>
    </head>

    <body>
        <h1>WAPP에 오신 여러분들 환영합니다.</h1>
        <a href="/admin/home">관리자페이지</a>
        <c:choose>
            <c:when test="${empty userName}">
            <h2 class="login-status">로그인 상태 : X</h2>
            </c:when>
            <c:otherwise>
            <h2 class="login-status">로그인 상태 : O ${username}</h2>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${empty userName}">
                <a href="/login">로그인</a>
                <a href="/signIn">회원가입</a>
            </c:when>
            <c:otherwise>
                <a href="/logout">로그아웃</a>
            </c:otherwise>
        </c:choose>

    </body>
</html>