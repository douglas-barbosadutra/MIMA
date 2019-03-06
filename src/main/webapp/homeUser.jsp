<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Utente</title>
	</head>
	<body>
	
		<h1 class="title">Benvenuto ${utente.getName()}</h1>
		
		<%@ include file="menu_user.html" %>
        
	</body>
</html>