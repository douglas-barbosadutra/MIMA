package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.domain.Scheduling;
import com.virtualpairprogrammers.utils.ConnectionSingleton;

public class SchedulingDAO {
	
	private final String QUERY_INSERT = "insert into schedulazione (nome, timestamp_inizio, timestamp_fine, id_macchinario) values (?,?,?,?)";
	private final String QUERY_DELETE = "DELETE FROM schedulazione WHERE id = ?";
	private final String QUERY_ALL_BY_MACCHINARIO = "SELECT * FROM schedulazione WHERE id_macchinario = ?";
	private final String QUERY_SELECT = "SELECT * FROM schedulazione WHERE id = ?";
	private final String QUERY_UPDATE = "UPDATE schedulazione SET timestamp_inizio = ?, timestamp_fine = ? WHERE id = ?";

	public SchedulingDAO() {
		
	}
	
	public void insertScheduling(Scheduling scheduling) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
        	PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setString(1, scheduling.getNome());
            preparedStatement.setTimestamp(2, scheduling.getInizio());
            preparedStatement.setTimestamp(3, scheduling.getFine());
            preparedStatement.setInt(4, scheduling.getIdMacchinario());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void deleteScheduling (int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List<Scheduling> getSchedulingByMachine(int idMacchinario){
		Connection connection = ConnectionSingleton.getInstance();
		List<Scheduling> scheduling= new ArrayList<>();
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(QUERY_ALL_BY_MACCHINARIO);
			preparedstatement.setInt(1, idMacchinario);
			ResultSet resultSet = preparedstatement.executeQuery();
	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	        	String nome = resultSet.getString("nome");
	        	Timestamp inizio = resultSet.getTimestamp("timestamp_inizio");
	        	Timestamp fine = resultSet.getTimestamp("timestamp_fine");
	        	scheduling.add(new Scheduling(id, nome, inizio, fine, idMacchinario));
	        }
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		return scheduling;
	}
	
	public Scheduling getScheduling(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		Scheduling scheduling = null;
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(QUERY_SELECT);
			preparedstatement.setInt(1, id);
			ResultSet resultSet = preparedstatement.executeQuery();
			String nome = resultSet.getString("nome");
        	Timestamp inizio = resultSet.getTimestamp("timestamp_inizio");
        	Timestamp fine = resultSet.getTimestamp("timestamp_fine");
        	int idMacchinario = resultSet.getInt("id_macchinario");
        	scheduling = new Scheduling(id, nome, inizio, fine, idMacchinario);
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return scheduling;
	}
	
	public void updateScheduling(int id, Timestamp inizio, Timestamp fine) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(QUERY_UPDATE);
			preparedstatement.setTimestamp(1, inizio);
			preparedstatement.setTimestamp(2, fine);
			preparedstatement.setInt(3, id);
			preparedstatement.executeUpdate();
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
