package com.pCarpet.converter;

import com.pCarpet.dto.OperationSchedulingDTO;
import com.pCarpet.model.OperationScheduling;

public class OperationSchedulingConverter {

	public static OperationScheduling convertToEntity(OperationSchedulingDTO osdto) {
		OperationScheduling os = null;
		if(osdto != null) {
			os = new OperationScheduling();
			os.setId(osdto.getId());
			os.setOrder(osdto.getOrdine());
		}
		return os;
	}

	public static OperationSchedulingDTO convertToDto(OperationScheduling os) {
		OperationSchedulingDTO osdto = null;
		if(os != null) {
			osdto = new OperationSchedulingDTO();
			osdto.setId(os.getId());
			osdto.setId_schedulazione(os.getScheduling().getId());
			osdto.setId_task(os.getTask().getId());
			osdto.setOrdine(os.getOrder());
		}
		return osdto;
	}
	
}
