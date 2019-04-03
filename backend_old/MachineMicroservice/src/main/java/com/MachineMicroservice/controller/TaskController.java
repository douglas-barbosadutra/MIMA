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

import com.MachineMicroservice.dto.ParamDTO;
import com.MachineMicroservice.dto.TaskDTO;
import com.MachineMicroservice.service.TaskService;
import com.MachineMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	public TaskController() {	}
	
	@PostMapping("/insertTask")
	public ResponseEntity<TaskDTO> insertTask(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				LinkedHashMap task = (LinkedHashMap) paramDTO.getParam();
				TaskDTO taskDTO = new TaskDTO(0,task.get("description").toString(), Integer.parseInt(task.get("idMachine").toString()));
				return ResponseEntity.status(HttpStatus.OK).body(taskService.insertTask(taskDTO));
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}
	
	@DeleteMapping("/deleteTask")
	public ResponseEntity<Boolean> deleteTask(@RequestParam(value="idTask") int idTask, @RequestParam(value="jwt") String jwt) {	
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTask(idTask));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		
	}
	
	@GetMapping("/showTask")
	public ResponseEntity<List<TaskDTO>> showTask(@RequestParam(value="idMachine") int idMachine, @RequestParam(value="jwt") String jwt) {
		
		int rank;
		
		try {
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0) 
				return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(idMachine));
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
