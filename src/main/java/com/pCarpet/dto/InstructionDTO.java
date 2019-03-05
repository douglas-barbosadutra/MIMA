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
	private String nomeIstruzione;
	private int durata;
	private int id;
	private String codice;
	
}
