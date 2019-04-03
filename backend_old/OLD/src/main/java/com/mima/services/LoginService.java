package com.mima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.UserConverter;
import com.mima.dao.UserDAO;
import com.mima.dto.UserDTO;

@Service
public class LoginService {

	@Autowired
    private UserDAO userDAO;

    public LoginService() {    }

    public UserDTO login(String username, String password) {
        return UserConverter.toDTO(this.userDAO.findUserByUsernameAndPassword(username, password));
    }
}
