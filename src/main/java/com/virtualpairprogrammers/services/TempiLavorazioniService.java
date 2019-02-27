package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.dto.TimeDTO;
import com.virtualpairprogrammers.converter.TimeConverter;
import com.virtualpairprogrammers.dto.InstructionDTO;
import com.virtualpairprogrammers.dto.ItemDTO;
import com.virtualpairprogrammers.dto.LavorazioneDTO;

public class TempiLavorazioniService {
	
	private final InstructionService istruzioneService = new InstructionService();
	private final LavorazioneService lavorazioniService = new LavorazioneService();
	private final ItemService itemService = new ItemService();
	public TempiLavorazioniService() {
		
	}
	
	public List<TimeDTO> getAllTempi(int idTask){
		List<TimeDTO> tempiDTO = new ArrayList<>();
		List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
		for(InstructionDTO istruzione : istruzioni) {
			List<LavorazioneDTO> lavorazioni = 	lavorazioniService.getAllLavorazioni(istruzione.getId());
			for(LavorazioneDTO lavorazione : lavorazioni) {
				ItemDTO item = itemService.getItemById(lavorazione.getItem());
				tempiDTO.add(TimeConverter.convertToTimesDTO(istruzione, lavorazione, item));
			}
			
		}
		return tempiDTO;
	}
}
