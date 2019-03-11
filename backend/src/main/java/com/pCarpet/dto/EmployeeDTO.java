package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
	public int id;			//chiave della tabella employee
	private int idUser;		//id che identifica l'employee nella tabella user
	private int idTask;
	private String name;
	private int idBusinessOwner;	//id del business owner a cui fa riferimento il dipendente
}
