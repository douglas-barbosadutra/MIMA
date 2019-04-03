package com.mima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mima.model.User;
import com.mima.model.WBS;

public interface WBSDAO extends JpaRepository<WBS, Integer>{
	
	public List<WBS> findAllByUser(User u);

}
