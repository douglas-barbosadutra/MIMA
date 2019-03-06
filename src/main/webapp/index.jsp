<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Login</title>
	</head>
	
	<body background="/images/Sfondo.png">
		
		<div style="width:25%; margin-top:15%; margin-left:35%;">
		
			<h1 class="title">MIMA</h1>
			
			<form action="LoginServlet" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Username</label>
					<input type="text" class="form-control" name="username">
					
					<label class="col-form-label">Password</label>
					<input type="password" class="form-control" name="password">
				</div>
				
				<div class="form-group">
					<button style="margin-top:2%" class="btn btn-primary" type="submit" value="Login">Login</button>
				</div>
			
			</form>
			
		</div>		
		
	</body>
</html>