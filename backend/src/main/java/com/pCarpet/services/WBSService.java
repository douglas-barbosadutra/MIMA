package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.UserConverter;
import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.WBSDAO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.model.WBS;

@Service
public class WBSService {
	
	private WBSDAO wbsDao;
	
	@Autowired
	public WBSService(WBSDAO wbsDao) {
		this.wbsDao = wbsDao;
	}
	
	public boolean deleteWBS(int id) {
		wbsDao.deleteById(id);
		return true;
	}
	
	public WBSDTO insertWBS(WBSDTO wbsDTO) {
		WBS wbs = WBSConverter.convertToEntity(wbsDTO);
		wbsDao.save(wbs);
		return WBSConverter.convertToDto(wbs);
	}
	
	public List<WBSDTO> showWBS(UserDTO user){
		List<WBS> wbs = wbsDao.findAllByUser(UserConverter.toEntity(user));
		return WBSConverter.toListDTO(wbs);
	}

}
