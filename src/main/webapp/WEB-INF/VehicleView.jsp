<%@ page import="com.virtualpairprogrammers.domain.Vehicle" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Romina
  Date: 11/12/2017
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Veicoli</title>
    </head>
    <body>
        <%
            String mode = (String)session.getAttribute("mode");
            List<Vehicle> vehicles = (List<Vehicle>)session.getAttribute("vehicles");
        %>
            <h1>Lista Veicoli Disponibili </h1>
            <%
            switch (mode) {
                case "all": {

                for (Vehicle vehicle: vehicles)
                {
        %>
                 <%= vehicle %>


        <%   }
        break;
        }
            case "insert":{


            }

        }
        %>

    </body>
</html>
