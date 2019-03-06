<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Utente</title>
	</head>
	<body>
	
		<h1 class="title">Benvenuto ${utente.getName()}</h1>
		
		<ul>
		  <li>
	  		<div class="dropdown">
				<button class="dropbtn">Macchinari</button>
				
				<div class="dropdown-content"> 
					<a href="MachineServlet?action=openInsertMachine">Inserisci</a>
					<a href="MachineServlet?action=deleteMachineManagement">Elimina</a>
					<a href="MachineServlet?action=showMachine">Lista</a>
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
        
	</body>
</html>