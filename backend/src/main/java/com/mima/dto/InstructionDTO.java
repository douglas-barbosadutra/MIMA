package com.pCarpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InstructionDTO {
	private int id;
	private int duration;
	private String codex;
	private String nameInstruction;
	private int idTask;
}
