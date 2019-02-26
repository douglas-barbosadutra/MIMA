<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elimina istruzione</title>
</head>
<body>
	<form action="UserServlet" method="post">
	    Nome istruzione: <input type="text" name="nomeIstruzione"><br>
	    Id Task: <input type="text" name="idTask"><br>
	    <input type="hidden" name="action" value="deleteInstruction"><br><br>
		<input type="submit" value="Elimina">
	</form>
	
	<form action="UserServlet" method="post">
		 <button type="submit" name="action" value="indietro">Indietro</button>
	</form>
</body>
</html>