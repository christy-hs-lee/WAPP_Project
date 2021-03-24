<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/signIn.do" method="post">
    <div>
        <label for="name">이름</label>
        <input type="text" placeholder="홍길동" id="name" name="username">
    </div>
    <div>
        <label for="email">이메일</label>
        <input type="email" placeholder="example@example.com" id="email" name="useremail">
    </div>
    <div>
        <label for="password">비밀번호</label>
        <input type="password" placeholder="********" id="password" name="userpassword">
    </div>
    <div>
        <label for="passwordCheck">비밀번호확인</label>
        <input type="password" placeholder="********" id="passwordCheck">
    </div>
    <div>
        <button type="submit">회원가입</button>
    </div>
</form>

</body>
</html>
