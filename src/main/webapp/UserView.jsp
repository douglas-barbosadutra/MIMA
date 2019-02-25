<!DOCTYPE html>
<%@ page import = "com.virtualpairprogrammers.domain.User" %>
<%@ page import = "java.util.List" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utenti</title>
        <%
            String mode = (String)session.getAttribute("mode");
            List<User> users = (List<User>)session.getAttribute("users");
        %>
    </head>
    <body>
      <%
        switch (mode) {
            case "all": {
        %>
                <table border="1">
                    <caption>Lista utenti registrati</caption>
                        <th>
                            <td>Nome</td>
                            <td>Cognome</td>
                            <td>Data Nascita</td>
                            <td>Codice Fiscale</td>
                            <td>Regione Sociale</td>
                            <td>P.IVA</td>
                            <td>Comune</td>
                            <td>CAP</td>
                            <td>Città</td>
                            <td>Indirizzo</td>
                            <td>Telefono</td>
                            <td>Ruolo</td>
                        </th>
                        <%
                        for (User user: users) {
                        %>
                            <tr>
                                <td><%= user.getFirstname() %></td>
                                <td><%= user.getUsername() %></td>
                                <td><%= user.getDateofbirth() %></td>
                                <td><%= user.getFiscalcode() %></td>
                                <td><%= user.getBusinessname() %></td>
                                <td><%= user.getVat() %></td>
                                <td><%= user.getMunicipality() %></td>
                                <td><%= user.getPost() %></td>
                                <td><%= user.getCity() %></td>
                                <td><%= user.getAddress() %></td>
                                <td><%= user.getTelephone() %></td>
                                <td><%= user.getRole() %></td>
                            </tr>
                        <%}%>
                </table>
                <%
              } break;
              case "reg": {%>
                        <div style="width: 400px">
                            <form action="MainDispatcherServlet" method="post">
                                <fieldset>
                                    <legend>Inserisci dati anagrafici</legend>
                                        <table style="width: 400px">
                                            <tr>
                                                <td>Username</td>
                                                <td><input type="text" name="username"></td>
                                            </tr>
                                            <tr>
                                                <td>Password</td>
                                                <td><input type="text" name="password"></td>
                                            </tr>
                                            <tr>
                                                <td>Nome</td>
                                                <td><input type="text" name="firstname"></td>
                                            </tr>
                                            <tr>
                                                <td>Cognome</td>
                                                <td><input type="text" name="lastname"> </td>
                                            </tr>
                                            <tr>
                                                <td>Data Nascita</td>
                                                <td><input type="text" name="dateofbirth"> </td>
                                            </tr>
                                            <tr>
                                                <td>Codice Fiscale</td>
                                                <td><input type="text" name="fiscalcode"></td>
                                            </tr>
                                            <tr>
                                                <td>Regione Sociale</td>
                                                <td><input type="text" name="businessname"></td>
                                            </tr>
                                            <tr>
                                                <td>P.IVA</td>
                                                <td><input type="text" name="vat"></td>
                                            </tr>
                                            <tr>
                                                <td>Comune</td>
                                                <td><input type="text" name="municipality"></td>
                                            </tr>
                                            <tr>
                                                <td>CAP</td>
                                                <td><input type="text" name="post"></td>
                                            </tr>
                                            <tr>
                                                <td>Città</td>
                                                <td><input type="text" name="city"></td>
                                            </tr>
                                            <tr>
                                                <td>Indirizzo</td>
                                                <td><input type="text" name="address"></td>
                                            </tr>
                                            <tr>
                                                <td>Telefono</td>
                                                <td><input type="text" name="telephone"></td>
                                            </tr>
                                            <tr>
                                                <td align="center" colspan="2">
                                                    <input type="submit" value="CONFERMA">
                                                </td>
                                            </tr>
                                        </table>
                                </fieldset>
                            </form>
                        </div>
            <%}
            break;
        }
        %>
    </body>
</html>