<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.TimeDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Lista tempi</title>
	</head>
	
	<body>
	
		<%@ include file="menu_user.html" %>
	
		<h1 class="title">Lista tempi del task ${idTaskScelto}</h1>
		
		<div style="padding-left:10%; padding-right:10%">
			
			<table class="table">
			    <thead>
			      
			      <tr>
			        <th>Nome Istruzione</th>
			        <th>Tempi effettivi</th>
			        <th>Tempi previsti</th>
			      </tr>
			      
			    </thead>
			    
			    <tbody>
			    
			    	<%
						List<TimeDTO> gestioneTempiDTO = (List<TimeDTO>)session.getAttribute("listaTempi");
						
						for(int i = 0; i < gestioneTempiDTO.size(); i++)
						{
							%>
								<tr bgcolor="<%out.println(gestioneTempiDTO.get(i).getRisultato().toString());%>" >
								  	<td><%out.println(gestioneTempiDTO.get(i).getNomeIstruzione().toString());%></td>
							    	<td><%out.println(gestioneTempiDTO.get(i).getDurataEffettiva());%></td>
							    	<td><%out.println(gestioneTempiDTO.get(i).getDurataPrevista());%></td>
								</tr>
							<%
						}
					%>
			      
			    </tbody>
			    
		  </table>
					
		</div>
	
	</body>
</html>