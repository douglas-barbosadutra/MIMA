package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OperationSchedulingDTO {

	private int id;
	private int id_task;
	private int id_schedulazione;
	private int ordine;
	
}
