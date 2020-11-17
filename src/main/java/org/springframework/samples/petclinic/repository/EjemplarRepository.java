package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Ejemplar;


public interface EjemplarRepository  extends CrudRepository<Ejemplar, Integer>{

	    Collection<Ejemplar> findAll();
	
}
