package com.virtualpairprogrammers.dao;

import com.virtualpairprogrammers.domain.Gomma;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

//import main.controller.GestoreEccezioni;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GommaDAO {

    private final String QUERY_ALL = "select * from gomme";
    private final String QUERY_INSERT = "insert into gomme (idGomme, model, manufacturer,price,width,height,diameter,weight,speed,season,typeVehicle,quantity) values (NULL,?,?,?,?,?,?,?,?,?,?,?)";

    public GommaDAO() {

    }

    public List<Gomma> getAllIdGommeForIdVehicle(Integer idVehicle)
    {
        Connection connection = ConnectionSingleton.getInstance();
        List<Gomma> gommes=new ArrayList<Gomma>();
        String query="select idGomme from compatibility where idVehicle= ?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idVehicle);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Integer idGomme=resultSet.getInt("idGomme");
                Gomma gomma=getGommaForID(idGomme);
                if(gomma!=null)
                    gommes.add(gomma);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }

        return gommes;
    }
    public Gomma getGommaForID(Integer idGomma)
    {
        Gomma gomma=null;
        Connection connection = ConnectionSingleton.getInstance();
        String query="select * from gomme where idGomme= ?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idGomma);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int idGomme= resultSet.getInt("idGomme");
                String model = resultSet.getString("model");
                String manufacturer = resultSet.getString("manufacturer");
                double price = resultSet.getDouble("price");
                double pwidth = resultSet.getDouble("width");
                double pheight = resultSet.getDouble("height");
                double pdiameter = resultSet.getDouble("diameter");
                double pweight = resultSet.getDouble("weight");
                String pspeed = resultSet.getString("speed");
                String pseason = resultSet.getString("season");
                String ptypeVehicle = resultSet.getString("typeVehicle");
                int quantity = resultSet.getInt("quantity");
                gomma=(new Gomma(idGomme, model, manufacturer,price,pwidth,pheight,pdiameter,pweight,pspeed,pseason,ptypeVehicle,quantity));
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return gomma;
    }
    public List<Gomma> getAllGommeForSize(double width,double height,double diameter,double weight,String speed,String season,String typeVehicle)
    {
        Connection connection = ConnectionSingleton.getInstance();
        List<Gomma> gomme= new ArrayList<Gomma>();
        if((typeVehicle.equals("moto"))||(typeVehicle.equals("commerciale")))
        {
            String query = "SELECT * FROM gomme where width = ? and height = ? and diameter = ? and weight= ? and speed = ?  and typeVehicle = ?";
            try
            {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setDouble(1,width);
                preparedStatement.setDouble(2,height);
                preparedStatement.setDouble(3,diameter);
                preparedStatement.setDouble(4,weight);
                preparedStatement.setString(5,speed);
                preparedStatement.setString(6,typeVehicle);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    int idGomme= resultSet.getInt("idGomme");
                    String model = resultSet.getString("model");
                    String manufacturer = resultSet.getString("manufacturer");
                    double price = resultSet.getDouble("price");
                    double pwidth = resultSet.getDouble("width");
                    double pheight = resultSet.getDouble("height");
                    double pdiameter = resultSet.getDouble("diameter");
                    double pweight = resultSet.getDouble("weight");
                    String pspeed = resultSet.getString("speed");
                    String pseason = resultSet.getString("season");
                    String ptypeVehicle = resultSet.getString("typeVehicle");
                    int quantity = resultSet.getInt("quantity");
                    gomme.add(new Gomma(idGomme, model, manufacturer,price,pwidth,pheight,pdiameter,pweight,pspeed,pseason,ptypeVehicle,quantity));
                }
            }
            catch (SQLException e) { e.printStackTrace(); }
        }
        else
            if(typeVehicle.equals("auto"))
            {
                String query = "SELECT * FROM gomme where width = ? and height = ? and diameter = ?  and season = ? and typeVehicle = ?";
                try
                {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setDouble(1,width);
                    preparedStatement.setDouble(2,height);
                    preparedStatement.setDouble(3,diameter);
                    preparedStatement.setString(4,season);
                    preparedStatement.setString(5,typeVehicle);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next())
                    {
                        int idGomme= resultSet.getInt("idGomme");
                        String model = resultSet.getString("model");
                        String manufacturer = resultSet.getString("manufacturer");
                        double price = resultSet.getDouble("price");
                        double pwidth = resultSet.getDouble("width");
                        double pheight = resultSet.getDouble("height");
                        double pdiameter = resultSet.getDouble("diameter");
                        double pweight = resultSet.getDouble("weight");
                        String pspeed = resultSet.getString("speed");
                        String pseason = resultSet.getString("season");
                        String ptypeVehicle = resultSet.getString("typeVehicle");
                        int quantity = resultSet.getInt("quantity");
                        gomme.add(new Gomma(idGomme, model, manufacturer,price,pwidth,pheight,pdiameter,pweight,pspeed,pseason,ptypeVehicle,quantity));
                    }
                }
                catch (SQLException e) { e.printStackTrace(); }
            }
        return gomme;
    }

    public List<Gomma> getAllGommeForManufacturer (String brand,String type)
    {/**/
        Connection connection = ConnectionSingleton.getInstance();
        List<Gomma> gomme= new ArrayList<Gomma>();
        String query="SELECT * FROM gomme where manufacturer=\""+brand+"\" and typeVehicle=\""+type+"\"";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                int idGomme= resultSet.getInt("idGomme");
                String model = resultSet.getString("model");
                String manufacturer = resultSet.getString("manufacturer");
                double price = resultSet.getDouble("price");
                double width = resultSet.getDouble("width");
                double height = resultSet.getDouble("height");
                double diameter = resultSet.getDouble("diameter");
                double weight = resultSet.getDouble("weight");
                String speed = resultSet.getString("speed");
                String season = resultSet.getString("season");
                String typeVehicle = resultSet.getString("typeVehicle");
                int quantity = resultSet.getInt("quantity");
                gomme.add(new Gomma(idGomme, model, manufacturer,price,width,height,diameter,weight,speed,season,typeVehicle,quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gomme;

    }
    public List<String> getAllManufacturerForTypeVehicle(String type)
    {/**/
        Connection connection = ConnectionSingleton.getInstance();
        List<String> manufacturers= new ArrayList<String>();
        String query="SELECT DISTINCT manufacturer FROM gomme WHERE typeVehicle=\""+type+"\"";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                manufacturers.add(resultSet.getString("manufacturer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }
    public List<Gomma> getAllGomme ()
    {
        List<Gomma> gomme = new ArrayList<Gomma>();
        Connection connection = ConnectionSingleton.getInstance();
        try
        {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(QUERY_ALL);
           while (resultSet.next())
           {
               int idGomme= resultSet.getInt("idGomme");
               String model = resultSet.getString("model");
               String manufacturer = resultSet.getString("manufacturer");
               double price = resultSet.getDouble("price");
               double width = resultSet.getDouble("width");
               double height = resultSet.getDouble("height");
               double diameter = resultSet.getDouble("diameter");
               double weight = resultSet.getDouble("weight");
               String speed = resultSet.getString("speed");
               String season = resultSet.getString("season");
               String typeVehicle = resultSet.getString("typeVehicle");
               int quantity = resultSet.getInt("quantity");
               gomme.add(new Gomma(idGomme, model, manufacturer,price,width,height,diameter,weight,speed,season,typeVehicle,quantity));
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return gomme;
    }

    public boolean insertGomma(Gomma gomma)
    {
        Connection connection = ConnectionSingleton.getInstance();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);

            preparedStatement.setString(1, gomma.getModel());
            preparedStatement.setString(2, gomma.getManufacturer());
            preparedStatement.setDouble(3, gomma.getPrice());
            preparedStatement.setDouble(4, gomma.getWidth());
            preparedStatement.setDouble(5, gomma.getHeight());
            preparedStatement.setDouble(6, gomma.getDiameter());
            preparedStatement.setDouble(7, gomma.getWeight());
            preparedStatement.setString(8, gomma.getSpeed());
            preparedStatement.setString(9, gomma.getSeason());
            preparedStatement.setString(10, gomma.gettypeVehicle());
            preparedStatement.setInt(11, gomma.getQuantity());
            return preparedStatement.execute();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            e.printStackTrace();
            return false;
        }

    }
}
