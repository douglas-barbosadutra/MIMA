<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.MachineDTO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista macchinari</title>
</head>
<body>

	<h1>Lista macchinari</h1>
	
	<%
		List<MachineDTO> machines = (List<MachineDTO>)session.getAttribute("machines_list");
		
		for(int i = 0; i < machines.size(); i++)
		{
			%><p><%out.println(machines.get(i));%> - <a href="MachineServlet?action=deleteMachine&id=<%=machines.get(i).getId()%>">elimina</a> - <a href="TaskServlet?action=openInsertTask&id_macchinario=<%=machines.get(i).getId()%>">inserisci task</a> - <a href="TaskServlet?action=showTask&id_macchinario=<%=machines.get(i).getId()%>">visualizza task</a></p><%
			
		}
	%>
	
	<form action="MachineServlet" method="post">
		<button type="submit" name="action" value="indietro">Indietro</button>
	</form>

</body>
</html>