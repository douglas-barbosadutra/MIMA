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

import com.WBSMicroservice.dto.InputDTO;
import com.WBSMicroservice.dto.ItemDTO;
import com.WBSMicroservice.dto.ParamDTO;
import com.WBSMicroservice.service.ItemService;
import com.WBSMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/Item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	public ItemController() {
	}

	@GetMapping("/showNodes")
	public ResponseEntity<ItemDTO> showNodes(@RequestParam(value = "jwt") String jwt,
			@RequestParam(value = "idWBS") int idWBS) {
		try {
			int rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemByWBS(idWBS));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@PostMapping("/addNode")
	public ResponseEntity<ItemDTO> addNode(@RequestBody ParamDTO param) {
		LinkedHashMap item = (LinkedHashMap) param.getParam();
		int rank;
		try {
			rank = this.getRankFromJwt(param.getJwt());
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(itemService.insertItem((ItemDTO) item.get(0)));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@DeleteMapping("/removeNode")
	public ResponseEntity<Boolean> removeNode(@RequestParam(value = "jwt") String jwt,
			@RequestParam(value = "idItem") int idItem) {
		try {
			int rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(itemService.deleteItem(idItem));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@GetMapping("/showItem")
	public ResponseEntity<List<ItemDTO>> showItem(@RequestParam(value = "jwt") String jwt) {
		try {
			int rank = this.getRankFromJwt(jwt);
			if (rank == 0)
				return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItem());
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	// DA MODIFICARE
	@PostMapping("/insertInput")
	public ResponseEntity<Boolean> insertInput(@RequestBody InputDTO input) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.insertInput(input.getIdItem(), input.getIdTask()));
	}

	private int getRankFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {

		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int rank = Integer.parseInt(data.get("scope").toString());

		return rank;
	}
}
