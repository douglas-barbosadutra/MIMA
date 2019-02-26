<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Istruzioni</title>
</head>
<body>
	<h1>Gestione istruzioni</h1>
	
	<form action="UserServlet" method="post">
	  <button type="submit" name="action" value="openInsertInstruction">Inserisci una nuova istruzione</button>
	  <button type="submit" name="action" value="openDeleteInstruction">Elimina un istruzione</button>
	  <button type="submit" name="action" value="openModifyInstruction">Modifica un istruzione</button>
	  <button type="submit" name="action" value="showInstruction">Visualizza istruzioni di un determinato task</button>
	  <button type="submit" name="action" value="goTask">Torna indietro</button>
	</form>
</body>
</html>