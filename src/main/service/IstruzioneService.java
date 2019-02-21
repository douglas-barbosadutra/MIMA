package main.service;

import main.dao.IstruzioneDAO;
import main.model.Istruzione;

import java.util.*;

import converter.IstruzioneConverter;
import dto.IstruzioneDTO;

public class IstruzioneService {
	
	private IstruzioneDAO istruzioneDAO;
	
	public IstruzioneService() {
		this.istruzioneDAO = new IstruzioneDAO();
	}
	
	public List<IstruzioneDTO> getAllIstruzioni(int idTask){
		List<Istruzione> istruzioni = this.istruzioneDAO.getAllIstruzioni(idTask);
		List<IstruzioneDTO> istruzioniDTO = new ArrayList<>();
		for(Istruzione istruzione: istruzioni) {
			istruzioniDTO.add(IstruzioneConverter.convertToDto(istruzione));
		}
		return istruzioniDTO;
	}
	
	public boolean insertIstruzione(Istruzione istruzione, int idTask) {
		return this.istruzioneDAO.insertIstruzione(istruzione, idTask);
	}
	
	public boolean deleteIstruzione(String nome, int idTask) {
		return this.istruzioneDAO.deleteIstruzione(nome, idTask);
	}
	
	public boolean modifyIstruzione(Istruzione istruzione, int idTask) {
		return this.istruzioneDAO.modifyIstruzione(istruzione, idTask);
	}
}
