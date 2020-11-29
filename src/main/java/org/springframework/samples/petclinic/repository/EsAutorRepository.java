package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.EsAutor;

public interface EsAutorRepository extends CrudRepository<EsAutor, Integer>{
    Collection<EsAutor> findAll();
}
