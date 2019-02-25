<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		<div>username: <input type="text" name="username"></div>
		<div>password: <input type="password" name="password"></div>
		<button type="submit" value="Login">Login</button>
	</form>
</body>
</html>