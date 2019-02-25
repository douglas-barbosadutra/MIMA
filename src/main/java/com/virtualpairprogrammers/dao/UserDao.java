package com.virtualpairprogrammers.dao;

import com.virtualpairprogrammers.domain.User;


import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao
{
    private final String QUERY_ALL = "select * from users where role=\"user\"";
    private final String QUERY_INSERT ="INSERT INTO users (idUser,username,password,firstname,lastname,dateofbirth,fiscalcode,businessname,vat,municipality,post,city,address,telephone,role) VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public UserDao() {}

    public boolean insertUser(User user)
    {
        Connection connection = ConnectionSingleton.getInstance();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFirstname());
            preparedStatement.setString(4,user.getLastname());
            preparedStatement.setString(5,user.getDateofbirth());
            preparedStatement.setString(6,user.getFiscalcode());
            preparedStatement.setString(7,user.getBusinessname());
            preparedStatement.setString(8,user.getVat());
            preparedStatement.setString(9,user.getMunicipality());
            preparedStatement.setString(10,user.getPost());
            preparedStatement.setString(11,user.getCity());
            preparedStatement.setString(12,user.getAddress());
            preparedStatement.setString(13,user.getTelephone());
            preparedStatement.setString(14,"user");

            return preparedStatement.execute();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            e.printStackTrace();
            return false;
        }
    }
    public List<User> getAllUser()
    {
        List <User> users=new ArrayList<User>();
        Connection connection = ConnectionSingleton.getInstance();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            while (resultSet.next())
            {
                int idUser=resultSet.getInt("idUser");
                String username=resultSet.getString("username");
                String password=resultSet.getString("password");
                String firstname=resultSet.getString("firstname");
                String lastname=resultSet.getString("lastname");
                String dateofbirth=resultSet.getString("dateofbirth");
                String fiscalcode=resultSet.getString("fiscalcode");
                String businessname=resultSet.getString("businessname");
                String vat=resultSet.getString("vat");
                String municipality=resultSet.getString("municipality");
                String post=resultSet.getString("post");
                String city=resultSet.getString("city");
                String address=resultSet.getString("address");
                String telephone=resultSet.getString("telephone");
                String role=resultSet.getString("role");
                users.add(new User(idUser,username,password,firstname,lastname,dateofbirth,fiscalcode,businessname,vat,municipality,post,city,address,telephone,role));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  users;
    }
}