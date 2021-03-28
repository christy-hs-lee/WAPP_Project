<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="/login.do" method="post">
        <div>
            <label for="email">이메일</label>
            <input type="email" placeholder="example@example.com" id="email" name="userEmail">
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" placeholder="********" id="password" name="userPassword">
        </div>
        <div>
            <button type="submit">로그인</button>
        </div>
    </form>

</body>
</html>
