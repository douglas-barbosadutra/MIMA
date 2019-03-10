package com.pCarpet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.UserConverter;
import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;

@Service
public class LoginService {

    private UserDAO userDAO;

    @Autowired
    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDTO login(String username, String password) {
        return UserConverter.toDTO(this.userDAO.findUserByUsernameAndPassword(username, password));
    }
}
