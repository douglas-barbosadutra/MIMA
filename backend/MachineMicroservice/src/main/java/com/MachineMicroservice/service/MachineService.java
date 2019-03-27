package com.MachineMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MachineMicroservice.converter.MachineConverter;
import com.MachineMicroservice.dao.MachineDAO;
import com.MachineMicroservice.dto.MachineDTO;
import com.MachineMicroservice.model.Machine;

@Service
public class MachineService {

	@Autowired
    private MachineDAO machineDAO;

    @Autowired
    public MachineService() {    }

    public MachineDTO insertMachine (MachineDTO machineDTO) {
    	Machine machine = MachineConverter.convertToEntity(machineDTO);
    	return MachineConverter.convertToDto(this.machineDAO.saveAndFlush(machine));
    }
    
    public boolean deleteMachine (int id) {
        this.machineDAO.deleteById(id);
        return true;
    }
    
    public List<MachineDTO> getAllMachinesByIdUser(int idUser) {
    	return (MachineConverter.toListDTO(this.machineDAO.findAllByIdUser(idUser)));
    }
}
