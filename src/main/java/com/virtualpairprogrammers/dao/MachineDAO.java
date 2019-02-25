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

	private final String QUERY_ALL = "SELECT * FROM macchinari";
	private final String QUERY_INSERT_MACHINE = "INSERT INTO macchinari (nome,modello,id_utente) VALUES (?,?,?)";
	private final String QUERY_DELETE_MACHINE = "DELETE FROM macchinari WHERE id = ? AND id_utente = ?";
	private final String QUERY_UPDATE_MACHINE = "UPDATE macchinari SET nome = ?, modello = ? WHERE id = ? AND id_utente = ?";
	
	
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
	 
	 public void deleteMachine (int id, int id_utente) {
		 
	        Connection connection = ConnectionSingleton.getInstance();
	        try {
	            PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_MACHINE);
	            statement.setInt(1, id);
	            statement.setInt(2, id_utente);
	            
	            statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            GestoreEccezioni.getInstance().gestisciEccezione(e);
	        }

	}
	 
	 public void updateMachine (String nome, String modello, int id, int id_utente) {
		 
	        Connection connection = ConnectionSingleton.getInstance();
	        try {
	            PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_MACHINE);
	            statement.setString(1, nome);
	            statement.setString(2, modello);
	            statement.setInt(3, id);
	            statement.setInt(4, id_utente);
	            
	            statement.executeUpdate();
	        }
	        catch (SQLException e) {
	            GestoreEccezioni.getInstance().gestisciEccezione(e);
	        }

	}
	 
    public List<Machine> getAllMachines () {
        List<Machine> machines = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(QUERY_ALL);
           while (resultSet.next()) {
        	   int id = resultSet.getInt("id");
               String nome = resultSet.getString("nome");
               String modello = resultSet.getString("modello");
               int id_utente = resultSet.getInt("id_utente");
               
               machines.add(new Machine(id, nome, modello, id_utente));
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }
}
