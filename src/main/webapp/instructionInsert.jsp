<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inserimento istruzione</title>
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
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
	
		<h1>Inserimento istruzione al task n. ${idTaskScelto}</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="InstructionServlet" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Nome istruzione</label>
					<input type="text" class="form-control" name="nome">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Durata in secondi</label>
					<input type="text" class="form-control" name="durata">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Codice</label>
					<input type="text" class="form-control" name="codice">				
					
				</div>
				
				<input type="hidden" name="action" value="insertInstruction">
				
				<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" value="Inserisci">Inserisci</button>	
			
			</form>
			
		</div>
				
	</body>
</html>