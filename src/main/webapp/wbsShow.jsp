<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.WBSDTO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista wbs</title>
	</head>
	
	<body>
		
		<%@ include file="menu_user.html" %>
		
		<h1 class="title">Lista wbs</h1>
		
		<% String mode = session.getAttribute("showWbs").toString(); %>
		
		<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        
			        <%if(!mode.equals("list")){
			        	
			        	%><th>Opzioni</th><%
			        	
			        } %>
			         
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<WBSDTO> wbs = (List<WBSDTO>)session.getAttribute("listWbs");
						
				    	for(int i = 0; i < wbs.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(wbs.get(i).getId()); %></th>
								<th><%out.println(wbs.get(i).getName()); %></th>
								
								<th>
								
								
									<a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Item/showNodes?id=<%=wbs.get(i).getId()%>&name=<%=wbs.get(i).getName()%>">Inserisci Nodi</a>
								
								<%if(mode.equals("delete")){
									
									%><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/WBS/deleteWbs/?id=<%=wbs.get(i).getId()%>">Elimina</a><%
								
								} %>
							</th>	
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
	
	</body>
</html>