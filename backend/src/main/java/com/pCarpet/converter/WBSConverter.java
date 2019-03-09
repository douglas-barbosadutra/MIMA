package com.pCarpet.converter;

import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.WBS;
import com.pCarpet.services.UserService;

public class WBSConverter {
	
	public static WBSDTO convertToDto(WBS wbs) {
		
		WBSDTO wbsDTO = null;
		
		if(wbs != null) {
			wbsDTO = new WBSDTO();
			
			wbsDTO.setId(wbs.getId());
			wbsDTO.setName(wbs.getName());
			
		}
		return wbsDTO;
	}
	
	public static WBS convertToEntity(WBSDTO wbsDTO) {
		
		WBS wbs= null;
		
		if(wbsDTO != null) {
			
			wbs = new WBS();
			
			wbs.setId(wbsDTO.getId());
			wbs.setName(wbsDTO.getName());
			wbs.setUser(UserService.getUserSession());
			
		}
		return wbs;
	}

}
