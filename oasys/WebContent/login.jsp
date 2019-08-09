<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理系统</title>
<link rel="stylesheet" type="text/css" href="css/Login_style.css" />
<!-- <script type="text/javascript" src="Javascript/Login_javascript.js"></script> -->
</head>
<body>
<div class="login_frame">
</div>
<div class="LoginWindow">
  <div>
    <form method="post" action="" onsubmit="return user_input()" class="login">
    <p>
      <label for="login">帐号:</label>
      <input type="text" name="id" id="id" value="">
    </p>
    <p>
      <label for="password">密码:</label>
      <input type="password" name="password" id="password" value="">
    </p>
    <p class="login-submit">
      <button type="button" class="login-button">Login</button>
    </p>
    </form>
  </div>
</div>


<br /><br /><br />
<h1 align="center">学生信息管理平台</h1>
<div style=" width:600px;margin:0 auto; text-align:center; font-size:12px;">
</div>
</body>
</html>
