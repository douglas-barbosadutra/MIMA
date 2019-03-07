package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.services.ItemService;

@Controller
@RequestMapping("/Item")
public class ItemController {

	private ItemService itemService;
	
	@Autowired
	public ItemController(ItemService is) {
		itemService = is;
	}
	
	@RequestMapping(value="/test")
	public String test(HttpServletRequest request) {

		WBSDTO wbs = new WBSDTO();
		wbs.setId(1);
		
		List<ItemDTO> items = itemService.getItemByWBS(wbs);
			
		for (ItemDTO element : items) {
		    System.out.println(element);
		}

		return "index";
	}
	
	@RequestMapping(value="/showNodes", method= RequestMethod.GET)
	public String showNodes(HttpServletRequest request) {
		
		int id_wbs = Integer.parseInt(request.getParameter("id").toString());
		
		WBSDTO wbs = new WBSDTO();
		wbs.setId(id_wbs);
		
		List<ItemDTO> itemList = itemService.getItemByWBS(wbs);

		if(!itemList.isEmpty()) {
			
			int maxLevel = itemService.getMaxLevelByWbs(wbs);
			request.getSession().setAttribute("levels", maxLevel);
			
			for(int i=1; i<=maxLevel; i++) {
				
				List<ItemDTO> items = itemService.getItemByLevelAndWbs(i, wbs);
				request.getSession().setAttribute("level"+i, items);
			}
		}
		
		return "test";
		
	}
	
}
