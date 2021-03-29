<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/style.css">
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
            <div>
                <button type="submit">로그인</button>
            </div>
            <div class="kakao-login">
                <button type="button" class="kakao-login-btn" onclick="location.href='${kakaoUrl}'"></button>
            </div>
            <div>
                <button type="button" onclick="location.href='/signIn'">회원가입</button>
            </div>
        </div>
    </form>

</body>
</html>
