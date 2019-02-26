<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserimento macchinario</title>
</head>
<body>

	<h1>Inserimento macchinario</h1>
	
	<form action="MachineServlet" method="post">
	    Nome: <input type="text" name="nome"><br>
	    Modello: <input type="text" name="modello"><br>
	    
  		<input type="hidden" name="action" value="insertMachine">
  		<input type="submit" value="Inserisci">
	</form>
	
	<form action="MachineServlet" method="post">
  		<button type="submit" name="action" value="openManagementMachine">Indietro</button>
	</form>
	

</body>
</html>