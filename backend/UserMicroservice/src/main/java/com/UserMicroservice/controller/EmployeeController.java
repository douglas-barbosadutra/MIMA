package com.UserMicroservice.controller;

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

import com.UserMicroservice.dto.EmployeeDTO;
import com.UserMicroservice.dto.ParamDTO;
import com.UserMicroservice.dto.UserDTO;
import com.UserMicroservice.service.EmployeeService;
import com.UserMicroservice.service.UserService;
import com.UserMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	
	public EmployeeController() {}
	
	@PostMapping("/insertEmployee")
	public ResponseEntity<EmployeeDTO> insertEmployee(@RequestBody ParamDTO paramDTO) {
		System.out.println(paramDTO);
		int rank;
		int idUser;
		
		try {
			
			rank = this.getRankFromJwt(paramDTO.getJwt());
			idUser = this.getIdUserFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				LinkedHashMap employee = (LinkedHashMap) paramDTO.getParam();
				LinkedHashMap user = (LinkedHashMap) employee.get("user");
				
				UserDTO userDTO = new UserDTO(0, user.get("username").toString(), user.get("password").toString(), user.get("name").toString(), user.get("surname").toString(), user.get("email").toString(), user.get("phone").toString(), Integer.parseInt(user.get("rank").toString()));
				EmployeeDTO employeeDTO = new EmployeeDTO(0,userDTO,0,idUser);
				
				userDTO.setRank(2);
				userService.insertUser(userDTO);
				
				userDTO = userService.getUserByUsername(userDTO.getUsername());
				employeeDTO.setUser(userDTO);
				
				return ResponseEntity.status(HttpStatus.OK).body(employeeService.insertEmployee(employeeDTO));
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}
	
	@PutMapping("/assignTask")
	public ResponseEntity<EmployeeDTO> assignTask(@RequestBody ParamDTO paramDTO) {
		
		int rank;
		int idBusinessOwner;
		
		try {
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				idBusinessOwner = this.getIdUserFromJwt(paramDTO.getJwt());
				
				LinkedHashMap employee = (LinkedHashMap) paramDTO.getParam();
				EmployeeDTO employeeDTO = new EmployeeDTO(Integer.parseInt(employee.get("id").toString()), null, Integer.parseInt(employee.get("idTask").toString()), idBusinessOwner);
				return ResponseEntity.status(HttpStatus.OK).body(employeeService.insertEmployee(employeeDTO));
			}
			
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}
	
	@GetMapping("/showEmployee")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByBusinessOwner(@RequestParam(value="jwt") String jwt){
		
		int rank;
		int idUser;
		
		try {
			
			rank = this.getRankFromJwt(jwt);
			
			if(rank == 0) {
				
				idUser = this.getIdUserFromJwt(jwt);
				return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getEmployeeByIdBusinessOwner(idUser));
				
			}
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
	}
	
	@GetMapping("/findEmployee")
	public ResponseEntity<EmployeeDTO> findEmployee(@RequestParam(value="idUser") int idUser) {
		return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getEmployeeByUser(idUser));
	}
	
	@PostMapping("/deleteEmployee")
	public ResponseEntity<Boolean> deleteEmployee(@RequestBody ParamDTO paramDTO) {
		
		System.out.println(paramDTO);
		
		int rank;
		
		try {
			
			rank = this.getRankFromJwt(paramDTO.getJwt());
			
			if(rank == 0) {
				
				int idUser = (int) paramDTO.getParam();
				return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(idUser));
			}
			
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
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
