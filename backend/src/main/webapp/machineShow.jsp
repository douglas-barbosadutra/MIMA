<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.MachineDTO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista macchinari</title>
	</head>
	
	<body>
		
		<% String mode = session.getAttribute("showMachine").toString(); %>
		
		<%@ include file="menu_user.html" %>
	
		<h1 class="title">Lista macchinari</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Modello</th>
			        
			        <%if(!mode.equals("list")){
			        	
			        	%><th>Opzioni</th><%
			        	
			        } %>
			         
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<MachineDTO> machines = (List<MachineDTO>)session.getAttribute("machines_list");
						
						for(int i = 0; i < machines.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(machines.get(i).getId()); %></th>
								<th><%out.println(machines.get(i).getNome()); %></th>
								<th><%out.println(machines.get(i).getModello()); %></th>
								
								<%if(mode.equals("delete")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Machine/deleteMachine?id=<%=machines.get(i).getId()%>">Elimina</a></th><%
								
								} else if(mode.equals("choose")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Task/chooseMachine?id=<%=machines.get(i).getId()%>">Seleziona</a></th><%
									
								}%>
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  	
		</div>
		
		
	
	</body>
</html>