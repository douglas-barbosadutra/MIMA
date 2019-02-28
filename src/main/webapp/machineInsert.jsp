<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Inserimento macchinario</title>
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
					<a href="TaskServlet?action=chooseMachine">Seleziona</a>
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
					<a href="TaskServlet?action=showTime">Tempi</a>
					<a href="InstructionServlet?action=chooseTask">Seleziona</a>
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
				<a href="" class="dropbtn">Logout</a>				
			</div>
		  </li>

		</ul>
		
		<div class="content">
			
			<h1 class="title">Inserimento macchinario</h1>
			
			<div style="padding-left: 20%; padding-right: 20%;">
		
				<form action="MachineServlet" method="post">
			
					<div class="form-group">
						<label class="col-form-label">Nome</label>
						<input type="text" class="form-control" name="nome">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Modello</label>
						<input type="text" class="form-control" name="modello">				
						
					</div>
					
					<input type="hidden" name="action" value="insertMachine">
					
					<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" >Inserisci</button>	
				
				</form>
				
			</div>
			
		</div>
		
	</body>
</html>