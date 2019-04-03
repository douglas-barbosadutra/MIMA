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

import com.MachineMicroservice.dto.OperationSchedulingDTO;
import com.MachineMicroservice.dto.ParamDTO;
import com.MachineMicroservice.dto.TaskScheduledDTO;
import com.MachineMicroservice.service.TaskScheduledService;
import com.MachineMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/TaskScheduled")
public class TaskScheduledController {
	
	@Autowired
	TaskScheduledService taskScheduledService;

	public TaskScheduledController() {
	}

	@PostMapping("/insertOperationScheduling")
	public ResponseEntity<Boolean> insertOperazionScheduling(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		
		try {
			
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				LinkedHashMap os = (LinkedHashMap) paramDTO.getParam();
				OperationSchedulingDTO osDTO = new OperationSchedulingDTO();
				osDTO.setIdChild(Integer.parseInt(os.get("idChild").toString()));
				osDTO.setIdFather(Integer.parseInt(os.get("idFather").toString()));
				osDTO.setIdScheduling(Integer.parseInt(os.get("idScheduling").toString()));
				osDTO.setIdTask(Integer.parseInt(os.get("idTask").toString()));
				
				taskScheduledService.insertScheduledRelations(osDTO);
				return ResponseEntity.status(HttpStatus.OK).body(true);
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		
		
		
	}

	@PostMapping("/insertTaskScheduled")
	public ResponseEntity<TaskScheduledDTO> insertTaskScheduled(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				LinkedHashMap ts = (LinkedHashMap) paramDTO.getParam();
				TaskScheduledDTO tsDTO = new TaskScheduledDTO();
				tsDTO.setId(0);
				tsDTO.setHasFather(false);
				tsDTO.setIdScheduling(Integer.parseInt(ts.get("idScheduling").toString()));
				tsDTO.setIdTask(Integer.parseInt(ts.get("idTask").toString()));
				tsDTO.setName(ts.get("name").toString());
				tsDTO.setTaskScheduledChildren(null);
				
				return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.insertTaskScheduled(tsDTO));
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}

	@DeleteMapping("/deleteTaskScheduled")
	public ResponseEntity<Boolean> deleteTaskScheduled(@RequestParam(value = "idTaskScheduled") int idTaskScheduled, @RequestParam(value="jwt") String jwt) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.deleteTaskScheduled(idTaskScheduled));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		
	}

	@GetMapping("/showTaskScheduled")
	public ResponseEntity<List<TaskScheduledDTO>> showTaskScheduled(@RequestParam(value = "idScheduling") int idScheduling, @RequestParam(value="jwt") String jwt) {
		System.out.println("ciao");
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0) {
				
				List<TaskScheduledDTO> temp = taskScheduledService.getTaskScheduling(idScheduling);
				System.out.println(temp);
				return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.getTaskScheduling(idScheduling));
			}
				
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}

	@GetMapping("/showOperationScheduling")
	public ResponseEntity<List<OperationSchedulingDTO>> showOperationScheduling(@RequestParam(value = "idScheduling") int idScheduling, @RequestParam(value="jwt") String jwt) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.getOperationScheduling(idScheduling));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}

	@GetMapping("/insertOutput")
	public ResponseEntity<Boolean> insertOutput(@RequestParam(value = "idItem") int idItem, @RequestParam(value = "idOperationScheduling") int idOperationScheduling, @RequestParam(value="jwt") String jwt) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.insertOutput(idItem, idOperationScheduling));
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
