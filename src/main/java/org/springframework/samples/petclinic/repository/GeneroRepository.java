package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Genero;

public interface GeneroRepository extends CrudRepository<Genero, Integer>{
    Collection<Genero> findAll();
}
