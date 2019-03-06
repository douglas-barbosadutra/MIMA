package com.pCarpet.converter;

import com.pCarpet.dto.OperationSchedulingDTO;
import com.pCarpet.model.OperationScheduling;
import com.pCarpet.model.Scheduling;
import com.pCarpet.model.Task;

public class OperationSchedulingConverter {

	public static OperationScheduling convertToEntity(OperationSchedulingDTO osdto) {
		OperationScheduling os = null;
		if(osdto != null) {
			os = new OperationScheduling();
			os.setId(osdto.getId());
			os.setOrderTask(osdto.getOrder());
			Task task = new Task();
			task.setId(osdto.getIdTask());
			Scheduling scheduling = new Scheduling();
			scheduling.setId(osdto.getIdScheduling());
			os.setTask(task);
			os.setScheduling(scheduling);
		}
		return os;
	}

	public static OperationSchedulingDTO convertToDto(OperationScheduling os) {
		OperationSchedulingDTO osdto = null;
		if(os != null) {
			osdto = new OperationSchedulingDTO();
			osdto.setId(os.getId());
			osdto.setIdScheduling(os.getScheduling().getId());
			osdto.setIdTask(os.getTask().getId());
			osdto.setOrder(os.getOrderTask());
		}
		return osdto;
	}
	
}
