package com.virtualpairprogrammers.dao;

import java.sql.*;
import java.util.ArrayList;

import com.virtualpairprogrammers.domain.Task;
import com.virtualpairprogrammers.utils.ConnectionSingleton;
import com.virtualpairprogrammers.utils.GestoreEccezioni;

public class TaskDAO {
	private final String QUERY_INSERT = "insert into tasks(descrizione,id_macchinario,data) values(?,?,?)";
	private final String QUERY_ALL = "select * from tasks where id_macchinario = ? ";
	private final String QUERY_UPDATE = "update tasks set descrizione = ?,data = ? where id = ?";
	private final String QUERY_DELETE = "delete from tasks where id = ?";
	
	public TaskDAO() {
		
	}
	
	public void deleteTask(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public void updateTask(String descrizione, int id) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			
			preparedStatement.setString(1, descrizione);
			preparedStatement.setTimestamp(2, new  Timestamp (System.currentTimeMillis ()));
			preparedStatement.setInt(3, id);
			preparedStatement.executeUpdate();
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}
	
	public boolean insertTask(String descrizione, int macchinario) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			
			preparedStatement.setString(1, descrizione);
			preparedStatement.setInt(2, macchinario);
			preparedStatement.setTimestamp(3, new  Timestamp (System.currentTimeMillis ()));
			return preparedStatement.execute();
			
			
		} catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}
	
	public ArrayList<Task> getAllTasks(int macchinario){
		
		ArrayList<Task> tasks = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL);
			preparedStatement.setInt(1, macchinario);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String descrizione = resultSet.getString("descrizione");
				Timestamp data = resultSet.getTimestamp("data");
				tasks.add(new Task(id,descrizione,macchinario,data));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
}
