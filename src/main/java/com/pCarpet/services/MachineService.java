package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.MachineConverter;
import com.pCarpet.dao.MachineDAO;
import com.pCarpet.dto.MachineDTO;

@Service
public class MachineService {

    private MachineDAO machineDAO;

    @Autowired
    public MachineService(MachineDAO machineDAO) {
        this.machineDAO = machineDAO;
    }

    public void insertMachine (MachineDTO machineDTO) {
       this.machineDAO.save(MachineConverter.convertToEntity(machineDTO));
    }
    
    public void deleteMachine (int id) {
        this.machineDAO.deleteById(id);
    }
    
    public List<MachineDTO> getAllMachines() {
    	return (MachineConverter.toListDTO(this.machineDAO.findAllByUser(UserService.getUserSession())));
    }
    
}
