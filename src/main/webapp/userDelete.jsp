<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Elimina utente</title>
</head>
<body>

	<form action="UserServlet" method="post">
	    ID Utente: <input type="text" name="id"><br>
	    <input type="hidden" name="action" value="deleteUser"><br><br>
		<input type="submit" value="Elimina">
	</form>
	
	<form action="UserServlet" method="post">
		 <button type="submit" name="action" value="indietro">Indietro</button>
	</form>
	
</body>
</html>