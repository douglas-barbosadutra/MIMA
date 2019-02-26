<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.TaskDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

List<TaskDTO> tasks = (List<TaskDTO>)session.getAttribute("taskList");

for(int i = 0; i < tasks.size(); i++)
{
	%><p><%out.println(tasks.get(i));%> - <a href="TaskServlet?action=deleteUser&id=<%=tasks.get(i).getID()%>">elimina</a> - <a href="InstructionServlet?action=insertInstructionOpen&idTask=<%=tasks.get(i).getID()%>">inserisci istruzione</a> - <a href="InstructionServlet?action=showInstruction&idTask=<%=tasks.get(i).getID()%>">visualizza istruzioni</a></p>
	<%
}
%>

<form action="TaskServlet" method="post">
	<button type="submit" name="action" value="indietro">Indietro</button>
</form>

</body>
</html>