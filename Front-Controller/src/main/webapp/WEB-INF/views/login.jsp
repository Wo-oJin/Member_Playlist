<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Front-Controller</title>
  </head>
  <body>
    <div class="register_container">
      <h1 class="title">로그인 해주세요!</h1>
      <!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
      <form class="register_content" action="check" method="post">
        <div class="username_content">
          <label class="username_label" for="username">아이디</label>
          username:
          <input
            id="username"
            placeholder="아이디"
            type="text"
            name="username"
          />
        </div>
        <div class="password_contetnt">
          <label class="password_label" placeholder="비밀번호" for="password"
            >비밀번호</label
          >
          password: <input id="password" type="password" name="password" />
        </div>
        <button class="register_submit_btn" type="submit">로그인</button>
      </form>
    </div>
  </body>
</html>
