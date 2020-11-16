package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Integer>{
    Collection<Libro> findAll();
}