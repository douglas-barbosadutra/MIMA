package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;


public class UserDAO {

	private final String QUERY_ALL = "SELECT * FROM users";
	private final String QUERY_INSERT_USER = "INSERT INTO users (`name`, `surname`, `email`, `phone`, `rank`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String QUERY_DELETE_USER = "DELETE FROM users WHERE id = ?";
	private final String QUERY_UPDATE_USER = "update users set email = ?, phone = ?, username = ?, password = ? where id = ?";
	
	 public void insertUser (String username, String password, String name, String surname, String email, String phone, int rank) {
		 
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER);
            
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setInt(5, rank);
            statement.setString(6, username);
            statement.setString(7, password);
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
        }

	 }
	 
	 public void deleteUser (int id) {
		 
	        Connection connection = ConnectionSingleton.getInstance();
	        try {
	            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_USER);
	            statement.setInt(1, id);
	            
	            statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            GestoreEccezioni.getInstance().gestisciEccezione(e);
	        }

	}
	 
	 public List<User> getAllUsers () {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        
        try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(QUERY_ALL);
           
           while (resultSet.next()) {
        	   
        	   int id = resultSet.getInt("id");
        	   String name = resultSet.getString("name");
        	   String surname = resultSet.getString("surname");
        	   String email = resultSet.getString("email");
        	   String phone = resultSet.getString("phone");
        	   String username = resultSet.getString("username");
        	   String password = resultSet.getString("password");
        	   int rank = resultSet.getInt("rank");
               
               users.add(new User(id, username, password, name, surname, email, phone, rank));
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
	 
	 public void updateUser(String email, String phone, String username, String password, int id) {
		 
		 Connection connection = ConnectionSingleton.getInstance();
		 
		 try {
			 PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_USER);
	            
			 statement.setString(1, email);
			 statement.setString(2, phone);
			 statement.setString(3, username);
			 statement.setString(4, password);
			 statement.setInt(5, id);
            
			 statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            GestoreEccezioni.getInstance().gestisciEccezione(e);
	        }
	 }
}
