package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Autor;

public interface AutorRepository extends CrudRepository<Autor, Integer>{
	Collection<Autor> findAll();
}
