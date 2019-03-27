package com.MachineMicroservice.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.MachineMicroservice.dto.MachineDTO;
import com.MachineMicroservice.dto.ParamDTO;
import com.MachineMicroservice.service.MachineService;
import com.MachineMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Machine")
public class MachineController {
	
	@Autowired
	private MachineService machineService;
	
	public MachineController() {	}
	
	@PostMapping("/insertMachine")
	public ResponseEntity<MachineDTO> insertMachine(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		int idUser;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				idUser = this.getIdUserFromJwt(paramDTO.getJwt());
				
				LinkedHashMap machine = (LinkedHashMap) paramDTO.getParam();
				MachineDTO machineDTO = new MachineDTO(0,machine.get("name").toString(), machine.get("model").toString(), idUser);
				MachineDTO machineInsert = machineService.insertMachine(machineDTO);
				
				if(machineInsert != null)
					return ResponseEntity.status(HttpStatus.OK).body(machineInsert);
				else
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}
	
	@PostMapping("/deleteMachine")
	public ResponseEntity<Boolean> deleteMachine(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				int idMachine = (int) paramDTO.getParam();
				return ResponseEntity.status(HttpStatus.OK).body(machineService.deleteMachine(idMachine));
			}
			
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
	}
	
	@GetMapping("/showMachine")
	public ResponseEntity<List<MachineDTO>> showMachine(@RequestParam(value="jwt") String jwt) {
		
		int rank;
		int idUser;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0) {
				
				idUser = this.getIdUserFromJwt(jwt);
				return ResponseEntity.status(HttpStatus.OK).body(machineService.getAllMachinesByIdUser(idUser));
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

	}
	
	private int getRankFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int rank = Integer.parseInt(data.get("scope").toString());
		
		return rank;
	}
	
	private int getIdUserFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int idUser = Integer.parseInt(data.get("subject").toString());
		
		return idUser;
	}
}
