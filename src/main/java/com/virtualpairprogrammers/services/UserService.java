package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.UserConverter;
import com.virtualpairprogrammers.dao.UserDAO;
import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.dto.UserDTO;

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

    public void updateUser(String name, String surname, String email, String phone, String username, String password) {
    	
    	userDAO.updateUser(name, surname, email, phone, username, password, user.getID());
    }
    
	public static void setUserSession(User u) {
		user = u;
	}
	
	public static User getUserSession() {
		return user;
	}
	
}
