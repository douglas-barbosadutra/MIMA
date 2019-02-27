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
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary" >Inserisci</button>	
			
			</form>
			
			<form action="MachineServlet" method="post">
				 <button style="margin-top:2%" class="btn btn-primary" type="submit" name="action" value="openManagementMachine">Indietro</button>
			</form>
			
		</div>	
	
	</body>
</html>