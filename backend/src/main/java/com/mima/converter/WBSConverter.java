package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.WBSDTO;
import com.pCarpet.model.User;
import com.pCarpet.model.WBS;

public class WBSConverter {
	
	public static WBSDTO convertToDto(WBS wbs) {
		
		WBSDTO wbsDTO = null;
		
		if(wbs != null) {
			wbsDTO = new WBSDTO();
			
			wbsDTO.setId(wbs.getId());
			wbsDTO.setName(wbs.getName());
			wbsDTO.setIdUser(wbs.getUser().getId());
			
		}
		return wbsDTO;
	}
	
	public static WBS convertToEntity(WBSDTO wbsDTO) {
		
		WBS wbs= null;
		
		if(wbsDTO != null) {
			
			wbs = new WBS();
			
			wbs.setId(wbsDTO.getId());
			wbs.setName(wbsDTO.getName());
			User user = new User();
			user.setId(wbsDTO.getIdUser());
			wbs.setUser(user);
			
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
