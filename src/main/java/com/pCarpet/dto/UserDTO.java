package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {

	private int id;
	private String username;
	private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int rank;

}
