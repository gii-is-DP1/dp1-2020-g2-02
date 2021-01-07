package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cantidad;


public interface CantidadRepository extends  CrudRepository<Cantidad, Integer>{
    Collection<Cantidad> findAll();
}
