package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.virtualpairprogrammers.domain.OperazioneSchedulazione;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

public class OperazioneSchedulazioneDAO {
	
	private final String QUERY_FROM_ID = "SELECT operazioni_schedulazione.* FROM operazioni_schedulazione INNER JOIN schedulazione on schedulazione.id = operazioni_schedulazione.id_schedulazione WHERE schedulazione.id = ?";
	private final String QUERY_INSERT = "INSERT INTO operazioni_schedulazione(id_schedulazione,id_task,ordine) values(?,?,?)";
	private final String QUERY_UPDATE = "UPDATE operazioni_schedulazione SET ordine = ? WHERE id = ?";
	private final String QUERY_DELETE = "DELETE FROM operazioni_schedulazione WHERE id = ?";
	
	public OperazioneSchedulazioneDAO() {
		
	}
	
	public void deleteOperazioneSchedulazione(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public void updateOperazioneSchedulazione(String ordine, int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			
			preparedStatement.setString(1, ordine);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public boolean insertOperazioneSchedulazione(int id_schedulazione, int id_task, int ordine) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			
			preparedStatement.setInt(1, id_schedulazione);
			preparedStatement.setInt(2, id_task);
			preparedStatement.setInt(3, ordine);
			return preparedStatement.execute();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}
	
	public ArrayList<OperazioneSchedulazione> getOperazioniSchedulazioneByIdSchedulazione(int idSchedulazione) {
		
		ArrayList<OperazioneSchedulazione> oss = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FROM_ID);
			preparedStatement.setInt(1, idSchedulazione);
	        ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				int id_schedulazione = resultSet.getInt("id_schedulazione");
				int id_task = resultSet.getInt("id_task");
				int ordine = resultSet.getInt("ordine");
				
				oss.add(new OperazioneSchedulazione(id,id_task,id_schedulazione,ordine));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return oss;
	}
	
}
