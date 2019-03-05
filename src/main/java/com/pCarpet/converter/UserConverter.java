package com.pCarpet.converter;

import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;

public class UserConverter{

	public static UserDTO toDTO(User user) {
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setEmail(user.getEmail());
			userDTO.setRank(user.getRankUser());
			userDTO.setSurname(user.getSurname());
			userDTO.setPhone(user.getPhone());
		}
		return userDTO;
	}

	public static User toEntity(UserDTO userDTO) {
		User user = null;
		if (userDTO != null) {
			user = new User();
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			user.setRankUser(userDTO.getRank());
			user.setSurname(userDTO.getSurname());
			user.setPhone(userDTO.getPhone());
		}
		return user;
	}
	
}

