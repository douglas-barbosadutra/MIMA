<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.MachineDTO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista macchinari</title>
	</head>
	
	<body>
	
		<h1 class="title">Lista macchinari</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Modello</th>
			        <th>Opzioni</th>
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
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="MachineServlet?action=deleteMachine&id=<%=machines.get(i).getId()%>">Elimina</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="TaskServlet?action=openInsertTask&id_macchinario=<%=machines.get(i).getId()%>">Inserisci task</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="TaskServlet?action=showTask&id_macchinario=<%=machines.get(i).getId()%>">Visualizza task</a></th>
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  
		  	<form action="MachineServlet" method="post">
				<button style="margin-left:40%;" class="btn btn-primary" type="submit" name="action" value="openManagementMachine">Indietro</button>
			</form>
					
		</div>
		
		
	
	</body>
</html>