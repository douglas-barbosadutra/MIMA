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
	private int idTask;
	private String name;
	private int order;
	private int idOperationScheduling;
}



