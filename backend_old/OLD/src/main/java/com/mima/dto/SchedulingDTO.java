package com.mima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class SchedulingDTO {

	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private int idMachine;
	
}
