<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Modifica Scheduling</title>
	</head>
	
	<body>
	
		<%@ include file="menu_user.html" %>
	
		
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="/Scheduling/modifyScheduling" method="post">
						
				<div class="form-group">
					<label class="col-form-label">Data Inizio</label>
					<input type="datetime-local" class="form-control" name="dataInizio">				
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Data Fine</label>
					<input type="datetime-local" class="form-control" name="dataFine">				
				</div>

				<input type="hidden" name="action" value="modifyScheduling">
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary" >Modifica</button>	
			
			</form>
			
		</div>	
	
	</body>
</html>