<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserisci utente</title>
</head>
<body>

	<form action="UserServlet" method="post">
	    Username: <input type="text" name="username"><br>
	    Password: <input type="password" name="password"><br>
	    Nome: <input type="text" name="nome"><br>
	    Cognome: <input type="text" name="cognome"><br>
	    Email: <input type="email" name="email"><br>
	    Telefono: <input type="text" name="telefono"><br>
	    <input type="radio" name="rank" value="0" checked="checked">Utente<br>
  		<input type="radio" name="rank" value="1">Admin<br><br>
  		<input type="hidden" name="action" value="insertUser">
  		<input type="submit" value="Inserisci">
	</form>
	
	<form action="UserServlet" method="post">
		 <button type="submit" name="action" value="indietro">Indietro</button>
	</form>

</body>
</html>