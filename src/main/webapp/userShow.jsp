<%@ page import="java.util.List" %>
<%@ page import="com.virtualpairprogrammers.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "Stile.css" rel = "stylesheet" media = "screen">
		<title>Visualizza Utenti</title>
	</head>
	
	<body>
	
		<% String mode = session.getAttribute("showUser").toString(); %>
		
		<ul>
			<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=openInsertUser" class="dropbtn">Inserisci utente</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=deleteUserManagement" class="dropbtn">Elimina utente</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=showUser" class="dropbtn">Lista utenti</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="UserServlet?action=logout" class="dropbtn">Logout</a>				
				</div>
	  		</li>
		</ul>
		
		<div class="main">
			
			<h1 class="title">Lista utenti</h1>
			
			<div style="padding-left:10%; padding-right:10%">
			
				<table class="table table-striped">
				    <thead>
				      
				      <tr>
				        <th>ID</th>
				        <th>Nome</th>
				        <th>Cognome</th>
				        <th>Email</th>
				        <th>Telefono</th>
				        <% if(mode.equals("delete")){
				        	%><th>Opzioni</th><%
				        }%>
				        
				      </tr>
				      
				    </thead>
				    
				    <tbody>
				    
				    	<%
							List<UserDTO> users = (List<UserDTO>)session.getAttribute("users_list");
							
							for(int i = 0; i < users.size(); i++)
							{
								%>
								<tr>
									<th><%out.println(users.get(i).getID()); %></th>
									<th><%out.println(users.get(i).getName()); %></th>
									<th><%out.println(users.get(i).getSurname()); %></th>
									<th><%out.println(users.get(i).getEmail()); %></th>
									<th><%out.println(users.get(i).getPhone()); %></th>
									
									<%if(mode.equals("delete")){
										%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="UserServlet?action=deleteUser&id=<%=users.get(i).getID()%>">elimina</a></th><%
									} %>
									
								</tr><%
							}
						%>
				      
				    </tbody>
				    
			  </table>
			  						
			</div>
		
		</div>
		
	</body>
</html>