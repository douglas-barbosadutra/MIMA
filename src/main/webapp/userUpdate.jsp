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
		
		<h1 class="title">Scegli cosa aggiornare</h1>
		
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
					<input type="text" class="form-control" name="telefono" value="${utente.getPhone()}">				
				</div>
				
				<input type="hidden" name="action" value="updateUser">
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary" >Aggiorna</button>	
			
			</form>
			
			<form action="UserServlet" method="post">
				 <button style="margin-top:2%" class="btn btn-primary" type="submit" name="action" value="indietroUser">Indietro</button>
			</form>
			
		</div>
	
	</body>
</html>