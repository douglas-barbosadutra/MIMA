package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.dto.UserDTO;

public class UserConverter{

	public static User convertToUser(UserDTO userdto) {
		return (new User(userdto.getID(), "", "", userdto.getName(), userdto.getSurname(), userdto.getEmail(), userdto.getPhone(), userdto.getRank()));
	}

	public static UserDTO convertToDto(User user) {
		return (new UserDTO(user.getID(), user.getName(), user.getSurname(), user.getEmail(), user.getPhone(), user.getRank()));
	}
}

