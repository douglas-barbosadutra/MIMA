<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.domain.Vehicle" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>

<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Veicoli </title>
    </head>
    <body>
    <%
        List<Vehicle> vehicles = (List<Vehicle>)session.getAttribute("vehicles");
    %>
        <h1>Lista Veicoli Disponibili </h1>
        <p><i> ***** </i></p>
        <form action="******Lista******" method="post"><br><br><br><br><br>
            Inserisci Nome utente<input type="text" name="utente"><br>
            Inserisci password<input type="text" name="password"><br>
            <input type="submit" name="submit" value="accedi"><br>

        </form>
    </body>


</html>
