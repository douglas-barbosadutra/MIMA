package com.pCarpet.services;

import com.pCarpet.dao.UserDAO;
import com.pCarpet.model.User;

public class LoginService {

    private UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        return this.userDAO.findUserByUsernameAndPassword(username, password);
    }
}
