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
		Nome: <input type="text" name="nome" value="${utente.getName()}"><br>
	    Cognome: <input type="text" name="cognome" value="${utente.getSurname()}"><br>
	    Email: <input type="email" name="email" value="${utente.getEmail()}"><br>
	    Telefono: <input type="tel" name="telefono" value="${utente.getPhone()}"><br>
	    Username: <input type="text" name="username" value="${utente.getUsername()}"><br>
	    Password: <input type="password" name="password" value="${utente.getPassword()}"><br>
  		
  		<input type="hidden" name="action" value="updateUser">
  		<input type="submit" value="Aggiorna">
	</form>
	
	<form action="UserServlet" method="post">
		 <button type="submit" name="action" value="indietroUser">Indietro</button>
	</form>

</body>
</html>