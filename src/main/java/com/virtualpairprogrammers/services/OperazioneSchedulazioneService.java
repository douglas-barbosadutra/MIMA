package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.virtualpairprogrammers.converter.OperazioneSchedulazioneConverter;
import com.virtualpairprogrammers.dao.OperazioneSchedulazioneDAO;
import com.virtualpairprogrammers.domain.OperazioneSchedulazione;
import com.virtualpairprogrammers.dto.OperazioneSchedulazioneDTO;
import com.virtualpairprogrammers.dto.TaskDTO;
import com.virtualpairprogrammers.dto.TaskSchedulatiDTO;

public class OperazioneSchedulazioneService {
	
	private OperazioneSchedulazioneDAO operazioneSchedulazioneDAO;
	
	public OperazioneSchedulazioneService() {
		this.operazioneSchedulazioneDAO = new OperazioneSchedulazioneDAO();
	}
	
	public void deleteOperazioneSchedulazione(int id, List<TaskSchedulatiDTO> listaTaskSchedultatiDTO) {
		int i = 0;
		while(listaTaskSchedultatiDTO.get(i).getIdOperazioneSchedulazione() != id)
			i++;
		i++;
		for(; i<listaTaskSchedultatiDTO.size(); i++) {
			updateOperazioneSchedulazione(i, listaTaskSchedultatiDTO.get(i).getIdOperazioneSchedulazione());
			listaTaskSchedultatiDTO.get(i).setOrdine(i);
		}
		this.operazioneSchedulazioneDAO.deleteOperazioneSchedulazione(id);
	}
	
	public boolean insertOperazioneSchedulazione(int id_schedulazione, int id_task, int ordine) {
		return this.operazioneSchedulazioneDAO.insertOperazioneSchedulazione(id_schedulazione,id_task,ordine);
	}
	
	public void updateOperazioneSchedulazione(int ordine, int id) {
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
	
	public List<TaskSchedulatiDTO> getTaskSchedulati(int idSchedulazione, List<TaskDTO> listaTask){
		List<TaskSchedulatiDTO> listaTaskSchedulati = new ArrayList<>();
		List<OperazioneSchedulazioneDTO> oss = this.getOperazioniSchedulazioneByIdSchedulazione(idSchedulazione);
		for(OperazioneSchedulazioneDTO os: oss) {
			for(TaskDTO task : listaTask) {
				if(task.getID() == os.getTask()) {
					listaTaskSchedulati.add(new TaskSchedulatiDTO(task.getID(), task.getDescrizione(), os.getOrdine(), os.getID()));
					break;
				}
			}
		}
		return listaTaskSchedulati;
	}
	
	public List<TaskSchedulatiDTO> modifyPosition(List<TaskSchedulatiDTO> listaTaskSchedulati, int oldPosition, int newPosition) {
		if(newPosition < 1 || (newPosition > listaTaskSchedulati.size()))
			return listaTaskSchedulati;
		updateOperazioneSchedulazione(newPosition, listaTaskSchedulati.get(oldPosition-1).getIdOperazioneSchedulazione());
		updateOperazioneSchedulazione(oldPosition, listaTaskSchedulati.get(newPosition-1).getIdOperazioneSchedulazione());
		listaTaskSchedulati.get(oldPosition-1).setOrdine(newPosition);
		listaTaskSchedulati.get(newPosition-1).setOrdine(oldPosition);
		Collections.swap(listaTaskSchedulati, oldPosition-1, newPosition-1);
		return listaTaskSchedulati;
	}
}
