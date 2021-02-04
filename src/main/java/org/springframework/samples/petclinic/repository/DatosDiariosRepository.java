package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.DatosDiarios;

public interface DatosDiariosRepository extends CrudRepository<DatosDiarios, Integer>{
	
	List<DatosDiarios> findAllByOrderByFechaDesc();
	
	
}
