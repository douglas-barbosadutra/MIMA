package main.service;

import main.dao.IstruzioneDAO;
import main.model.Istruzione;

import java.util.*;

public class IstruzioneService {
	
	private IstruzioneDAO istruzioneDAO;
	
	public IstruzioneService() {
		this.istruzioneDAO = new IstruzioneDAO();
	}
	
	public List<Istruzione> getAllIstruzioni(int idTask){
		return this.istruzioneDAO.getAllIstruzioni(idTask);
	}
	
	public boolean insertIstruzione(Istruzione istruzione, int idTask) {
		return this.istruzioneDAO.insertIstruzione(istruzione, idTask);
	}
	
	public boolean deleteIstruzione(String nome, int idTask) {
		return this.istruzioneDAO.deleteIstruzione(nome, idTask);
	}
}
