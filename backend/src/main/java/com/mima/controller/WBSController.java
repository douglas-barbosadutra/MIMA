package com.mima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mima.dto.WBSDTO;
import com.mima.services.WBSService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/WBS")
public class WBSController {
	
	@Autowired
	private WBSService wbsService;
	
	public WBSController() {	}
	
	@PostMapping("/insertWbs")
	public ResponseEntity<WBSDTO> insertWbs(@RequestBody WBSDTO wbsDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(wbsService.insertWBS(wbsDTO));
	}
	
	@GetMapping("/showWbs")
	public ResponseEntity<List<WBSDTO>> showWbs(@RequestParam(value="idUser") int idUser) {		
		return ResponseEntity.status(HttpStatus.OK).body(wbsService.showWBS(idUser));
	}
	
	@DeleteMapping("/deleteWbs")
	public ResponseEntity<Boolean> deleteWbs(@RequestParam(value="idWbs") int idWbs) {		
		return ResponseEntity.status(HttpStatus.OK).body(wbsService.deleteWBS(idWbs));
	}
	

}
