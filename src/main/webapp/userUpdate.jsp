<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User update</title>
</head>
<body>
	
	<h1>Scegli cosa aggiornare</h1>
	
	<form action="UserServlet" method="post">
	    Email: <input type="text" name="email"><br>
	    Telefono: <input type="number" name="telefono"><br>
	    Username: <input type="text" name="username"><br>
	    Password: <input type="password" name="password"><br>
  		
  		<input type="hidden" name="action" value="updateUser">
  		<input type="submit" value="Aggiorna">
	</form>

</body>
</html>