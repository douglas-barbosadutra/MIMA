package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.User;
import com.pCarpet.model.WBS;

public interface WBSDAO extends CrudRepository<WBS, Integer>{
	
	public List<WBS> findAllByUser(User u);

}
