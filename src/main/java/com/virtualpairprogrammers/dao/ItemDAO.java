package com.virtualpairprogrammers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.virtualpairprogrammers.domain.Item;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

public class ItemDAO {
	
	private final String QUERY_ALL = "SELECT * FROM items";
	private final String QUERY_INSERT = "INSERT INTO items(descrizione) values(?)";
	private final String QUERY_UPDATE = "UPDATE items SET descrizione = ? WHERE id = ?";
	private final String QUERY_DELETE = "DELETE FROM items WHERE id = ?";
	
	public ItemDAO() {
		
	}
	
	public void deleteItem(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public void updateItem(String descrizione, int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			
			preparedStatement.setString(1, descrizione);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public boolean insertItem(String descrizione) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			
			preparedStatement.setString(1, descrizione);
			return preparedStatement.execute();
			
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}
	
	public ArrayList<Item> getAllItems(){
		
		ArrayList<Item> items = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String descrizione = resultSet.getString("descrizione");
				items.add(new Item(id,descrizione));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}

}
