<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>User update</title>
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
		
		<h1 class="title">Aggiornamento info profilo</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="UserServlet" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Username</label>
					<input type="text" class="form-control" name="username" value="${utente.getUsername()}">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Password</label>
					<input type="password" class="form-control" name="password" value="${utente.getPassword()}">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Nome</label>
					<input type="text" class="form-control" name="nome" value="${utente.getName()}">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Congome</label>
					<input type="text" class="form-control" name="cognome" value="${utente.getSurname()}">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Email</label>
					<input type="email" class="form-control" name="email" value="${utente.getEmail()}">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Telefono</label>
					<input type="number" class="form-control" name="telefono" value="${utente.getPhone()}">				
				</div>
				
				<input type="hidden" name="action" value="updateUser">
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary" >Aggiorna</button>	
			
			</form>
			
		</div>
	
	</body>
</html>