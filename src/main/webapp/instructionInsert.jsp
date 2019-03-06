<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inserimento istruzione</title>
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
	</head>
	
	<body>
	
		<%@ include file="menu_user.html" %>
	
		<h1 class="title">Inserimento istruzione al task n. ${idTaskScelto}</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="/Instruction/insertInstruction" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Nome istruzione</label>
					<input type="text" class="form-control" name="nome">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Durata in secondi</label>
					<input type="number" class="form-control" name="durata">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Codice</label>
					<input type="text" class="form-control" name="codice">				
					
				</div>
				
				<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" name="action" value="insertInstruction">Inserisci</button>	
			
			</form>
			
		</div>
				
	</body>
</html>