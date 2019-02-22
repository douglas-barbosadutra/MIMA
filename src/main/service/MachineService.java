package main.service;

import java.util.ArrayList;
import java.util.List;

import main.converter.MachineConverter;
import main.dao.MachineDAO;
import main.dto.MachineDTO;
import main.model.Machine;

public class MachineService {

    private MachineDAO machineDAO;

    public MachineService() {
        this.machineDAO = new MachineDAO();
    }

    public void insertMachine (String nome, String modello, int id_utente) {
       this.machineDAO.insertMachine(nome, modello, id_utente);
    }
    
    public void deleteMachine (int id, int id_utente) {
        this.machineDAO.deleteMachine(id, id_utente);
    }
    
    public void updateMachine (String nome, String modello, int id, int id_utente) {
        this.machineDAO.updateMachine(nome, modello, id, id_utente);
    }
    
    public List<MachineDTO> getAllMachines() {
    	List<Machine> macchine = this.machineDAO.getAllMachines();
    	List<MachineDTO> machinedto = new ArrayList<>();
    	
		for(Machine macchina: macchine) {
			machinedto.add(MachineConverter.convertToDto(macchina));
		}
		return machinedto;
    }
    


    
	
}
