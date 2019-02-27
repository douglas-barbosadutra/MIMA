<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Amministratore</title>
	</head>
	<body>
	
	<h1 class="title">Benvenuto ${utente.getName()}</h1>
	
		<form style="margin-left:30%; margin-top: 15%;" class="form-inline" action="UserServlet" method="post">
		  <button class="btn btn-primary" type="submit" name="action" value="openInsertUser">Inserisci utente</button>
		  <button class="btn btn-primary" type="submit" name="action" value="showUser">Gestione utenti</button>
		  <button class="btn btn-primary" type="submit" name="action" value="logout">Logout</button>
		</form>
		
	</body>
</html>