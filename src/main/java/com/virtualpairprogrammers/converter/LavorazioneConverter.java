package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.Lavorazione;
import com.virtualpairprogrammers.dto.LavorazioneDTO;

public class LavorazioneConverter {
	
	public static Lavorazione convertToLavorazione(LavorazioneDTO lavorazionedto) {
		return (new Lavorazione(lavorazionedto.getID(), lavorazionedto.getItem(), lavorazionedto.getIstruzione(), lavorazionedto.getDurata()));
	}

	public static LavorazioneDTO convertToDto(Lavorazione lavorazione) {
		return (new LavorazioneDTO(lavorazione.getID(), lavorazione.getItem(), lavorazione.getIstruzione(), lavorazione.getDurata()));
	}

}
