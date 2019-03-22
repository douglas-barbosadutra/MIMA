package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.MachineConverter;
import com.mima.dao.MachineDAO;
import com.mima.dto.MachineDTO;
import com.mima.model.Machine;
import com.mima.model.User;

@Service
public class MachineService {

	@Autowired
    private MachineDAO machineDAO;

    @Autowired
    public MachineService() {    }

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
