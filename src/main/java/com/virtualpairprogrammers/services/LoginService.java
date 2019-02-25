package com.virtualpairprogrammers.services;

import com.virtualpairprogrammers.dao.LoginDAO;
import com.virtualpairprogrammers.domain.User;

public class LoginService {

    private LoginDAO loginDAO;

    public LoginService() {
        this.loginDAO = new LoginDAO();
    }

    public User login (String username, String password) {
        return this.loginDAO.login(username, password);
    }
    
}
