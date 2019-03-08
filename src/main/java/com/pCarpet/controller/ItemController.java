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
	private static int idWbs = 0;
	private static String nameWbs = null;

	@Autowired
	public ItemController(ItemService is) {
		itemService = is;
	}

	@RequestMapping(value = "/showNodes", method = RequestMethod.GET)
	public String showNodes(HttpServletRequest request) {
		idWbs = Integer.parseInt(request.getParameter("id").toString());
		nameWbs = request.getParameter("name").toString();
		return callShowView(request);

	}

	@RequestMapping(value = "/openAddNode", method = RequestMethod.GET)
	public String openAddNode(HttpServletRequest request) {
		int id_nodo;
		try {
			id_nodo = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			id_nodo = 0;
		}
		request.getSession().setAttribute("id_nodo", id_nodo);

		return "itemInsert";

	}

	@RequestMapping(value = "/addNode", method = RequestMethod.POST)
	public String addNode(HttpServletRequest request) {

		int id_nodo = Integer.parseInt(request.getParameter("id_nodo"));
		String nome = request.getParameter("nome");
		if (id_nodo != 0) {
			ItemDTO itemdto_padre = itemService.getItemById(id_nodo);
			itemService.insertItem(nome, itemdto_padre.getId(), itemdto_padre.getIdWBS(), itemdto_padre.getLevel() + 1);
		} else {
			itemService.insertItem(nome, 0, idWbs, 1);
		}
		return "homeUser";
	}

	@RequestMapping(value = "/removeNode", method = RequestMethod.GET)
	public String removeNode(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));

		itemService.deleteItem(id);

		return callShowView(request);

	}

	private String callShowView(HttpServletRequest request) {
		WBSDTO wbs = new WBSDTO();
		wbs.setId(idWbs);
		List<ItemDTO> items = itemService.getItemByWBS(wbs);
		ItemDTO padre = null;
		for (ItemDTO item : items) {
			if (item.getIdFather() == 0) {
				padre = item;
				break;
			}
		}
		if (padre == null) {
			request.getSession().setAttribute("action", "alberoVuoto");
			return "treeShow";
		}
		String prova = creaAlbero(padre);
		request.getSession().setAttribute("prova", prova);
		request.getSession().setAttribute("action", "alberoConNodi");
		request.getSession().setAttribute("name", nameWbs);
		return "treeShow";
	}

	private String creaAlbero(ItemDTO item) {
		String result = "";
		if (item.itemChildrenDTO != null) {
			result = result + "<ul>";
		}
		result = result + "<li>" + item.getName() + " <a style=\"text-decoration: none;\" href=\"/Item/removeNode?id="
				+ item.getId() + "\">-</a> <a style=\"text-decoration: none;\" href=\"/Item/openAddNode?id="
				+ item.getId() + "\">+</a>";

		if (item.itemChildrenDTO != null) {
			for (ItemDTO child : item.itemChildrenDTO) {
				result = result + creaAlbero(child);
			}
			result = result + "</li></ul>";
		} else {
			result = result + "</li>";
		}
		return result;
	}
}
