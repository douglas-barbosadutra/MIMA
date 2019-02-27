<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Utente</title>
	</head>
	<body>
	
		<h1 class="title">Benvenuto ${utente.getName()}</h1>
		
			<div style="width:100%;">
			
				<form style="margin-left:30%; margin-top: 10%;" class="form-inline" action="MachineServlet" method="post">
				  <button class="btn btn-primary" type="submit" name="action" value="openManagementMachine">Gestione macchinario</button>
				</form>
				
				<form style="margin-top: 10%;" class="form-inline" action="UserServlet" method="post">
				  <button class="btn btn-primary" type="submit" name="action" value="openUpdateUser">Aggiorna info profilo</button>
				  <button class="btn btn-primary" type="submit" name="action" value="logout">Logout</button>
				</form>
				
			</div>
					
	
	</body>
</html>