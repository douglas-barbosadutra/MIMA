<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MENU</title>
        <%String role =((String) session.getAttribute("role"));%>
            <%if (role.equals("user")){%>
                <style>
                    #brand{
                        display:none;
                    }
                    #gommesize{
                        display:none;
                    }
                    #vehicle{
                        display:none;
                    }
                 </style>
            <%}%>
    </head>
    <body background="sfondo.jpg">
   <% String name= (String) session.getAttribute("firstname"); %>
   <%
       session.setAttribute("method", "callAction");
       session.setAttribute("servlet","");
   %>

   <div style="width:350px;position:relative;top:20px;left:600px;">
       <div style="width:60px;position:relative;top:90px;left:250px;">
           <form action="MainDispatcherServlet" method="post">
               <input type="submit" name="bott" value="Logout">
           </form>
       </div>
       <%if (role.equals("admin")){%>
       <h1>MENU ADMIN <%= name%></h1>
                       <div style="width: 300px">
                           <form action="MainDispatcherServlet" method="post">
                               <fieldset>
                                   <legend>Inserimento</legend>
                                   <table style="width: 300px">
                                       <tr>
                                           <td>
                                               <input type="radio" name="sel" value="1"/>Inserisci gomma
                                           </td>
                                       </tr>
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="2"/>Inserisci veicolo
                           </td>
                       </tr>
                       <tr>
                           <td align="center">
                               <input type="submit" name="bott" value="CONFERMA">
                           </td>
                       </tr>
                   </table>
               </fieldset>
           </form>
       </div>
       </br>
       <div style="width: 300px">
           <form action="MainDispatcherServlet" method="post">
               <fieldset>
                   <legend>Visualizza</legend>
                   <table style="width: 300px">
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="3"/>Visualizza gomme disponibili
                           </td>
                       </tr>
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="4">Visualizza utenti registrati
                           </td>
                       </tr>
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="5"/>Visualizza veicoli registrati

                           </td>
                       </tr>
                       <tr>
                           <td align="center">
                               <input type="submit" name="bott" value="CONFERMA">
                           </td>
                       </tr>
                   </table>
               </fieldset>
           </form>
       </div>
       <%}else if(role.equals("user")) {%>
       <h1>MENU USER <%= name%></h1>
       <div style="width: 400px">
           <form action="MainDispatcherServlet" method="post">
               <fieldset>
                   <legend>Ricerca</legend>
                   <table style="width: 400px">
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="6" onclick="myFunction1()"/>Cerca gomme per brand
                           </td>
                       </tr>
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="7" onclick="myFunction2()"/>Cerca gomme per dimensioni
                           </td>
                       </tr>
                       <tr>
                           <td>
                               <input type="radio" name="sel" value="8" onclick="myFunction3()"/>Cerca gomme per il tuo veicolo
                           </td>
                       </tr>
                   </table>
               </fieldset>
           </form>
       </div>
       <div id="brand" style="width: 400px">
           <form action="MainDispatcherServlet" method="post">
               <fieldset>
                   <legend>Cerca gomma per brand</legend>
                   <table style="width: 400px">
                       <tr>
                           <td>Tipo di veicolo</td>
                           <td>
                               <select name ="type">
                                   <option value="0" selected>Seleziona</option>
                                   <option value="moto">Moto</option>
                                   <option value="auto">Auto</option>
                                   <option value="commerciale">Commerciale</option>
                               </select>
                           </td>
                       </tr>
                       <tr>
                           <td align="center" colspan="2">
                               <input type="submit" name="bott" value="CONFERMA">
                           </td>
                       <tr>
                   </table>
               </fieldset>
           </form>
       </div>
       <div id="gommesize" style="width: 400px">
           <form action="MainDispatcherServlet" method="post">
               <fieldset>
                   <legend>Cerca gomma per dimensioni</legend>
                   <table style="width: 400px">
                       <tr>
                           <td>Tipo di veicolo</td>
                           <td>
                               <select name="type">
                                   <option value="0" selected>Seleziona</option>
                                   <option value="moto">Moto</option>
                                   <option value="auto">Auto</option>
                                   <option value="commerciale">Commerciale</option>
                               </select>
                           </td>
                       </tr>
                       <tr>
                           <td align="center" colspan="2">Dimensioni</td>
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
                           <td> Diametro</td>
                           <td><input type="text" name="diameter"> </td>
                       </tr>
                       <tr>
                           <td>Carico</td>
                           <td><input type="text" name="weight"> </td>
                       </tr>
                       <tr>
                           <td>Velocita</td>
                           <td><input type="text" name="speed"></td>
                       </tr>
                       <tr>
                           <td align="center" colspan="2">
                               <input type="submit" name="bott" value="CONFERMA">
                           </td>
                       </tr>
                   </table>
               </fieldset>
           </form>
       </div>
       <div id="vehicle" style="width: 400px">
           <form action="MainDispatcherServlet" method="post">
               <fieldset>
                   <legend>Cerca brand gomma per il tuo veicolo</legend>
                   <table style="width: 400px">
                       <tr>
                           <td align="center" colspan="2">Inserisci valori</td>
                       </tr>
                       <tr>
                           <td>Marca</td>
                           <td>
                               <input type="text" name="brand"> </td>
                       </tr>
                       <tr>
                           <td>Modello</td>
                           <td><input type="text" name="model"></td>
                       </tr>
                       <tr>
                           <td>Alimentazione</td>
                           <td>
                               <input type="text" name="fuel">
                           </td>
                       </tr>
                       <tr>
                           <td>Versione</td>
                           <td><input type="text" name="version"></td>
                       </tr>
                       <tr>
                           <td>Cilindrata</td>
                           <td><input type="text" name="capacity"></td>
                       </tr>
                       <tr>
                           <td align="center" colspan="2">
                               <input type="submit" name="bott" value="CONFERMA">
                           </td>
                       </tr>
                   </table>
               </fieldset>
           </form>
       </div>
       <%}%>
   </div>
   <script src="subMenu.js"></script>
    </body>
</html>
