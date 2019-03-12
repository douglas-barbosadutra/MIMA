package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="/showWbs", method= RequestMethod.GET)
	public List<WBSDTO> showWbs(@RequestParam(value="idUser") int idUser) {		
		return wbsService.showWBS(idUser);
	}
	
	@RequestMapping(value="/deleteWbs", method= RequestMethod.DELETE)
	public boolean deleteWbs(@RequestParam(value="idWbs") int idWbs) {		
		return wbsService.deleteWBS(idWbs);
	}
	

}
