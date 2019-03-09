package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TimeDTO {
	private String nomeIstruzione;
	private String item;
	private int durataEffettiva;
	private int durataPrevista;
	private String risultato;

}
