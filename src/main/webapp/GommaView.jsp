<%@ page import="com.virtualpairprogrammers.servlets.MainDispatcherServlet" %>
<%@ page import="com.virtualpairprogrammers.domain.Gomma" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Integer choice = (Integer) session.getAttribute("choice");
    String role = (String) session.getAttribute("role");

    switch (role)
    {
        case "user":
        {
            switch (choice)
            {
                case 1:
                {

                }
                break;
                case 2:
                {

                }
                break;
                case 3:
                {

                }
            }
        }
        break;
        case "admin":
        {
            switch (choice)
            {
                case 1:
                {
                    session.setAttribute("choice",3);
                    %>
                 <div>
                     <form action="MainDispatcherServlet" method="post">
                         <input type="radio" name="sel" value="0" checked="checked" hidden/>
                       <fieldset>
                       <legend align="center">Welcome project gomma</legend>
                         <table>
                             <tr>
                                 <td>Modello</td>
                                 <td><input type="text" name="model"></td>
                             </tr>
                             <tr>
                                 <td>Produttore</td>
                                 <td><input type="text" name="manufacturer"></td>
                             </tr>
                             <tr>
                                 <td>Prezzo</td>
                                 <td><input type="text" name="price"></td>
                             </tr>
                             <tr>
                                 <td>Larghezza</td>
                                 <td><input type="text" name="width"></td>
                             </tr>
                             <tr>
                                 <td>Altezza</td>
                                 <td><input type="text" name="height"></td>
                             </tr>
                             <tr>
                                 <td>Diametro</td>
                                 <td><input type="text" name="diameter"></td>
                             </tr>
                             <tr>
                                 <td>Carico</td>
                                 <td><input type="text" name="weight"></td>
                             </tr>
                             <tr>
                              <td>Velocita</td>
                              <td><input type="text" name="speed"></td>
                             </tr>
                             <tr>
                                 <td>Stagioni</td>
                                 <td><input type="text" name="season"></td>
                             </tr>
                             <tr>
                                 <td>Tipo di veicolo</td>
                                 <td><input type="text" name="typeVehicle"></td>
                             </tr>
                             <tr>
                                 <td>Quantita</td>
                                 <td><input type="text" name="quantity"></td>
                             </tr>
                             <tr>
                                 <td><input type="submit" value="Invia" name="bott"> </td>
                             </tr>
                         </table>
                       </fieldset>
                     </form>
                 </div>
<%
                }
                break;
                case 2:
                {
                   response.getWriter().println();
                }
                break;
            }

        }
    }
%>
</body>
</html>
