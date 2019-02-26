<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserisci Istruzione</title>
</head>
<body>
	<form action="InstructionServlet" method="post">
	    Nome Istruzione: <input type="text" name="nomeIstruzione"><br>
	    durata: <input type="text" name="durata"><br>
  		<input type="hidden" name="action" value="insertInstruction">
  		<input type="submit" value="Inserisci">
	</form>
	
	<form action="UserServlet" method="post">
		 <button type="submit" name="action" value="indietro">Indietro</button>
	</form>
</body>
</html>