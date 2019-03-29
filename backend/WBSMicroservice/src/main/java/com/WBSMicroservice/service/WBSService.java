package com.WBSMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WBSMicroservice.converter.WBSConverter;
import com.WBSMicroservice.dao.WBSDAO;
import com.WBSMicroservice.dto.ItemDTO;
import com.WBSMicroservice.dto.WBSDTO;
import com.WBSMicroservice.model.WBS;

@Service
public class WBSService {
	
	@Autowired
	private WBSDAO wbsDao;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	public WBSService() {}
	
	public boolean deleteWBS(int id) {
		wbsDao.deleteById(id);
		return true;
	}
	
	public WBSDTO insertWBS(WBSDTO wbsDTO) { 
		WBS wbs = WBSConverter.convertToEntity(wbsDTO);		
		wbs = wbsDao.saveAndFlush(wbs);
		ItemDTO item = new ItemDTO();
		item.setIdWBS(wbs.getId());
		item.setName(wbs.getName());
		item = itemService.insertItem(item);
		return WBSConverter.convertToDto(wbs);
	}
	
	public List<WBSDTO> showWBS(int idUser){
		List<WBS> wbs = wbsDao.findAllByIdUser(idUser);
		return WBSConverter.toListDTO(wbs);
	}

}
