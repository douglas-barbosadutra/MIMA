package com.mima.machine.repository;

import com.mima.machine.domain.Machine;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Machine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
	
	public List<Machine> findAllByIdUser(int idUser);
	
	/*//test JPQL query
	@Query("SELECT m FROM Machine m WHERE m.idUser = ?1 ORDER BY m.id DESC")
	public List<Machine> TESTfindAllMachineByUserId(Integer idUser);*/

	//test native query
	@Query(value = "SELECT * FROM machine WHERE id_user = ?1", nativeQuery = true)
	public List<Machine> TESTfindAllMachineByUserId(Integer idUser);
	
}
