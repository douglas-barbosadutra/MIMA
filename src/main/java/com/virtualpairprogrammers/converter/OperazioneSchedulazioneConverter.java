package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.OperazioneSchedulazione;
import com.virtualpairprogrammers.dto.OperazioneSchedulazioneDTO;

public class OperazioneSchedulazioneConverter {

	public static OperazioneSchedulazione convertToOperazioneSchedulazione(OperazioneSchedulazioneDTO osdto) {
		return (new OperazioneSchedulazione(osdto.getID(), osdto.getTask(), osdto.getSchedulazione(), osdto.getOrdine()));
	}

	public static OperazioneSchedulazioneDTO convertToDto(OperazioneSchedulazione os) {
		return (new OperazioneSchedulazioneDTO(os.getID(), os.getTask(), os.getSchedulazione(), os.getOrdine()));
	}
	
}
