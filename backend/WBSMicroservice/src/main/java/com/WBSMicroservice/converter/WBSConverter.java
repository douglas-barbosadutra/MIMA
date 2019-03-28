package com.WBSMicroservice.converter;

import java.util.ArrayList;
import java.util.List;

import com.WBSMicroservice.dto.WBSDTO;
import com.WBSMicroservice.model.WBS;

public class WBSConverter {
	
	public static WBSDTO convertToDto(WBS wbs) {
		
		WBSDTO wbsDTO = null;
		
		if(wbs != null) {
			wbsDTO = new WBSDTO();
			
			wbsDTO.setId(wbs.getId());
			wbsDTO.setName(wbs.getName());
			wbsDTO.setIdUser(wbs.getIdUser());
			
		}
		return wbsDTO;
	}
	
	public static WBS convertToEntity(WBSDTO wbsDTO) {
		
		WBS wbs= null;
		
		if(wbsDTO != null) {
			
			wbs = new WBS();
			
			wbs.setId(wbsDTO.getId());
			wbs.setName(wbsDTO.getName());
			wbs.setIdUser(wbsDTO.getIdUser());
			
		}
		return wbs;
	}

	public static List<WBSDTO> toListDTO(List<WBS> list){
		List<WBSDTO> listWbsDto = new ArrayList<>();
		if(list != null) {
			for(WBS wbs: list) {
				listWbsDto.add(WBSConverter.convertToDto(wbs));
			}
		}
		return listWbsDto;
	}
}
