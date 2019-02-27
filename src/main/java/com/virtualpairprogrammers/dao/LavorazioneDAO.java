package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.virtualpairprogrammers.domain.Lavorazione;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

public class LavorazioneDAO {

	private final String QUERY_ALL_FROM_ID = "SELECT lavorazioni.* FROM ((lavorazioni INNER JOIN istruzioni ON istruzioni.id = lavorazioni.id_istruzione) INNER JOIN tasks ON tasks.id = istruzioni.id_tasks) WHERE tasks.id = ?";
	private final String QUERY_INSERT = "INSERT INTO lavorazioni(id_item,id_istruzione,durata) values(?,?,?)";
	private final String QUERY_UPDATE = "UPDATE lavorazioni SET durata = ? WHERE id = ?";
	private final String QUERY_DELETE = "DELETE FROM lavorazioni WHERE id = ?";
	
	public LavorazioneDAO() {
		
	}
	
	public void deleteLavorazione(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public void updateLavorazione(int durata, int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			
			preparedStatement.setInt(1, durata);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public boolean insertLavorazione(int idItem, int idIstruzione, int durata) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			
			preparedStatement.setInt(1, idItem);
			preparedStatement.setInt(2, idIstruzione);
			preparedStatement.setInt(3, durata);
			return preparedStatement.execute();
			
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}
	
	public ArrayList<Lavorazione> getAllLavorazioni(int idTask){
		
		ArrayList<Lavorazione> lavorazioni = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_FROM_ID);
			preparedStatement.setInt(1, idTask);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int idItem = resultSet.getInt("id_item");
				int idIstruzione = resultSet.getInt("id_istruzione");
				int durata = resultSet.getInt("durata");
				lavorazioni.add(new Lavorazione(id,idItem,idIstruzione,durata));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lavorazioni;
	}

	
}
