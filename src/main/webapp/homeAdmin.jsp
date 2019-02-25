<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Amministratore</title>
</head>
<body>

<h1>Benvenuto: ${utente}</h1>

	<form action="DispatcherServlet" method="post">
	  <input type="radio" name="page" value="userInsert"> Inserisci utente<br>
	  <input type="radio" name="page" value="index"> Elimina utente<br>
	  <input type="radio" name="page" value="index"> Visualizza utenti<br>  
	  <input type="radio" name="page" value="index"> Logout<br>  
	  <input type="submit" value="Submit">
	</form>
	
</body>
</html>