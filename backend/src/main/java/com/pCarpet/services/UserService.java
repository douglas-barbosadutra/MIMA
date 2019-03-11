package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;
import com.pCarpet.converter.UserConverter;


@Service
public class UserService {

	private UserDAO userDAO;
	
	private static User user = null;
	public static int idMacchinario = 0;
	public static int idTask = 0;
	public static int idScheduling = 0;

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean deleteUser(int id) {
		this.userDAO.deleteById(id);
		return true;
	}

	public UserDTO insertUser(UserDTO userDTO) {
		User user = UserConverter.toEntity(userDTO);
		userDAO.saveAndFlush(user);
		return UserConverter.toDTO(user);
	}
	
	public List<UserDTO> getAllUsers(){
		return UserConverter.toListDTO(userDAO.findAll());
	}
	
	public User findUserById(int id) {
		return userDAO.findUserById(id);
	}

	public UserDTO getUserByUsername(String username) {
		return UserConverter.toDTO(userDAO.findUserByUsername(username));
	}
	
	public static void setUserSession(User u) {
		user = u;
	}

	public static User getUserSession() {
		return user;
	}
	
}
