<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.TaskDTO" %>
<%@ page import="com.virtualpairprogrammers.dto.TaskSchedulatiDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<title>Lista Scheduling</title>
</head>
<body>
		
		<h1 class="title">da scegliere</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			    <h3>Task Schedulati</h3>
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
						List<TaskSchedulatiDTO> taskSchedulati = (List<TaskSchedulatiDTO>)session.getAttribute("listaTaskScheduling");
						
						for(int i = 0; i < taskSchedulati.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(taskSchedulati.get(i).getID()); %></th>
								<th><%out.println(taskSchedulati.get(i).getDescrizione()); %></th>
								<th><%out.println(taskSchedulati.get(i).getOrdine()); %></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=modifyOrderScheduling&oldPosition=<%=taskSchedulati.get(i).getOrdine()%>&newPosition=<%=taskSchedulati.get(i).getOrdine()-1%>">Su</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=modifyOrderScheduling&oldPosition=<%=taskSchedulati.get(i).getOrdine()%>&newPosition=<%=taskSchedulati.get(i).getOrdine()+1%>">Giu</a></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=deleteOperazioneScheduling&id=<%=taskSchedulati.get(i).getIdOperazioneSchedulazione()%>">Elimina</a></th>
							</tr><%
						}
					%>
			      
			    </tbody> 
		  </table>
		  
		  <table class="table table-striped">
			    <thead>
			    <h3>Task Disponibili</h3>
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
								<th><%out.println(taskDisponibili.get(i).getID()); %></th>
								<th><%out.println(taskDisponibili.get(i).getDescrizione()); %></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=insertOperazione&id=<%=taskDisponibili.get(i).getID()%>">Aggiungi</a></th>
							</tr><%
						}
					%>
			      
			    </tbody> 
		  </table>
		  
		  	<form action="OperazioneSchedulazioneServlet" method="post">
				<button style="margin-left:40%;" class="btn btn-primary" type="submit" name="action" value="indietro">Indietro</button>
			</form>
					
		</div>
	
	</body>
</html>