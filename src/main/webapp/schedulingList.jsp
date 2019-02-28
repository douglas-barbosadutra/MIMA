<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.SchedulingDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Scheduling</title>
</head>
<body>
		
		<h1 class="title">Lista Scheduling</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Data inizio</th>
			        <th>Data fine</th>
			        <th>ID macchinario</th>
			        <th>Opzioni</th>
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<SchedulingDTO> schedulingList = (List<SchedulingDTO>)session.getAttribute("listaScheduling");
						
						for(int i = 0; i < schedulingList.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(schedulingList.get(i).getId()); %></th>
								<th><%out.println(schedulingList.get(i).getNome()); %></th>
								<th><%out.println(schedulingList.get(i).getInizio()); %></th>
								<th><%out.println(schedulingList.get(i).getFine()); %></th>
								<th><%out.println(schedulingList.get(i).getIdMacchinario()); %></th>
								<th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="SchedulingServlet?action=deleteScheduling&id=<%=schedulingList.get(i).getId()%>">Elimina</a><a style="text-decoration:none; text-align:center; margin-left:2%;" class="btn btn-primary" href="OperazioneSchedulazioneServlet?action=showOperazioni&idSchedulazione=<%=schedulingList.get(i).getId()%>&idMacchinario=<%=schedulingList.get(i).getIdMacchinario()%>">Gestione Schedulazione</a></th>
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  	<form action="TaskServlet" method="post">
				<button style="margin-left:40%;" class="btn btn-primary" type="submit" name="action" value="indietro">Indietro</button>
			</form>
					
		</div>
	
	</body>
</html>