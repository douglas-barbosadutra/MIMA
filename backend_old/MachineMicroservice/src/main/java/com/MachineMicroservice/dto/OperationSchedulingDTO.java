package com.MachineMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationSchedulingDTO {
	
	int idFather;
	int idChild;
	int idTask;	//questo campo, se diverso da zero, indicherà che bisogna inserire un nuovo task nello scheduling. Se invece è zero indica che è stata soltanto aggiunta una relazione tra due task già schedulati
	int idScheduling;
}
