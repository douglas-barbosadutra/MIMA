<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.TaskDTO" %>
<%@ page import="com.virtualpairprogrammers.dto.TaskSchedulatiDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista Scheduling</title>
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
		
		<h1 class="title">Gestione scheduling n. ${idSchedulazioneScelta}</h1>
	
		<div style="padding-left:10%; padding-right:10%">
			
			<h2 class="title" style="text-align:left">Task Disponibili</h2>
			      
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
						List<TaskDTO> taskDisponibili = (List<TaskDTO>)session.getAttribute("listaTaskDisponibili");
						
						for(int i = 0; i < taskDisponibili.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(taskDisponibili.get(i).getID()); %></th>
								<th><%out.println(taskDisponibili.get(i).getDescrizione()); %></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=insertOperazione&id=<%=taskDisponibili.get(i).getID()%>">Aggiungi</a></th>
							</tr><%
						}
					%>
			      
			    </tbody> 
		  	</table>
		  	
		  	<h2 class="title" style="text-align:left;">Task Schedulati</h2>
		  	
		  	<table class="table table-striped">
				      
				    <thead>
				      <tr>
				        <th>ID</th>
				        <th>Nome</th>
				        <th>Ordine</th>
				        <th>Cambia Ordine</th>
				        <th>Opzioni</th>
				      </tr>
				    </thead>
				    
				    <tbody>
				    
				    	<%
							List<TaskSchedulatiDTO> taskSchedulati = (List<TaskSchedulatiDTO>)session.getAttribute("listaTaskScheduling");
							
							for(int i = 0; i < taskSchedulati.size(); i++)
							{
								%>
								<tr>
									<th><%out.println(taskSchedulati.get(i).getID()); %></th>
									<th><%out.println(taskSchedulati.get(i).getDescrizione()); %></th>
									<th><%out.println(taskSchedulati.get(i).getOrdine()); %></th>
									<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=modifyOrderScheduling&oldPosition=<%=taskSchedulati.get(i).getOrdine()%>&newPosition=<%=taskSchedulati.get(i).getOrdine()-1%>">Su</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=modifyOrderScheduling&oldPosition=<%=taskSchedulati.get(i).getOrdine()%>&newPosition=<%=taskSchedulati.get(i).getOrdine()+1%>">Giu</a></th>
									<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=deleteOperazioneScheduling&id=<%=taskSchedulati.get(i).getIdOperazioneSchedulazione()%>">Elimina</a></th>
								</tr><%
							}
						%>
				      
				    </tbody> 
			  	</table>
				
		</div>
		
	</body>
</html>