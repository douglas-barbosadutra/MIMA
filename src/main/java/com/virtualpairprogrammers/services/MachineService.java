package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.MachineConverter;
import com.virtualpairprogrammers.dao.MachineDAO;
import com.virtualpairprogrammers.domain.Machine;
import com.virtualpairprogrammers.dto.MachineDTO;


public class MachineService {

    private MachineDAO machineDAO;

    public MachineService() {
        this.machineDAO = new MachineDAO();
    }

    public void insertMachine (String nome, String modello) {
       this.machineDAO.insertMachine(nome, modello, UserService.getUserSession().getID());
    }
    
    public void deleteMachine (int id) {
        this.machineDAO.deleteMachine(id);
    }
    
    public List<MachineDTO> getAllMachines() {
    	List<Machine> macchine = this.machineDAO.getAllMachines(UserService.getUserSession().getID());
    	List<MachineDTO> machinedto = new ArrayList<>();
    	
		for(Machine macchina: macchine) {
			machinedto.add(MachineConverter.convertToDto(macchina));
		}
		return machinedto;
    }
    


    
	
}
