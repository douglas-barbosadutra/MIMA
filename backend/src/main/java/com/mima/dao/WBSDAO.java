package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pCarpet.model.User;
import com.pCarpet.model.WBS;

public interface WBSDAO extends JpaRepository<WBS, Integer>{
	
	public List<WBS> findAllByUser(User u);

}
