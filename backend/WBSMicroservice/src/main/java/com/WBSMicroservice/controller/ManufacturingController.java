package com.WBSMicroservice.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WBSMicroservice.dto.TimeDTO;
import com.WBSMicroservice.services.TimeService;
import com.WBSMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/Manufacturing")
public class ManufacturingController {

	@Autowired
	TimeService timeService;

	public ManufacturingController() {
	}

	@GetMapping("/showTime")
	public ResponseEntity<List<TimeDTO>> showTime(@RequestParam(value = "jwt") String jwt,
			@RequestParam(value = "idTask") int idTask) {
		try {
			int rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(timeService.getAllTempi(idTask));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	private int getRankFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {

		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int rank = Integer.parseInt(data.get("scope").toString());

		return rank;
	}
}
