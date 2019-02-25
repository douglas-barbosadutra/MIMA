<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body background="sfondo.jpg">
		<%
           session.setAttribute("method", "callAction");
           session.setAttribute("servlet",null);
			session.setAttribute("role","reg");
		%>
		<div style="width:250px;position:relative;top:300px;left:730px;">
			<form action="MainDispatcherServlet" method="post">
				<fieldset>
				<legend align="center">Welcome project gomma</legend>
					<table>
						<tr>
							<td>Username</td>
							<td><input type="text" name="user"></td>
						</tr>
						<tr>
							<td>Password</td>
							<td>
								<input type="password" name="pwd">
							</td>
						<tr align="center">
							<td>
								<input type="submit" value="Login" name="bott">
							</td>
							<td>
								<input type="submit" value="Registrati" name="bott">
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</body>
</html>


