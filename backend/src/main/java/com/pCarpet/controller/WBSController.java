package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.UserDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.services.WBSService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/WBS")
public class WBSController {
	
	private WBSService wbsService;
	
	@Autowired
	public WBSController(WBSService wbsService) {
		this.wbsService = wbsService;
	}
	
	@RequestMapping(value="/insertWbs", method= RequestMethod.POST)
	public WBSDTO insertWbs(@RequestBody WBSDTO wbsDTO) {
		return wbsService.insertWBS(wbsDTO);
	}
	
	@RequestMapping(value="/showWbs", method= RequestMethod.POST)
	public List<WBSDTO> showWbs(@RequestBody UserDTO user) {		
		return wbsService.showWBS(user);
	}
	
	@RequestMapping(value="/deleteWbs", method= RequestMethod.POST)
	public boolean deleteWbs(@RequestBody WBSDTO wbsDTO) {		
		return wbsService.deleteWBS(wbsDTO.getId());
	}
	

}
