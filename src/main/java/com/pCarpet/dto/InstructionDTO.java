package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InstructionDTO {
	private int id;
	private int durata;
	private String codice;
	private String nomeIstruzione;
	private int idTask;
}
