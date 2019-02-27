<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.TaskDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<h1 class="title">Lista tasks</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Descrizione</th>
			        <th>Opzioni</th>
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<TaskDTO> tasks = (List<TaskDTO>)session.getAttribute("taskList");
						
						for(int i = 0; i < tasks.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(tasks.get(i).getID()); %></th>
								<th><%out.println(tasks.get(i).getDescrizione()); %></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="TaskServlet?action=deleteTask&id=<%=tasks.get(i).getID()%>">Elimina</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="InstructionServlet?action=insertInstructionOpen&idTask=<%=tasks.get(i).getID()%>">Inserisci istruzione</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="InstructionServlet?action=showInstruction&idTask=<%=tasks.get(i).getID()%>">Visualizza istruzioni</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="LavorazioniServlet?action=showTime&idTask=<%=tasks.get(i).getID()%>">Visualizza tempi</a></th>
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  
		  	<form action="TaskServlet" method="post">
				<button style="margin-left:40%;" class="btn btn-primary" type="submit" name="action" value="indietro">Indietro</button>
			</form>
					
		</div>
	
	</body>
</html>