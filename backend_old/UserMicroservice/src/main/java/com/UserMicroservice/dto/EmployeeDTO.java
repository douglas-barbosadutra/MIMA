package com.UserMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
	
	private int id;			//chiave della tabella employee
	private UserDTO user;		//dto con le informazioni delll'employee
	private int idTask;
	private int idBusinessOwner;	//id del business owner a cui fa riferimento il dipendente
}
