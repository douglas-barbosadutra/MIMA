package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class SchedulingDTO {

	private int id;
	private String name;
	private Timestamp startDate;
	private Timestamp endDate;
	private int idMachine;
	
}
