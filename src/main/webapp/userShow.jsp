<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualizza Utenti</title>
</head>
<body>

<%

List<UserDTO> users = (List<UserDTO>)session.getAttribute("users_list");

for(int i = 0; i < users.size(); i++)
{
	%><p><%out.println(users.get(i));%> - <a href="UserServlet?action=deleteUser&id=<%=users.get(i).getID()%>">elimina</a></p><%
}
%>

<form action="UserServlet" method="post">
	<button type="submit" name="action" value="indietro">Indietro</button>
</form>

</body>
</html>