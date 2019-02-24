package main.service;

import java.util.ArrayList;
import java.util.List;

import main.converter.UserConverter;
import main.dao.UserDAO;
import main.dto.UserDTO;
import main.model.User;

public class UserService {

    private UserDAO userDAO;
    private static User user = null;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void insertUser (String username, String password, String name, String surname, String email, String phone, int rank) {
       this.userDAO.insertUser(username, password, name, surname, email, phone, rank);
    }
    
    public void deleteUser (int id) {
        this.userDAO.deleteUser(id);
    }
    
    public List<UserDTO> getAllUsers() {
    	
    	List<User> utenti = this.userDAO.getAllUsers();
    	List<UserDTO> utentidto = new ArrayList<>();
    	
		for(User utente: utenti) {
			utentidto.add(UserConverter.convertToDto(utente));
		}
		return utentidto;
    }
    
	public static void setUserSession(User u) {
		user = u;
	}
	
	public static User getUserSession() {
		return user;
	}
	
}
