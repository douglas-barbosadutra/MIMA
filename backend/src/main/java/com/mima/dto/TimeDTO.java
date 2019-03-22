package com.mima.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TimeDTO {
	private String nameInstruction;
	private String item;
	private int effectiveDuration;
	private int expectedDuration;
	private String result;

}
