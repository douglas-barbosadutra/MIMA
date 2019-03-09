package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ManufacturingDTO {

	private int id;
	private int durata;
	private int item;
	private int istruzione;	
}
