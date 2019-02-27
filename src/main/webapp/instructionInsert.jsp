<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inserimento istruzione</title>
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
	</head>
	
	<body>
	
		<h1>Inserimento istruzione</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="InstructionServlet" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Nome istruzione</label>
					<input type="text" class="form-control" name="nome">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Durata in secondi</label>
					<input type="text" class="form-control" name="durata">				
					
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Codice</label>
					<input type="text" class="form-control" name="codice">				
					
				</div>
				
				<input type="hidden" name="action" value="insertInstruction">
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary" value="Inserisci">Inserisci</button>	
			
			</form>
			
			<form action="UserServlet" method="post">
				 <button style="margin-top:2%" class="btn btn-primary" type="submit" name="action" value="indietroUser">Indietro</button>
			</form>
			
		</div>
				
	</body>
</html>