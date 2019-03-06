<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Amministratore</title>
	</head>
	
	<body>
		<h1 class="title">Benvenuto ${utente.getName()}</h1>
	
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
		
	</body>
</html>