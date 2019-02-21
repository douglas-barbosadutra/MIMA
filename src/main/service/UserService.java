package main.service;

import java.util.List;

import main.dao.UserDAO;
import main.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void insertUser (String username, String password) {
       this.userDAO.insertUser(username, password);
    }
    
    public void deleteUser (int id) {
        this.userDAO.deleteUser(id);
    }
    
    public List<User> getAllUsers() {
    	return this.userDAO.getAllUsers();
    }
	
}
