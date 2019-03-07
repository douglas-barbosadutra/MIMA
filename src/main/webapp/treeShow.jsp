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
		
		<% int levels = Integer.parseInt(session.getAttribute("levels").toString()); %>
		
		<%for(int i=1; i<=levels; i++){
			
			List<ItemDTO> items = (List<ItemDTO>)session.getAttribute("level"+i);
			int size = items.size();
			
			%><div style="text-align:center; width:100%; margin-bottom:1%;">
				
				<%for(int k=0; k<size; k++){
					%><span> <% out.println(items.get(k).getName()); %> <a style="text-decoration: none;" href="/Item/removeNode?id=<% out.println(items.get(k).getId()); %>">-</a> <a style="text-decoration: none;" href="/Item/openAddNode?id=<% out.println(items.get(k).getId()); %>">+</a> </span><%
				} %>
				
			</div><%
			
		} %>
	
	</body>
</html>