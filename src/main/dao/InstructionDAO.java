package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.Instruction;

import java.sql.*;
import java.util.*;

public class InstructionDAO {
	
	private final String QUERY_SELECT = "select * from istruzioni where id_tasks = ? ";
	private final String QUERY_INSERT = "insert into istruzioni (id_tasks, nome_istruzioni, durata) values (?,?,?)";
	private final String QUERY_DELETE = "DELETE FROM istruzioni WHERE id_tasks = ? && nome_istruzioni = ?";
	private final String QUERY_UPDATE = "UPDATE istruzioni SET durata = ? WHERE id_tasks = ? AND nome_istruzioni = ?";
	
	public InstructionDAO() {
		
	}
	
	public List<Instruction> getAllIstruzioni(int idTask){
		List<Instruction> istruzioni = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(QUERY_SELECT);
			preparedstatement.setInt(1, idTask);
	        ResultSet resultSet = preparedstatement.executeQuery();
	        while (resultSet.next()) {
	            String nome = resultSet.getString("nome_istruzioni");
	            int durata = resultSet.getInt("durata");
	            istruzioni.add(new Instruction(nome, durata, idTask));
	           }
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		return istruzioni;
	}
	
	public boolean insertIstruzione(Instruction istruzione) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, istruzione.getIdTask());
            preparedStatement.setString(2, istruzione.getNome());
            preparedStatement.setInt(3, istruzione.getDurata());
            return preparedStatement.execute();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }

    }
	
	public boolean deleteIstruzione(String nomeIstruzione, int idTask) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, idTask);
            preparedStatement.setString(2, nomeIstruzione);
            return preparedStatement.execute();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }
	
	public boolean modifyIstruzione(Instruction istruzione) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			preparedStatement.setInt(1, istruzione.getDurata());
			preparedStatement.setInt(2, istruzione.getIdTask());
			preparedStatement.setString(3, istruzione.getNome());
			preparedStatement.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}
}
