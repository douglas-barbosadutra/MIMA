package com.pCarpet.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TaskScheduledDTO {
	private int idOperationScheduling;
	private int idTask;
	private String name;
	private List<TaskScheduledDTO> taskScheduledChildren;
}



