package com.pCarpet.converter;

import com.pCarpet.dto.LoginDTO;
import com.pCarpet.model.User;

public class LoginConverter {

	public static LoginDTO convertToDTO(User user) {
		LoginDTO login = null;
		if(user != null) {
		login = new LoginDTO();
			login.setUsername(user.getUsername());
			login.setPassword(user.getPassword());
		}
		return login;
	}
	
	public static User convertToEntity(LoginDTO login) {
		User user = null;
		if(login != null) {
			user = new User();
			user.setUsername(login.getUsername());
			user.setPassword(login.getPassword());
		}
		return user;
	}
}
