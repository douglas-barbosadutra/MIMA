package com.pCarpet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;

@Service
public class UserService {

	private UserDAO userDAO;
	private static User user = null;

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void deleteUser(int id) {
		this.userDAO.deleteById(id);
	}

	public void updateUser(UserDTO userDTO) {
		// userDAO.updateUser(name, surname, email, phone, username, password,
		// user.getID());
	}

	public static void setUserSession(User u) {
		user = u;
	}

	public static User getUserSession() {
		return user;
	}
}
