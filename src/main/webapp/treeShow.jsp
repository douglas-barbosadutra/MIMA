<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.ItemDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Test</title>
	</head>
	
	<body>
		
		<h1 class="title">Test</h1>
		<div>
		<%
		String prova = (String) session.getAttribute("prova");
		out.println(prova);
	%></div>
	</body>
</html>