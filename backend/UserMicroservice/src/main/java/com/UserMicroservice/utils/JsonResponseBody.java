package com.UserMicroservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonResponseBody {
	
	private int server;
	private Object response;
}
