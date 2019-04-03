package com.MachineMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MachineDTO {
	
	private int id;
	private String name;
	private String model;
	private int idUser;
}
