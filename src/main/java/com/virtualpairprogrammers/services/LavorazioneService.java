package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.LavorazioneConverter;
import com.virtualpairprogrammers.dao.LavorazioneDAO;
import com.virtualpairprogrammers.domain.Lavorazione;
import com.virtualpairprogrammers.dto.LavorazioneDTO;

public class LavorazioneService {

	private LavorazioneDAO lavorazioneDAO;
	
	public LavorazioneService() {
		this.lavorazioneDAO = new LavorazioneDAO();
	}
	
	public List<LavorazioneDTO> getAllLavorazioni(int idIstruzione){
		List<Lavorazione> lavorazioni = this.lavorazioneDAO.getAllLavorazioniByIdIstruzione(idIstruzione);
		List<LavorazioneDTO> lavorazioniDTO = new ArrayList<>();
		for(Lavorazione lavorazione : lavorazioni) {
			lavorazioniDTO.add(LavorazioneConverter.convertToDto(lavorazione));
		}
		return lavorazioniDTO;
	}
}
