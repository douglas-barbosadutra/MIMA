package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.MachineConverter;
import com.pCarpet.dao.MachineDAO;
import com.pCarpet.dto.MachineDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.User;

@Service
public class MachineService {

    private MachineDAO machineDAO;

    @Autowired
    public MachineService(MachineDAO machineDAO) {
        this.machineDAO = machineDAO;
    }

    public MachineDTO insertMachine (MachineDTO machineDTO) {
    	Machine machine = MachineConverter.convertToEntity(machineDTO);
    	this.machineDAO.saveAndFlush(machine);
    	return MachineConverter.convertToDto(machine);
    }
    
    public boolean deleteMachine (int id) {
        this.machineDAO.deleteById(id);
        return true;
    }
    
    public List<MachineDTO> getAllMachinesByIdUser(int idUser) {
    	User user = new User();
    	user.setId(idUser);
    	return (MachineConverter.toListDTO(this.machineDAO.findAllByUser(user)));
    }
    
}
