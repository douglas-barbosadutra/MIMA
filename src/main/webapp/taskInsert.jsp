<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Inserimento Task</title>
	</head>
	
	<body>
	
	<%@ include file="menu_user.html" %>
	
		<h1 class="title">Inserimento task al macchinario n. ${idMacchinarioScelto}</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="/Task/insertTask" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Descrizione</label>
					<input type="text" class="form-control" name="descrizione">				
					
				</div>
				
				<button style="margin-top:2%; margin-left:40%;" type="submit" class="btn btn-primary" name="action" value="insertTask" >Inserisci</button>	
			
			</form>
			
		</div>
			
	</body>
</html>