package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.WBSDTO;
import com.pCarpet.services.WBSService;

@Controller
@RequestMapping("/WBS")
public class WBSController {
	
	private WBSService wbsService;
	
	@Autowired
	public WBSController(WBSService wbsService) {
		this.wbsService = wbsService;
	}
	
	@RequestMapping(value="/openInsertWbs")
	public String openInsertWbs(HttpServletRequest request) {
		return "wbsInsert";
	}
	
	@RequestMapping(value="/insertWbs", method= RequestMethod.POST)
	public String insertWbs(HttpServletRequest request) {
		
		String nome = request.getParameter("nome");
		
		WBSDTO wbsDTO = new WBSDTO(0,nome);
		wbsService.insertWBS(wbsDTO);
		
		request.getSession().setAttribute("showWbs", "list");
		
		List<WBSDTO> wbs = wbsService.showWBS();
		request.getSession().setAttribute("listWbs", wbs);
		
		return "wbsShow";
	}
	
	@RequestMapping(value="/showWbs")
	public String showWbs(HttpServletRequest request) {
		
		request.getSession().setAttribute("showWbs", "list");
		
		List<WBSDTO> wbs = wbsService.showWBS();
		request.getSession().setAttribute("listWbs", wbs);
		return "wbsShow";
	}
	
	@RequestMapping(value="/deleteWbsManagement")
	public String deleteWbsManagement(HttpServletRequest request) {
		
		request.getSession().setAttribute("showWbs", "delete");
		
		List<WBSDTO> wbs = wbsService.showWBS();
		request.getSession().setAttribute("listWbs", wbs);
		return "wbsShow";
	}
	
	@RequestMapping(value="/deleteWbs", method= RequestMethod.GET)
	public String deleteWbs(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id").toString());
		wbsService.deleteWBS(id);
		
		request.getSession().setAttribute("showWbs", "list");
		
		List<WBSDTO> wbs = wbsService.showWBS();
		request.getSession().setAttribute("listWbs", wbs);
		return "wbsShow";
	}
	

}
