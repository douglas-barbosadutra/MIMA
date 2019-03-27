package com.UserMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParamDTO {
	
	private String jwt;
	private Object param;
}
