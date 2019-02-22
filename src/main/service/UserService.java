package main.service;

import java.util.ArrayList;
import java.util.List;

import converter.UserConverter;
import dto.UserDTO;
import main.dao.UserDAO;
import main.model.User;

public class UserService {

    private UserDAO userDAO;
    private static User user = null;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void insertUser (String username, String password) {
       this.userDAO.insertUser(username, password);
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
