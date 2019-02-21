package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.User;

public class UserDAO {

	private final String QUERY_ALL = "SELECT * FROM users";
	private final String QUERY_INSERT_USER = "INSERT INTO users (username,password) VALUES (?,?)";
	private final String QUERY_DELETE_USER = "DELETE FROM users WHERE id = ?";
	
	
	 public void insertUser (String username, String password) {
		 
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            
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
               String username = resultSet.getString("username");
               String password = resultSet.getString("password");
               int rank = resultSet.getInt("rank");
               
               users.add(new User(id, username, password, rank));
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
