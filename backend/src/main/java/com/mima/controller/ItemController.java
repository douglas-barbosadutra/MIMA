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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mima.dto.InputDTO;
import com.mima.dto.ItemDTO;
import com.mima.dto.WBSDTO;
import com.mima.services.ItemService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	public ItemController() {	}

	@PostMapping("/showNodes")
	public ResponseEntity<ItemDTO> showNodes(@RequestBody WBSDTO wbs) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemByWBS(wbs));
	}

	@PostMapping("/addNode")
	public ResponseEntity<ItemDTO> addNode(@RequestBody ItemDTO item) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.insertItem(item));
	}

	@DeleteMapping("/removeNode")
	public ResponseEntity<Boolean> removeNode(@RequestParam(value="idItem") int idItem) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.deleteItem(idItem));
	}
	
	@GetMapping("/showItem")
	public ResponseEntity<List<ItemDTO>> showItem(){
		return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItem());
	}
	
	@PostMapping("/insertInput")
	public ResponseEntity<Boolean> insertInput(@RequestBody InputDTO input) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.insertInput(input.getIdItem(), input.getIdTask()));
	}	

}
