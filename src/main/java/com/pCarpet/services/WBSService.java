package com.pCarpet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.WBSDAO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.model.WBS;

@Service
public class WBSService {
	
	private WBSDAO wbsDao;
	
	@Autowired
	public WBSService(WBSDAO wbsDao) {
		this.wbsDao = wbsDao;
	}
	
	public void deleteWBS(int id) {
		wbsDao.deleteById(id);
	}
	
	public void insertWBS(WBSDTO wbsDTO) {
		wbsDao.save(WBSConverter.convertToEntity(wbsDTO));
	}
	
	public List<WBSDTO> showWBS(){
		
		List<WBS> wbs = wbsDao.findAllByUser(UserService.getUserSession());
		List<WBSDTO> wbsDTO = new ArrayList<>();
		
		for(WBS w : wbs)
			wbsDTO.add(WBSConverter.convertToDto(w));
		
		return wbsDTO;
		
	}
	

}
