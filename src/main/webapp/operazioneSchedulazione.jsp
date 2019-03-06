<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.TaskDTO" %>
<%@ page import="com.pCarpet.dto.TaskScheduledDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista Scheduling</title>
	</head>
	
	<body>
	
		<%@ include file="menu_user.html" %>
		
		<h1 class="title">Gestione scheduling n. ${idSchedulazioneScelta}</h1>
	
		<div style="padding-left:10%; padding-right:10%">
			
			<h2 class="title" style="text-align:left">Task Disponibili</h2>
			      
		      <table class="table table-striped">
			    <thead>
			      <tr>
			        <th>ID</th>
			        <th>Descrizione</th>
			        <th>Opzioni</th>
			      </tr> 
			    </thead>
			    
			    <tbody>
	
			    	<%
						List<TaskDTO> taskDisponibili = (List<TaskDTO>)session.getAttribute("listaTaskDisponibili");
						
						for(int i = 0; i < taskDisponibili.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(taskDisponibili.get(i).getId()); %></th>
								<th><%out.println(taskDisponibili.get(i).getDescrizione()); %></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=insertOperazione&id=<%=taskDisponibili.get(i).getId()%>">Aggiungi</a></th>
							</tr><%
						}
					%>
			      
			    </tbody> 
		  	</table>
		  	
		  	<h2 class="title" style="text-align:left;">Task Schedulati</h2>
		  	
		  	<table class="table table-striped">
				      
				    <thead>
				      <tr>
				        <th>ID</th>
				        <th>Nome</th>
				        <th>Ordine</th>
				        <th>Cambia Ordine</th>
				        <th>Opzioni</th>
				      </tr>
				    </thead>
				    
				    <tbody>
				    
				    	<%
							List<TaskScheduledDTO> taskSchedulati = (List<TaskScheduledDTO>)session.getAttribute("listaTaskScheduling");
							
							for(int i = 0; i < taskSchedulati.size(); i++)
							{
								%>
								<tr>
									<th><%out.println(taskSchedulati.get(i).getId()); %></th>
									<th><%out.println(taskSchedulati.get(i).getDescrizione()); %></th>
									<th><%out.println(taskSchedulati.get(i).getOrdine()); %></th>
									<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=modifyOrderScheduling&oldPosition=<%=taskSchedulati.get(i).getOrdine()%>&newPosition=<%=taskSchedulati.get(i).getOrdine()-1%>">Su</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=modifyOrderScheduling&oldPosition=<%=taskSchedulati.get(i).getOrdine()%>&newPosition=<%=taskSchedulati.get(i).getOrdine()+1%>">Giu</a></th>
									<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=deleteOperazioneScheduling&id=<%=taskSchedulati.get(i).getIdOperazioneSchedulazione()%>">Elimina</a></th>
								</tr><%
							}
						%>
				      
				    </tbody> 
			  	</table>
				
		</div>
		
	</body>
</html>