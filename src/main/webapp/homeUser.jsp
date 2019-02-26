<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Utente</title>
</head>
<body>

<h1>Benvenuto: ${utente}</h1>

	<form action="MachineServlet" method="post">
	  <button type="submit" name="action" value="openManagementMachine">Gestione macchinari</button>
	</form>
	
	<form action="TaskServlet" method="post">
	  <button type="submit" name="action" value="openManagementTasks">Gestione tasks</button>
	</form>

	<form action="UserServlet" method="post">
	  <button type="submit" name="action" value="openUpdateUser">Aggiorna info profilo</button><br>
	  <button type="submit" name="action" value="logout">Logout</button>
	</form>

</body>
</html>