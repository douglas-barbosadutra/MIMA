<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.ItemDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Albero</title>
	</head>
	
	<body>
		
		<h1 class="title">${name}</h1>
		<%@ include file="menu_user.html" %>
		<div class = "tree">
		<%
		String action = (String) session.getAttribute("action");
		if(action == "alberoVuoto"){
			%>
			<form action="/Item/openAddNode" method="get">
			<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" name="action" value="openAddNode" >Inserisci Radice</button>	
			</form>
			<%
		}
		else {
			String prova = (String) session.getAttribute("prova");
			out.println(prova);
		}
		
	%></div>
	</body>
</html>