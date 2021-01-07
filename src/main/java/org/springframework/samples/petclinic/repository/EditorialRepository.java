package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Editorial;

public interface EditorialRepository extends CrudRepository<Editorial, Integer>{
	Collection<Editorial> findAll();
}
