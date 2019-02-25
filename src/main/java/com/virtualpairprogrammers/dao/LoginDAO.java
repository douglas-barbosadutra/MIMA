package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

public class LoginDAO {

    private final String QUERY_LOGIN = "select * from users where username = ? and password = ?";

    public User login (String username, String password) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()) {          	
            	return new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("surname"),rs.getString("email"),rs.getString("phone"),rs.getInt("rank"));
            }else {
            	return null;
            }

        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return null;
        }
    }
    
}
