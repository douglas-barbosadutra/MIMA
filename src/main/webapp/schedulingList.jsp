<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.SchedulingDTO" %>
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
	
		<% String mode = session.getAttribute("showScheduling").toString(); %>
	
		<%@ include file="menu_user.html" %>
		
		<h1 class="title">Lista Scheduling del macchinario n. </h1> 
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table table-striped">
			    <thead>
			      
			      <tr>
			        <th>ID</th>
			        <th>Nome</th>
			        <th>Data inizio</th>
			        <th>Data fine</th>
			        
			        <%if(!mode.equals("list")){
			        	
			        	%><th>Opzioni</th><% 
			        	
			        } %>
			        
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<SchedulingDTO> schedulingList = (List<SchedulingDTO>)session.getAttribute("schedulingList");
						
						for(int i = 0; i < schedulingList.size(); i++)
						{
							%>
							<tr>
								<th><%out.println(schedulingList.get(i).getId()); %></th>
								<th><%out.println(schedulingList.get(i).getName()); %></th>
								<th><%out.println(schedulingList.get(i).getDataInizio()); %></th>
								<th><%out.println(schedulingList.get(i).getDataFine()); %></th>
								
								<%if(mode.equals("delete")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Scheduling/deleteScheduling?id=<%=schedulingList.get(i).getId()%>">Elimina</a></th><%
																															
								} else if(mode.equals("management")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/OperationScheduling/showOperazionScheduling?idScheduling=<%=schedulingList.get(i).getId()%>&idMacchinario=<%=schedulingList.get(i).getIdMacchinario()%>">Gestisci</a><%
									
								} else if(mode.equals("modify")){
									
									%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/Scheduling/modifySchedulingOpen?id=<%=schedulingList.get(i).getId()%>&name=<%=schedulingList.get(i).getName()%>">Modifica</a></th><%
								}%>
								
							</tr><%
						}
					%>
			      
			    </tbody>
			    
		  </table>
		  	
		</div>
	
	</body>
</html>