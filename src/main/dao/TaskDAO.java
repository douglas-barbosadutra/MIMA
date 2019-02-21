package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.Task;
import java.sql.*;
import java.util.ArrayList;

public class TaskDAO {
	private final String QUERY_INSERT = "insert into tasks(descrizione,id_macchinario,data) values(?,?,?)";
	private final String QUERY_ALL = "select * from tasks where id_macchinario = ? ";
	
	public TaskDAO() {
		
	}
	
	public boolean insertTask(Task task) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			
			String descrizione = task.getDescrizione();
			int macchinario = task.getMacchinario();
			Timestamp data = task.getData();
			
			preparedStatement.setString(1, descrizione);
			preparedStatement.setInt(2, macchinario);
			preparedStatement.setTimestamp(3, data);
			return preparedStatement.execute();
			
			
		} catch(SQLException e) {
			System.out.println("Errore");
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
