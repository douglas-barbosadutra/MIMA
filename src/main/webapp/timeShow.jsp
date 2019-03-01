<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.TimeDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista tempi</title>
	</head>
	
	<body>
	
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
	
		<h1 class="title">Lista tempi del task ${idTaskScelto}</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table">
			    <thead>
			      
			      <tr>
			        <th>Nome Istruzione</th>
			        <th>Tempi effettivi</th>
			        <th>Tempi previsti</th>
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<TimeDTO> gestioneTempiDTO = (List<TimeDTO>)session.getAttribute("listaTempi");
						
						for(int i = 0; i < gestioneTempiDTO.size(); i++)
						{
							%>
								<tr bgcolor="<%out.println(gestioneTempiDTO.get(i).getRisultato().toString());%>" >
								  	<td><%out.println(gestioneTempiDTO.get(i).getNomeIstruzione().toString());%></td>
							    	<td><%out.println(gestioneTempiDTO.get(i).getDurataEffettiva());%></td>
							    	<td><%out.println(gestioneTempiDTO.get(i).getDurataPrevista());%></td>
								</tr>
							<%
						}
					%>
			      
			    </tbody>
			    
		  </table>
					
		</div>
	
	</body>
</html>