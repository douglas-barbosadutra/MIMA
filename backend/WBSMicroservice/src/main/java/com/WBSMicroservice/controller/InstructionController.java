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

import com.WBSMicroservice.dto.InstructionDTO;
import com.WBSMicroservice.dto.ParamDTO;
import com.WBSMicroservice.service.InstructionService;
import com.WBSMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/Instruction")
public class InstructionController {

	@Autowired
	private InstructionService istruzioneService;

	public InstructionController() {
	}

	@PostMapping("/insertInstruction")
	public ResponseEntity<InstructionDTO> insertInstruction(@RequestBody ParamDTO param) {
		LinkedHashMap instruction = (LinkedHashMap) param.getParam();
		int rank;
		try {
			rank = this.getRankFromJwt(param.getJwt());
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK)
						.body(istruzioneService.insertIstruzione((InstructionDTO) instruction.get(0)));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@DeleteMapping("/deleteInstruction")
	public ResponseEntity<Boolean> deleteInstruction(@RequestParam(value = "jwt") String jwt,
			@RequestParam(value = "idInstruction") int idInstruction) {
		int rank;
		try {
			rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(istruzioneService.deleteIstruzione(idInstruction));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@GetMapping("/showInstruction")
	public ResponseEntity<List<InstructionDTO>> showInstruction(@RequestParam(value = "jwt") String jwt,
			@RequestParam(value = "idTask") int idTask) {
		try {
			int rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(istruzioneService.getAllIstruzioniByIdTask(idTask));
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
