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
		
		<%String mode = session.getAttribute("showTask").toString(); %>
	
		<ul>
		  <li>
	  		<div class="dropdown">
				<button class="dropbtn">Macchinari</button>
				
				<div class="dropdown-content"> 
					<a href="MachineServlet?action=openInsertMachine">Inserisci</a>
					<a href="MachineServlet?action=deleteMachineManagement">Elimina</a>
					<a href="MachineServlet?action=showMachine">Lista</a>
					<a href="SchedulingServlet?action=showScheduling">Scheduling</a>
					<a href="MachineServlet?action=chooseMachineManagement">Seleziona</a>
				</div>
			</div>
		  </li>
		  
		  <li>
	  		<div class="dropdown">
				<button class="dropbtn">Scheduling</button>
				
				<div class="dropdown-content"> 
					<a href="SchedulingServlet?action=insertSchedulingOpen">Inserisci</a>
					<a href="SchedulingServlet?action=deleteSchedulingManagement">Elimina</a>
					<a href="SchedulingServlet?action=modifySchedulingOpen">Modifica</a>
					<a href="SchedulingServlet?action=showScheduling">Lista</a>
					<a href="SchedulingServlet?action=managementScheduling">Gestione</a>
				</div>
			</div>
		  </li>
		  
		  <li>
	  		<div class="dropdown">
				<button class="dropbtn">Task</button>
				
				<div class="dropdown-content"> 
					<a href="TaskServlet?action=openInsertTask">Inserisci</a>
					<a href="TaskServlet?action=deleteTaskManagement">Elimina</a>
					<a href="TaskServlet?action=showTask">Lista</a>
					<a href="InstructionServlet?action=showTime">Tempi</a>
					<a href="TaskServlet?action=chooseTask">Seleziona</a>
				</div>
			</div>
		  </li>
		  
		  <li>
	  		<div class="dropdown">
				<button class="dropbtn">Istruzioni</button>
				
				<div class="dropdown-content"> 
					<a href="InstructionServlet?action=insertInstructionOpen">Inserisci</a>
					<a href="InstructionServlet?action=deleteInstructionManagement">Elimina</a>
					<a href="InstructionServlet?action=showInstruction">Lista</a>
				</div>
			</div>
		  </li>
		  
		  <li>
	  		<div class="dropdown">
				<a href="UserServlet?action=openUpdateUser" class="dropbtn">Profilo</a>				
			</div>
		  </li>
		  
		  <li>
	  		<div class="dropdown">
				<a href="UserServlet?action=logout" class="dropbtn">Logout</a>				
			</div>
		  </li>

		</ul>
		
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
								<th><%out.println(tasks.get(i).getID()); %></th>
								<th><%out.println(tasks.get(i).getDescrizione()); %></th>
								
								<%if(mode.equals("delete")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="TaskServlet?action=deleteTask&id=<%=tasks.get(i).getID()%>">Elimina</a></th><%
								
								} else if(mode.equals("choose")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="InstructionServlet?action=chooseTask&id=<%=tasks.get(i).getID()%>">Seleziona</a></th><%
									
								}%>
								
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  		
		</div>
	
	</body>
</html>