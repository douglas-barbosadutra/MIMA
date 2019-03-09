<%@ page import="java.util.List" %>
<%@ page import="com.pCarpet.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href = "/css/Stile.css" rel = "stylesheet" media = "screen">
		<title>Visualizza Utenti</title>
	</head>
	
	<body>
	
		<% String mode = session.getAttribute("showUser").toString(); %>
		
		<ul>
			<li>
		  		<div class="dropdown">
					<a href="/User/openInsertUser" class="dropbtn">Inserisci utente</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="/User/showUser?showUser=delete" class="dropbtn">Elimina utente</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="/User/showUser?showUser=list" class="dropbtn">Lista utenti</a>				
				</div>
	  		</li>
	  		
	  		<li>
		  		<div class="dropdown">
					<a href="/User/logout" class="dropbtn">Logout</a>				
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
									<th><%out.println(users.get(i).getId()); %></th>
									<th><%out.println(users.get(i).getName()); %></th>
									<th><%out.println(users.get(i).getSurname()); %></th>
									<th><%out.println(users.get(i).getEmail()); %></th>
									<th><%out.println(users.get(i).getPhone()); %></th>
									
									<%if(mode.equals("delete")){
										%><th><a style="text-decoration:none; text-align:center;" class="btn btn-primary" href="/User/deleteUser?id=<%=users.get(i).getId()%>">elimina</a></th><%
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