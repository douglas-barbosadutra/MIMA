package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.UserConverter;
import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.WBSDAO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.model.WBS;

@Service
public class WBSService {
	
	private WBSDAO wbsDao;
	private ItemService itemService;
	
	@Autowired
	public WBSService(WBSDAO wbsDao, ItemService itemService) {
		this.wbsDao = wbsDao;
		this.itemService = itemService;
	}
	
	public boolean deleteWBS(int id) {
		wbsDao.deleteById(id);
		return true;
	}
	
	public WBSDTO insertWBS(WBSDTO wbsDTO) {
		WBS wbs = WBSConverter.convertToEntity(wbsDTO);
		wbs = wbsDao.save(wbs);
		wbsDao.flush();
		ItemDTO item = new ItemDTO();
		item.setIdWBS(wbs.getId());
		item.setName(wbs.getName());
		itemService.insertItem(item);
		return WBSConverter.convertToDto(wbs);
	}
	
	public List<WBSDTO> showWBS(int idUser){
		UserDTO user = new UserDTO();
		user.setId(idUser);
		List<WBS> wbs = wbsDao.findAllByUser(UserConverter.toEntity(user));
		return WBSConverter.toListDTO(wbs);
	}

}
