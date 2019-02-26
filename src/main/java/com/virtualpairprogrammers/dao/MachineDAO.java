package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.domain.Machine;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

public class MachineDAO {

	private final String QUERY_ALL = "SELECT * FROM macchinari where id_utente = ?";
	private final String QUERY_INSERT_MACHINE = "INSERT INTO macchinari (nome,modello,id_utente) VALUES (?,?,?)";
	private final String QUERY_DELETE_MACHINE = "DELETE FROM macchinari WHERE id = ?";
	
	 public void insertMachine (String nome, String modello, int id_utente) {
		 
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_MACHINE);
            statement.setString(1, nome);
            statement.setString(2, modello);
            statement.setInt(3, id_utente);
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
        }

	 }
	 
	 public void deleteMachine (int id) {
		 
	        Connection connection = ConnectionSingleton.getInstance();
	        try {
	            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_MACHINE);
	            statement.setInt(1, id);
	            
	            statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            GestoreEccezioni.getInstance().gestisciEccezione(e);
	        }

	}

	 
    public List<Machine> getAllMachines (int id_utente) {
        List<Machine> machines = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        
        try {
        	
           PreparedStatement statement = connection.prepareStatement(QUERY_ALL);
           statement.setInt(1, id_utente);
           
           ResultSet resultSet = statement.executeQuery();
           
           while (resultSet.next()) {
        	   int id = resultSet.getInt("id");
               String nome = resultSet.getString("nome");
               String modello = resultSet.getString("modello");
               
               machines.add(new Machine(id, nome, modello, id_utente));
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }
}
