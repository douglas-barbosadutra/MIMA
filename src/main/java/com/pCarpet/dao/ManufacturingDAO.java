package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.Instruction;
import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;


public interface ManufacturingDAO extends CrudRepository<Manufacturing, Integer>{
	public List<Manufacturing> findAllByInstruction(Instruction i);
	public List<Manufacturing> findAllByItems(Item i);
	public Manufacturing findByItems(Item i);
}