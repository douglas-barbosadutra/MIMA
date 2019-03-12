package com.pCarpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.services.ItemService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Item")
public class ItemController {

	private ItemService itemService;

	@Autowired
	public ItemController(ItemService is) {
		itemService = is;
	}

	@RequestMapping(value = "/showNodes", method = RequestMethod.POST)
	public ItemDTO showNodes(@RequestBody WBSDTO wbs) {
		return itemService.getItemByWBS(wbs);
	}

	@RequestMapping(value = "/addNode", method = RequestMethod.POST)
	public ItemDTO addNode(@RequestBody ItemDTO item) {
		return itemService.insertItem(item);
	}

	@RequestMapping(value = "/removeNode", method = RequestMethod.DELETE)
	public boolean removeNode(@RequestParam(value="idItem") int idItem) {
		return itemService.deleteItem(idItem);
	}

}
