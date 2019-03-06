<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.TaskDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<%String mode = session.getAttribute("showTask").toString(); %>
	
		<%@ include file="menu_user.html" %>
		
		<h1 class="title">Lista tasks del macchinario n. ${idMacchinarioScelto}</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Descrizione</th>
			        
			        <%if(!mode.equals("list")){
			        	%><th>Opzioni</th><%
			        } %>
			        
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<TaskDTO> tasks = (List<TaskDTO>)session.getAttribute("taskList");
						
						for(int i = 0; i < tasks.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(tasks.get(i).getId()); %></th>
								<th><%out.println(tasks.get(i).getDescrizione()); %></th>
								
								<%if(mode.equals("delete")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Task/deleteTask?id=<%=tasks.get(i).getId()%>">Elimina</a></th><%
								
								} else if(mode.equals("choose")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="InstructionServlet?action=chooseTask&id=<%=tasks.get(i).getId()%>">Seleziona</a></th><%
									
								}%>
								
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  		
		</div>
	
	</body>
</html>