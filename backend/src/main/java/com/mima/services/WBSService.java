package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.UserConverter;
import com.mima.converter.WBSConverter;
import com.mima.dao.WBSDAO;
import com.mima.dto.ItemDTO;
import com.mima.dto.UserDTO;
import com.mima.dto.WBSDTO;
import com.mima.model.WBS;

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
		UserDTO user = new UserDTO();
		user.setId(idUser);
		List<WBS> wbs = wbsDao.findAllByUser(UserConverter.toEntity(user));
		return WBSConverter.toListDTO(wbs);
	}

}
