<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert machine</title>
</head>
<body>

	<h1>Inserisci macchinario</h1>
	
	<form action="MachineServlet" method="post">
	    Nome: <input type="text" name="nome"><br>
	    Modello: <input type="text" name="modello"><br>
	    
  		<input type="hidden" name="action" value="insertMachine">
  		<input type="submit" value="Inserisci">
	</form>

</body>
</html>