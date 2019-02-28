<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Inserisci utente</title>
	</head>
	
	<body>
	
		<ul>
			<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=openInsertUser" class="dropbtn">Inserisci utente</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=deleteUserManagement" class="dropbtn">Elimina utente</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=showUser" class="dropbtn">Lista utenti</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=logout" class="dropbtn">Logout</a>				
				</div>
	  		</li>
		</ul>
		
		<div class="main">
		
			<h1 class="title">Inserisci utente</h1>
			
			<div style="padding-left: 20%; padding-right: 20%;">
		
				<form action="UserServlet" method="post">
			
					<div class="form-group">
						<label class="col-form-label">Username</label>
						<input type="text" class="form-control" name="username">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Password</label>
						<input type="password" class="form-control" name="password">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Nome</label>
						<input type="text" class="form-control" name="nome">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Congome</label>
						<input type="text" class="form-control" name="cognome">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Email</label>
						<input type="email" class="form-control" name="email">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Telefono</label>
						<input type="number" class="form-control" name="telefono">				
						
					</div>
					
					<div class="form-group">
						
						<input type="radio" name="rank" value="0" checked="checked">Utente					
						
					</div>
					
					<div class="form-group">
						
						<input type="radio" name="rank" value="1">Admin			
						
					</div>
					
					<input type="hidden" name="action" value="insertUser">
					
					<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" >Inserisci</button>	
				
				</form>
				
			</div>
			
		</div>
		
	</body>
</html>