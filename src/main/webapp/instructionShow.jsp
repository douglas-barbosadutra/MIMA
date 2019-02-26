<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.InstructionDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza istruzioni</title>
</head>
<body>
<%

List<InstructionDTO> istruzioni = (List<InstructionDTO>)session.getAttribute("listaIstruzioni");

for(int i = 0; i < istruzioni.size(); i++)
{
	%><p><%out.println(istruzioni.get(i));%> - <a href="InstructionServlet?action=deleteInstruction&nomeIstruzione=<%=istruzioni.get(i).getNomeIstruzione()%>">elimina</a></p><%
}
%>
	<form action="InstructionServlet" method="post">
		<button type="submit" name="action" value="indietro">Indietro</button>
	</form>
</body>
</html>