<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.TimeDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizzazione tempi</title>
</head>
<body>

<table>
  <caption>Tempi lavorazioni</caption>
  <tr>
  	<th>Nome Istruzione</th>
    <th>Tempi effettivi</th>
    <th>Tempi previsti</th>
  </tr>
<%
List<TimeDTO> gestioneTempiDTO = (List<TimeDTO>)session.getAttribute("listaTempi");
for(int i = 0; i < gestioneTempiDTO.size(); i++){
%>
  	<tr bgcolor="<%out.println(gestioneTempiDTO.get(i).getRisultato().toString());%>" >
	  	<td><%out.println(gestioneTempiDTO.get(i).getNomeIstruzione().toString());%></td>
    	<td><%out.println(gestioneTempiDTO.get(i).getDurataEffettiva());%></td>
    	<td><%out.println(gestioneTempiDTO.get(i).getDurataPrevista());%></td>
	</tr>
<%	  	
  }
  %>
  
  	<form action="LavorazioniServlet" method="post">
		<button type="submit" name="action" value="indietro">Indietro</button>
	</form>
</table>
</body>
</html>