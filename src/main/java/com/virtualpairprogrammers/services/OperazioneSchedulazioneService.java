package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.OperazioneSchedulazioneConverter;
import com.virtualpairprogrammers.dao.OperazioneSchedulazioneDAO;
import com.virtualpairprogrammers.domain.OperazioneSchedulazione;
import com.virtualpairprogrammers.dto.OperazioneSchedulazioneDTO;

public class OperazioneSchedulazioneService {
	
	private OperazioneSchedulazioneDAO operazioneSchedulazioneDAO;
	
	public OperazioneSchedulazioneService() {
		this.operazioneSchedulazioneDAO = new OperazioneSchedulazioneDAO();
	}
	
	public void deleteOperazioneSchedulazione(int id) {
		this.operazioneSchedulazioneDAO.deleteOperazioneSchedulazione(id);
	}
	
	public boolean insertOperazioneSchedulazione(int id_schedulazione, int id_task, int ordine) {
		return this.operazioneSchedulazioneDAO.insertOperazioneSchedulazione(id_schedulazione,id_task,ordine);
	}
	
	public void updateOperazioneSchedulazione(String ordine, int id) {
		this.operazioneSchedulazioneDAO.updateOperazioneSchedulazione(ordine, id);
	}

	public List<OperazioneSchedulazioneDTO> getOperazioniSchedulazioneByIdSchedulazione(int idSchedulazione){
		
    	List<OperazioneSchedulazione> oss = this.operazioneSchedulazioneDAO.getOperazioniSchedulazioneByIdSchedulazione(idSchedulazione);;
    	List<OperazioneSchedulazioneDTO> osdto = new ArrayList<>();
    	
		for(OperazioneSchedulazione os: oss) {
			osdto.add(OperazioneSchedulazioneConverter.convertToDto(os));
		}
		return osdto;
		
	}

}
