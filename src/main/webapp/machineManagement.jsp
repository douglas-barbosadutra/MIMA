<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Gestione macchinari</title>
	</head>
	
	<body>
	
		<h1 class="title">Gestione macchinari</h1>
		
		<form style="margin-left:30%; margin-top: 15%;" class="form-inline" action="MachineServlet" method="post">
		  <button class="btn btn-primary" type="submit" name="action" value="openInsertMachine">Inserisci macchinario</button>
		  <button class="btn btn-primary" type="submit" name="action" value="showMachine">Lista macchinari</button>
		  <button class="btn btn-primary" type="submit" name="action" value="indietro">Indietro</button>
		</form>
	
	</body>
</html>