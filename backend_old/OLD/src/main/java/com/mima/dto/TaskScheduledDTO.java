package com.mima.dto;

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
	private int id;
	private int idTask;
	private boolean hasFather;
	private String name;
	private int idScheduling;
	private List<TaskScheduledDTO> taskScheduledChildren;
}



