<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione macchinari</title>
</head>
<body>

	<h1>Gestione macchinari</h1>
	
	<form action="MachineServlet" method="post">
	  <button type="submit" name="action" value="openInsertMachine">Inserisci macchinario</button>
	  <button type="submit" name="action" value="showMachine">Gestione macchinario</button>
	  <button type="submit" name="action" value="indietro">Indietro</button>
	</form>

</body>
</html>