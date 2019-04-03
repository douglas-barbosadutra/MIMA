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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MachineMicroservice.dto.ParamDTO;
import com.MachineMicroservice.dto.SchedulingDTO;
import com.MachineMicroservice.service.SchedulingService;
import com.MachineMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Scheduling")
public class SchedulingController {
	
	@Autowired
	private SchedulingService schedulingService;

	public SchedulingController() {	}

	@PostMapping("/insertScheduling")
	public ResponseEntity<SchedulingDTO> insertScheduling(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				LinkedHashMap scheduling = (LinkedHashMap) paramDTO.getParam();
				SchedulingDTO schedulingDTO = new SchedulingDTO();
				schedulingDTO.setId(0);
				schedulingDTO.setName(scheduling.get("name").toString());
				schedulingDTO.setStartDate(scheduling.get("startDate").toString());
				schedulingDTO.setEndDate(scheduling.get("endDate").toString());
				schedulingDTO.setIdMachine(Integer.parseInt(scheduling.get("idMachine").toString()));
				
				return ResponseEntity.status(HttpStatus.OK).body(schedulingService.insertScheduling(schedulingDTO));
			}
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}

	@DeleteMapping("/deleteScheduling")
	public ResponseEntity<Boolean> deleteScheduling(@RequestParam(value="idScheduling") int idScheduling, @RequestParam(value="jwt") String jwt) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0) 
				return ResponseEntity.status(HttpStatus.OK).body(schedulingService.deleteScheduling(idScheduling));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}

	@GetMapping("/showScheduling")
	public ResponseEntity<List<SchedulingDTO>> showScheduling(@RequestParam(value="idMachine") int idMachine, @RequestParam(value="jwt") String jwt) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(schedulingService.getAllScheduling(idMachine));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}
	
	@PutMapping("/updateScheduling")
	public ResponseEntity<SchedulingDTO> updateScheduling(@RequestBody ParamDTO paramDTO) {
		System.out.println(paramDTO.getParam());
		int rank;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				LinkedHashMap scheduling = (LinkedHashMap) paramDTO.getParam();
				SchedulingDTO schedulingDTO = new SchedulingDTO();
				schedulingDTO.setId(Integer.parseInt(scheduling.get("id").toString()));
				schedulingDTO.setName(scheduling.get("name").toString());
				schedulingDTO.setStartDate(scheduling.get("startDate").toString());
				schedulingDTO.setEndDate(scheduling.get("endDate").toString());
				schedulingDTO.setIdMachine(Integer.parseInt(scheduling.get("idMachine").toString()));
				
				return ResponseEntity.status(HttpStatus.OK).body(schedulingService.insertScheduling(schedulingDTO));
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
}
