package com.WBSMicroservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WBSMicroservice.model.WBS;

public interface WBSDAO extends JpaRepository<WBS, Integer>{
	
	public List<WBS> findAllByIdUser(Integer idUser);

}
