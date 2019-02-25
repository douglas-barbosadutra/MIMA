package com.virtualpairprogrammers.dao;

import com.virtualpairprogrammers.domain.Vehicle;

import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;
//import main.controller.GestoreEccezioni;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO
{
    private final String QUERY_ALL ="select * from Vehicle";
    private final String QUERY_INSERT="INSERT INTO vehicle (idVehicle,brand,model,fuel,version,capacity) VALUES (NULL,?,?,?,?,?);";
    public VehicleDAO() {}

    public List<Vehicle> getAllVehicle()
    {
        List<Vehicle> vehicles= new ArrayList<Vehicle>();
        Connection connection = ConnectionSingleton.getInstance();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            while (resultSet.next())
            {
                Integer idVehicle=resultSet.getInt("idVehicle");
                String brand=resultSet.getString("brand");
                String model=resultSet.getString("model");
                String fuel=resultSet.getString("fuel");
                String version=resultSet.getString("version");
                String capacity=resultSet.getString("capacity");
                vehicles.add(new Vehicle(idVehicle,brand,model,fuel,version,capacity));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  vehicles;
    }
    public boolean insertVehicle(Vehicle vehicle)
    {
        Connection connection = ConnectionSingleton.getInstance();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);

            preparedStatement.setString(1,vehicle.getBrand());
            preparedStatement.setString(2,vehicle.getModel());
            preparedStatement.setString(3,vehicle.getFuel());
            preparedStatement.setString(4,vehicle.getVersion());
            preparedStatement.setString(5,vehicle.getCapacity());

            return preparedStatement.execute();
        }
        catch (SQLException e)
        {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            e.printStackTrace();
            return false;
        }

    }
//    public Integer getIdVehicle(String brand,String model, String fuel, String version, String capacity)
//    {
//        Integer idVehicle=null;
//        String query="select idVehicle from Vehicle where brand= ? and model= ? and fuel= ? and version =? and capacity=?";
//        Connection connection=ConnectionSingleton.getInstance();
//        try
//        {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1,brand);
//            preparedStatement.setString(2,model);
//            preparedStatement.setString(3,fuel);
//            preparedStatement.setString(4,version);
//            preparedStatement.setString(5,capacity);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next())
//            {
//                idVehicle=resultSet.getInt("idVehicle");
//            }
//        }
//        catch (SQLException e) { e.printStackTrace(); }
//
//        return idVehicle;
//    }
}
