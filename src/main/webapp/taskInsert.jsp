<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserimento Task</title>
</head>
<body>

	<form action="TaskServlet" method="post">
	    Descrizione: <input type="text" name="descrizione"><br>
  		<input type="hidden" name="action" value="insertTask">
  		<input type="submit" value="Inserisci">
	</form>
	
	<form action="TaskServlet" method="post">
		 <button type="submit" name="action" value="indietro">Indietro</button>
	</form>
	
</body>
</html>