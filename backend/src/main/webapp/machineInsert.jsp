<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Inserimento macchinario</title>
	</head>
	
	<body>
		
		<%@ include file="menu_user.html" %>
		
		<div class="content">
			
			<h1 class="title">Inserimento macchinario</h1>
			
			<div style="padding-left: 20%; padding-right: 20%;">
		
				<form action="/Machine/insertMachine" method="post">
			
					<div class="form-group">
						<label class="col-form-label">Nome</label>
						<input type="text" class="form-control" name="nome">				
						
					</div>
					
					<div class="form-group">
						<label class="col-form-label">Modello</label>
						<input type="text" class="form-control" name="modello">				
						
					</div>
					
					<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" name="action" value="insertMachine" >Inserisci</button>	
				
				</form>
				
			</div>
			
		</div>
		
	</body>
</html>