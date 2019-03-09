<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.InstructionDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista istruzioni</title>
	</head>
	
	<body>
		
		<% String mode = session.getAttribute("showInstruction").toString(); %>
	
		<%@ include file="menu_user.html" %>
	
		<h1 class="title">Lista istruzioni del task n. ${idTaskScelto}</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Durata</th>
			        <th>Codice</th>
			        
			        <%if(mode.equals("delete")){
			        	
			        	%><th>Opzioni</th><% 
			        	
			        } %>
			        
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<InstructionDTO> instructions = (List<InstructionDTO>)session.getAttribute("listaIstruzioni");
						
						for(int i = 0; i < instructions.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(instructions.get(i).getId()); %></th>
								<th><%out.println(instructions.get(i).getNomeIstruzione()); %></th>
								<th><%out.println(instructions.get(i).getDurata()); %></th>
								<th><%out.println(instructions.get(i).getCodice()); %></th>
								
								<%if(mode.equals("delete")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Instruction/deleteInstruction?id=<%=instructions.get(i).getId()%>">Elimina</a></th><%
								
								} %>
								
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
					
		</div>
	
	</body>
</html>