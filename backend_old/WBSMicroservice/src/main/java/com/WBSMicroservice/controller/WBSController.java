package com.WBSMicroservice.controller;

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

import com.WBSMicroservice.utils.JwtUtils;
import com.WBSMicroservice.dto.ParamDTO;
import com.WBSMicroservice.dto.WBSDTO;
import com.WBSMicroservice.service.WBSService;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/WBS")
public class WBSController {

	@Autowired
	private WBSService wbsService;

	public WBSController() {
	}

	@PostMapping("/insertWbs")
	public ResponseEntity<WBSDTO> insertWbs(@RequestBody ParamDTO param) {
		LinkedHashMap wbs = (LinkedHashMap) param.getParam();
		int rank;
		int idUser;
		try {
			rank = this.getRankFromJwt(param.getJwt());
			idUser = this.getIdUserFromJwt(param.getJwt());
			WBSDTO prova = new WBSDTO(0, wbs.get("name").toString(), idUser);
			if (rank == 0) {
				return ResponseEntity.status(HttpStatus.OK).body(wbsService.insertWBS(prova));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@GetMapping("/showWbs")
	public ResponseEntity<List<WBSDTO>> showWbs(@RequestParam(value = "jwt") String jwt)
			throws ExpiredJwtException, UnsupportedEncodingException {
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		try {
			int rank = this.getRankFromJwt(jwt);
			int idUser = Integer.parseInt(data.get("subject").toString());
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(wbsService.showWBS(idUser));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

	}

	@DeleteMapping("/deleteWbs")
	public ResponseEntity<Boolean> deleteWbs(@RequestParam(value = "jwt") String jwt,
			@RequestParam(value = "idWbs") int idWbs) {
		int rank;
		try {
			rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(wbsService.deleteWBS(idWbs));
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

	private int getIdUserFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {

		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int idUser = Integer.parseInt(data.get("subject").toString());

		return idUser;
	}
}
