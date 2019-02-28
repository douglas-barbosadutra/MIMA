<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Inserimento Scheduling</title>
	</head>
	<body>
	
		<h1 class="title">Inserimento Scheduling</h1>
		
		<div style="padding-left: 20%; padding-right: 20%;">
		
			<form action="SchedulingServlet" method="post">
		
				<div class="form-group">
					<label class="col-form-label">Nome</label>
					<input type="text" class="form-control" name="nome">				
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Data Inizio(formato gg-mm-aaaa)</label>
					<input type="text" class="form-control" name="dataInizio">				
				</div>
				
				<div class="form-group">
					<label class="col-form-label">Data Fine(formato gg-mm-aaaa)</label>
					<input type="text" class="form-control" name="dataFine">				
				</div>

				<input type="hidden" name="action" value="insertScheduling">
				
				<button style="margin-top:2%" type="submit" class="btn btn-primary" >Inserisci</button>	
			
			</form>
			
			<form action="SchedulingServlet" method="post">
				 <button style="margin-top:2%" class="btn btn-primary" type="submit" name="action" value="indietro">Indietro</button>
			</form>
			
		</div>	
	
	</body>
</html>