package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.DatosDiarios;

public interface DatosDiariosRepository extends CrudRepository<DatosDiarios, Integer>{
	Collection<DatosDiarios> findAll();
}
