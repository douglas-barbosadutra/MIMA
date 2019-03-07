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
		
		return "treeShow";
		
	}
	
	@RequestMapping(value="/openAddNode", method= RequestMethod.GET)
	public String openAddNode(HttpServletRequest request) {
	  
	  int id_nodo = Integer.parseInt(request.getParameter("id"));
	  
	  request.getSession().setAttribute("id_nodo", id_nodo);
	  
	  return "itemInsert";
	  
	}

	@RequestMapping(value="/addNode", method= RequestMethod.POST)
	public String addNode(HttpServletRequest request) {
	  
	  int id_nodo = Integer.parseInt(request.getParameter("id_nodo"));
	  String nome = request.getParameter("nome");
	  
	  ItemDTO itemdto_padre = itemService.getItemById(id_nodo);	
	  //System.out.println(itemdto_padre);
	  
	  itemService.insertItem(nome, itemdto_padre.getId(), itemdto_padre.getIdWBS(), itemdto_padre.getLevel()+1);
	  
	  return "homeUser";
	  
	}
	
	@RequestMapping(value="/removeNode", method= RequestMethod.GET)
	public String removeNode(HttpServletRequest request) {
	  
	  int id = Integer.parseInt(request.getParameter("id"));
	  
	  itemService.deleteItem(id);	
	  
	  return "homeUser";
	  
	}
	
}
