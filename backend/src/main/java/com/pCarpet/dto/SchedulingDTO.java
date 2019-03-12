package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class SchedulingDTO {

	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int idMachine;
	
}
