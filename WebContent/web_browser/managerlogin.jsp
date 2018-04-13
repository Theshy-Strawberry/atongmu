<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>管理员登录</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="web_browser/css/style.css">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
	<script>
		function check() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			if (username == "" || username == "用户名") {
				alert("请输入用户名");
				loginForm.username.focus();
				return false;
			}
			if (password == "" || password == "密码") {
				alert("请输入密码");
				loginForm.password.focus();
				return false;
			}
			return true;
		}
	</script>
	<section class="container">
		<div class="login">
			<h1>欢迎进入阿同木管理系统</h1>
			<form method="post" action="ManagerLoginServlet"
				name="loginForm" onsubmit="return check();">
				<p>
					<%
 					Object loginResultObj = request.getAttribute("loginFail");
					String loginResult = "0";//默认值为成功
 					if (loginResultObj != null) {
 						loginResult = (String)loginResultObj;
				 		if ("1".equals(loginResult)) {
				 	%>
 					<label style="color:#003366;">用户名或密码错误</label>
	 				<%
						}else if("2".equals(loginResult)){
						 	%>
		 					<label style="color:#003366;">您还没有登录，请您登陆后再进行操作</label>
			 				<%
						}
 					}
	 				%>
	 			</p>
				<p>
					<%
					Object usernameObj = request.getAttribute("username");
					String username = "";
					if(usernameObj != null){
						username = (String)usernameObj;
					%>
					<input type="text" name="username" id="username" value="<%=username %>"	placeholder="用户名">
					<%
					}else{
					%>
					<input type="text" name="username" id="username" value=""	placeholder="用户名">
					<%
					}
					%>
				</p>
				<p>
					<input type="password" name="password" id="password" value="" placeholder="密码">
				</p>

				<p class="submit">



					<input type="submit" name="commit" value="登陆">
				</p>
			</form>
		</div>
	</section>
</body>
</html>
