package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Encargo;

public interface EncargoRepository extends CrudRepository<Encargo, Integer>{
    Collection<Encargo> findAll();
}
