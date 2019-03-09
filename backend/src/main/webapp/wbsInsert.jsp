<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Inserimento WBS</title>
	</head>
	
	<body>
		
		<%@ include file="menu_user.html" %>
	
		<h1 class="title">Inserimento WBS</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="/WBS/insertWbs" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Nome</label>
					<input type="text" class="form-control" name="nome">				
				</div>
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary">Inserisci</button>	
			
			</form>
			
		</div>
	
	</body>
</html>