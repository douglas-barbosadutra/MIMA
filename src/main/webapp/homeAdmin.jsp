<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Amministratore</title>
</head>
<body>

<h1>Benvenuto ${utente.getName()}</h1>

	<form action="UserServlet" method="post">
	  <button type="submit" name="action" value="openInsertUser">Inserisci utente</button>
	  <button type="submit" name="action" value="showUser">Gestione utenti</button>
	  <button type="submit" name="action" value="logout">Logout</button>
	</form>
	
</body>
</html>