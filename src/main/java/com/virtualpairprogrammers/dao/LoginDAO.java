package com.virtualpairprogrammers.dao;



import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private final String QUERY_LOGIN = "select * from users where username = ? and password = ?";

    public String login (String username, String password) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                String result=resultSet.getString("firstname")+":";
                result=result+resultSet.getString("role");
                return result;
            }
            else
                return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return null;
        }
    }
}
