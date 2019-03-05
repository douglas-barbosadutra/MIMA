package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TaskScheduledDTO {
	private int id;
	private String descrizione;
	private int ordine;
	private int idOperazioneSchedulazione;
	
}



